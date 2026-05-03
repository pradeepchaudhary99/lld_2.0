/*
Notification System]
    Clients ---- Notification System --- Subscribers/Receivers..

Requirements:
    Functional Requirements;
        - system should support multiple channels of communication
            - SMS, Whatsapp, SMS, Push Notification
        - System should support user preferences
        - Support multiple types of notification
            --> Transcational
            --> Promotional
            --> Communication
        - should support notification scheduling
        - Should support notification priority
        - should support multiple clients pushing notification in the system async.

     ------ OUT of SCOPE --------
        Retry Mechanism (Decorator Design pattern)

    Non-Functional Requirements:
        - Atleast once delivery
        - thread-safe
        - 




Core Entities:
    NotificationService, Notification, NotificationChannels
    enum TypesOfNotification, Priority,
    User_preference_service
        --> User -- {Preference}
    
    NotificationDispatchingStrategy
        -- NotificationChannelFactory
        
    Source   ---> NotificationService 
             -->  sendNotification(Notification)
                  
    Source ---> Service ----> Subscribers

    Relationship:
    NotificationService
        - UserPreference
        - NotificationDispatchingStrategy(Notification, ChannelPreference, UserID)

    NotificationChannel
        - sendNotification()
    --> SMS
    --> EMAIL
    --> WHATSAPP

    Notification:   --> builder design pattern
        - id
        - message
        - userId
        - Priority
        - toBeScheduled
        - long_time

    NotifiationChannelFactory   --> to simply channel creation
    NotificationDispatchingStrategy

*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

enum TYPE{
    PROMOTIONAL, TRANSCATION, INFO
}
enum Priority{
    HIGH, LOW, MEDIUM, OPTIONAL
}

class Notification{
    int id;
    String message;
    enum Priority;
    TYPE type;
    String userId;
}

interface NotificationChannel{
    void sendNotification(Notification notification);
}

class SMS implements NotificationChannel{
    @Override
    public void sendNotification(Notification notification) {
    }
}
class WHATSAPP implements NotificationChannel{
    @Override
    public void sendNotification(Notification notification) {
    }
}
class EMAIL implements NotificationChannel{
    @Override
    public void sendNotification(Notification notification) {
    }
}

//Introduce Repository.. 
class UserPreferenceRepository{
    HashMap<String, Set<NotificationChannel>> preferences;
}

interface NotificationDispatchingStrategy{
    void dispatchNotification(Notification notification, Set<NotificationChannel> channel);
}

class NotificationDispatcher_by_Oklas implements NotificationDispatchingStrategy{


    ExecutorService service = Executors.newFixedThreadPool(5);
    @Override
    public void dispatchNotification(Notification notification, Set<Notification.NotificationChannel> channels) {

        //Synchronization sending installing..
        for(NotificationChannel channel : channels){
            service.submit(()->{
                channel.sendNotification(notification);
            });
        }


    }
}


class NotificationService{
    UserPreferenceRepository userPreferenceRepository;
    NotificationDispatchingStrategy strategy;
    public NotificationService(){

    }

    void sendNotification(Notification notification){
        Set<NotificationChannel> channels = userPreferenceRepository.get(notification.getUserId());
        strategy.dispatchNotification(notification, channels);
    }


}

public class Notification_System_demo {
    
}



/*
Thread 
Runnable how to use it
who to pass an object in thread.

how to make any object run in its thread
wait()
nofity()
ExecutorService --> ThreadPool
    --> Submit()




*/