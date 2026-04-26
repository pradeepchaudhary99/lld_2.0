// package week5_onwards_interview_ques.week_6;

import java.util.ArrayList;
import java.util.List;



enum OrderStatus{
    PLACED,
    PREPARING,
    OUT_FOR_DELIVERY,
    DELIVERED
}

class Order{
    List<Observer> observers;
    OrderStatus status;
    public Order(){
        observers = new ArrayList<>();
        status = OrderStatus.PLACED;
    }
    void addObserver(Observer o){
        observers.add(o);
    }
    void remove(Observer o){
        observers.remove(o);
    }

    void changeStatus(OrderStatus status){
        if(this.status != status){
            this.status = status;
            notifyObservers();
        }
    }


    void notifyObservers(){
        for(Observer o : observers){
            o.notify_users(status.toString());
        }
    }
}


interface Observer{
    void notify_users(String status);
}


class Pinky_PhoneApp implements Observer{

    @Override
    public void notify_users(String status) {
        System.out.println("Pinky: My Order status: "+status);
    }
}

class Dashboard implements Observer{

    @Override
    public void notify_users(String status) {
        System.out.println("Dashbord : My Order status: "+status);
    }
}

public class Observer_demo {
    public static void main(String[] args) {
        Order order = new Order();
        Pinky_PhoneApp pinky = new Pinky_PhoneApp();
        Dashboard dashboard = new Dashboard();
        order.addObserver(pinky);
        order.addObserver(dashboard);
        order.changeStatus(OrderStatus.OUT_FOR_DELIVERY);
        order.changeStatus(OrderStatus.DELIVERED);
    }
}
