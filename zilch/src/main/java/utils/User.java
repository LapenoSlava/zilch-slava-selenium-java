package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class User {
    private String email;
    private String password;
    private String userName;

    // Constructor to load properties
    public User() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("user.properties")) {
            if (input == null) {
                return;
            }
            properties.load(input);

            this.email = properties.getProperty("email");
            this.password = properties.getProperty("password");
            this.userName = properties.getProperty("userName");
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load properties file", ex);
        }
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public String userName() {
        return userName;
    }
}