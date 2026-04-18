package week1;
class Vehicle {
    public void startEngine() {
        System.out.println("Engine started");
    }

    public void fuelUp() {
        System.out.println("Fueling vehicle");
    }

    public void openTrunk() {
        System.out.println("Opening trunk");
    }
}

class Car extends Vehicle {
    public void startEngine() {
        System.out.println("Car engine started");
    }

    public void fuelUp() {
        System.out.println("Fueling car");
    }

    public void openTrunk() {
        System.out.println("Opening car trunk");
    }
}

class ElectricCar extends Vehicle {
    @Override
    public void fuelUp() {
        throw new UnsupportedOperationException("No fuel in electric car"); 
    }
}

class Bike extends Vehicle {
    @Override
    public void openTrunk() {
        throw new UnsupportedOperationException("No trunk in bike"); 
    }
}

class Bicycle extends Vehicle {
    @Override
    public void startEngine() {
        throw new UnsupportedOperationException("No engine"); 
    }

    @Override
    public void fuelUp() {
        throw new UnsupportedOperationException("No fuel"); 
    }

    @Override
    public void openTrunk() {
        throw new UnsupportedOperationException("No trunk");
    }
}
public class LiskovSubsitutionPrinciple_Demo {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.fuelUp();
    }
}

