package com.isulan.financial.desktop.util;

import com.isulan.financial.model.User;
import com.isulan.financial.repository.UserRepository;
import com.isulan.financial.repository.TransactionRepository;
import com.isulan.financial.service.UserService;
import com.isulan.financial.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.isulan.financial.FinancialManagementApplication;

/**
 * Utility class to access Spring beans from Swing application
 * Provides database connectivity for desktop GUI
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
public class SpringContextUtil {

    private static ConfigurableApplicationContext context;
    private static User currentUser;

    /**
     * Initialize Spring context for desktop application
     */
    public static void initializeContext() {
        if (context == null) {
            context = SpringApplication.run(FinancialManagementApplication.class, 
                    "--spring.main.web-application-type=none");
        }
    }

    /**
     * Get Spring bean by class
     * 
     * @param <T> Bean type
     * @param beanClass Class of the bean
     * @return Bean instance
     */
    public static <T> T getBean(Class<T> beanClass) {
        if (context == null) {
            initializeContext();
        }
        return context.getBean(beanClass);
    }

    /**
     * Get UserService bean
     * 
     * @return UserService instance
     */
    public static UserService getUserService() {
        return getBean(UserService.class);
    }

    /**
     * Get TransactionService bean
     * 
     * @return TransactionService instance
     */
    public static TransactionService getTransactionService() {
        return getBean(TransactionService.class);
    }

    /**
     * Get UserRepository bean
     * 
     * @return UserRepository instance
     */
    public static UserRepository getUserRepository() {
        return getBean(UserRepository.class);
    }

    /**
     * Get TransactionRepository bean
     * 
     * @return TransactionRepository instance
     */
    public static TransactionRepository getTransactionRepository() {
        return getBean(TransactionRepository.class);
    }

    /**
     * Set currently logged in user
     * 
     * @param user Current user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Get currently logged in user
     * 
     * @return Current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Clear current user (logout)
     */
    public static void clearCurrentUser() {
        currentUser = null;
    }

    /**
     * Shutdown Spring context
     */
    public static void shutdown() {
        if (context != null) {
            context.close();
            context = null;
        }
    }
}
