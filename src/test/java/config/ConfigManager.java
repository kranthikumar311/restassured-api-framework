package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static 
    {
        try (InputStream input = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config.properties"))
        {

            if (input == null) 
            {
                throw new RuntimeException("config.properties not found in src/test/resources/");
            }
            properties.load(input);

        } 
        catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getEnvironment() 
    {
        return properties.getProperty("environment", "dev");
    }

    public static String getBaseURI()
    {
        String env = getEnvironment();
        return properties.getProperty(env + ".baseURI");
    }

    public static String getProperty(String key) 
    {
        return properties.getProperty(key);
    }
}