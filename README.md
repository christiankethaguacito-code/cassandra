# Financial Management System

Hybrid financial management application with web and desktop interfaces sharing a single SQLite database.

## Features
- User authentication and registration
- Income/expense tracking with categories
- Dashboard with charts and visualizations
- Transaction CRUD operations
- Profile management
- PDF receipt generation

## Tech Stack
- Java 17, Spring Boot 3.2.0
- SQLite Database, Hibernate ORM
- Web: Thymeleaf, Chart.js, Bootstrap
- Desktop: Java Swing, JFreeChart
- Security: BCrypt, Spring Security
- PDF: iText library

## Quick Start

Requirements: Java 17+, Maven 3.8+

### Option 1: Run from Executable JAR (Recommended)

**Build the executables first:**
```bash
mvn clean package -DskipTests
```

**Run Desktop Application:**
```bash
# Easiest way - Double-click this file:
Launch-Desktop-App.bat

# Or use command line:
.\run-desktop-app.bat

# Or run JAR directly (will show console):
java -jar target\financial-management-1.0.0-desktop.jar

# Or run without console window:
javaw -jar target\financial-management-1.0.0-desktop.jar
```

**Run Web Application:**
```bash
.\run-web-app.bat
# Or directly: java -jar target\financial-management-1.0.0-web.jar
```
Access: http://localhost:8080

**Important Notes:**
- First startup takes 5-10 seconds (Spring Boot initialization)
- Desktop app will open login window after initialization
- Make sure Java 17+ is installed: `java -version`
- If double-clicking JAR doesn't work, use the .bat launcher files

### Option 2: Run from Source

**Web Application:**
```bash
mvn spring-boot:run
```

**Desktop Application:**
```bash
.\Launch-Desktop-App.bat
cd C:\Users\USER\OneDrive\Desktop\NEWBUSINESS; SPRING_OUTPUT_ANSI_ENABLED=always "JAVA_HOME=C:\\Program Files\\Apache NetBeans\\jdk" cmd /c "\"C:\\Program Files\\Apache NetBeans\\java\\maven\\bin\\mvn.cmd\" \"-Drun.jvmArguments=-noverify -XX:TieredStopAtLevel=1\" -Drun.mainClass=com.isulan.financial.FinancialManagementApplication -Dexec.vmArgs= -Dexec.appArgs= \"-Dmaven.ext.class.path=C:\\Program Files\\Apache NetBeans\\java\\maven-nblib\\netbeans-eventspy.jar\" --no-transfer-progress spring-boot:run"
WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::staticFieldBase has been called by com.google.inject.internal.aop.HiddenClassDefiner (file:/C:/Program%20Files/Apache%20NetBeans/java/maven/lib/guice-5.1.0-classes.jar)
WARNING: Please consider reporting this to the maintainers of class com.google.inject.internal.aop.HiddenClassDefiner
WARNING: sun.misc.Unsafe::staticFieldBase will be removed in a future release
Scanning for projects...

------------------< com.isulan:financial-management >-------------------
Building Personal Financial Management System 1.0.0
  from pom.xml
--------------------------------[ jar ]---------------------------------

>>> spring-boot:3.2.0:run (default-cli) > test-compile @ financial-management >>>

--- resources:3.3.1:resources (default-resources) @ financial-management ---
Copying 2 resources from src\main\resources to target\classes
Copying 10 resources from src\main\resources to target\classes

--- compiler:3.11.0:compile (default-compile) @ financial-management ---
Changes detected - recompiling the module! :source
Compiling 24 source files with javac [debug release 17] to target\classes
/C:/Users/USER/OneDrive/Desktop/NEWBUSINESS/src/main/java/com/isulan/financial/desktop/ui/DashboardPanel.java: C:\Users\USER\OneDrive\Desktop\NEWBUSINESS\src\main\java\com\isulan\financial\desktop\ui\DashboardPanel.java uses or overrides a deprecated API.
/C:/Users/USER/OneDrive/Desktop/NEWBUSINESS/src/main/java/com/isulan/financial/desktop/ui/DashboardPanel.java: Recompile with -Xlint:deprecation for details.
-------------------------------------------------------------
COMPILATION ERROR : 
-------------------------------------------------------------
com/isulan/financial/desktop/ui/DashboardPanel.java:[498,32] variable chartPanel is already defined in class com.isulan.financial.desktop.ui.DashboardPanel
1 error
-------------------------------------------------------------
------------------------------------------------------------------------
BUILD FAILURE
------------------------------------------------------------------------
Total time:  3.019 s
Finished at: 2025-11-06T10:49:32+08:00
------------------------------------------------------------------------
Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.11.0:compile (default-compile) on project financial-management: Compilation failure
com/isulan/financial/desktop/ui/DashboardPanel.java:[498,32] variable chartPanel is already defined in class com.isulan.financial.desktop.ui.DashboardPanel

-> [Help 1]

To see the full stack trace of the errors, re-run Maven with the -e switch.
Re-run Maven using the -X switch to enable full debug logging.

For more information about the errors and possible solutions, please read the following articles:
[Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException

```

