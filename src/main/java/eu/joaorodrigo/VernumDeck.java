package eu.joaorodrigo;//package eu.joaorodrigo.vernumdeckspring;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTablesJNI;
import edu.wpi.first.util.CombinedRuntimeLoader;
import edu.wpi.first.util.WPIUtilJNI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.UnknownHostException;
import java.util.Arrays;

@SpringBootApplication
public class VernumDeck implements CommandLineRunner {

    public static NetworkTableInstance table;

    private static Logger LOG = LoggerFactory
            .getLogger(VernumDeck.class);

    public static void main(String[] args) {
        SpringApplication.run(VernumDeck.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        NetworkTablesJNI.Helper.setExtractOnStaticLoad(false);
        WPIUtilJNI.Helper.setExtractOnStaticLoad(false);

        CombinedRuntimeLoader.loadLibraries(VernumDeck.class, "wpiutiljni", "ntcorejni");

        int teamNumber = 9611;
        String ip = teamNumberToIpAddress(teamNumber);
//


        table = NetworkTableInstance.getDefault();
        table.startClient4("vernum");
        table.setServer("127.0.0.1");

        Arrays.stream(table.getTopics()).forEach(System.out::println);

        while(true) {
            Thread.sleep(1000);
        }

//        table.close();
    }

    public static String teamNumberToIpAddress(int teamNumber) throws UnknownHostException {
        char[] team = (teamNumber + "").toCharArray();
        String compositeString = "";
        for(int i=0; i<team.length; i++) {
            if(i == 2) compositeString+=".";
            compositeString+=team[i];
        }
        return "10."+compositeString+".2";
    }
}
