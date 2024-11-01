# AI Predictive Maintenance System
Backend Design 
## Overview

The AI Predictive Maintenance System is designed to monitor industrial machines and their sensor data in real time, predict potential failures, and help with proactive maintenance. The system stores machine data, processes sensor readings like temperature, vibration, and pressure, and is being extended with machine learning models for predictive analysis.

## Features

- **Machine Management**: Create, update, and delete machines with relevant metadata such as name, location, and status.
- **Sensor Data Processing**: Capture real-time sensor data including temperature, vibration, and pressure for each machine.
- **Predictive Analytics (Planned)**: Integrate machine learning algorithms to predict potential failures based on historical sensor data.
- **Threshold-Based Alerts (Planned)**: Alerting system for detecting anomalies in sensor readings based on defined thresholds.
- **User Interface (Planned)**: A UI to provide a better user experience for system monitoring and management.

---

## Project Structure

### Models

1. **Machine**
    - Represents a machine in the system.
    - Contains attributes like `name`, `location`, `status`.
    - One-to-many relationship with `SensorData` to track sensor readings for each machine.
    
    ```java
    public class Machine {
        private Long id;
        private String name;
        private String location;
        private String status;
        private List<SensorData> sensorDataList;
    }
    ```

2. **SensorData**
    - Represents the sensor readings from a machine at a specific time.
    - Tracks `temperature`, `vibration`, and `pressure` along with a timestamp.
    - Many-to-one relationship with `Machine`.
    
    ```java
    public class SensorData {
        private Long id;
        private LocalDateTime timestamp;
        private double temperature;
        private double vibration;
        private double pressure;
        private Machine machine;
    }
    ```

---

### Controllers

1. **MachineController**
    - RESTful API to manage machines (Create, Read, Update, Delete).
    - **Endpoints**:
        - `POST /api/machines`: Create or update a machine.
        - `GET /api/machines`: Retrieve all machines.
        - `GET /api/machines/{id}`: Retrieve machine by ID.
        - `PUT /api/machines/{id}`: Update machine details.
        - `DELETE /api/machines/{id}`: Delete machine by ID.
        
    ```java
    @PostMapping
    public ResponseEntity<Machine> createOrUpdateMachine(@RequestBody Machine machine) {
        Machine savedMachine = machineService.saveMachine(machine);
        return ResponseEntity.ok(savedMachine);
    }
    ```

2. **SensorDataController** (Planned)
    - RESTful API to manage sensor data associated with machines.

---

### Services

1. **MachineService**
    - Contains business logic to handle machine-related operations such as saving, updating, retrieving, and deleting machine records.
    
    ```java
    public Machine saveMachine(Machine machine) {
        return machineRepository.save(machine);
    }
    ```

2. **SensorDataService** (Planned)
    - Business logic for handling sensor data such as processing and validating sensor readings.

---

### Repository

1. **MachineRepository**
    - Interface extending `JpaRepository<Machine, Long>`.
    - Provides CRUD operations for `Machine` entities.
    
2. **SensorDataRepository** (Planned)
    - Interface extending `JpaRepository<SensorData, Long>`.
    - Provides CRUD operations for `SensorData` entities.

---

### Data Flow and Processing

1. **Machine Creation and Management**
    - Users can create machines with basic information such as name and location.
    - Sensor data will be associated with machines via the one-to-many relationship.

2. **Sensor Data Collection**
    - Sensor data, including temperature, pressure, and vibration, will be captured periodically and stored in relation to its machine.
    
3. **Predictive Maintenance (Planned)**
    - A machine learning model will analyze historical sensor data to predict potential failures.
    - Data will be processed and passed to the model for predictions via a REST API endpoint.

---

### Pending Features and Future Plans

1. **Sensor Data Processing**:
    - Implementation of `SensorDataService` and `SensorDataController` to handle sensor data operations.

2. **Machine Learning Integration**:
    - A machine learning model will be trained using historical sensor data to predict machine failures.

3. **Threshold-Based Alerting**:
    - The system will trigger alerts when sensor readings exceed predefined thresholds.

4. **User Interface**:
    - A front-end interface will allow users to interact with the system, view machine data, and monitor predictions.

---

## Setup Instructions

### Prerequisites

- **Java 8**
- **Spring Boot 3.3.4**
- **MySQL or PostgreSQL**
- **Maven** (for dependency management)
- **JPA and Hibernate** (for ORM)

### How to Run

1. **Clone the Repository**:
   ```bash
   git clone https://https://github.com/AbhishekReddys07/AI-Maintenance-System
   cd your-repository
2. **Configure the Database**:
   Set up a MySQL or PostgreSQL database and update application.properties with your database credentials.
   ex: spring.datasource.url=jdbc:mysql://localhost:3306/you DataBase Name
       spring.datasource.username=your user name
       spring.datasource.password=yourpassword

3. **Run the Application**: mvn spring-boot:run

4. **API Endpoints**:=
    Access the machine API at http://localhost:8080/api/machines.
