package ru.job4j.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class UsageLog4j {

    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        PropertyConfigurator.configure("./src/main/resources/log4j.properties");
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        System.out.println(System.getProperty("java.class.path"));
        // /Users/lan/IdeaProjects/job4j_design/target/classes:
        // /Users/lan/.m2/repository/junit/junit/4.13.2/junit-4.13.2.jar:
        // /Users/lan/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:
        // /Users/lan/.m2/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar
    }
}
