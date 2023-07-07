Employee Asset Management System :
The Employee Asset Management System is a web-based application that allows organizations to track and manage hardware assets assigned to employees. It provides features for employee sign-in, sign-up, update profile, user asset details, create asset, asset assignment, asset return update asset status.

Features
Employee Sign-in: Employees can sign in to access their account and view their assigned assets.
Asset Creation: Administrators can add new assets.
Asset Assignment : Employees can request for new assets and return their existing assets.
Asset Status: The system maintains the status of assets, whether they are assigned or unassigned.
Dashboard: Employees can view their assigned assets and their details on their dashboard.


Technologies Used :
Java
Spring Boot
Spring Security
Thymeleaf 
Spring Data JPA 
MySQL 
HTML/CSS/BootStrap/JavaScript 


Getting Started
To run the Employee Asset Management System locally, follow these steps:

Clone the repository:
git clone https://github.com/vidhyaratha/Vidhya_Ratha_Capstone.git

git checkout master


Navigate to the project directory:
cd Vidhya_Ratha_Capstone


Configure the database:
Create a MySQL database.
Update the database connection settings in the application.properties file located in the src/main/resources directory.


Execute the Queries: 
create database employeeassetmanagementdemo

Asset table  :
INSERT INTO `employeeassetmanagementdemo`.`assets` (`Id`, `assetCreatedDate`, `assetId`, `assetName`, `assetType`, `status`) VALUES ('1002', '01-01-2023', 'AID1002', 'HP 21 inch Monitor', 'Monitor', 'Unassigned');
INSERT INTO `employeeassetmanagementdemo`.`assets` (`Id`, `assetCreatedDate`, `assetId`, `assetName`, `assetType`, `status`) VALUES ('1003', '01-01-2023', 'AID1003', 'Mac book Pro', 'Laptop-Mac', 'Unassigned');
INSERT INTO `employeeassetmanagementdemo`.`assets` (`Id`, `assetCreatedDate`, `assetId`, `assetName`, `assetType`, `status`) VALUES ('1004', '01-01-2023', 'AID1004', 'Dell I7 Laptop', 'Laptop-Windows', 'Unassigned');
INSERT INTO `employeeassetmanagementdemo`.`assets` (`Id`, `assetCreatedDate`, `assetId`, `assetName`, `assetType`, `status`) VALUES ('1005', '01-01-2023', 'AID1005', 'Logitech Keyboard', 'Keyboard', 'Unassigned');
INSERT INTO `employeeassetmanagementdemo`.`assets` (`Id`, `assetCreatedDate`, `assetId`, `assetName`, `assetType`, `status`) VALUES ('1006', '01-01-2023', 'AID1006', 'HP 21 inch Monitor', 'Monitor', 'Unassigned');


Role table :
INSERT INTO `employeeassetmanagementdemo`.`role` (`Id`, `role`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `employeeassetmanagementdemo`.`role` (`Id`, `role`) VALUES ('2', 'ROLE_USER');



Build and run the application:
./mvnw spring-boot:run


Access the application:

Open a web browser and visit http://localhost:8080 to access the Employee Asset Management System.

Usage
Sign up as an employee with your employee ID, email, and password, gender, location, name.
Sign in using your credentials.
On the Userdetails page, you can view the details of the employee and the assets assigned to that employee.
On the Userdetails page, Click the "Request a Device" option from the navigator to request a new device.
From Navigator menu, Click the "Return Device" to return the existing device.













