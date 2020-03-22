# Changelog
__In descending date order first__ (most recent date and time first).  
Timestamp Format: __day month HOURS:MINUTES (military time) USER__  
Example: 10 mar 21:13 SAJID

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

