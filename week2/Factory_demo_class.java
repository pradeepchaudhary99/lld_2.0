

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


class NotificationFactory{

    private NotificationFactory(){}
    private static class FactoryHolder{
        private static final NotificationFactory INSTANCE = new NotificationFactory();
    }

    public static NotificationFactory getInstance(){
        return FactoryHolder.INSTANCE;
    }
    Notification getNotificationInstance(String type){
        if(type.equals("SMS")){
            return new SMSNotification();
        }
        if(type.equals("whatsapp")){
            return new WhatsappNotification();
        }
        return new SMSNotification();
    }
}


public class Factory_demo_class {
    public static void main(String[] args) {
        NotificationFactory notificationFactory = NotificationFactory.getInstance();
        Notification whatsapp = notificationFactory.getNotificationInstance("whatsapp");
        whatsapp.sendNotification();
    }
}
