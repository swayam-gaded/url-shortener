## Overall Changes 
- **Moved to hashid from base62 encoding**
- **Changed the sequence generator using GeneratedValue,Query and Sequence Generator**
- **Removed the command line runner and switched to Intellij's method of sending HTTP requests**
- **Used JPA Auditing to fill the createdAt column in table** 
---
### 1. Base62 &rarr; HashId
Initially had thought of implementing both but was struck down when i realised both required `long` input and gave out `string` output <br>
I have not found a way to make both work while simultaneously maintaining the shortCode to be short <br>
Realised the usage of bean management and how this implementation was different from the Base62 implementation<br> 
The major difference was that in HashId, it had to be passed as a dependency to the service layer while in the base62 it could be accessed directly since it was defined in this project itself<br>
Working : <br>
Encoding/Decoding: Hashids is not a one-way cryptographic hash function. It is a reversible encoding scheme, functioning more like a simple substitution cipher that makes database IDs unguessable to the public<br>
Salt: A private string used to shuffle the internal alphabet, ensuring the generated IDs are unique to your specific application<br>
**Note** : Not to be used for for secure password storage or any scenario requiring true cryptographic hashing<br> 
---
### 2. Sequence Generator for Database IDs
The annotations used were 
- @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_gen")
- @SequenceGenerator( <br>
           name = "url_gen", <br>
           sequenceName = "url_sequence", // should match the SQL sequence name in the db exactly<br>
           allocationSize = 1             // tells Hibernate to increment by 1<br>
  )<br>
- @Query(value = "SELECT nextval('url_sequence')", nativeQuery = true)
<br>
for understanding <br>
GeneratedValue is only to tell Hibernate that it has to follow the sequence generator called url_gen <br>
SequenceGenerator is the configuration and allocationSize refers to the number of records it must keep calculate and keep <br> 
The above two work hand in hand with the PostgreSQL command typed which is actual command to start the sequence<br>
Query is just a hibernate annotation used to specify that the particular data member will gets it value from the native PostgreSQL command specified in the value parameter <br>
---
### 3. JPA Auditing 
The annnotations used were 
- @EnableJpaAuditing
- @EntityListener(AuditingEntityListener.class)
- @CreatedDate 
<br>
Jpa by default does not save any metadata regarding each and every entity but with the EnableJpaAuditing, it tells Spring to scan the context for metadata and create a corresponding pojo <br>
EntityListener(AuditingEntityListener.clas) is the observer which is a part of JPA specification and tells hibernate to notify the AuditingEntityListener before making any db operation<br>
CreatedDate specifies that the data member it has annotated must be filled with the timestamp of creation<br>
All of the notifications and injection of data member happens before the SQL Insert command takes place <br> 