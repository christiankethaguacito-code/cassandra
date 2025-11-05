# 🚀 DEPLOYMENT GUIDE
## Personal Financial Management System

---

## 📦 DEPLOYMENT OPTIONS

### Option 1: Run from Source (Development)
**Best for**: Development, Testing, Local Use

**Requirements:**
- Java JDK 17+
- Maven 3.8+

**Steps:**
```powershell
# Navigate to project
cd C:\Users\USER\OneDrive\Desktop\NEWBUSINESS

# Build
mvn clean install

# Run Web
mvn spring-boot:run

# OR Run Desktop
mvn exec:java -Dexec.mainClass="com.isulan.financial.desktop.DesktopApplication"
```

---

### Option 2: Create Executable JAR
**Best for**: Distribution, Production

#### Build JAR File

```powershell
# Create standalone JAR
mvn clean package

# Output location:
# target/financial-management-1.0.0.jar
```

#### Run JAR - Web Application

```powershell
java -jar target/financial-management-1.0.0.jar
```
Access at: http://localhost:8080

#### Run JAR - Desktop Application

```powershell
java -cp target/financial-management-1.0.0.jar com.isulan.financial.desktop.DesktopApplication
```

---

### Option 3: Create Windows Executable (.exe)
**Best for**: Non-technical Users

#### Using Launch4j

1. **Download Launch4j**
   - Website: http://launch4j.sourceforge.net/
   - Extract to `C:\launch4j`

2. **Create Configuration File** (`launch4j-config.xml`):
```xml
<launch4jConfig>
  <headerType>gui</headerType>
  <jar>financial-management-1.0.0.jar</jar>
  <outfile>FinancialManager-Desktop.exe</outfile>
  <icon>icon.ico</icon>
  <jre>
    <minVersion>17</minVersion>
  </jre>
</launch4jConfig>
```

3. **Generate EXE**:
```powershell
C:\launch4j\launch4jc.exe launch4j-config.xml
```

4. **Distribute**:
   - `FinancialManager-Desktop.exe`
   - `financial-management-1.0.0.jar`
   - (Keep them in same folder)

---

### Option 4: Deploy as Windows Service
**Best for**: Always-on Web Server

#### Using NSSM (Non-Sucking Service Manager)

1. **Download NSSM**
   - Website: https://nssm.cc/download
   - Extract to `C:\nssm`

2. **Install Service**:
```powershell
C:\nssm\nssm.exe install FinancialManagerWeb "C:\Program Files\Java\jdk-17\bin\java.exe"
C:\nssm\nssm.exe set FinancialManagerWeb AppParameters "-jar C:\FinancialManager\financial-management-1.0.0.jar"
C:\nssm\nssm.exe set FinancialManagerWeb AppDirectory "C:\FinancialManager"
C:\nssm\nssm.exe set FinancialManagerWeb DisplayName "Financial Manager Web"
C:\nssm\nssm.exe set FinancialManagerWeb Description "Personal Financial Management Web Service"
C:\nssm\nssm.exe set FinancialManagerWeb Start SERVICE_AUTO_START
```

3. **Start Service**:
```powershell
net start FinancialManagerWeb
```

4. **Access**:
   - Open browser: http://localhost:8080

---

### Option 5: Deploy to External Server
**Best for**: Remote Access, Multiple Users

#### A. Deploy to Azure

1. **Create Azure Account**
   - https://azure.microsoft.com

2. **Install Azure CLI**
   ```powershell
   winget install Microsoft.AzureCLI
   ```

3. **Login**
   ```powershell
   az login
   ```

4. **Create Web App**
   ```powershell
   az webapp up --name financial-manager-isulan --resource-group FinancialRG --runtime "JAVA:17-java17"
   ```

5. **Deploy JAR**
   ```powershell
   az webapp deploy --name financial-manager-isulan --resource-group FinancialRG --src-path target/financial-management-1.0.0.jar --type jar
   ```

