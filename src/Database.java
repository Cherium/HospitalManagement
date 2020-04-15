import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * @author Sajid C, Mohammed R Handle database importing and exporting
 */
public class Database {

	// main database accessed everywhere
	public HashMap<String, UserSuperClass> users;

	// Can later be expanded by the appropriate user that has rights to add a
	// department
	private ArrayList<String> departmentList = new ArrayList<>(
			List.of("Cardiology", "General", "Nephrology", "Neurology", "ER"));

	private ArrayList<DoctorModel> docsToLoad = new ArrayList<DoctorModel>();
	private String path = System.getProperty("user.dir");
	private Path tempFolder = Paths.get(path + "/temp/");
	private Path exDbaseFolder = Paths.get(path + "/externalDatabase/");

	/**
	 * Constructor
	 * 
	 * @author Sajid C
	 * @param filePath provided filename to import when initializing database
	 */
	public Database(String filePath) {

		users = new HashMap<String, UserSuperClass>();
		if (!Files.exists(exDbaseFolder)) {
			importDatabase(filePath);
		} else {
			try {
				importExternalDatabase(exDbaseFolder.toString());

			} catch (IOException e) {
				System.out.println("import not successfull");
				importDatabase(filePath);
			}
		}

	}

	/**
	 * import information from the provided file
	 * 
	 * @author Sajid C
	 * @param filePath filename to import when initializing database
	 */
	public void importDatabase(String filePath) {
		System.out.println("I am working now the internal database");

		/*
		 * https://stackoverflow.com/questions/1464291/how-to-really-read-text-file-from
		 * -classpath-in-java
		 * https://stackoverflow.com/questions/21023065/open-file-that-is-in-the-
		 * resource-folder-inside-jar?noredirect=1&lq=1
		 * https://stackoverflow.com/questions/24521830/how-to-convert-an-inputstream-to
		 * -a-scanner-java
		 */
		InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);

		Scanner sc = new Scanner(stream);
		sc.nextLine(); // ignore first line of headers

		// import database, line by line
		while (sc.hasNextLine()) {

			String data = sc.nextLine(); // read in entire line
			String[] split = data.split("\t"); // regex split into arrow on tabs

			// read in values in each line
			String importRole = split[0];
			String importUsername = split[1];
			char[] importPassword = split[2].toCharArray();
			String importName = split[3];
			String importDepartment = split[4];
			String[] importAssignedNurseUsernames = split[5].split(",");
			String importAssignedDocUsername = split[6];
			String importAddress = split[7];
			String importPhoneNumber = split[8];
			String importEmail = split[9];
			float importAmountDue = Float.parseFloat(split[10]);
			String importDob = split[11];
			String importBlood = split[12];
			String importSex = split[13];
			String[] importAssigPat = split[14].split(",");

			// import fields into internal database

			// if the data to import pertains to a doctor
			if (importRole.compareTo("doctor") == 0) {
				// read in doctor availability
				InputStream wv = getClass().getClassLoader()
						.getResourceAsStream("dbase/" + importUsername + "Avail.txt");

				Scanner fc = new Scanner(wv);

				ArrayList<String> importAvail = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line

					importAvail.add(time);
				}
				fc.close();
				// create a temporary doctor object using database information
				DoctorModel temp = new DoctorModel(importUsername, importPassword, importName, importDepartment,
						importAssignedNurseUsernames, importAssigPat, importAvail.toArray(new String[0]));

				// add that doctor to the internal database
				users.put(importUsername, temp);

				// add doctor to list to initialize appointments after importing
				docsToLoad.add(temp);
			}

			else if (importRole.compareTo("nurse") == 0) {

				// read in nurse availability
				InputStream wv = getClass().getClassLoader()
						.getResourceAsStream("dbase/" + importUsername + "Avail.txt");

				Scanner fc = new Scanner(wv);

				ArrayList<String> importAvail = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line

					importAvail.add(time);
				}
				fc.close();

				NurseModel temp = new NurseModel(importUsername, importPassword, importName, importDepartment,
						importAssignedDocUsername, importAvail.toArray(new String[0]));

