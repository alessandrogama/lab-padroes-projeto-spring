# Country Explorer API (Design Patterns with Spring Boot)

This project is a Spring Boot application designed to search and explore country data by integrating with an external Rest Countries API. It demonstrates how to apply classic Gang of Four (GoF) design patterns in a modern Spring Boot context using native features such as `RestClient`, Dependency Injection, Records, and Spring-managed beans.

---

## Technologies Used

- **Java 21**: Utilizing modern features like Java Records, pattern matching, and standard collections.
- **Spring Boot 4.0.7**: Core framework for web, dependency injection, and configuration.
- **Spring Web / RestClient**: For executing REST calls to external services.
- **Spring Boot Starter Test**: For robust unit testing using JUnit 5 and Mockito.

---

## Architectural Design Patterns Implemented

The architecture leverages Spring's IoC container to implement clean, decoupled design patterns:

### 1. **Strategy Pattern**
Enables flexible and pluggable search behaviors.
- **Interface**: `CountrySearchStrategy` defines the search contract.
- **Implementations**:
  - `SearchByNameStrategy`: Uses `CountryProvider` to find countries by name.
  - `SearchByRegionStrategy`: Uses `CountryProvider` to find countries by region.
- **Spring Integration**: Each strategy is registered as a named `@Component` (e.g. `@Component("name")`, `@Component("region")`).

### 2. **Factory Pattern**
Maintains decoupling by creating/supplying strategies dynamically at runtime based on user input.
- **Implementation**: `CountrySearchStrategyFactory` injects a Map of all registered `CountrySearchStrategy` beans (`Map<String, CountrySearchStrategy>`).
- **Dynamic Resolution**: It retrieves the appropriate bean using the map keys (lowercase values of search types), throwing a clear error for unsupported strategies.

### 3. **Facade Pattern**
Simplifies the subsystem interaction for the presentation layer by providing a single point of access.
- **Implementation**: `CountryFacade` exposes a high-level `search(type, value)` method. It coordinates the Strategy Factory to fetch data from the provider and uses `CountryService` to convert results into clean, standardized response DTOs.

### 4. **Adapter Pattern**
Decouples the system from external API responses and structural variations.
- **Implementation**: `RestCountriesAdapter` implements the internal `CountryProvider` interface. It translates Rest Countries API response structures (`RestCountriesResponse`) into the domain-friendly `CountryApiResponse` objects, ensuring the core service remains decoupled from external API changes.

---

## Running the Application

### Prerequisites
- **Java 21** or higher
- Maven wrapper (provided as `mvnw` and `mvnw.cmd`)

### Configuration
Configure your credentials in `src/main/resources/application.yaml` or set the following environment variables:
```yaml
restcountries:
  base-url: https://api.restcountries.com/
  api-key: ${API_REST_COUNTRIES}
```

### Run Commands
Execute the following in your terminal to start the application:

```bash
# On Windows
.\mvnw.cmd spring-boot:run

# On Linux / macOS
./mvnw spring-boot:run
```
The application will start on port `8080` by default.

---

##  API Endpoints

### **Search Countries**
Perform a flexible query for countries using different strategy types.

- **URL**: `/api/countries/search`
- **Method**: `GET`
- **Query Parameters**:
  - `type` (Required): The search strategy to use (e.g., `name`, `region`).
  - `value` (Required): The search term / value.
- **Example Request**:
  ```http
  GET /api/countries/search?type=name&value=brazil
  ```
- **Example Response (Success - `200 OK`)**:
  ```json
  [
    {
      "name": "Brazil",
      "officialName": "Federative Republic of Brazil",
      "capital": "Brasília",
      "region": "Americas",
      "subregion": "South America",
      "population": 214000000
    }
  ]
  ```

---

## Testing

The codebase includes comprehensive unit tests verifying data conversions, error handling, and robust null-safety handling under `src/test/java`.

To run the unit tests:
```bash
# Using Maven wrapper
./mvnw test
```
