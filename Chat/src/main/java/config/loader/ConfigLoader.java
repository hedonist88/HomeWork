package config.loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader configLoader;
    private String configFile;
    private Properties props;

    public ConfigLoader(String configFile) {
        props = new Properties();
        this.configFile = configFile;
        loadProps();
    }

    private void loadProps(){
        try (InputStream input = new FileInputStream(configFile)){
            props.load(input);
        } catch (IOException e) {
            System.out.println("Config file not found");
            e.printStackTrace();
        }
    }

    public static ConfigLoader getConfig(String configFile){
        if(configLoader == null){
            configLoader = new ConfigLoader(configFile);
        }
        return configLoader;
    }

    public Properties getProps() {
        return props;
    }



}
