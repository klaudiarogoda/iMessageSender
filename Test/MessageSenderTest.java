import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class MessageSenderTest {


    @Test
    public void calculateDelay(){
        Scanner scanner=new Scanner(System.in);
        MessageService service=new MessageService();
        String [] messagesTest={"Test1", "Test2", "Test3", "Test4", "Test5"};
        MessageScheduler scheduler=new MessageScheduler(service, messagesTest);
        int targetHour = 20;
        long delay=scheduler.calculateDelay(targetHour);

        long expectedDelay= TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS);
        assertEquals(expectedDelay, delay);
    }

    @Test
    public void sendTestMessage(){
        MessageService service=new MessageService();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        service.sendMessage("Test");
        String output = baos.toString();

        assertTrue(output.contains("Message sent"));
    }

    @Test
    public void randomCalculation(){
        MessageService service=new MessageService();
        String [] messagesTest={"Test1", "Test2", "Test3", "Test4", "Test5"};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        MessageScheduler scheduler=new MessageScheduler(service, messagesTest);
        Set<String> set=new HashSet<>();
        for(int i=0; i<5; i++){
            scheduler.scheduleMessage();
            String output = baos.toString();
            set.add(output);
        }
        assertEquals(5, set.size());
    }
}