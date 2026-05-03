

/*
ElevatorSystem:
system manages a set of elevators assign them request, control them, move them, stop them..

Functional: 
 - ElevatorSystem should be able to manage multiple Elevators 
 - User can Request an elevator from any floor and from elevator as well
 - ElevatorSystem should use different ElevatorSelection Strategy
 - ElevatorSystem should not to and fro... and process request in SCAN method.. 

    ------ Out of Scope will cover if needed ----
        -- Floor
             List<ElevatorDoor>
        Request:
            Internal
            External
        AdminController
        EmergencyHandling


Non Functional Requirements:
    - Thread Safe
    - Elevators running independent 
    - Extensible --> Algorithmic
    - Reliability
        --> Handling the edge cases related to security, power, doors,weights.. out of scope..

Core Entities:
    - Elevator
    - ElevatorSystem
    - Request
        floorId
    - ElevatorSelectionStrategy
        - NearestElevator(Code)
        - EmptyFirstElevator(Code)

*/

import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;

class Request{
    int floorId;
}

enum Direction{
    UP, DOWN, IDLE
}
interface ElevatorSelectionStrategy{
    Elevator getElevator(List<Elevator> elevators);
}

class NearestElevator implements ElevatorSelectionStrategy{
    @Override
    public Elevator getElevator(List<Elevator> elevators) {
        //Logic..
        // 
    }
}


class Elevator implements Runnable{
    int elevatorId;
    int currentFloor;
    Direction direction;
    PriorityQueue<Integer> upStops; // minimum
    PriorityQueue<Integer> downStops; //maximum
    
    public Elevator(int id){
        upStops = new PriorityQueue<>();
        downStops = new PriorityQueue<>((a,b)->b-a);
        elevatorId = id;
        currentFloor = 1;
        direction = Direction.IDLE;
    }

    // Elevator...
    // submitRequest, addRequest
    // 
    void addSubmit(Request request){
        if(request.floorId > currentFloor){
            upStops.add(request.floorId);
        }else{
            downStops.add(request.floorId);
        }
    }
    void moveElevator(Direction direction){
        System.out.println("Elevator is moving " +direction);
        if(direction == Direction.UP)
            currentFloor++;
        else
            currentFloor--;
    }
    void processRequest(){
        System.out.println("Lift stopping at floor "+currentFloor);
    }

    @Override
    public void run() {

        while(!upStops.isEmpty()){
            if(upStops.peek() == currentFloor){
                processRequest();
                upStops.poll();
            }
            moveElevator(Direction.UP);
        }

        if(upStops.isEmpty()){
            //change the direction
            if(downStops.isEmpty())
                direction = Direction.IDLE;
            else
                direction = Direction.DOWN;
        }

        while(!downStops.isEmpty()){
            if(downStops.peek() == currentFloor){
                processRequest();
                downStops.poll();
            }
            moveElevator(Direction.DOWN);
        }

        if(downStops.isEmpty()){
            //change the direction
            if(upStops.isEmpty())
                direction = Direction.IDLE;
            else
                direction = Direction.UP;
        }
    }
}


class ElevatorSystem{
    List<Elevator> elevators;
    List<Thread> threads;
    ElevatorSelectionStrategy strategy;

    public ElevatorSystem(int numberOfElevators, ElevatorSelectionStrategy strategy){
        for(int i = 0; i < numberOfElevators; i++){
            Elevator elevator = new Elevator(i);
            elevators.add(elevator);
            Thread thread = new Thread(elevator);
            threads.add(thread);
            thread.start();
        }
    }

    public void submitRequest(Request request){
        Elevator elevator = strategy.getElevator(elevators);
        elevator.addSubmit(request);
    }
}