### Build Executables
```bash
mvn clean package -DskipTests
```
This creates:
- `target\financial-management-1.0.0-web.jar` (Web application)
- `target\financial-management-1.0.0-desktop.jar` (Desktop application)
- Both are fully executable with all dependencies included

## How to Use

### Web Application Usage

1. **Registration**
   - Navigate to http://localhost:8080
   - Click "Register" link
   - Enter name, email, and password (min 6 characters)
   - Submit to create account

2. **Login**
   - Enter registered email and password
   - Click "Login" to access dashboard

3. **Dashboard**
   - View total income, expenses, and balance
   - See pie charts for income/expense breakdown by category
   - Overview of recent transactions

4. **Managing Transactions**
   - Go to "Transactions" menu
   - Click "Add Transaction" button
   - Fill in: Type (Income/Expense), Category, Description, Amount, Date
   - Click "Save" to add transaction
   - Click "Edit" on any transaction to modify
   - Click "Delete" to remove transaction
   - All changes sync to database immediately

5. **Profile Management**
   - Go to "Profile" section
   - Update name, email, or password
   - Changes are saved to database

### Desktop Application Usage

1. **Launch Application**
   - Double-click `Launch-Desktop-App.bat` launcher
   - Or from NetBeans: Right-click `DesktopApplication.java` → Run File
   - Wait for Spring Boot to initialize (5-6 seconds)
   - Login window appears with purple gradient theme

2. **Registration**
   - Click "Register" button on login screen
   - Fill in name, email, and password
   - Submit to create account
   - Automatically returns to login

3. **Login**
   - Enter email and password
   - Click "Login" to access main application

4. **Dashboard Tab**
   - View animated summary cards (Income, Expenses, Balance)
   - See JFreeChart pie charts for category breakdown
   - Real-time data from database

5. **Transactions Tab**
   - View all transactions in table format
   - Click "Add Transaction" button to create new entry
   - Double-click any row to edit transaction
   - Select row and click "Delete" to remove
   - Click "Receipt" to generate PDF receipt for selected transaction
   - Table auto-refreshes after each operation

6. **Profile Tab**
   - View and update account information
   - Change password securely
   - Updates sync to database

## Components Explained

### Backend Components

**Spring Boot Framework**
- Main application container and dependency injection
- Auto-configuration for database, security, and web
- Embedded Tomcat server for web interface
- ApplicationContext shared between web and desktop modes

**Hibernate ORM**
- Object-Relational Mapping for database operations
- Automatic table creation and schema updates
- Entity management for User and Transaction models
- Transaction management and rollback support

**Spring Security**
- BCrypt password hashing (12 rounds)
- Authentication and authorization
- Session management
- CSRF protection (configurable)

**SQLite Database**
- File-based database (financial_management.mv.db)
- No server required, embedded in application
- Single database file shared by web and desktop
- ACID compliant transactions

**Spring Data JPA**
- Repository pattern for data access
- Automatic query generation from method names
- Custom query support with @Query annotation
- Pagination and sorting capabilities

### Frontend Components

