package com.algaworks.hospedagem.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PersistenceProperties {

    private static final String JDBC_URL = "JDBC_URL";

    private static final String JDBC_USER = "JDBC_USER";

    private static final String JDBC_PASSWORD = "JDBC_PASSWORD";

    public static Properties get() throws Exception {
        Properties props = new Properties();
        
        props.putAll(userHomeJdbcFile());
        
        props.putAll(systemEnv()); // Heroku
        
        props.putAll(javaPropertyJdbcFile());
        
        props.putAll(javaProperties());

        return props;
    }
    
    private static Properties userHomeJdbcFile() throws Exception {
        Properties props = new Properties();
        
        File fileProperties = new File(System.getProperty("user.home"), "jdbc.properties");
        
        if (fileProperties.exists()) {
            props.load(new FileInputStream(fileProperties));
        }
        
        return props;
    }
    
    private static Properties systemEnv() {
        Properties props = new Properties();
        
        if (System.getenv().containsKey(JDBC_URL)) {
            props.put("javax.persistence.jdbc.url", System.getenv(JDBC_URL));
        }
        
        if (System.getenv().containsKey(JDBC_USER)) {
            props.put("javax.persistence.jdbc.user", System.getenv(JDBC_USER));
        }
        
        if (System.getenv().containsKey(JDBC_PASSWORD)) {
            props.put("javax.persistence.jdbc.password", System.getenv(JDBC_PASSWORD));
        }
        
        return props;
    }
    
    private static Properties javaPropertyJdbcFile() throws Exception {
        Properties props = new Properties();
        
        if (!System.getProperties().containsKey("jdbc-file")) {
            return props;
        }
        
        File fileProperties = new File(System.getProperty("jdbc-file"));
        
        if (fileProperties.exists()) {
            props.load(new FileInputStream(fileProperties));
        }
        
        return props;
    }
    
    private static Properties javaProperties() {
        Properties props = new Properties();
        
        if (System.getProperties().containsKey(JDBC_URL)) {
            props.put("javax.persistence.jdbc.url", System.getProperty(JDBC_URL));
        }
        
        if (System.getProperties().containsKey(JDBC_USER)) {
            props.put("javax.persistence.jdbc.user", System.getProperty(JDBC_USER));
        }
        
        if (System.getProperties().containsKey(JDBC_PASSWORD)) {
            props.put("javax.persistence.jdbc.password", System.getProperty(JDBC_PASSWORD));
        }
        
        return props;
    }
}