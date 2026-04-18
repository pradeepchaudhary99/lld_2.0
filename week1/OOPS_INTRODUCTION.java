package week1;
import java.util.ArrayList;
import java.util.List;


class Base{
    Derived child;
    public Base(){
        System.out.println("Base class Constructor");
        child = new Derived();
    }
    void basic(){
        System.out.println("Base Class");
    }
    void useChild(){
        child.basic();
    }
}

class Derived extends Base{

    public Derived(){
        super();
        System.out.println("Derived class Constructor");
    }
    void derive(){
        System.out.println("derived Class");
    }
}

// main() --> Base() --> Derived --> 


public class OOPS_INTRODUCTION{
    public static void main(String[] args) {
        Base base = new Base();
        // base.useChild();

    }

}

