package cz.daniellinda.pv.Service;

import cz.daniellinda.pv.Log.Logger;
import org.springframework.boot.CommandLineRunner;

import java.io.File;


public class Service implements CommandLineRunner {
    public static String state = "Starting";

    public static boolean start() {
        Thread shutdown = new Thread(Service::stop);
        Runtime.getRuntime().addShutdownHook(shutdown);
        File dir = new File("/etc/pv/");
        dir.mkdirs();
        Logger.writeLog("Initiated start");
        state = "Started";
        Logger.writeLog("Started");
        return true;
    }

    public static void stop() {
        state = "Stopping";
        Logger.writeLog("Initiated shutdown");
        state = "Stopped";
        Logger.writeLog("Stopped");
        Runtime.getRuntime().halt(0);
    }

    @Override
    public void run(String... args) {
        System.out.println(start());
    }
}
