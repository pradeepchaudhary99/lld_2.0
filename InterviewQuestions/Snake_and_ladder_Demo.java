// package InterviewQuestions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

/*
Snake and ladder game
Game
Board
Player
Dice
Snakes_and_ladder
Driver
Jump
*/

class Player{
    String name;
    int currentPosition;
    public Player(String name){
        this.name = name;
        this.currentPosition = 1;
    }
    void setPosition(int position){
        this.currentPosition = position;
    }
}

class Board{
    // last Index
    int endPosition;
    int startPosition;
    private Map<Integer, Integer> snakes_and_ladder;
    public Board(){
        endPosition = 100;
        startPosition = 1;
        snakes_and_ladder = new HashMap<>();
        // generate Random Snakes and Ladder
        int snake_count = 2;
        int ladder_count = 2;
        while(snake_count > 0 || ladder_count > 0){
            int start = (int)(Math.random()*100);
            int end = (int)(Math.random()*100);
            if(start > end && snake_count > 0){
                // its a snake add to the map
                snakes_and_ladder.put(start, end);
                snake_count--;
            }else if(start < end && ladder_count > 0){
                snakes_and_ladder.put(start, end);
                ladder_count--;
            }
        }
    }

    int getNextPosition(int current){ // position+ dice --> current ---> check if ladder or snake is present and return the updated value..
        if(snakes_and_ladder.containsKey(current)){
            if(snakes_and_ladder.get(current) > current){
                System.out.println("Ladder "+ current +" To "+snakes_and_ladder.get(current));
            }else{
                System.out.println("Snake Bit :) "+ current +" To "+snakes_and_ladder.get(current));
            }
            return snakes_and_ladder.get(current);
        }else{
            return current;
        }
    }
}

class Game{
    Board board;
    Queue<Player> players;
    Dice dice;
    public Game(Player p1, Player p2){
        board = new Board();
        dice = new Dice();
        players = new ArrayDeque<>();
        players.add(p1);
        players.add(p2);
    }

    void startGame(){
        //Game Loop
        int turn = 0;

        while(true){
            Player current = players.get(turn);
            // System.out.println(current.name + " your turn please roll the dice");
            int diceValue = dice.rollDice();
            System.out.println(current.name + " dice value : " + diceValue);
            int nextPosition = diceValue + current.currentPosition;
            System.out.println(current.name +" next Position --> "+nextPosition);
            nextPosition = board.getNextPosition(nextPosition);
            current.currentPosition = nextPosition;
            if(nextPosition >= board.endPosition){
                System.out.println(current.name +" won the game, congratulations");
                return;
            }
            turn = 1-turn;
        }
    }
}

class Dice{
    Random random;
    public Dice(){
        random = new Random();
    }
    int rollDice(){
        return random.nextInt(6) +1; // [0,5]+1 --> 1,6
    }
}

public class Snake_and_ladder_Demo {
    public static void main(String[] args) {
        Game game = new Game(new Player("Pradeep"), new Player("Tanishi"));
        game.startGame();
    }
}
