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

## 2. Security
In the SecurityConfig.java file <br>
Explicit Bean configuration is used since external classes are being used <br> 
---
**SecurityFilterChain** <br>
@EnableWebSecurity is the annotation which tells Spring to use the beans defined in that class as the security(such as SecurityFilterChain) instead of its default configuration <br>
`DelegatingFilterProxy` which acts as the bridge between `Servlet Container` and `ApplicationContext` and `FilterChainProxy` are automatically created by Spring, only the list of rules followed by this `FilterChainProxy` is defined here <br>
`HttpSecurity` is a builder which is a specialiased object prviding DSL(Domain Specific Language) to define exactly which filters are to be applied <br>
Matchers are URL pattern detectors which look for that particular Url and then go on to apply filter methods on it<br>
the `anyRequest().authenticated()` is used for securing any other url not mentioned before it with some basic auth<br>
`httpBasic` enables the HTTP basic auth and is the simplest form of security. the Customizer.withDefaults() is a shortcut to tell Spring to use basic auth without custom config <br>
the `http.build()` is the one constructing the SecurityChainFilter object that Spring uses. basically you are configuring the blueprint of the security<br>
`csrf.disable()` is done because CSRF(Cross Site Request Forgery) requires a unique token to be sent with every POST request which goes against the stateless feature of REST APIs<br>
in the passwords method we have `{noop}` which stands for **No Operation** which tells the spring to use the password as plain text. Usually password requires an ecnrypted password <br>
`{noop}` has been taken down and a bcrypt encoder is being used to encode the password <br>
Also there is an additional security for checking whether the client accessing the view all get endpoint has the Admin role using a PreAuthorize annotation on the endpoint in the controller layer <br>
---
**UserDetailsService** <br>
UserDetailsService is an interface with only one method (loadUserByUsername) which acts as the search engine for Spring security and here it mainly is used to check the in memory list of users i have made (only admin for now)<br>
User is the helper class which lets us build a default user until we create a custom user class <br>
Request comes in with a username/password<br>
Spring calls your UserDetailsService bean<br>
The InMemoryUserDetailsManager looks for the User you built<br>
If it matches, the request is allowed to pass through the filter<br>
### Extra Points 
- `@ReqArgsConstructor` - now the required arguments are the ones defined as `final` or annotated with `@NonNull`
