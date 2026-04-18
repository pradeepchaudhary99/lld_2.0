package week1;

class PaymentServiceBad {

    public void pay(String type) {
        if (type.equals("UPI")) {
            System.out.println("Paying via UPI");
        } else if (type.equals("CREDIT_CARD")) {
            System.out.println("Paying via Credit Card");
        } else if (type.equals("PAYPAL")) {
            System.out.println("Paying via PayPal");
        }
        // Requirement: PhonePay
        else if(type.equals("PhonePay")){
            System.out.println("Phonepay");
        }
    }
}
//everytime you add something to this class, you are risking the existing tested and working code.



//Fix
interface PaymentMethod {
    void pay();
}

class UpiPayment implements PaymentMethod {
    public void pay() {
        System.out.println("Paying via UPI");
    }
}

class CreditCardPayment implements PaymentMethod {
    public void pay() {
        System.out.println("Paying via Credit Card");
    }
}

class PaypalPayment implements PaymentMethod {
    public void pay() {
        System.out.println("Paying via PayPal");
    }
}

class PhonePay implements PaymentMethod{
    public void pay() {
        System.out.println("paying using phonePay");
    }
}

class PaymentServiceGood {

    public void processPayment(PaymentMethod paymentMethod) {
        paymentMethod.pay();
    }
}

public class OpenClosedPrinciple_Demo {
    public static void main(String[] args) {
        PaymentServiceGood paymentServiceGood = new PaymentServiceGood();
        paymentServiceGood.processPayment(new CreditCardPayment());
    }
}
