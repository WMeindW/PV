package cz.daniellinda.pv.Service;

import cz.daniellinda.pv.Log.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * The type Service.
 */
@Component
public class Service {
    /**
     * The constant state.
     */
    public static String state = "Starting";

    /**
     * Start boolean.
     *
     * @return the boolean
     */
    @PostConstruct
    public static boolean start() {
        File dir = new File("/etc/pv/");
        dir.mkdirs();
        Logger.writeLog("Initiated start");
        state = "Started";
        Logger.writeLog("Started");
        return true;
    }

    /**
     * Stop.
     */
    @PreDestroy
    public static void stop() {
        state = "Stopping";
        Logger.writeLog("Initiated shutdown");
        state = "Stopped";
        Logger.writeLog("Stopped");
    }
}
