# Scheduling System

This project is a work-in-progress scheduling management system targeted at hospitals. Doctors and nurses can use the system to view their schedule and manage appointment with patients. Patients can use the system to view available doctors and manage their upcoming appointments and lab tests.

## Functionality

In its current iteration, the program can run various GUI windows for most of the roles required. The login page allows for account credential verifcation and new patient account creation.

The Doctor portal page allows for viewing various GUI elements as well as a functioning combobox that displays the assigned nurses of the doctor to the user. A signout button is functional for going back
to the login screen.

The Admin portal allows for the creation of new doctors, including setting up all their required attributes. It also allows allows for the creation of patients, as well as the deletion of any user based on their
username.

The remaining portals load but have limited functionality.

## Getting Started

The following instructions will get you a functional copy of the software running on your local machine. 

### Prerequisites

Here are the things you need to install on your local machine before downloading the source code. 

```
The latest version of Eclipse (any version from 2019 works).
```

The most recent version of Windows Builder (and all corresponding items in the release) should be downloaded in Eclipse prior to launching the program.

```
JRE 1.8.0 (or a more recent version)
```

### Installing

To get the software up and running, first download all files on this branch into your Eclipse project folder(effectively overwriting existing files and folders)

Then, run `main.java` from Eclipse. 

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



# Scheduling System

Rework of the master branch into MVC architecture. Currently, the Doctor and Login are reworked.

Supports the database import function when the program starts up. The users imported are in the dbase.txt file. Please do not edit this text file. Place this text file out side the `src` file of your eclipse project. The rest of the files should go inside the `src` file.


