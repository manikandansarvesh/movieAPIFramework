package runners;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Reportgenerator {


    // get generated cucumber json reports names
    public static List<String> getfilenames() {
        File folder = new File("target/cucumber-reports");
        File[] listOfFiles = folder.listFiles();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if (listOfFiles[i].getName().contains(".json"))
                    names.add(listOfFiles[i].getName());
            }
        }
        return names;
    }


    // To generate HTML reports from the json files
    public static void main(String[] args) {
        File reportOutputDirectory = new File("output");
        List<String> jsonfiles = new ArrayList<>();
        List<String> names = getfilenames();
        for (String string : names) {
            jsonfiles.add("target/cucumber-reports/" + string);
        }
        String projectName = "cucumberProject";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        ReportBuilder reportBuilder = new ReportBuilder(jsonfiles, configuration);
        reportBuilder.generateReports();
    }
}

