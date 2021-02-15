# contacts-list
A simple web application for listing and searching from the pre-loaded csv.


# REST APIs

1.getContacts

#URL : http://localhost:8080/contacts/apis/getContacts?pageSize=10&pageNo=1


2.searchContact

#URL : http://localhost:8080/contacts/apis/searchContact?contactName=Sanjay?pageSize=10&pageNo=1

# DB 

http://localhost:8080/h2-console

# To build and run the application

1. run : "mvn clean install"  at pom.xml directory (it will create the target folder.)
2. take the jar "contact-list-0.0.1-SNAPSHOT.jar" and open command-promt/shell window at this directory.
3. run : "java -jar contact-list-0.0.1-SNAPSHOT.jar" and wait for application to start-up
4. Test some url : eg. : http://localhost:8080/contacts/apis/getContacts?pageSize=10&pageNo=1
5. Access DB at : http://localhost:8080/h2-console
