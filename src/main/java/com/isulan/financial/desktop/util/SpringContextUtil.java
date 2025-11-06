package com.isulan.financial.desktop.util;

import com.isulan.financial.model.User;
import com.isulan.financial.repository.UserRepository;
import com.isulan.financial.repository.TransactionRepository;
import com.isulan.financial.service.UserService;
import com.isulan.financial.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.isulan.financial.FinancialManagementApplication;

public class SpringContextUtil {

    private static ConfigurableApplicationContext context;
    private static User currentUser;

    
    public static void initializeContext() {
        if (context == null) {
            context = SpringApplication.run(FinancialManagementApplication.class, 
                    "--spring.main.web-application-type=none");
        }
    }

    
    public static <T> T getBean(Class<T> beanClass) {
        if (context == null) {
            initializeContext();
        }
        return context.getBean(beanClass);
    }

    
    public static UserService getUserService() {
        return getBean(UserService.class);
    }

    
    public static TransactionService getTransactionService() {
        return getBean(TransactionService.class);
    }

    
    public static UserRepository getUserRepository() {
        return getBean(UserRepository.class);
    }

    
    public static TransactionRepository getTransactionRepository() {
        return getBean(TransactionRepository.class);
    }

    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    
    public static User getCurrentUser() {
        return currentUser;
    }

    
    public static void clearCurrentUser() {
        currentUser = null;
    }

    
    public static void shutdown() {
        if (context != null) {
            context.close();
            context = null;
        }
    }
}
