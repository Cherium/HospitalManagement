# Changelog
__In descending date order first__ (most recent date and time first).  
Timestamp Format: __day month HOURS:MINUTES (military time) USER__  
Example: 10 mar 21:13 SAJID\

## 28 MARCH 21:19 Jenny
* DoctorView: Added the two panels for scheduling patient appointments. The list of patients for viewing patient record is the same list used in scheduling. Two buttons are included that toggle between the two views. 

## 28 mar 00:45 Sajid

* Database: added code to allow importing nurse availability; added txt files to represent nurse availability
* Nurse MVC: 
    * availability now populates from database
    * model constructor updated
    * buttons now active/inactive based on user choice
* Schedule:
    * nextShiftsToString(..) function created to:
        * consider the days worked of the current user and
        * return a list of strings showing the date worked + start time + end time for 14 days from current date
    * changed arrayToLDTArray to take in String[] instead of ArrayList
* Doctor Model: changed constructor definition as per the previous point

## 27 mar 08:30 Sajid
* changed doctor constructors in DoctorModel and in CreateNeDoctorModel

## 27 mar 08:19 Sajid
* added dummy test values for 'labtest' appointment for a patient


## 27 mar 00:45 Sajid
* PatientModel: line 67-99 is a function that takes the database imported list of a patients appointments, and creates a Hashmap of those values. Postcondition: PatientModel has a HashMap containing all of its appointments
    * Added getters and setters for this
        * the doctor can now access this database to get appointment times to fill the doctors schedule
* UserSuperClass: line 21, 22 added. `protected` so all subclasses can use it without getters
* CreateNewPatient: updated constructor
* Database: line 105-120; read in patient appt info
* dbase folder --> added files for patient appointments, and doctor availability


## 27 MARCH Jenny
* Removed availability field in DoctorModel. 
# 08:55
* Added getters + setters for availability variable in UserSuperClass.
# 13:58
* Added updateSchedule(String[]): LocalDateTime[] and updateSchedule(LocalDateTime[]): String[] to Schedule. 
* The weekly calendar in DoctorView is now set up with the new appointment system. The appointments are initizlied in DoctorController, when the DoctorView is being loaded. The nurse buttons does not update as nurses have no availability as of now.
# 14:15
* The monthly calendar in DoctorView is now set up with appointment information from DoctorModel.
# 17:25
* Somewhat cleared up code from Schedule and Doctor MVC. Removed unneccessary functions and commented out functionalities. 

##26 mar 13:16 Sajid
**In branch nurse**
* all buttons, boxes, and other fields added. Patient names populate labels.
* need to populate availability box, and also set nurse availability.

## 26 mar 05:01 Sajid
**In Branch nurse**
* Added nurse functionality from scratch
    * panels for assigned docs patients, patient info, book appt, see schedule, request time off, change availability

## 26 MARCH Jenny
# 10:46 
* Rehaul of Schedule and how appointments are stored/view with Sajid. Commenting out all information in Doctor MVC related to Schedule.
# 10:57
* Adding new field to DoctorModel constructor.

## 25 MARCH 21:44 Jenny
Overview/Summary:
**Schedule**
* There is now a default constructor in Schedule that randomly generates scheduled days of a work week (that will remain constant throughout time, currently). The default constructor also generates 60 random appointments (24 hours randomly) within 1 month of the current date for the two patients on file (patient, patient2). 
**DoctorView & DoctorController**
* The aforementioned bug has been fixed. The doctor view will show the weekly schedule of either the doctor or one of the selected assigned nurses, only from 08:00 - 19:00 currently. The data within view stores 24 hours. The monthly view shows a list of scheduled patients for each day. Nurses have no appointments. Moving forward/backward a week/month will update the appointment view. 
* In DoctorController, the list of appointments are altered by replacing the username with the corresponding name and passed onto DoctorView.
* In DoctorView, the altered list of appointments is filtered into an array of size 35 (based on the LocalDate now, within DoctorView), and used to populate the monthly schedule.
* Some duplicate code have been moved into functions for easier readability. Toggling between monthly/weekly view has been moved into a function. Setting the name labels on top of the monthly/weekly schedule has been moved to a function.
* Some of the direct references to global variables within DoctorView have been changed to their relevant setters and getters instead, in an effort to decrease coupling. 

