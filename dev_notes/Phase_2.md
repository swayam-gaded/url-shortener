## Plan 
- ### Architecture Update : <br> 
Cleaned the file hierarchy and added the necessary directories(empty) into it. 
- ### Logic Upgrade : <br>
Removed the old shortCodeGenerator which used .random() and added the Base62 encoding. This allowed for unique shortCodes to be generated. 
- ### DTO Introduction : <br>
- ### Global Exception Handler : <br>




## Learning 
- ### About file system : <br>
Java projects typically follow the **Standard Directory Layout** where the <br>
`src/main/java` is production code <br>
`src/main/resources` is where non java files such as application properties, sql scripts and html/css files are present <br>
`src/test/java` follows the same structure but in testing <br>

`com.darklord.url_shortener` is a reverse domain name and is mainly used for uniqueness and fast class finding for JVM <br>
`controller` - allows for different versions without breaking the logic in other parts <br>
`service` - allows for easier testing and particular testing only <br>
`repository` - allows for easy change or modifications in databases used without affecting other parts <br>
`dto` and `model` - give a layer of security. this is a practical application of OOPs abstraction <br>

- ### About shortCode logic upgradation : <br>
Encoding is for compression and URL safety <br>
Base62 is the standard because it maximizes character variety without using "special" URL characters. 
ID-Mapping (our method) is preferred over Random Strings because it eliminates the "Does this code already exist?" database query, making the app faster. <br>

- ### About DTO Logic : <br>
Streams are pipelines for processing collections. <br>
Map is used to transform data (e.g., Entity → DTO). <br>
DTO Mapping ensures that the "Database shape" of your data never leaves the Service layer. <br> 

In addNewUrl method of UrlController : @RequestBody: Maps incoming JSON $\rightarrow$ Java Object. <br>
ResponseEntity: The "Full Envelope" (Data + Status + Headers). <br>
HttpStatus.CREATED: The specific HTTP "Success" code for new data. <br>

- ### About exception handler : <br>
@RestControllerAdvice is a meta annotation made up of ControllerAdvice and ResponseBody and this applies to all every single controller in the project <br>
Usually when an exception is thrown, the controller crashes out and Spring sends a default white label error but with the RestControllerAdvice we can check if there is a way to explain this error explicitly<br> 
The ResponseEntity is a container. The Map is the content (JSON), and the Status is the metadata (HTTP header). They are two different parts of one single response sent back to the user<br>
The MethodArgumentNotValidException checks for all possible not valid errors and the details for this is found in the folder called FieldErrors under BindingResult and these particular errors are binded to the body <br> 


## Some extra points found from mcqs asked by Gemini (19/20 Correct so doing not bad) 7/6/26
- Jackson is the default Spring Library which uses to map JSON data to POJOs and vice versa. This is related to the @RequestBody annotation. 
- DTO is used and entities are not directly sent in get mapping because entities often mirror the db schema exactly, including IDs which can be used to map the system size or perform IDOR(Insecure Direct Object Reference) attacks. 
- Validation through @Valid annotation happens during the binding phase, so invalid data never reaches the business logic and instead you get a 400 bad request error. 
- An exception thrown without an exception handler being present leads to the display of 500 Internal Server Error with a messy stack trace. Therefore you need to have a custom exception handler. 
- Without transaction boundary(@Transactional), each save operation is treated as an independent atomic action that is committed immediately. 
- Two requests with same customAlias hit the server at the same millisecond then to prevent a 'Race Condition' at the db level, you add a UNIQUE constraint to the 'short_code' column in the db.
- The .parallelStream() method from Stream API is used to split the mapping tasks across the multiple CPU cores for faster processing. 
- 302 Found is the standard HTTP code for temporary redirection. 
- Using DTOs allows you to change your db without breaking the contract you have with your API users. 
- The core strength of DTO is that it gives the control over which fields are 'visible' to the outside world. 
- the GET method considered 'Idempotent' while the POST method is not. This is because multiple Get request have the same effect as one request whereas multiple Post requests create multiple resources. 
- Transactional Proxies manage the db session and issue a rollback command if an unchecked exception is caught. 
- System.out.println() is not used for logging in professional Spring Applications because it is a blocking operation that can slow down high concurrency application and lack log levels. 
- Lazy loading optimizes performance by avoiding unnecessary db joins for data that might not be needed. 