#### B. Deploy to Heroku

1. **Create Heroku Account**
   - https://www.heroku.com

2. **Install Heroku CLI**
   ```powershell
   winget install Heroku.HerokuCLI
   ```

3. **Login**
   ```powershell
   heroku login
   ```

4. **Create App**
   ```powershell
   heroku create financial-manager-isulan
   ```

5. **Deploy**
   ```powershell
   git init
   git add .
   git commit -m "Initial commit"
   git push heroku master
   ```

#### C. Deploy to Local Network (LAN)

1. **Configure Server**
   
   Edit `application.properties`:
   ```properties
   server.address=0.0.0.0
   server.port=8080
   ```

2. **Allow Firewall**
   ```powershell
   netsh advfirewall firewall add rule name="Financial Manager" dir=in action=allow protocol=TCP localport=8080
   ```

3. **Run Application**
   ```powershell
   java -jar financial-management-1.0.0.jar
   ```

4. **Access from Network**
   - Find your IP: `ipconfig`
   - Access from other computers: `http://192.168.1.x:8080`

---

## 🗄️ DATABASE DEPLOYMENT

### Option 1: Use SQLite (Default)
**Best for**: Single-user, Local deployment

- No additional setup needed
- Database file: `financial_management.db`
- Automatically created on first run

### Option 2: Migrate to PostgreSQL
**Best for**: Multi-user, Production

1. **Install PostgreSQL**
   ```powershell
   winget install PostgreSQL.PostgreSQL
   ```

2. **Update `pom.xml`**:
   ```xml
   <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
       <scope>runtime</scope>
   </dependency>
   ```

3. **Update `application.properties`**:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/financial_db
   spring.datasource.username=postgres
   spring.datasource.password=yourpassword
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   ```

### Option 3: Migrate to MySQL
**Best for**: Shared hosting, Traditional servers

1. **Install MySQL**
   ```powershell
   winget install Oracle.MySQL
   ```

2. **Update `pom.xml`**:
   ```xml
   <dependency>
       <groupId>com.mysql</groupId>
       <artifactId>mysql-connector-j</artifactId>
       <scope>runtime</scope>
   </dependency>
   ```

3. **Update `application.properties`**:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/financial_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
   ```

---

## 🔒 PRODUCTION SECURITY CHECKLIST

### Before Deployment

- [ ] **Enable CSRF Protection**
  ```java
  // In SecurityConfig.java, remove:
  .csrf(csrf -> csrf.disable())
  ```