## 25 MARCH 21:05 Jenny
* Toggling between months in DoctorView will now update the appointments shown. There is a bug where moving between months does not refresh the appointments or just removes them.

## 25 MARCH 18:58 Jenny
* Refined randomized appointment data in Schedule. Also changed input in DoctorView to take in the format of Schedule.

## 25 March 18:55 Neil
* Most of Reception View has been nicely set. Need to work on the second half and patient file loading. Non-functional edit on buttons performed for login


## 25 MARCH 12:56 Jenny
* Added dummy data in Schedule default constructor.

## 24 MARCH 21:53 Jenny
* Clarified storage of scheduleDays in Schedule toString function.

## 24 MARCH 21:18 Jenny
* Structured what information a Schedule should contain and also a toString() function that indicates how it'll be stored in a database.

## 24 MARCH 20:06 Jenny
**DoctorView**
* Integrated scheduled appointments into monthly schedule with the help of Mohammed.
* Current bug: switching between months does not update the appointments shown.

## 24 MARCH 17:02 Jenny
**DoctorView**
* Implemented a view of sheduled appointments for monthly schedule in DoctorView.

## 24 MARCH 15:14 Neil
* Fixed the color issue for the Login screen for Mac users

## 24 MARCH 14:41 Jenny
* Fixed screen error where minimizing DoctorView results in a very small window. Minimization size has been set to 1000x700.

## 24 MARCH 13:07 Jenny
* Fixed error where changing times in doctor view does not update the appointments.

## 23 mar 23:55 Sajid
* swapped newMaster content with master, and deleted neMaster branch
* updated readme
* included project JAR file `run.jar`
* formalized databse entries

## 23 MARCH 21:43 Jenny
* Got the doctor view to display the current week's appointments from a list of hardcoded appointments. Currently has an error where either changing between weeks updates the highlighted appointments and the dates fail to update or changing between weeks does not update the appointments and the dates update.

## 23 MARCH 21:01 Jenny
* Fixed issue with toggling between weekly and monthly view by setting the hidden value of MigLayout.
* Resulting layout isn't centered, but that is on a lower priority.

## 23 MARCH 17:57 Jenny
* Changed layout of weekly and monthly schedule to MigLayout. Implemented a dummy scheduled days in doctor and set up the weekly and monthly schedule to reflect scheduled days.
* Moved every major panel initialization to functions in DoctorView.
* Now, when choosing a nurse from the drop down list, the schedule's title will change to show that it is that nurse's schedule. Switching to doctor's schedule will show the doctor's schedule. (Schedules are currently hard coded and only contains the days of the week that a doctor/nurse is working, does NOT contain appointment information).
* Now, the weekly and monthly schedule under MigLayout does not shrink when components are hidden. The button panel is forcibly docked south of the weekly and monthly schedule by setting the preferred sizes of the schedules to be large (2000x1000). Once the view switches between weekly/monthly, the component hides itself but the size it takes up remains.


## 23 MARCH 08:39 Jenny
* Changed the display of patients to their names instead of usernames.

## 23 mar 06:30
** The following is done in a new branch masterBranch; fully functioning code**

**Doctor**
* patient information is now read in from the database and updated on GUI
* 'treatment' button updates internal database

**Datbase**
* made a way to read in patient files from external file (later need to save to here on sign out or save)

## 23 mar 05:15 Sajid
** The following is done in a new branch masterBranch; fully functioning code**

**Doctor**
* View
    *  method setUpPatientView takes in list of patients from controller, and sets up a JList, and adds the JList to the scrollPane
        * the scrollPane no longer has JPanels or JLabels, it just has the JList above.
