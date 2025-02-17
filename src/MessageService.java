import java.io.IOException;
import java.util.*;
public class MessageService {
    public void sendMessage(String message){
        try{
            //AppleScript
            String script = "tell application \"Messages\"\n" + "set targetBuddy to \"myBuddy\"\n" +
                    "send \"" + message + "\" to buddy targetBuddy\n" +
                    "end tell";


            Process process = Runtime.getRuntime().exec(new String[]{"/usr/bin/osascript", "-e", script});
            process.waitFor();
            System.out.println("Message sent");

        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}