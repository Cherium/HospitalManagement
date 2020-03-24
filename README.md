# Hospital Management System

This project is a work-in-progress management system targeted at hospitals. It allows for various individuals to view and update central database information depending on the role of the respective user. Various functionality exists for the following roles: Doctor, Patient, Nurse, Administrator, Receptionist, and Hospital Authority.

## Usage:

The following instructions will get you a functional copy of the software running on your local machine. 

### Prerequisites

* Java JDK version 11 or higher.
* The latest version of Eclipse.
* The most recent version of Windows Builder (and all corresponding items in the release) should be downloaded in Eclipse prior to launching the program.

#### Option #1
1. Download the file `run.jar` from the master branch.
2. Open a terminal where the `.jar` file is located, and execute the following command: `java -jar run.jar`

#### Option #2
1. Download everything from the master branch into a folder
2. Open Eclipse and import that folder as a java project
3. Right click the project name -> Build Path -> configure build path
4. Click the Libraries tab ->  click class path section -> Add JAR
5. Navigate to the lib folder within your project, and select the only file in there
6. Apply and close the dialog box
7. Right click the project -> refresh
8. Click Run in Eclipse and the GUI should load.

## Functionality

In its current iteration, the program can run various GUI windows for most of the roles required. The login page allows for account credential verifcation and new patient account creation, that all checks against an existing, imported, database.

The Doctor portal page allows for viewing various GUI elements as well as a functioning combobox that displays the assigned nurses of the doctor to the user. The Doctor can view the list of patients assigned to them, including detailed patient history, which is imported from an existing database. The Doctor can also append notes to a patients file and save those changes, which then sync with the database.

The Admin portal allows for the creation of new doctors, including setting up all their required attributes. It also allows allows for the creation of patients, as well as the deletion of any user based on their username. The admin portal can also create new departments for the hospital. All changes are integrated with the database.

The patient portal allows the user to view their personal information. It also allows them to edit their information. The patient can change their passwords as well. This change is synced with the database and takes effect immediately.

The remaining portals have assigned portals, however the portals only display name information for that set user.

## Built With

The software is built using Java, with love. 

## Authors
* **Sajid Choudhry**
* **Jeremy Fan**
* **Neil Mariano**
* **Qian Ni Zhang**

See the list of [contributors] (https://github.com/Cherium/HospitalManagement/graphs/contributors) who made this project possible.

## Acknowledgments

Thank you for reading thus far! Please contact one of the above contributors if you have any problems running the software.
