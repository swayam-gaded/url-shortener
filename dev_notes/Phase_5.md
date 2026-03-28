## Overall Change 
- **API Documentation**

## 1. Swagger 
It is a metadata engine which represents the API on visual format with the help of annotations<br>
The main places to check this visual representation are : localhost:port:/{swagger-ui/index.html, v3/api-docs, v3/api-docs.yaml}<br>
This is designed to document the API surface(Controller layer) only <br>
Swagger uses these url to display and hence it is required for permitAll() to be applied to these<br>
Created a interface to separate out the controller and its description class<br>
Made a config class so as to imitate the authorization of an endpoint in swagger-ui, did it by customising the built in OpenAPI response<br>
Changed the session to be stateless so that the browser doesnt save the login details in cookie which removes the need for credentials if done once<br>

### Annotations 
@Tag - used to tag a class, mainy uses the name ="" to display it<br>
@Operation - used to annotate a method or endpoint and specify its operation using summary and description<br>
@ApiResponses - have to manually type out the possible codes that particular endpoint can get<br>
@Schema - used to mark the DTOs used in the controller layer<br>