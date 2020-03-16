# Changelog
__In descending date order first__ (most recent date and time first).  
Timestamp Format: __day month HOURS:MINUTES (military time) USER__  
Example: 10 mar 21:13 SAJID


## 16 mar 17:20 Sajid
* changed relative path of dbase so that .jar can be exported

## 16 mar 06:01 Sajid
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

## 15 mar 00:30 Sajid
* added 'create department' functionality for admin

## 15 mar 15:50 Sajid
* Created three classes: Receptionist M,V,C, to handle the receptionist class
* updates dbase.txt with a receptionist user, with username= recipe, password = recipe


## 15 MARCH 8:53 Jenny
**CreateNewDoctorView**
* Fixed issue where selecting a ER department for doctor and switching to another department does not reenable add nurses button. 

## 10 mar 21:51 Sajid
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