**Web Interface (Thymeleaf)**
- Server-side templating engine
- Dynamic HTML generation with Spring integration
- Form binding and validation
- Secure by default (XSS protection)

**Chart.js**
- JavaScript charting library for web dashboard
- Pie charts for income/expense visualization
- Responsive and interactive charts
- Color-coded by transaction type

**Bootstrap CSS**
- Responsive grid layout
- Pre-styled components (buttons, forms, cards)
- Mobile-friendly design
- Custom CSS overrides in style.css

**Desktop Interface (Swing)**
- Java GUI toolkit for desktop application
- NetBeans GUI Builder for visual design (.form files)
- Event-driven programming model
- Native look and feel

**JFreeChart**
- Java charting library for desktop dashboard
- 3D pie charts for category visualization
- Professional chart rendering
- Export capabilities

**iText PDF**
- PDF generation for transaction receipts
- Programmatic document creation
- Table formatting and styling
- File system integration

## Project Architecture Flow

### Application Startup Flow

**Web Mode:**
```
User runs: mvn spring-boot:run
→ FinancialManagementApplication.main() executes
→ Spring Boot auto-configuration starts
→ Database connection established (SQLite)
→ Hibernate creates/updates tables
→ Security configuration applied
→ Embedded Tomcat starts on port 8080
→ Web controllers registered
→ Application ready for HTTP requests
```

**Desktop Mode:**
```
User runs: .\start-desktop-gui.bat
→ DesktopApplication.main() executes
→ Spring Boot ApplicationContext initialized
→ Database connection established (same SQLite file)
→ Services and repositories loaded
→ SpringContextUtil stores context
→ LoginFrame Swing window created
→ Event listeners attached
→ GUI displayed to user
```

### Database Flow

**Entity Relationship:**
```
User (1) ----< has many >---- (N) Transaction

User Table:
- id (Primary Key)
- name
- email (Unique)
- password (BCrypt hashed)
- profile_picture

Transaction Table:
- id (Primary Key)
- user_id (Foreign Key → User.id)
- type (Income/Expense)
- category
- description
- amount
- date
```

**Data Access Pattern:**
```
Controller/UI → Service Layer → Repository → Hibernate → SQLite Database

Example Transaction Creation:
1. User submits form (web) or clicks Save (desktop)
2. Controller/Panel calls TransactionService.saveTransaction()
3. Service validates data and calls TransactionRepository.save()
4. Repository uses Hibernate to generate SQL INSERT
5. Hibernate executes: INSERT INTO transactions VALUES (...)
6. SQLite commits transaction to file
7. Result returned up the stack
8. UI updated with new data
```

**Database Operations:**
- CREATE: repository.save(entity)
- READ: repository.findById() / findAll() / custom queries
- UPDATE: repository.save(existingEntity) - Hibernate detects changes
- DELETE: repository.deleteById(id)

### Request/Response Flow

**Web Request Flow:**
```
Browser → HTTP Request → Tomcat → Spring DispatcherServlet
→ Controller Method (@GetMapping/@PostMapping)
→ Service Layer (business logic)
→ Repository (database access)
→ Database Query/Update
→ Response Data
→ Thymeleaf Template Rendering
→ HTML Response → Browser Display
```

**Desktop Event Flow:**
```
User Action (button click) → ActionListener.actionPerformed()
→ UI Panel method (e.g., addButtonActionPerformed())
→ Service Layer (via SpringContextUtil.getBean())
→ Repository (database access)
→ Database Query/Update
→ Result returned
→ UI Update (table refresh, dialog display)
→ User sees changes
```

### Authentication Flow

**Web:**
```
1. User submits login form → POST /login
2. Spring Security intercepts request
3. CustomUserDetailsService.loadUserByUsername() called
4. User fetched from database via UserRepository
5. Password verified with BCrypt.matches()
6. Session created and cookie sent
7. User redirected to /dashboard
8. Subsequent requests authenticated via session
```

**Desktop:**
```
1. User enters credentials and clicks Login
2. LoginFrame calls UserService.login()
3. Service fetches user from database
4. BCrypt verifies password hash
5. If valid, LoginFrame closes
6. MainFrame opens with user context
7. User object passed to all panels
8. Panels use user.id for data filtering
```