				users.put(importUsername, temp);
			}

			else if (importRole.compareTo("patient") == 0) {
				// get Patient appt times
				InputStream st = getClass().getClassLoader()
						.getResourceAsStream("dbase/" + importUsername + "Appt.txt");
				Scanner fc = new Scanner(st);

				ArrayList<String> tempp = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line
					String[] split2 = time.split("\t"); // regex split into arrow on tabs

					String docName = split2[0];
					String apptTime = split2[1];

					tempp.add(docName);
					tempp.add(apptTime);
				}
				fc.close();

				// get Patient record notes
				try {
					// https://stackoverflow.com/questions/3891375/how-to-read-a-text-file-resource-into-java-unit-test?noredirect=1&lq=1
					String record = new String(getClass().getClassLoader()
							.getResourceAsStream("dbase/" + importUsername + ".txt").readAllBytes());

					// create a patient internally
					PatientModel temp = new PatientModel(importUsername, importPassword, importName, importAddress,
							importPhoneNumber, importEmail, importAmountDue, importDob, importBlood, importSex, record,
							tempp);

					users.put(importUsername, temp);
				} catch (IOException e) {
					System.out.println("Could not open file");
					e.printStackTrace();
				}

			}

			else if (importRole.compareTo("admin") == 0) {
				// read in admin availability
				InputStream wv = getClass().getClassLoader()
						.getResourceAsStream("dbase/" + importUsername + "Avail.txt");

				Scanner fc = new Scanner(wv);

				ArrayList<String> importAvail = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line

					importAvail.add(time);
				}
				fc.close();

				AdminModel temp = new AdminModel(importUsername, importPassword, importName,
						importAvail.toArray(new String[0]));

				users.put(importUsername, temp);
			}

			else if (importRole.compareTo("authority") == 0) {
				// read in authority availability
				InputStream wv = getClass().getClassLoader()
						.getResourceAsStream("dbase/" + importUsername + "Avail.txt");

				Scanner fc = new Scanner(wv);

				ArrayList<String> importAvail = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line

					importAvail.add(time);
				}
				fc.close();

				AuthorityModel temp = new AuthorityModel(importUsername, importPassword, importName,
						importAvail.toArray(new String[0]));

				users.put(importUsername, temp);
			}

			else if (importRole.compareTo("receptionist") == 0) {
				// read in receptionist availability
				InputStream wv = getClass().getClassLoader()
						.getResourceAsStream("dbase/" + importUsername + "Avail.txt");

				Scanner fc = new Scanner(wv);

				ArrayList<String> importAvail = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line

					importAvail.add(time);
				}
				fc.close();

				ReceptionistModel temp = new ReceptionistModel(importUsername, importPassword, importName,
						importAvail.toArray(new String[0]));

				users.put(importUsername, temp);
			}
		}

		// close the scanner
		sc.close();

	}

	/**
	 * 
	 * import information from the external database File
	 * 
	 * @author Sajid C, Mohammed R
	 * @param filePath filename to import when initializing database
	 * @throws IOException
	 */
	public void importExternalDatabase(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		System.out.println("I am working now the external database");
		FileInputStream fis = new FileInputStream(filePath + "/" + "dbase.txt");

		Scanner sc = new Scanner(fis);
		sc.nextLine(); // ignore first line of headers

		// import database, line by line
		while (sc.hasNextLine()) {

			String data = sc.nextLine(); // read in entire line
			String[] split = data.split("\t"); // regex split into arrow on tabs

			// read in values in each line
			String importRole = split[0];
			String importUsername = split[1];
			char[] importPassword = split[2].toCharArray();
			String importName = split[3];
			String importDepartment = split[4];
			String[] importAssignedNurseUsernames = split[5].split(",");
			String importAssignedDocUsername = split[6];
			String importAddress = split[7];
			String importPhoneNumber = split[8];
			String importEmail = split[9];
			float importAmountDue = Float.parseFloat(split[10]);
			String importDob = split[11];
			String importBlood = split[12];
			String importSex = split[13];
			String[] importAssigPat = split[14].split(",");

			// import fields into internal database

			// if the data to import pertains to a doctor
			if (importRole.compareTo("doctor") == 0) {
				// read in doctor availability
				FileInputStream wv = new FileInputStream(filePath + "/" + importUsername + "Avail.txt");

				Scanner fc = new Scanner(wv);

				ArrayList<String> importAvail = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line

					importAvail.add(time);
				}
				fc.close();
				// create a temporary doctor object using database information
				DoctorModel temp = new DoctorModel(importUsername, importPassword, importName, importDepartment,
						importAssignedNurseUsernames, importAssigPat, importAvail.toArray(new String[0]));

				// add that doctor to the internal database
				users.put(importUsername, temp);

				// add doctor to list to initialize appointments after importing
				docsToLoad.add(temp);
			}

			else if (importRole.compareTo("nurse") == 0) {

				// read in nurse availability
				FileInputStream wv = new FileInputStream(filePath + "/" + importUsername + "Avail.txt");
				Scanner fc = new Scanner(wv);

				ArrayList<String> importAvail = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line

					importAvail.add(time);
				}
				fc.close();

				NurseModel temp = new NurseModel(importUsername, importPassword, importName, importDepartment,
						importAssignedDocUsername, importAvail.toArray(new String[0]));

				users.put(importUsername, temp);
			}

			else if (importRole.compareTo("patient") == 0) {
				// get Patient appt times
				FileInputStream st = new FileInputStream(filePath + "/" + importUsername + "Appt.txt");
				Scanner fc = new Scanner(st);

				ArrayList<String> tempp = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line
					String[] split2 = time.split("\t"); // regex split into arrow on tabs

					String docName = split2[0];
					String apptTime = split2[1];

					tempp.add(docName);
					tempp.add(apptTime);
				}
				fc.close();

				// get Patient record notes
				try {
					// https://stackoverflow.com/questions/3891375/how-to-read-a-text-file-resource-into-java-unit-test?noredirect=1&lq=1
					FileInputStream wv = new FileInputStream(filePath + "/" + importUsername + ".txt");
					String record = new String(wv.readAllBytes());
					System.out.println(record);
					// create a patient internally
					PatientModel temp = new PatientModel(importUsername, importPassword, importName, importAddress,
							importPhoneNumber, importEmail, importAmountDue, importDob, importBlood, importSex, record,
							tempp);

					users.put(importUsername, temp);
				} catch (IOException e) {
					System.out.println("Could not open file");
					e.printStackTrace();
				}

			}

			else if (importRole.compareTo("admin") == 0) {
				// read in admin availability
				FileInputStream wv = new FileInputStream(filePath + "/" + importUsername + "Avail.txt");

				Scanner fc = new Scanner(wv);

				ArrayList<String> importAvail = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line

					importAvail.add(time);
				}
				fc.close();

				AdminModel temp = new AdminModel(importUsername, importPassword, importName,
						importAvail.toArray(new String[0]));

				users.put(importUsername, temp);
			}

			else if (importRole.compareTo("authority") == 0) {
				// read in authority availability
				FileInputStream wv = new FileInputStream(filePath + "/" + importUsername + "Avail.txt");

				Scanner fc = new Scanner(wv);

				ArrayList<String> importAvail = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line

					importAvail.add(time);
				}
				fc.close();

				AuthorityModel temp = new AuthorityModel(importUsername, importPassword, importName,
						importAvail.toArray(new String[0]));

				users.put(importUsername, temp);
			}

			else if (importRole.compareTo("receptionist") == 0) {
				// read in receptionist availability
				FileInputStream wv = new FileInputStream(filePath + "/" + importUsername + "Avail.txt");

				Scanner fc = new Scanner(wv);

				ArrayList<String> importAvail = new ArrayList<>(5);
				while (fc.hasNextLine()) {

					String time = fc.nextLine(); // read in entire line

					importAvail.add(time);
				}
				fc.close();

				ReceptionistModel temp = new ReceptionistModel(importUsername, importPassword, importName,
						importAvail.toArray(new String[0]));

				users.put(importUsername, temp);
			}
		}

		System.out.println(path.getName(0));
		System.out.println("hihihihihihi");
		// close the scanner
		sc.close();
	}

	/**
	 * Check if the temporary verified database created exist then make it the final
	 * database
	 * 
	 * @author Mohammed Rakeeb
	 * @throws IOException
	 */
	public void TempToExternalDatabase() throws IOException {

		String[] filename;

		File src = new File(tempFolder.toString());
		File dest = new File(exDbaseFolder.toString());

		if (!Files.exists(exDbaseFolder)) {
			src.renameTo(dest);
		} else {
			filename = dest.list();
			while (filename.length != 0) {
				filename = dest.list();
				for (String p : filename) {
					File currentFile = new File(dest.getPath(), p);
					currentFile.delete();
				}
			}
			dest.delete();
			src.renameTo(dest);

		}
	}

	/**
	 * method called during exiting the program it creates the external temporary
	 * verified database and then moves to the final external Database Folder. And
	 * throws exception if any error occurs in the process .In the exception the
	 * corrupted database is deleted Tested from the main class
	 * 
	 * @author Mohammed Rakeeb
	 * 
	 */
	public void exitProgram() {
		try {
			exportDbase();
			TempToExternalDatabase();
		} catch (Exception e) {
			deleteFolder();
			e.printStackTrace();
			System.out.println("export unsuccessful ");
		}

	}

	/**
	 * The method is used to handle the exception when the created external database
	 * gets corrupted. The method deletes the corrupted database so that the program
	 * can use the default database. Not tested
	 * 
	 * @author Mohammed Rakeeb
	 */
	public void deleteFolder() {
		File tempDbase = new File(tempFolder.toString());
		File exterDbase = new File(exDbaseFolder.toString());

		// check if the temp Folder exists then delete it
		if (Files.exists(tempFolder)) {

			// first delete the internal contents of the directory if any before deleting
			// the dir

			String[] filename = tempDbase.list();
			while (filename.length != 0) {

				for (String p : filename) {
					File currentFile = new File(tempDbase.getPath(), p);
					currentFile.delete();
				}
				filename = tempDbase.list();
			}
			// delete the dir
			tempDbase.delete();
		}

		// check if the externalDatabaseFolder exists then delete it
		else if (Files.exists(exDbaseFolder)) {
			// first delete the internal contents of the directory if any before deleting
			// the dir
			String[] filename = exterDbase.list();
			while (filename.length != 0) {
				for (String p : filename) {
					File currentFile = new File(exterDbase.getPath(), p);
					currentFile.delete();
				}
				filename = exterDbase.list();
			}
			// delete the dir
			exterDbase.delete();
		}
	}

	/**
	 * Create temp database files, verify these files work with importDatabase() and
	 * then save temp files as actual dbase files --tested from main
	 * 
	 * @author Sajid C, Mohammed Rakeeb
	 * @throws IOException
	 */
	public void exportDbase() throws IOException {

		// https://www.baeldung.com/java-how-to-create-a-file
		// create a path object (that may not yet exist) and try to create the specified
		// file at the specified path
		String path = System.getProperty("user.dir");
		Path tempFolder = Paths.get(path + "/temp/");

		if (!Files.exists(tempFolder))
			Files.createDirectory(tempFolder);

		Path realPath = tempFolder.toRealPath(LinkOption.NOFOLLOW_LINKS);

		Path tempDbase = Paths.get(tempFolder.toString() + "/" + "dbase.txt");
		if (!Files.exists(tempDbase)) {
			Files.createFile(tempDbase);
			System.out.println("created");

		} else {
			System.out.println("exists");
		}

		// create a writer to the dbase file
		BufferedWriter writer = Files.newBufferedWriter(tempDbase, Charset.forName("UTF-8"));

		StringBuilder tmp = new StringBuilder(); // builds dbase.txt
		tmp.append("role");
		tmp.append("\t");
		tmp.append("username");
		tmp.append("\t");
		tmp.append("password");
		tmp.append("\t");
		tmp.append("department");
		tmp.append("\t");
		tmp.append("nursesUsernames");
		tmp.append("\t");
		tmp.append("assignedDoctorUsername");
		tmp.append("\t");
		tmp.append("address");
		tmp.append("\t");
		tmp.append("phoneNumber");
		tmp.append("\t");
		tmp.append("email");
		tmp.append("\t");
		tmp.append("amountDue");
		tmp.append("\t");
		tmp.append("dob");
		tmp.append("\t");
		tmp.append("blood");
		tmp.append("\t");
		tmp.append("sex");
		tmp.append("\t");
		tmp.append("docPatUsernames");
		tmp.append("\n");

		for (Map.Entry<String, UserSuperClass> i : users.entrySet()) {
			if (i.getValue().getRole().compareTo("doctor") == 0) {
				DoctorModel user = (DoctorModel) i.getValue();

				// write entry for main database
				tmp.append(user.toStringDbase());
				tmp.append("\n");

				// write entry for availability
				writeToFile(tempFolder.toString() + "/" + user.getUsername() + "Avail.txt",
						user.toStringAvailability());

				System.out.println(user.toStringDbase());
			}

			else if (i.getValue().getRole().compareTo("nurse") == 0) {
				NurseModel user = (NurseModel) i.getValue();

				// write entry for main database
				tmp.append(user.toStringDbase());
				tmp.append("\n");

				// write entry for availability
				writeToFile(tempFolder.toString() + "/" + user.getUsername() + "Avail.txt",
						user.toStringAvailability());

				System.out.println(user.toStringDbase());
			}

			else if (i.getValue().getRole().compareTo("patient") == 0) {
				PatientModel user = (PatientModel) i.getValue();

				// write entry for main database
				tmp.append(user.toStringDbase());
				tmp.append("\n");

				// write entry for appointments
				writeToFile(tempFolder.toString() + "/" + user.getUsername() + "Appt.txt", user.toStringAppt());

				// write entry for patient record
				writeToFile(tempFolder.toString() + "/" + user.getUsername() + ".txt", user.toStringRecord());

				System.out.println(user.toStringDbase());
			}

			else if (i.getValue().getRole().compareTo("admin") == 0) {
				AdminModel user = (AdminModel) i.getValue();

				// write entry for main database
				tmp.append(user.toStringDbase());
				tmp.append("\n");

				// write entry for availability
				writeToFile(tempFolder.toString() + "/" + user.getUsername() + "Avail.txt",
						user.toStringAvailability());

				System.out.println(user.toStringDbase());
			}

			else if (i.getValue().getRole().compareTo("authority") == 0) {
				AuthorityModel user = (AuthorityModel) i.getValue();

				// write entry for main database
				tmp.append(user.toStringDbase());
				tmp.append("\n");

				// write entry for availability
				writeToFile(tempFolder.toString() + "/" + user.getUsername() + "Avail.txt",
						user.toStringAvailability());

				System.out.println(user.toStringDbase());
			}

			else if (i.getValue().getRole().compareTo("receptionist") == 0) {
				ReceptionistModel user = (ReceptionistModel) i.getValue();

				// write entry for main database
				tmp.append(user.toStringDbase());
				tmp.append("\n");

				// write entry for availability
				writeToFile(tempFolder.toString() + "/" + user.getUsername() + "Avail.txt",
						user.toStringAvailability());

				System.out.println(user.toStringDbase());
			}
		}

		tmp.deleteCharAt(tmp.length() - 1);
		writer.write(tmp.toString());
		writer.close();

	}

	/**
	 * @author Sajid C
	 * @param fileNameToCreate file path to create if it doesn't exist, and to then
	 *                         write to
	 * @param textToWrite      block of text to write to the file
	 * @throws IOException
	 */
	public void writeToFile(String fileNameToCreate, String textToWrite) throws IOException {
		// create the filename if it doesn't exist
		Path temp = Paths.get(fileNameToCreate);
		if (!Files.exists(temp))
			Files.createFile(temp);

		// create an appender to the dbase file
		BufferedWriter appender = Files.newBufferedWriter(temp, Charset.forName("UTF-8"));

		// write to the new file
		appender.write(textToWrite);

		appender.close();

	}

	/** Getters and Setters */
	public HashMap<String, UserSuperClass> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, UserSuperClass> users) {
		this.users = users;
	}

	public ArrayList<String> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(ArrayList<String> departmentList) {
		this.departmentList = departmentList;
	}

	public ArrayList<DoctorModel> getDocsToLoad() {
		return docsToLoad;
	}

	public void setDocsToLoad(ArrayList<DoctorModel> docsToLoad) {
		this.docsToLoad = docsToLoad;
	}

}
