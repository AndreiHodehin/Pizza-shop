# Pizza-shop
Java app for creating pizza menu by admin and ordering by user.

# About

Online pizza shop with .
This is an online pizza shop with the ability to register as a user, order pizza and track its readiness.
Also carried out operations for the management of a pizzeria. 
The administrator can add products to the warehouse with their quantity, compose and create menus from these products.
It is also possible to track the list of users, manage and change products in the warehouse, pizzas.
In the future, functionality for the consumption of products for pizzas will be added.

# This Website is built for following purpose:

- this is a project for learning new technologies.
- used by java, spring mvc , hibernate, spring security , spring cache , jsp.
- this version implements the main functionality, but there is still a lot to be done and this project has a lot of potential for changes and new things

# Administrator has the following options
- add products to the warehouse and manage it.
- create by from available product or update existing .
- See list of products , pizzas , users.
# User has the following options.
- Rgister, enter information about himself and change it.
- See list of all pizzas.
- Place an order .
- Check readiness of order.
# Techology used:
1.Front-end:
- jsp 
- html 5

2.Back-and
- Java
- Spring-mvc
- Spring-security
- Spring-cache
- Hibernate 5
- Maven

3.Database used:
- MySQL


======== Importing and Running The Project Through IntelligIdia ===========

Step 1: Open intelligIdea.

Step 2: Create new project from version control and past the repository URL as https://github.com/AndreiHodehin/Pizza-shop.git

Step 3: Open MySql workbench > login and copy and paste the following command: 

    create database if not exists pizza; 

Step 4:  Go inside src/main/resources/META-INF/persistence.xml  and update the value of database details as per your usage, like db.driver, db.host, db.username and db.password according to your installed mysql admin user credentials. 

Step 5: Connect Tomcat Server (Install, if not already installed). Run > Edit configuration > Add ew configuration > Tomcat Local > in Deployment make "application context" - "/" and press "Fix" > choose "war exploded" artifact. 

Step 6: Run app for hibernate creates tabes. Stop app.

Step 7: Copy paste the following MySql Commands in Workbench:

    insert into user(address, email, firstName, lastName, password, phoneNumber, username)
    VALUES ('***','somth@email.com','fName','lName','123','000-000','admin');
    
    insert into user_role(USER_ID, role) VALUES (1,'ROLE_ADMIN');
  
Step 8: Default Username And Password For Admin Is "Admin" And "123". Now you can insert product to warehouse and create pizza form them.

Step 9: For using as User logout and registrate .

















