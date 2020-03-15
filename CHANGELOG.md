# Changelog
__In descending date order first__ (most recent date and time first).  
Timestamp Format: __day month HOURS:MINUTES (military time) USER__  
Example: 10 mar 21:13 SAJID



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
