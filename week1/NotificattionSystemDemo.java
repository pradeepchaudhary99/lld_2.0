

// Notificatiion syustem 

interface Notification{
    void send();
}

class SMSNotification implements Notification{

}

class EmailNotification implements Notification{

}


class NotificationDispatcher{
    
    void sendNotification(String message, Notification channel){
        channel.send(message);
    }
}