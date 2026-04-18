package week1;

/*
High-level modules should not depend on low-level modules.
Both should depend on abstractions.
*/

// class EmailService {
//     public void sendEmail(String message) {
//         System.out.println("Sending EMAIL: " + message);
//     }
// }

// class OrderService {
//     private EmailService emailService = new EmailService(); // tightly coupled

//     public void placeOrder() {
//         System.out.println("Order placed");
//         emailService.sendEmail("Order confirmation");
//     }
// }

/*
Problems
    TightCoupling
    OrderService directly depends on EmailService
    If tomorrow you want:
    SMS
    Push Notification
    → You must modify OrderService
*/


// GOOD Design
interface NotificationService {
    void send(String message);
}

//Low Level Modules depend on Abstraction
class EmailService implements NotificationService {
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

class SMSService implements NotificationService {
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class WHATSAPPService implements NotificationService {
    public void send(String message) {
        System.out.println("Sending WHATSAPP: " + message);
    }
}



// High-level module depends on abstraction
class OrderServiceGood {
    private NotificationService notificationService;

    // Constructor Injection
    public OrderServiceGood(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder() {
        System.out.println("Order placed");
        notificationService.send("Order confirmation");
    }
}


public class DepedencyInversionPrinciple_Demo {
    public static void main(String[] args) {
        OrderServiceGood orderServiceGood = new OrderServiceGood(new SMSService());
        orderServiceGood.placeOrder();
    }
}
