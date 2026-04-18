package week5;

// ATM machine has multiple states.... NoCardState, Card_Inserted, Cash_Dipensing

interface ATMMachineBehaviours{
    void withdraw();
    void insertCard();
    void cancelTransaction();
}

class NoCardState implements ATMMachineBehaviours{
    
}


public class ATMMachineStateDemo {
    
}
