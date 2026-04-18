// State Interface
interface TrafficLightState {
    void change(TrafficLight context);
    String describe();
}

// Concrete States
class RedLightState implements TrafficLightState {
    @Override
    public void change(TrafficLight context) {
        System.out.println("🔴 Red → Green");
        context.setState(new GreenLightState());
    }
    
    @Override
    public String describe() {
        return "STOP";
    }
}

class GreenLightState implements TrafficLightState {
    @Override
    public void change(TrafficLight context) {
        System.out.println("🟢 Green → Yellow");
        context.setState(new YellowLightState());
    }
    
    @Override
    public String describe() {
        return "GO";
    }
}

class YellowLightState implements TrafficLightState {
    @Override
    public void change(TrafficLight context) {
        System.out.println("🟡 Yellow → Red");
        context.setState(new RedLightState());
    }
    
    @Override
    public String describe() {
        return "PREPARE TO STOP";
    }
}

// Context
class TrafficLight {
    private TrafficLightState state;
    
    public TrafficLight() {
        this.state = new RedLightState(); // Initial state
    }
    
    public void setState(TrafficLightState newState) {
        this.state = newState;
    }
    
    public void change() {
        state.change(this);
    }
    
    public String getStatus() {
        return state.describe();
    }
}

// Usage
public class TrafficLightDemo {
    public static void main(String[] args) {
        TrafficLight light = new TrafficLight();
        
        System.out.println(light.getStatus()); // STOP
        light.change(); // 🔴 Red → Green
        
        System.out.println(light.getStatus()); // GO
        light.change(); // 🟢 Green → Yellow
        
        System.out.println(light.getStatus()); // PREPARE TO STOP
        light.change(); // 🟡 Yellow → Red
        
        System.out.println(light.getStatus()); // STOP
    }
}