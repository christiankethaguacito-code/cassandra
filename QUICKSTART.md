# Quick Start Guide
## Personal Financial Management System

### 🚀 Get Started in 3 Steps

#### Step 1: Build the Project
```powershell
cd C:\Users\USER\OneDrive\Desktop\NEWBUSINESS
mvn clean install
```

#### Step 2: Choose Your Interface

**Option A - Web Application:**
```powershell
mvn spring-boot:run
```
Then open: http://localhost:8080

**Option B - Desktop Application:**
```powershell
mvn exec:java -Dexec.mainClass="com.isulan.financial.desktop.DesktopApplication"
```

#### Step 3: Register & Login
1. Click "Register" button
2. Fill in your details:
   - Full Name: Juan Dela Cruz
   - Email: juan@example.com
   - Password: password123 (min 6 chars)
3. Login with your credentials

---

### 📱 Quick Feature Tour

#### Add Your First Transaction
**Web:**
1. Click "Dashboard" → "Add Transaction"
2. Select Type: Income or Expense
3. Category: Salary, Food, etc.
4. Amount: 5000
5. Click "Add Transaction"

**Desktop:**
1. Go to "Transactions" tab
2. Click "Add Transaction"
3. Fill in details
4. Click "Save"

#### View Your Dashboard
- See total income, expenses, and balance
- View charts showing spending by category
- Track your financial health

#### Update Your Profile
- Go to "Profile" section
- Update your name and email
- Change your password securely

---

### 💡 Pro Tips

1. **Both Apps Share Data**: Add a transaction in the web app, see it in the desktop app!

2. **Categories**: Use consistent categories like:
   - Income: Salary, Business, Freelance
   - Expenses: Food, Transportation, Utilities, Entertainment

3. **Regular Updates**: Update your transactions daily for accurate tracking

4. **Security**: Always logout when using shared computers

---

### 🆘 Need Help?

**Common Issues:**

1. **"Port 8080 already in use"**
   - Another app is using port 8080
   - Change port in `application.properties` or stop other app

2. **"Database locked"**
   - Close all instances of the app
   - Restart the application

3. **"Login failed"**
   - Check email and password
   - Passwords are case-sensitive
   - Register if you're a new user

---

### 📚 Learn More

- Read full `README.md` for detailed documentation
- Check JavaDoc for API reference
- Explore the code for customization

---

**Happy Financial Management! 💰**
