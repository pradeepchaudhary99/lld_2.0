package week2;

// Group of projects
// Cars
// hatchback, suv, sedan
// Trucks
// trailer, heavy, jcb

//

// Step-1 Abstract products
interface Car{
    void drive();
}

interface Truck {
    void load();
}


// Concreate products
class TataPetrolCar implements Car{
    public void drive(){
        System.out.println("Driving petrol car");
    }
}
class TataELectricCar implements Car{
    public void drive(){
        System.out.println("Driving Electric car");
    }
}

class TataPetrolTruck implements Truck{
    public void load(){
        System.out.println("loading petrol truck");
    }
}

class TataElectricTruck implements Truck{
    public void load(){
        System.out.println("loading Electric truck");
    }
}

interface VechicleFactory{
    Car createCar();
    Truck createTruck();
}

class PetrolVehicleFactory implements VechicleFactory{

    public Car createCar(){
        return new TataPetrolCar();
    }
    public Truck createTruck() {
        return new TataPetrolTruck();
    }
}


class ElectricVechicleFactory implements VechicleFactory{

    public Car createCar(){
        return new TataELectricCar();
    }
    public Truck createTruck() {
        return new TataElectricTruck();
    }
}






public class TataMotors {
    public static void main(String[] args) {
        VechicleFactory factory;
        factory = new ElectricVechicleFactory();


        
        Car car = factory.createCar();
        Truck truck = factory.createTruck();
    }