- [ ] **Use HTTPS**
  - Get SSL certificate (Let's Encrypt, free)
  - Configure Spring Boot for HTTPS
  - Force HTTPS redirect

- [ ] **Secure Database**
  - Change default database location
  - Set file permissions (read/write owner only)
  - Regular backups

- [ ] **Environment Variables**
  ```properties
  # Don't hardcode passwords
  spring.datasource.password=${DB_PASSWORD}
  ```

- [ ] **Logging**
  ```properties
  # Enable production logging
  logging.level.root=WARN
  logging.level.com.isulan=INFO
  logging.file.name=application.log
  ```

- [ ] **Session Security**
  ```properties
  # Set secure session timeout
  server.servlet.session.timeout=30m
  server.servlet.session.cookie.secure=true
  server.servlet.session.cookie.http-only=true
  ```

---

## 📦 DISTRIBUTION PACKAGE

### Create Distribution Package

1. **Create Folder Structure**:
   ```
   FinancialManager-v1.0.0/
   ├── financial-management-1.0.0.jar
   ├── start-web.bat
   ├── start-desktop.bat
   ├── README.txt
   ├── LICENSE.txt
   └── database/ (empty folder)
   ```

2. **Create README.txt**:
   ```
   Personal Financial Management System v1.0.0
   
   INSTALLATION:
   1. Ensure Java 17+ is installed
   2. Double-click start-web.bat for web interface
   3. Double-click start-desktop.bat for desktop app
   
   WEB ACCESS:
   http://localhost:8080
   
   SUPPORT:
   Email: support@example.com
   ```

3. **Compress**:
   - Zip the folder
   - Name: `FinancialManager-v1.0.0-Windows.zip`

---

## 🐳 DOCKER DEPLOYMENT (Advanced)

### Create Dockerfile

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/financial-management-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Build Docker Image

```powershell
docker build -t financial-manager:1.0.0 .
```

### Run Docker Container

```powershell
docker run -p 8080:8080 -v ${PWD}/data:/app/data financial-manager:1.0.0
```

### Docker Compose (docker-compose.yml)

```yaml
version: '3.8'
services:
  web:
    build: .
    ports:
      - "8080:8080"
    volumes:
      - ./data:/app/data
    restart: unless-stopped
```

---

## ☁️ CLOUD DEPLOYMENT COMPARISON

| Provider | Free Tier | Setup Difficulty | Best For |
|----------|-----------|-----------------|----------|
| **Azure** | 12 months | Medium | Enterprise |
| **Heroku** | Yes (limited) | Easy | Startups |
| **AWS** | 12 months | Hard | Scalability |
| **Google Cloud** | 90 days | Medium | Google ecosystem |
| **DigitalOcean** | $200 credit | Easy | Developers |

---

## 🎯 DEPLOYMENT CHECKLIST

### Pre-Deployment

- [ ] All tests pass
- [ ] No compile errors
- [ ] Database configured
- [ ] Security settings reviewed
- [ ] Backup strategy defined
- [ ] Documentation updated

### During Deployment

- [ ] Create JAR file
- [ ] Test JAR locally
- [ ] Configure production settings
- [ ] Set up monitoring
- [ ] Configure backups
- [ ] Test all features

### Post-Deployment

- [ ] Verify web access
- [ ] Test desktop app
- [ ] Check database connectivity
- [ ] Monitor logs
- [ ] Test user registration
- [ ] Test transactions
- [ ] Verify charts display
- [ ] Check profile updates
- [ ] Document deployment

---

## 🔧 MAINTENANCE

### Regular Tasks

**Daily:**
- Check application logs
- Verify backup completion

**Weekly:**
- Review user activity
- Check disk space
- Update dependencies

**Monthly:**
- Security updates
- Performance review
- User feedback review

### Backup Strategy

**Database Backup**:
```powershell
# Copy SQLite file
copy financial_management.db backups\financial_management_%date:~-4,4%%date:~-10,2%%date:~-7,2%.db

# Schedule with Task Scheduler
schtasks /create /tn "Financial DB Backup" /tr "C:\path\to\backup-script.bat" /sc daily /st 23:00
```

---

## 📊 MONITORING

### Application Health

**Check if Running**:
```powershell
# Web
curl http://localhost:8080

# Desktop (Task Manager)
tasklist | findstr java
```

**View Logs**:
```powershell
# Check console output
# Or configure file logging in application.properties
```

### Performance Metrics

Monitor:
- Response time
- Memory usage
- CPU usage
- Database size
- Active sessions

---

## 🆘 TROUBLESHOOTING DEPLOYMENT

### Issue: "Address already in use"
**Solution**: Change port or stop other service

### Issue: "Database locked"
**Solution**: Ensure only one instance running

### Issue: "Cannot find java"
**Solution**: Set JAVA_HOME environment variable

### Issue: "Permission denied"
**Solution**: Run as administrator or adjust file permissions

---

## 📞 SUPPORT

After deployment, provide users:

1. **User Guide** (from README.md)
2. **FAQ Document**
3. **Support Email**
4. **Issue Reporting Form**
5. **Update Instructions**

---

## ✅ DEPLOYMENT COMPLETE

Your application is now deployed and ready to use!

**Next Steps:**
1. Share access details with users
2. Monitor initial usage
3. Gather feedback
4. Plan updates

**Built with ❤️ for Isulan, Sultan Kudarat**
