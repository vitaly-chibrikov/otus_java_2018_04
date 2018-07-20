package ru.otus.logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * %d{yyyy-MM-dd HH:mm:ss} = Date and time format, refer to SimpleDateFormat JavaDoc.
 * %-5p = The logging priority, like DEBUG or ERROR. The -5 is optional, for the pretty print format.
 * %c{1} = The logging name we set via getLogger(), refer to log4j PatternLayout guide.
 * %L = The line number from where the logging request.
 * %m%n = The message to log and line break.
 * <p>
 * Log message examples :
 * <p>
 * 2017-07-02 20:52:39 DEBUG className:200 - This is debug message
 * 2017-07-02 20:52:39 DEBUG className:201 - This is debug message2
 */
public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        if(logger.isDebugEnabled()){
            logger.debug("This is debug message");
        }

        if(logger.isInfoEnabled()){
            logger.info("This is info message");
        }

        logger.warn("This is warn message");
        logger.error("This is error message");
        logger.fatal("This is fatal message");
    }
}
