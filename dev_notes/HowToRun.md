## First Time 
- Ran with no problems i think cuz i am writing this after the second serious run.
- Used Command Line Runner to imitate testing and working properly 

## Second time 
- Ran through Intellij 
- ### New things
  - Used the http request method from Intellij  to imitate testing. 
  - Console and gui table of db 
  - Hibernate creates db based on class annotated with table unless explicitly specified. 

- ### Problems faced
  - Forgot to delete command line runner so application was failing 
  - The table annotation on UrlEntity creates a table with class name itself and i was checking the previous table.
  - got an BindException due to incorrect port working for JMX agent
  - hibernate.ddl-auto=update is additive only so the customAlias column was not dropped even though i removed it from the entity.
