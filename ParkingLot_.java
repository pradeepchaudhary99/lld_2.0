/*
Requirements:
    --> Parkinglot is a place, where people park there vechicles, of different 
        --> they multiple slots, vechile type, entry gates, exit gates, Ticket, Floors, SLots, 
        --> parkingLotManager... 

1. Functional Requirements:
   - User should be able to park/unpark there vechicle
   - parkingLot should manage multiple floors
   - a floor contains multiple slots of different types
   - Entry gates should generate a ticket and allocate a slot to the vechicle
   - exit gates should process the payment and vacant the slot 
   - should support multiple types of vechicles, slots types, etc..
   - different strategy for fees calculation
        --> HourPricing
        --> FixedPricing
   - paymentStretegy


Non-Functional Requirements:
    - Thread-safe
    - Extensibility


2.Identify the core entities and Relationships
    Vechicle
    ENUM VechicleType
    ParkingLotManager
    Floor
    ParkingSlot
    Ticket
    EntryGate
    ExitGate
    PaymentStrategy
        - UPI
        - CARD
    FeesCalculationStrategy
        - HourPricing
        - FixedPricing
    

    Relationship:
     ParkingLotManager
        - Map<VechileId, Ticket> ActiveTicket;
        - List<Floor>
        - EntryGate
        - ExitGate

        - parkMyVechile()
        - unParkVechile()
     Vehicle
        VehicleType

     EntryGate
        - IssuingTicket
        - 
     ExitGate
        - PaymentStrategy
        - FeesCalculationStrategy
        - unPark()
        - 
    
     ParkingSlot
        - isEmpty
        - Park()
        - unpark()

     Floor
        - List<ParkingSlot>

//Once Again Run the usecase...
// Vechicle --> ask ParkingLotManager to Park --> EntryGate --> Assign Ticket , ParkingSlot ---> got a slot --> Park the vechicel
    ---> add Active Ticket and we are done..

3.

    */


public class ParkingLot_ {
    
}
