import java.util.*;
public class MessageSender {
    MessageService mservice=new MessageService();

    String[] messages={"Love you :*", "Missing you!", "Have a great day!", "Have a nice day babe :*", "Thinking of you!",
            "Can't wait to see you!", "You're the best! Love you :*", "You mean the world to me :*", "Good morning, love!",
            "Just wanted to say I love you :*"};

    MessageScheduler messageScheduler=new MessageScheduler(mservice, messages);

    public static void main(String[] args) {
        // Create an instance of MessageSender and call scheduleMessage
        MessageSender sender = new MessageSender();
        sender.scheduleMessage();
    }

    public void scheduleMessage(){
        messageScheduler.scheduleMessage();
    }

}
