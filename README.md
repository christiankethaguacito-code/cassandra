# Personal Financial Management System
## Hybrid Web + Desktop Application

![Java](https://img.shields.io/badge/Java-17+-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green)
![SQLite](https://img.shields.io/badge/SQLite-Database-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

A comprehensive financial management system designed for residents and small business owners in **Isulan, Sultan Kudarat**. This hybrid application provides both a web-based interface and a desktop Java Swing GUI, sharing the same SQLite database for seamless offline and online access.

---

## 📋 Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [Usage Guide](#usage-guide)
- [API Documentation](#api-documentation)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

---

## ✨ Features

### 🌐 Web Application (Spring Boot + Thymeleaf)
- ✅ User registration and login with BCrypt encryption
- ✅ Interactive dashboard with financial overview
- ✅ Real-time charts using Chart.js
- ✅ Add, edit, and delete transactions
- ✅ Profile management (update name, email, password)
- ✅ Responsive design for mobile and desktop
- ✅ Spring Security for authentication

### 🖥️ Desktop Application (Java Swing)
- ✅ Native GUI with NetBeans .form files
- ✅ Tabbed interface (Dashboard, Transactions, Profile)
- ✅ JFreeChart for data visualization
- ✅ Offline access to financial data
- ✅ Same SQLite database as web version
- ✅ Full CRUD operations for transactions
- ✅ Profile management

### 💾 Shared Features
- ✅ SQLite database (shared between web and desktop)
- ✅ Income and expense tracking
- ✅ Category-based organization
- ✅ Financial reports and analytics
- ✅ Secure password encryption
- ✅ MVC architecture
- ✅ Comprehensive JavaDoc comments

---

## 🛠️ Technologies

### Backend
- **Java 17+**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security**
- **SQLite Database**
- **Hibernate**
- **BCrypt** (password encryption)

### Frontend
#### Web
- **Thymeleaf** (templating engine)
- **HTML5, CSS3**
- **Chart.js** (data visualization)
- **Responsive CSS**

#### Desktop
- **Java Swing**
- **NetBeans GUI Builder**
- **JFreeChart** (charts)

### Build Tool
- **Maven 3.8+**

---

## 📁 Project Structure

```
NEWBUSINESS/
├── pom.xml                                 # Maven configuration
├── README.md                               # This file
├── financial_management.db                 # SQLite database (auto-generated)
└── src/
    └── main/
        ├── java/com/isulan/financial/
        │   ├── FinancialManagementApplication.java    # Main Spring Boot app
        │   ├── config/
        │   │   └── SecurityConfig.java                 # Spring Security config
        │   ├── model/
        │   │   ├── User.java                          # User entity
        │   │   └── Transaction.java                   # Transaction entity
        │   ├── repository/
        │   │   ├── UserRepository.java                # User data access
        │   │   └── TransactionRepository.java         # Transaction data access
        │   ├── service/
        │   │   ├── UserService.java                   # User business logic
        │   │   └── TransactionService.java            # Transaction business logic
        │   ├── controller/
        │   │   ├── AuthController.java                # Login/Register
        │   │   ├── DashboardController.java           # Dashboard
        │   │   ├── TransactionController.java         # Transactions
        │   │   └── ProfileController.java             # Profile
        │   └── desktop/
        │       ├── DesktopApplication.java            # Desktop launcher
        │       ├── util/
        │       │   └── SpringContextUtil.java         # Spring context for Swing
        │       └── ui/
        │           ├── LoginFrame.java + .form        # Login GUI
        │           ├── RegisterFrame.java + .form     # Registration GUI
        │           ├── MainFrame.java + .form         # Main window
        │           ├── DashboardPanel.java            # Dashboard tab
        │           ├── TransactionsPanel.java         # Transactions tab
        │           └── ProfilePanel.java              # Profile tab
        └── resources/
            ├── application.properties                 # App configuration
            ├── static/css/
            │   └── style.css                          # Web styles
            └── templates/
                ├── index.html                         # Home page
                ├── login.html                         # Login page
                ├── register.html                      # Registration page
                ├── dashboard.html                     # Dashboard
                ├── transactions.html                  # Transactions list
                ├── add-transaction.html               # Add transaction
                ├── edit-transaction.html              # Edit transaction
                └── profile.html                       # Profile page
```

---

## 🚀 Getting Started

### Prerequisites

1. **Java Development Kit (JDK) 17 or higher**
   ```bash
   java -version
   ```

2. **Maven 3.8+**
   ```bash
   mvn -version
   ```

3. **NetBeans IDE** (recommended for editing Swing .form files)
   - Download from: https://netbeans.apache.org/

### Installation

1. **Clone or extract the project**
   ```bash
   cd C:\Users\USER\OneDrive\Desktop\NEWBUSINESS
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **The SQLite database will be created automatically** on first run
   - Database file: `financial_management.db` (in project root)

---

## 🎯 Running the Application

### Option 1: Run Web Application

```bash
mvn spring-boot:run
```

Then open your browser to: **http://localhost:8080**

### Option 2: Run Desktop Application

#### Using Maven:
```bash
mvn exec:java -Dexec.mainClass="com.isulan.financial.desktop.DesktopApplication"
```

#### Using NetBeans:
1. Open project in NetBeans
2. Right-click on `DesktopApplication.java`
3. Select **Run File**

#### Using JAR (after building):
```bash
java -cp target/financial-management-1.0.0.jar com.isulan.financial.desktop.DesktopApplication
```

### Option 3: Run Both Simultaneously

**Terminal 1 (Web):**
```bash
mvn spring-boot:run
```

**Terminal 2 (Desktop):**
```bash
mvn exec:java -Dexec.mainClass="com.isulan.financial.desktop.DesktopApplication"
```

Both applications will share the same SQLite database!

---

## 📖 Usage Guide

### First Time Setup

1. **Register a new account**
   - Web: Go to http://localhost:8080/register
   - Desktop: Click "Register" button on login screen

2. **Fill in your details**
   - Full Name
   - Email
   - Password (minimum 6 characters)

3. **Login**
   - Use your registered email and password

### Managing Transactions

#### Web Interface:
1. Go to **Transactions** menu
2. Click **Add Transaction**
3. Fill in:
   - Type: Income or Expense
   - Category: e.g., Salary, Food, Transportation
   - Description: Transaction details
   - Amount: PHP amount
   - Date: Transaction date
4. Click **Add Transaction**

#### Desktop Interface:
1. Click on **Transactions** tab
2. Click **Add Transaction** button
3. Fill in the same fields
4. Click **Save**

### Viewing Dashboard

- **Web**: Displays pie charts for expenses and income by category
- **Desktop**: Shows JFreeChart visualizations and summary cards

### Updating Profile

1. Go to **Profile** section
2. Update:
   - Name
   - Email
   - Password (requires current password)
3. Click **Update Profile** or **Change Password**

---

## 🔒 Security Features

- ✅ **BCrypt password hashing** (12-round salt)
- ✅ **Spring Security** for web authentication
- ✅ **Session management**
- ✅ **CSRF protection** (can be enabled in production)
- ✅ **SQL injection prevention** (JPA/Hibernate)

---

## 📊 Database Schema

### Users Table
```sql
CREATE TABLE users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  email TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  profile_picture TEXT
);
```

### Transactions Table
```sql
CREATE TABLE transactions (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  user_id INTEGER NOT NULL,
  type TEXT NOT NULL,
  category TEXT,
  description TEXT,
  amount REAL,
  date TEXT,
  FOREIGN KEY(user_id) REFERENCES users(id)
);
```

---

## 🎨 Customization

### Change Web Port
Edit `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Change Database Location
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:sqlite:C:/path/to/custom/location/financial.db
```

### Modify Colors
Edit `src/main/resources/static/css/style.css` for web theme colors.

---

## 🐛 Troubleshooting

### Database Locked Error
- Close all applications using the database
- Delete `financial_management.db` and restart

### Port Already in Use (Web)
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Change port in application.properties
```

### Desktop App Won't Start
- Ensure Spring Boot context initializes
- Check console for error messages
- Verify JDK version (must be 17+)

---

## 📝 JavaDoc Documentation

Generate JavaDoc:
```bash
mvn javadoc:javadoc
```

View at: `target/site/apidocs/index.html`

---

## 🤝 Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

**Isulan Development Team**
- Location: Isulan, Sultan Kudarat, Philippines
- Year: 2025

---

## 🙏 Acknowledgments

- Spring Boot Framework
- NetBeans IDE
- JFreeChart Library
- Chart.js Library
- SQLite Database
- iText PDF Library

---

## 📞 Support

For questions or issues:
- Check the troubleshooting section
- Review JavaDoc documentation
- Open an issue on GitHub

---

**Built with ❤️ for the people of Isulan, Sultan Kudarat**
