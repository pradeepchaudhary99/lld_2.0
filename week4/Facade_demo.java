


class Engine{

}

class TireController{

}

class FuelInjection{

}

class SafetyController{

}



class CarController{
    Engine engine = new Engine();
    TireController tireController = new TireController();
    FuelInjection injection = new FuelInjection();

    void start(){
        engine.run();
        tireController.run();
        injection.start();
    }
    
}



public class Facade_demo {

    public static void main(String[] args) {
        
        CarController car = new CarController();
        car.start();

    }
}
