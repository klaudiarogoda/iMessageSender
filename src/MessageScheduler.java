import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MessageScheduler {
    private MessageService messageService;
    private String [] messages;
    int daysSent;


    public MessageScheduler(MessageService messageService, String[] messages){
        this.messageService=messageService;
        this.messages=messages;
    }

    public void scheduleMessage(){
        ScheduledExecutorService scheduler= Executors.newSingleThreadScheduledExecutor();
        long delay=calculateDelay(8);

        scheduler.scheduleAtFixedRate(()->{
            if(daysSent>=3) {
                stopScheduler(scheduler);
                return;
            }
            int randomNumber=(int) (Math.random() * messages.length);
            String todaysMessage=messages[randomNumber];
            System.out.println("Today's message: " + todaysMessage);
            messageService.sendMessage(todaysMessage);
            daysSent++;
        },delay, TimeUnit.DAYS.toMillis(1),TimeUnit.MILLISECONDS);
    }
    public long calculateDelay(int hour){
        Calendar currentTime=Calendar.getInstance();

        Calendar targetTime = Calendar.getInstance();
        targetTime.set(Calendar.HOUR_OF_DAY, hour);
        targetTime.set(Calendar.MINUTE, 0);
        targetTime.set(Calendar.SECOND, 0);
        targetTime.set(Calendar.MILLISECOND, 0);

        if(currentTime.after(targetTime)){
            targetTime.add(Calendar.DATE, 1);
        }
        long delay=targetTime.getTimeInMillis()-currentTime.getTimeInMillis();
        return delay;
    }

    public void stopScheduler(ScheduledExecutorService scheduler){
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(30, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }

    }
}