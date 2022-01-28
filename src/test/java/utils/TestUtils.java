package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

public class TestUtils {


    public static LinkedHashMap<String, ArrayList<String>>getMapValues() throws Exception{
        Properties props = new PropertyManager().getProps();
        LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap();
        map.put("ar", new ArrayList<String>());
        String movieName = props.getProperty("movieName");
        System.out.println(movieName);
        map.get("ar").add(movieName);
        map.get("ar").add(" ");
        map.get("ar").add(" ");

        LinkedHashMap<String, ArrayList<String>> enMAp = new LinkedHashMap();
        enMAp.put("en", new ArrayList<String>());
        enMAp.get("en").add(movieName);
        enMAp.get("en").add(" ");
        enMAp.get("en").add(" ");
        map.putAll(enMAp);

        return map;
    }



    public Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }
}
