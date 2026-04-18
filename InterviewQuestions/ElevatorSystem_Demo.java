package InterviewQuestions;




//LLD Framework:
/*
    Requirements
    Entities
    Class Design
    Implementation
    Extensibility
*/


/*
Design an elevator control system for a building. The system
should handle multiple elevators, floor requests, and move elevators..

1. Primary Capabilities
    how many elevators and floors? Fixed or configurable?
    are Hall calls just up and down or do they choose a floor?
    can passengers select multiple destination floors inside the elevator?
    The problem says cars should "Move efficiently"? what does this mean?

2. Error Handling
    what about invalid floor requests?
    what if someone requests the floor they're already on?

3. Scope Boundaries
    capacity, weight limits, door mechanics, emergency stops?
    Simulation (step/tick) or actual hardware control software?



Requirements:
1. System manages 3 elevators serving 10 floors (0-9)
2. users can request an elevator from any floors. System decices which elevtor dispatch
3. Once inside, user can sleect one or more destination floors
4. simulation runs in discrete time steps 
5. elevator stops come in two types:
    - Hall Calls: Request from a floor with directio(UP or DOWN)
    - Destination: Request from inside elevator (no direction specified)

6. System handles multiple concurrent pickup requests across floors
7. Invalid requests should be rejected (return false)
8. requests for the current floor are treated as a no-op.


Out of Scope:
- weight capacity and passenger limits
- Door open/close mechanics
- Emergency stop functionality

Entities:
- Elevator (class)
- Floor (number)
- Request (class, need direction and potentially the floor)
-ElevatorController (class, main entry point, receives hall calls, orchatrator)
- 


*/

/* 
class ElevatorController{
    - elevators: List<Elevator>
    + ElevatorController()
    + requestElevator(floor, direction) --> boolean
    + step() --> void 
}

class Elevator{
    - floor: int
    - direction: Direction
    - requests: Set<Requests> // Handling duplicate requests

    + Elevator()
    + addRequests(request) --> Boolean
    + step() --> void() // step up/down
    + getFloor() --> int
    + getDirection() --> Direction

}  

class Request{
    // Hall Calls : Floor + Direction
    // Destination: Floor 
    floor : int
    type: RequestType
    + Request(floor, type)
    + getFloor() --> int
    + getType() --> RequestType
}

enum RequestType{
    PICKUP_UP
    PICKUP_DOWN
    DESTINATION
}


enum Direction{
    UP, DOWN, IDLE
}

# Implementation
# 1. Define the core logic
# 2. Consider the edge cases
*/

class ElevatorController{
    requestElevator(floor, PICKUP_UP | PICKUP_DOWN){
        // core logic
        //1. find the best elevator to handle this request
        //2. send the request to that elevator

        // Edge cases
        // 1. floor out of bounds --> throw error
        if(floor < 0 || floor > 9){
            return false;
        }
        request = Request(floor, type);
        best = selectBestElevator(request);
        return best.addRequests(request);
    }

    private selectBestELevator(request){
        /*
            core logic:
                try to find the elevator moving toward
                if non, try idle elevators nearest
                pick the nearest

            edge cases:
        */
        best = findMovingToward(request)
        if best != null
            return best
        
        best = findNeareastIdle(request.getFloor())
        if best != null
            return best
        
        return findNearest(request.getFloor())
    }

    private findMovingToward(request):
        // Core logic: scan elevators going that diection
        // keep track of closest
        // return closest

        floor = request.getFloor()
        direction = request.getType() == PICKUP_UP ? UP : DOWN

        nearest = null
        minDistance = MAX_VLAUE

        for e in elevators:
            if e.getDirection() != direction:
                continue
            
            if(direction == UP && e.getFloor() > floor) || (direction == DOWN && e.getFloor() < floor)
                continue
            

            
            distance = abs(e.getFloor() - floor)
            if distance < minDistance 
                minDistance = distance 
                nearest 



}


public class ElevatorSystem_Demo {
    
}
