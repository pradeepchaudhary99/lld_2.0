

//Simple Factory volating open closed principle.

interface Notification{
    public void sendNotification();
}
class SMSNotification implements Notification{
    @Override
    public void sendNotification(){
        System.out.println("Sending SMS notification");
    }
}

class WhatsappNotification implements Notification{
    @Override
    public void sendNotification(){
        System.out.println("Sending whatsapp notification");
    }
}

interface NotificationFactory{
    Notification createNotification();
}

class SMSFactory implements NotificationFactory{
    public Notification createNotification(){
        System.out.println("Notification created for SMS");
        return new SMSNotification();
    }
}

class WhatsappFactory implements NotificationFactory{
    public Notification createNotification(){
        System.out.println("Notification created for whatsapp");
        return new WhatsappNotification();
    }
}

class SlackNotification implements Notification{
    public void sendNotification(){
        System.out.println("Sending slack notification");
    }
}

class SlackFactory implements NotificationFactory{
    public Notification createNotification(){
        System.out.println("Notification created for Slack");
        return new SlackNotification();
    }
}


public class AdvanceFactory_demo_class {
    public static void main(String[] args) {
        NotificationFactory notificationFactory = new WhatsappFactory();
        Notification whatsapp = notificationFactory.createNotification();
        whatsapp.sendNotification();
    }
}