* Controller
    * new Mouse Listener to listen to clicks in JList
    * new method setUpPatientView gets database information to set up the textbox (and later the Patient record box)    

## 23 mar 00:40 Sajid
**DoctorModel**
* changed constructor to import assignedPatient list from dbase. Updated in PatientModel and Database accordingly

**DoctorController**
* added a method to take information from the database and set it in the panels that show up on the 'View Patient' GUI
    * This method runs in initView()

**DoctorView**
* moved 'dummy date' into its own function that is then used in the contrller as described above.

**Database**
* added fields for doctors patient list

**Main**
* changed dbase.txt path to relative path, so that we can organize our 'src' folder better.


### 22 MARCH 18:33 Jenny
**DoctorModel**
* Changed the array of scheduled patients to store Strings of patient usernames instead, to maintain consistency with the array of assigned nurses being Strings.
**DoctorController + DoctorView**
* The scrolling issue (couldn't get the list of patients panel in View Patients to scroll) has been fixed. The problem was that the component that JScrollPane was mounted on had a setPreferredSize method, which JScrollPane did not like. Move the method to JScrollPane instead.
* Default scrolling was too slow, changed the vertical unit scrolling increment to 10.
* Currently the patient panel still has dummy data. Clicking on a dummy data will cause the information in the data to be shown in the Patient Information box.
* The controller is set up to populate the patient list with actual, scheduled patients. Currently, it only displays patient names.

## 22 mar 07:30
**Login**
* designed layout
* added exit button and functionality

### 22 mar 01:00 Sajid
**CreateNewPatient**
* view
    * changed default combobox to non-blank (saves error checking)
    * Created method to return oject LocalDateTimeObject
    * created fields for blood type, sex, dob
    * fixed sizing of components
* Model
    * sex,dob, and bloodtype now are stored in internal database as string when patient is created.
    * now extends UserSuperClass to use its methods for date
    * changed constructor to accomodate loading in new fields

**Patient**
* Controller
    * commented out 'false state' listener for the save button to make things simpler
    * load in patient data for dob, age, sec, blood type from internal/external database when page opens.
        * patient page now views all 'information' other than scheduling :)
* View
    * created fields for blood type, sex, dob
* Model
    * created method to set the LocalDate dob object from an entered string of the format "yyyy/M/d"
        * used for taking view entered date, and storing it in PatientModel.
    * changed constructor to allow new fields to load form database

**Database**
* added fields for sec, dob, bloodtype

**UserSuperClass**
* Added a bunch of utility methods for converting strings to LocalDate and back. Subclasses will use these methods when needed
    * Also created methods to change from LocalDate to int and back, but not sure we will implement those at the end since LocalDate and LocalDateTime are pretty useful already.
* Added some date to int conversions for unix time, but thinking of keeping it simple so likely wont use those.


