# 🚂 Railway Deployment Guide
**Financial Management System - Deploy to Railway**

---

## 📋 Prerequisites

1. **GitHub Account** (to connect with Railway)
2. **Railway Account** (Sign up at https://railway.app)
3. **Git Initialized** (Your project should be in a Git repository)

---

## 🚀 Deployment Steps

### Step 1: Initialize Git Repository (If Not Done)

```powershell
# Navigate to your project
cd C:\Users\USER\OneDrive\Desktop\NEWBUSINESS

# Initialize Git
git init

# Create .gitignore
echo "target/
.mvn/
*.class
*.log
*.jar
*.war
*.ear
.DS_Store
.idea/
*.iml
.vscode/
financial_management.mv.db
financial_management.trace.db
financial_management.lock.db" > .gitignore

# Add all files
git add .

# Commit
git commit -m "Initial commit - Financial Management System with animated visualizations"
```

### Step 2: Push to GitHub

```powershell
# Create a new repository on GitHub (https://github.com/new)
# Then link it to your local repository:

git remote add origin https://github.com/YOUR_USERNAME/financial-management.git
git branch -M main
git push -u origin main
```

### Step 3: Deploy to Railway

#### Option A: Using Railway Dashboard (Recommended)

1. **Go to Railway**: https://railway.app
2. **Sign in** with your GitHub account
3. **Click "New Project"**
4. **Select "Deploy from GitHub repo"**
5. **Choose your repository**: `financial-management`
6. **Railway will auto-detect** Spring Boot and start deploying!

#### Option B: Using Railway CLI

```powershell
# Install Railway CLI
npm install -g @railway/cli

# Login to Railway
railway login

# Initialize Railway project
railway init

# Link to your project
railway link

# Deploy
railway up
```

### Step 4: Configure Environment Variables (Optional)

In Railway Dashboard:
1. Go to your project
2. Click on **"Variables"** tab
3. Add these variables (if needed):

```
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080
```

### Step 5: Add PostgreSQL Database (Upgrade from H2)

For a production-ready database:

1. In Railway Dashboard, click **"New"**
2. Select **"Database" → "PostgreSQL"**
3. Railway will provision a PostgreSQL database
4. Copy the **DATABASE_URL** connection string
5. Add these environment variables:

```
SPRING_DATASOURCE_URL=${DATABASE_URL}
SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
```

6. **Add PostgreSQL dependency** to `pom.xml`:
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

## 📦 Files Created for Railway

### 1. `railway.json` ✅
Railway configuration file that tells Railway how to build and run your app.

### 2. `nixpacks.toml` ✅
Nixpacks configuration for build process (uses Java 17).

### 3. `application-prod.properties` ✅
Production configuration with optimized settings.

### 4. `.gitignore` (Create if needed)
Excludes unnecessary files from Git.

---

## 🔧 Configuration Details

### Railway will:
- ✅ Auto-detect Spring Boot application
- ✅ Install Java 17
- ✅ Run `mvn clean package -DskipTests`
- ✅ Start your application with `java -jar`
- ✅ Assign a public URL (https://your-app.up.railway.app)
- ✅ Enable HTTPS automatically
- ✅ Auto-restart on failures

### Your app will:
- ✅ Run on port assigned by Railway (`$PORT` variable)
- ✅ Use production configuration
- ✅ Disable DevTools and H2 console
- ✅ Enable compression
- ✅ Secure cookies
- ✅ Log at INFO level

---

## 🌐 After Deployment

### Access Your App
Once deployed, Railway will provide a URL like:
```
https://financial-management-production.up.railway.app
```

### Check Deployment Status
1. Go to Railway Dashboard
2. View deployment logs
3. Check for any errors

### Monitor Your App
- **Logs**: Real-time logs in Railway Dashboard
- **Metrics**: CPU, Memory, Network usage
- **Restart**: If needed, click "Restart" button

---

## 🔒 Security Enhancements (Post-Deployment)

### 1. Enable Custom Domain (Optional)
```
Settings → Domains → Add Custom Domain
```

### 2. Set Up Environment Variables for Secrets
```
JWT_SECRET=your-secret-key-here
DB_PASSWORD=your-db-password
```

### 3. Enable Railway's Built-in Features
- **Auto-scaling**: Handles traffic spikes
- **Zero-downtime deploys**: Seamless updates
- **SSL/HTTPS**: Enabled by default

---

## 📊 Monitoring & Maintenance

### View Logs
```powershell
# Using Railway CLI
railway logs
```

### Redeploy
```powershell
# Make changes, commit, and push
git add .
git commit -m "Update feature"
git push

# Railway auto-deploys on push!
```

### Rollback (If Needed)
1. Go to Deployments tab in Railway
2. Click on a previous successful deployment
3. Click "Redeploy"

---

## 💾 Database Backup (If using PostgreSQL)

### Manual Backup
```powershell
# Using Railway CLI
railway run pg_dump $DATABASE_URL > backup.sql
```

### Restore
```powershell
railway run psql $DATABASE_URL < backup.sql
```

---

## 🚨 Troubleshooting

### Issue: Port Binding Error
**Solution**: Ensure you're using `server.port=${PORT:8080}` in application-prod.properties ✅

### Issue: Build Fails
**Solution**: Check Railway logs, ensure `mvn clean package` works locally

### Issue: Database Connection Error
**Solution**: Verify DATABASE_URL environment variable is set correctly

### Issue: Application Crashes
**Solution**: Check logs with `railway logs` or in Dashboard

---

## 💰 Railway Pricing

### Hobby Plan (Free Tier)
- **$5 free credit** per month
- Perfect for testing and small projects
- Includes:
  - 512MB RAM
  - 1GB Disk
  - Shared CPU

### Pro Plan ($20/month)
- **$20 usage credit** per month
- Better performance
- More resources
- Priority support

---

## 🎯 Deployment Checklist

Before deploying:
- [x] Git repository initialized
- [x] `.gitignore` created
- [x] Code committed
- [x] Pushed to GitHub
- [x] `railway.json` configured
- [x] `nixpacks.toml` configured
- [x] `application-prod.properties` created
- [ ] Railway account created
- [ ] Project deployed on Railway
- [ ] Application URL tested
- [ ] All features working in production

---

## 🔄 Continuous Deployment

Railway automatically deploys when you push to GitHub:

```powershell
# Make changes to your code
git add .
git commit -m "Add new feature"
git push

# Railway automatically:
# 1. Detects the push
# 2. Builds the application
# 3. Runs tests (if any)
# 4. Deploys to production
# 5. Provides the updated URL
```

---

## 📈 Upgrading Database to PostgreSQL

### Step 1: Add PostgreSQL in Railway
1. Click **"New" → "Database" → "PostgreSQL"**
2. Railway creates database and provides connection string

### Step 2: Update `pom.xml`
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

### Step 3: Update Environment Variables
```
DATABASE_URL=postgresql://user:password@host:5432/railway
SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
```

### Step 4: Commit and Push
```powershell
git add pom.xml
git commit -m "Add PostgreSQL support"
git push
```

Railway will automatically redeploy with PostgreSQL! 🎉

---

## 🌟 Advanced Features

### Custom Healthcheck
Add to Railway settings:
```
Healthcheck Path: /actuator/health
```

### Environment-based Configuration
```properties
# In application-prod.properties
spring.profiles.active=prod
```

### Scaling (Pro Plan)
- Horizontal scaling available
- Auto-scaling based on load

---

## 📞 Support & Resources

- **Railway Documentation**: https://docs.railway.app
- **Railway Discord**: https://discord.gg/railway
- **Railway Status**: https://status.railway.app
- **Your Dashboard**: https://railway.app/dashboard

---

## ✅ Quick Deploy Commands

```powershell
# 1. Initialize and commit
git init
git add .
git commit -m "Initial commit"

# 2. Create GitHub repo and push
git remote add origin https://github.com/YOUR_USERNAME/financial-management.git
git push -u origin main

# 3. Deploy to Railway (using dashboard)
# Visit https://railway.app → New Project → Deploy from GitHub

# 4. Done! Your app is live! 🎉
```

---

## 🎉 What You'll Get

After deployment, your app will have:

✅ **Public HTTPS URL** (e.g., https://your-app.up.railway.app)  
✅ **Automatic SSL certificate**  
✅ **Auto-deploys** on Git push  
✅ **Environment variables** management  
✅ **Logs and monitoring**  
✅ **99.9% uptime**  
✅ **Zero-downtime deployments**  
✅ **Automatic restarts** on crashes  
✅ **CDN for static assets**  
✅ **Metrics dashboard**  

---

## 🚀 Next Steps After Deployment

1. ✅ Test all features on live URL
2. ✅ Share the URL with users
3. ✅ Monitor logs and metrics
4. ✅ Set up custom domain (optional)
5. ✅ Upgrade to PostgreSQL (recommended)
6. ✅ Enable monitoring alerts
7. ✅ Set up automated backups
8. ✅ Add more features and push updates

---

**Ready to deploy? Let's do it!** 🚂💨

*Your app will be live in just a few minutes!*
