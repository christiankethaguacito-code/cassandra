# 🚂 Railway Project Configuration

## 📍 Your Railway Project

**Project URL**: https://railway.com/project/0729cbb8-8332-44b3-abff-4b2fa4f737e7/service/25ff21ef-521d-45f4-a160-f31c42e3f1ab

---

## ⚙️ Configuration Steps

### 1. Set Environment Variables (In Railway Dashboard)

Go to your Railway project → **Variables** tab and add:

```bash
SPRING_PROFILES_ACTIVE=prod
PORT=8080
```

### 2. Verify Build Settings

Railway should auto-detect:
- ✅ **Build Command**: `mvn clean package -DskipTests`
- ✅ **Start Command**: `java -Dserver.port=$PORT -jar target/financial-management-1.0.0.jar`

### 3. Check Service Settings

In **Settings** tab:
- ✅ **Root Directory**: `/` (leave empty or set to root)
- ✅ **Watch Paths**: Leave default
- ✅ **Healthcheck**: Not required (but can add `/` or `/actuator/health` if you add Spring Actuator)

---

## 🔧 If Build Fails

### Check Build Logs
1. Go to **Deployments** tab
2. Click on the latest deployment
3. View build logs

### Common Issues & Fixes

**Issue 1: Port Binding Error**
```bash
# Railway provides PORT variable automatically
# Your app is configured to use it: -Dserver.port=$PORT ✅
```

**Issue 2: Build Timeout**
```bash
# If build takes too long, Railway might timeout
# Solution: Build is optimized with -DskipTests ✅
```

**Issue 3: Out of Memory**
```bash
# Add Java memory settings in Variables:
JAVA_OPTS=-Xmx512m -Xms256m
```

Then update start command to:
```bash
java $JAVA_OPTS -Dserver.port=$PORT -jar target/financial-management-1.0.0.jar
```

---

## 🎯 Expected Deployment Flow

1. **Building** (2-3 minutes)
   - Installing Java 17
   - Downloading Maven dependencies
   - Compiling code
   - Packaging JAR file

2. **Deploying** (30-60 seconds)
   - Starting application
   - Initializing Spring Boot
   - Connecting to H2 database
   - Starting Tomcat server

3. **Live** ✨
   - App accessible via Railway URL
   - HTTPS enabled automatically

---

## 🌐 Access Your App

Once deployed, Railway will provide a domain like:
```
https://cassandra-production-XXXX.up.railway.app
```

**Find Your URL**:
1. Go to **Settings** tab
2. Scroll to **Domains** section
3. Click **Generate Domain**
4. Copy the URL!

---

## 📊 Monitor Deployment

### Real-time Logs
```bash
# In Railway dashboard, click "View Logs"
# You should see:
```
```
Started FinancialManagementApplication in X seconds
Tomcat started on port 8080
```

### Metrics
- **CPU Usage**: Should be low when idle
- **Memory**: ~300-500MB for Spring Boot app
- **Network**: Check incoming requests

---

## 🔄 Auto-Deployment

Your app will auto-deploy when you push to GitHub:

```bash
# Make changes
git add .
git commit -m "Update feature"
git push

# Railway automatically detects and redeploys! 🚀
```

---

## 💾 Database Upgrade (Optional)

### Add PostgreSQL:

1. Click **"New"** in Railway project
2. Select **"Database" → "PostgreSQL"**
3. Railway auto-provisions PostgreSQL
4. Add environment variable (Railway does this automatically):
   ```
   DATABASE_URL=postgresql://...
   ```

5. Update `pom.xml` (add PostgreSQL driver):
   ```xml
   <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
   </dependency>
   ```

6. Push changes - Railway redeploys with PostgreSQL!

---

## 🎊 Test Your Live App

Once deployed, test these features:

1. ✅ **Home Page**: Should load with login/register
2. ✅ **Register**: Create new account
3. ✅ **Login**: Authenticate
4. ✅ **Dashboard**: See animated charts
5. ✅ **Transactions**: Add/Edit/Delete
6. ✅ **Receipts**: Generate and download PDF
7. ✅ **Profile**: View and edit profile

---

## 🚨 Troubleshooting

### App Not Starting?

**Check Logs for**:
```
Error: Could not find or load main class
```
**Solution**: Verify JAR file name in railway.json matches actual build output

**Check Logs for**:
```
Port 8080 already in use
```
**Solution**: Use Railway's PORT variable (already configured ✅)

**Check Logs for**:
```
Application failed to start
```
**Solution**: Check application.properties and ensure H2 database is properly configured

---

## 📈 Performance Tips

1. **Enable Compression** (already configured in application-prod.properties ✅)
2. **Optimize Database Queries** (using Hibernate efficiently ✅)
3. **Cache Static Resources** (handled by Spring Boot ✅)
4. **Monitor Memory Usage** (Railway metrics)

---

## 🔒 Security Checklist

- ✅ HTTPS enabled (Railway default)
- ✅ Secure cookies configured
- ✅ Spring Security enabled
- ✅ BCrypt password encryption
- ✅ H2 console disabled in production
- ✅ Error messages hidden from users
- ✅ Stack traces disabled

---

## 💡 Next Steps

1. **Generate Domain**: Get your public URL
2. **Test All Features**: Verify everything works
3. **Share URL**: Let users access your app!
4. **Monitor Logs**: Watch for any issues
5. **Optional**: Add custom domain
6. **Optional**: Upgrade to PostgreSQL

---

## 🎉 You're Live!

Your Financial Management System with:
- ✨ Animated Visualizations
- 📊 Interactive Charts
- 💰 Transaction Management
- 📄 Receipt Generation
- 🔒 Secure Authentication

...is now deployed to the cloud! 🚀

---

**Project Dashboard**: https://railway.com/project/0729cbb8-8332-44b3-abff-4b2fa4f737e7

**Need Help?**
- Railway Docs: https://docs.railway.app
- Railway Discord: https://discord.gg/railway

---

*Congratulations on your deployment! 🎊*
