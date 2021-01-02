package tue.horse.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigPropertiesMain {

//    public static ConfigPropertiesMain instance;

    public Properties main(String configFileName)  {

        InputStream inputStream = null;
        Properties props = new Properties();

        try {

//            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);

            if (inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + configFileName + "' not found in the classpath");
            }

            inputStream.close();
            }
            catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return props;
    }
}