### 21 MARCH 10:22 Jenny
**CreateNewPatientModel+View+Controller**
* Implemented date of birth combo boxes, for year, month, and day. Selecting both month and year will populate the day combo box. There is also a getBirthday() function that returns the string of YYYY-MM-DD when called. During testing, sometimes returns YYYY-MM-null.
* The birthday field is reflected in CreateNewPatientModel and CreateNewPatientController. However, it does not impact the creation of a new patient account (birthday isn't stored).
**PatientView**
* Implemented the same 3 combo boxes as above, with all the same functions. As of now, the date of birth on record isn't show as the default option (as there is currently no date of birth in the database).
* The birthday field is currently commented out in PatientModel.


### 20 MARCH 21:19 Jenny
* Fixed issue where in DoctorView, the interactive text area resizes to a single line when attached to a scrollpane.
* In DoctorView, selections can be made (indicated by color changes, does not actually populate selected patient).
* Still couldn't attach a scrollpanel to list of patients, may have to change to a list instead.

### 20 MARCH 20:43 Jenny
* Fleshed out listener for adding treatment notes to a patient, can add treatment (hard coded, not saved).
* Added sections to patient that are relevant to treatment record (currently commented out as it's not implemented in database).

## 20 mar 13:13 Sajid
* added welcome label and sign out button to patient view, both with functionality.

### 20 mar 12:03 Sajid
* DatabaseModel name changed to Database
* ScheduleModel changed to Schedule
* UserModel changed to UserSuperClass


### 20 MARCH 11:53 Jenny
**DoctorModel + DoctorView**
* Added an empty listener in doctor view and text fields to patient view inside doctor. The functionality isn't incorporated but all the pieces are in place.

### 20 mar 00:14 Sajid
**CreateNewDoctoModel**
* fixed bug causing duplicate blank spaces in combobox  

**CreatNewPatientView**
* added a listener to handle the cancel button

## 19 MARCH 12:29 Jenny
* Went back and redid patient information in doctor. Currently has dummy data.

## 17 MARCH 9:40 Jenny
** ScheduleModel + DoctorModel + NurseModel**
* Created a rough guide of schedule for doctors and nurses. Currently only indicates the days of a week that a doctor/nurse is scheduled to work. This hasn't been implemented into the GUI.

### 16 MARCH 21:08 Jenny
* Started implementation of a patient view into doctor. Added patient record field into patient model and created a panel that allows toggling between patient view and schedule.

### 16 mar 17:20 Sajid
* changed relative path of dbase so that .jar can be exported

### 16 mar 06:01 Sajid
**patient portal functionality created**
* uploads patient information from the database
* checks that no fields are empty when save button is pressed
* doesnt do error checking or update the database on 'patient info' box yet when save button is pressed-- maybe leave it for next iteration
* patient credentials panel does error checking and updates the database to the changed password  

**databaseModel updated**
* new fields added" address, ph#, email, amountDue  

**dbase.txt updated with new fields**
* new fields added" address, ph#, email, amountDue  

**PatientModel**
* constructor changed to reflect changes above  

**CreateNewPatientMVC**
* refactored View using MigLayout
* Added fields address, email, phone number which store in database as is
* changed the controller and model to reflect these new fields

### 15 MARCH 20:32 Jenny
**DoctorModel & DoctorController & DoctorView**
* Added a field of scheduled patients to doctor and implemented a view within doctor view. When logging in, a list of patients scheduled under the doctor will be loaded.

**PatientModel**
* Added a field for treatment record to patient.


### 15 mar 00:30 Sajid
* added 'create department' functionality for admin

### 15 mar 15:50 Sajid
* Created three classes: Receptionist M,V,C, to handle the receptionist class
* updates dbase.txt with a receptionist user, with username= recipe, password = recipe


### 15 MARCH 8:53 Jenny
**CreateNewDoctorView**
* Fixed issue where selecting a ER department for doctor and switching to another department does not reenable add nurses button.

### 10 mar 21:51 Sajid
**Main**
* Internal database(HashMap) is now a global variable called dbase, and is accessible with Main.dbase.
    * Previously, Main.dbase.users was used to access the HashMap

**DatabaseModel**
* greatly simplified import code

**UserModel**
* added new method getObjectName

**DoctorController**
* changed which method retreives 'name' in initView()

**CreateNewPatientModel**
* changed order of constructor arguments in storeInDatabase()

**CreateNewDoctorModel**
* changed order of constructor arguments in storeInDatabase()
* getNurseList() retrieves ArrayList\<String> instead of ArrayList\<NurseModel>
* storeinDatabase() passes String[] instead of ArrayList<NurseModel> to DoctorModel

**CreateNewDoctorView & CreateNewDoctorController**
* Moved nurse-disable listener from controller to view(line 107)
    * the listener requires no communication with the model (only deals with enabling/disabling components of the view independant of model information) so the listener doesn't need to be in the controller and can stay in the view class.

**All Model classes**
* changed order of constructor arguments to reflect format in external database
