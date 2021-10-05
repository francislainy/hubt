## Hubspot Functional tests
These can be found under the `test/functional_tests` folder. Their dependencies are under the main pom file for the application.

These tests can be run either through a maven command, or a run button inside your IDE.

To target the test classes through the maven command, we tend to follow a naming pattern, in this case, they all end with the word "Test". which is represented by `.*Test` after the package location. 
The below is a sample for the maven command. Notice we can also provide extra parameters such as which environment we would like our tests to run on.

`mvn -Dtest=functional_tests.*Test test`

When our tests run, we print the curl for the api that is being targeted within the console terminal, which can be useful for debugging purposes. This can be
changed to a text file or other preferred means as it's controlled by a logback.xml file within the test/resources folder.
This printing is handled by an external library which exists within the pom file.
