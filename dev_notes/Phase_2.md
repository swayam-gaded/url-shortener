## Plan 
- ### Architecture Update : <br> 
Cleaned the file hierarchy and added the necessary directories(empty) into it. 
- ### Logic Upgrade : <br>
Removed the old shortCodeGenerator which used .random() and added the Base62 encoding. This allowed for unique shortCodes to be generated. 
- ### DTO Introduction : <br>




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