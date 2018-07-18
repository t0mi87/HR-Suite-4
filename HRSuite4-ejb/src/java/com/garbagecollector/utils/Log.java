
package com.garbagecollector.utils;
import java.io.IOException;
import java.util.logging.*;

public class Log {

    public static Logger logger = Logger.getLogger("fizetésemelés");

    static {
        try {
            FileHandler fileHandler = new FileHandler("files/log.txt", true);

            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        }
        catch (IOException | SecurityException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * INFO szintű logbejegyzést készítő metódus
     * @param log logfájlba írandó szöveg
     */
    public static void writeToLogFile(String log) {
        logger.log(Level.INFO, log);
    }

    /**
     * WARNING szintű logbejegyzést készítő metódus
     * @param log logfájlba írandó szöveg
     * @param ex kivétel objektum
     */
    public static void writeWarningToLogFile(String log, Exception ex) {
        logger.log(Level.WARNING, log, ex);
    }

    /**
     * ERROR szintű logbejegyzést készítő metódus
     * @param log logfájlba írandó szöveg
     * @param ex kivétel objektum
     */
    public static void writeErrorToLogFile(String log, Exception ex) {
        logger.log(Level.SEVERE, log, ex);
    }

}
