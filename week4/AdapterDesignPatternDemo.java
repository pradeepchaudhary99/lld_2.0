package week4;


interface PaymentProcessor{
    public void pay(int amount);
}

class PhonePay implements PaymentProcessor{
    @Override
    public void pay(int amount){
        System.out.println("P{ayment via phonepay is done");
    }
}

class RazorPay{
    public void payment(int amount){
        System.out.println("Process rzp payment");
    }
}


class RazorPayAdapter implements PaymentProcessor{
    RazorPay rzp;
    public RazorPayAdapter(RazorPay rzp){
        this.rzp = rzp;
    }
    @Override
    public void pay(int amount){
        rzp.payment(amount);
    }
}

public class AdapterDesignPatternDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PhonePay();
        processor.pay(100);   


        PaymentProcessor rzp = new RazorPayAdapter(new RazorPay());
        rzp.pay(200);


    }

}
