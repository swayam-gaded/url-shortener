# Url-Shortener

## Why is it required
- Acts as a loophole because some platforms have character limits and also allows for clean and less cluttered format.
- Easier to manage and simpler to copy, paste, remember and type shorter links.
- Enables some extra features such as number of clicks, devices, user locations and traffic sources.

## How to run 
1. Clone the repository
2. Start PostgreSQL
3. Create a database and update `application.properties`
4. Run the Spring Boot application
5. Use curl or Postman to test the AP

## Working 
- ### Model Layer: <br>
UrlShortener.java contains the structure of the class which has three data members (id,shortCode,originalUrl).
- ### Presentation Layer: <br>
 UrlController.java has three HTTP methods involved which are 
 GET for displaying, <br> 
 GET for redirection, <br> 
 POST for client entry.
- ### Business Layer: <br>
UrlService.java has three main methods involved <br>
getOriginalUrl used for the redirection of the page. <br>
addNew used for creation of new shortCode, validation and assignment. It uses sub methods - shortCodeGenerator and existShortCode. <br>
displayOutput used to show the shortCode in the form of Lists of UrlShorteners. 
- ### Persistence Layer: <br>
UrlRepo.java is an interface extending JpaRepository which enables CRUD methods 
- ### Database Layer: <br>
PostgreSQL stores the persisted UrlShortener entities.
- ### currently used for testing: <br>
UrlConfig.java is used to add test data and using it here to test the validation part of my business logic.

## API Endpoints: 
- POST /api/v1/url => Create a new short URL
- GET /api/v1/url => List all shortened URLs
- GET /api/v1/url/{shortCode} => Redirect to original URL

## Challenges and solutions used, during the project building
- shortCode definition - a generated value used only for identification.
- Redirection Part - implemented via HTTP redirect response in the GET method of the controller.
- tried to directly access the members of UrlShortener - used the getter method defined in the UrlShortener.
- forgot to mention a GET method in controller for the home page - created a new GET method which ran when api/v1/url was run.
- forgot how to properly run POST method and was waiting for its code to be run based only on the Config data - used curl to hit the POST method.
- didnt create a no-arg constructor used by Hibernate to instantiate entities from database rows - created a no-arg constructor for UrlShortener.

## New things learnt
- Redirection part <br>
status() <br>
location() <br>
build() <br>
- Setting up database in Postgres in Terminal <br>
Run database server - `sudo service postgresql start` <br>
Entering Postgres as an admin - `sudo -u postgres psql` <br>
Creation, Ownership transfer and Priviges grants.
- In PostgreSQL => Unquoted identifiers are automatically converted to lowercase.
- JPQL parameter style is different from SQL.
- The query commands written in JPQL such as WHERE require Entity classes.
- The POST methods are called currently using curl in terminal. 

## Future Improvements
- URL expiry
- Click count tracking
- Custom short codes
- Frontend UI
- Rate limiting