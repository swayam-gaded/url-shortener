## Overall Changes
- **Added testing** 
- **Added Spring Security** 

## 1. Testing
Difference between Unit tests and Integration tests <br> 

| Tests | Unit | Integration |
|---|---|---|
|Logic| Tests isolated class logic that is only a particular class's logic|Tests wiring and autoconfiguration more than logic|
| Dependency| @Mock | @MockitoBean|
| Performance | Extremely Fast cuz no Spring Boot Required | Slower cuz it starts the spring context partially | 

### UrlControllerTest<br>
The `@MockMvcTest` annotation is used to test only the API endpoints by starting a Web layer, making it faster and more focused. The alternative or other side is `@SpringBootTest`<br>
`MockMvc` acts as the fake browser which sends the http requests into the code without using the port 8080 allowing us to check whether the controller is correctly mapped to the path specified<br>
The line `Mockito.when(<br>.).thenReturn(<br>.)` is called **stubbing** and is used to return a fake response to verify whether the controller is accepting the request properly and is giving the expected response<br>
`ObjectMapper` is used to java objects into JSON strings and here we are using the `@RequestBody` annotation to convert the json request to the UrlRequest DTO<br>
The test failed in the beginning cuz of `@EnableJpaAuditing`. The location of this annotation must not be on the main application class cuz this breaks the slice tests(integration) and this led to creating the `JpaConfig` file<br>
JSONPath: Learned to navigate both single objects ($.field) and arrays ($[0].field or $.length())<br>
Don't ignore the stack trace: We learned that a 500 error in a test usually points to a NullPointerException because a Mock was not stubbed correctly<br>

### Extra Points 
- `@ReqArgsConstructor` - now the required arguments are the ones defined as `final` or annotated with `@NonNull`
