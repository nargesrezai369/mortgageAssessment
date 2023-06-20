# The Mortgage API

The Mortgage is a java based backend application that using REST.
It should contain the following endpoints;

- GET /api/interest-rates (get a list of current interest rates).
- POST /api/mortgage-check (post the parameters to calculate for a mortgage check).


## Functional Requirements

The Mortgage API has the following functional requirements:

- The list of current mortgage rates should be created in memory on.
- a mortgage should not exceed 4 times the income.
- a mortgage should not exceed the home value.


## Microservice structure:

- config layer for configuration files such as swagger configuration.
- controllers layer for implementation rests.
- domain layer for database entities.
- exception layer for implement exceptions and exception handler.
- mapper layer for implement mapping class between layers.
- models layer for input and output models or DTOs.
- services layer for implementation services and logic.


## How to Use My Project:

1. Clone the repository to your local machine.
2. Set up your development environment, ensuring you have Java and Spring Boot installed.
3. Import the project into your preferred IDE.
4. Build project.
5. Run the MortgageAssessmentApplication and verify that it starts successfully.
6. Access the SwaggerUI documentation to explore the available endpoints and their documentation
   (http://localhost:8081/swagger-ui/index.html#).
7. Use the provided Postman collections to test and interact with the API(Mortgage.postman_collection.json).
