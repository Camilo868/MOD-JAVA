Markdown
# Corporate Talent Hub

A robust Java enterprise management system developed to showcase modern **Java (17/21)** features, advanced object-oriented design principles, and a secure database-driven architecture using **JDBC** and the **MVC pattern**.

---

##  Key Features & Modern Java Implementation

This project bridges legacy Java practices with modern features (LTS versions):

*   **Sealed Classes (Java 17+):** The `Persona` class is defined as `sealed`, strictly restricting its extension hierarchy to authorized classes (`Empleado`, `ConsultorExterno`) to protect business domain integrity.
*   **Records (Java 14/16+):** Utilizes `DesempenoReport` as an immutable data carrier to map complex database queries cleanly, eliminating the boilerplate code of traditional POJOs.
*   **Pattern Matching for `instanceof` (Java 17/21):** Simplifies type-checking and eliminates mandatory explicit casting when handling specific roles like `Desarrollador` or `Gerente`.
*   **Interface Evolution (Java 8+):** Implements the `Promocionable` interface featuring `default` methods to add new behaviors without breaking legacy implementations.
*   **Modern JDBC & Resource Management:** Replaces legacy `finally` blocks with `try-with-resources` to guarantee automatic resource closure (preventing Connection and Memory Leaks).
*   **Security (SQL Injection Protection):** Implements the DAO pattern leveraging exclusively `PreparedStatement` parameters across all CRUD operations.
*   **Text Blocks:** Generates structured, clean textual reports effortlessly.

---

##  Project Architecture (MVC Pattern)

The project is structured into three main packages to ensure a clean separation of concerns:

```text
src/main/java/com/riwi/talent/
├── model/        # Entities, Sealed hierarchy, Records, and DAO implementation
├── controller/   # Mediator handling business logic between View and Model
├── view/         # Console interface managing user interaction (Scanner)
└── Main.java     # Application entry point
 Database Setup (PostgreSQL)
Create a PostgreSQL database named corporate_talent_hub.

Execute the required schema creation for tables (empleados, calificaciones).

Update your connection parameters in DatabaseConnection.java:

Java
private static final String URL = "jdbc:postgresql://localhost:5432/corporate_talent_hub";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
 How to Run
Clone the repository and switch to the main branch.

Open the project in your preferred IDE (NetBeans, IntelliJ IDEA, etc.).

Ensure your Java compiler is set to Java 17 or higher.

Run the Main.java file located in the root package.
