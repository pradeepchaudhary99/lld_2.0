package week5;

interface Observable{
    void addObserver();
    void removeObserver();
    void notifyObserver();
}

class Zomato implements Observable{

    List<Observer> observers;
    
    public Zomato(){
        observers = new ArrayList<>();
    }
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.add(o);
    }
    @Override
    public void notifyObserver() {
        for(Observer o : observers){
            o.notifyThem();
        }
    }
}

interface Observer{
    void notifyThem();
}

class David implements Observer{

}

class Payal implements Observer{
    
}


public class ZomatoObserver {
    
}