## Development Tools

### VS Code
- **Primary code editor** for Java source files
- Extensions used:
  - Java Extension Pack (IntelliSense, debugging)
  - Spring Boot Extension Pack
  - Maven for Java
- Used for:
  - Writing controller, service, repository classes
  - Configuration files (application.properties)
  - HTML templates (Thymeleaf)
  - CSS styling
  - Git version control
  - Terminal for Maven commands

### NetBeans
- **GUI Builder and Desktop Development**
- Used for:
  - Creating Swing .form files (visual GUI designer)
  - Drag-and-drop UI component placement
  - Property editing for Swing components
  - Auto-generated GUI code (between //GEN-BEGIN and //GEN-END)
  - Running desktop application:
    1. Open project in NetBeans
    2. Navigate to: `src/main/java/com/isulan/financial/desktop/DesktopApplication.java`
    3. Right-click the file → **Run File** (Shift+F6)
    4. Application starts with Spring Boot initialization
  - Building project with Maven integration
  - Managing NetBeans-specific configurations (nbactions.xml)

### Maven
- **Build tool and dependency management**
- pom.xml defines:
  - Project dependencies (Spring Boot, Hibernate, SQLite, iText, JFreeChart)
  - Build plugins and configuration
  - Java version (17)
- Commands:
  - `mvn clean package` - Build JAR file
  - `mvn spring-boot:run` - Run web application
  - `mvn compile` - Compile Java classes
  - `mvn exec:java` - Run desktop application

### Development Workflow

1. **Project Setup**: Created Maven project structure
2. **Backend Development** (VS Code):
   - Created entity classes (User, Transaction)
   - Implemented repositories with Spring Data JPA
   - Developed service layer with business logic
   - Built REST controllers for web interface
   - Configured Spring Security

3. **Web Frontend** (VS Code):
   - Designed Thymeleaf templates
   - Added Bootstrap styling
   - Implemented Chart.js visualizations
   - Created custom CSS

4. **Desktop GUI** (NetBeans):
   - Designed frames/panels with GUI Builder
   - Created .form files for visual layouts
   - Added custom code in non-generated sections
   - Connected UI to Spring services
   - Implemented event listeners

5. **Testing**:
   - Web: Browser testing at localhost:8080
   - Desktop: Run from NetBeans or batch file
   - Database: Verified with SQLite browser
   - Both interfaces share same database

6. **Build & Deploy**:
   - Maven packages everything into single JAR
   - Batch files for easy launching
   - Single database file for data portability

## Project Structure
```
src/main/java/com/isulan/financial/
├── config/          # Security and initialization
├── controller/      # Web request handlers
├── desktop/         # Swing GUI application
├── model/           # Database entities
├── repository/      # Data access layer
└── service/         # Business logic
```

## Distribution

### Desktop Application
The desktop app is packaged as a standalone executable JAR:
- **File**: `target\financial-management-1.0.0-desktop.jar`
- **Size**: ~60MB (includes all dependencies)
- **Requirements**: Java 17+ installed on target machine
- **Database**: Creates `financial_management.mv.db` in the same directory

**To distribute:**
1. Build: `mvn clean package -DskipTests`
2. Copy `financial-management-1.0.0-desktop.jar` to target machine
3. Run with: `java -jar financial-management-1.0.0-desktop.jar`
4. Or use the provided `run-desktop-app.bat` launcher

### Web Application
The web app is packaged as a standalone executable JAR:
- **File**: `target\financial-management-1.0.0-web.jar`
- **Size**: ~60MB (includes all dependencies + embedded Tomcat)
- **Requirements**: Java 17+ installed on server
- **Database**: Creates `financial_management.mv.db` in the same directory

**To deploy:**
1. Build: `mvn clean package -DskipTests`
2. Copy `financial-management-1.0.0-web.jar` to server
3. Run with: `java -jar financial-management-1.0.0-web.jar`
4. Access at: http://server-ip:8080

**Note**: Both JAR files are fully self-contained with all dependencies bundled inside.

## License
MIT
