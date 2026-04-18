package week1;
import java.time.format.SignStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Symbol{
    X,O
}

class Player{
    private String name;
    private Symbol mark;
    public Player(String name, Symbol mark){
        this.name = name;
        this.mark = mark;
    }
    public Symbol getMark(){
        return mark;
    }
    public String getName(){
        return this.name;
    }
}

class Board{
    Symbol [][] board;
    private int total_symbol;
    public Board(){
        board = new Symbol[3][3];
        total_symbol = 0;    
    }
    public boolean makeMove(int x, int y, Player player){

        if(x < 0 || y < 0 || x >= 3 || y >= 3 || board[x][y] != null){
            System.out.println("Invalid Move, try again");
            return false;
        }
        board[x][y] = player.getMark();
        total_symbol++;
        return true;
    }
    
    public int getTotalCount(){
        return total_symbol;
    }

    public boolean checkWin(Symbol symbol){
        // check the winning condition.
        for(int i = 0; i < 3; i++){
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][2] == symbol){
                return true;
            }
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[2][i] == symbol){
                return true;
            }
        }
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == symbol){
            return true;
        }
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] == symbol){
            return true;
        }
        return false;
    }
    public boolean checkWinOptimized(Symbol symbol){
        // check the winning conditi
    }

    public void displayBoard(){
        System.out.println("Board status");
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == null){
                    System.out.print("   ");
                }else if(board[i][j] == Symbol.X){
                    System.out.print(" X ");
                }else{
                    System.out.print(" O ");
                }
            }
            System.out.println();
        }
    }

}

class Game{
    private Board board;
    private List<Player> players;
    public Game(Player p1, Player p2){
        board = new Board();
        players = new ArrayList();
        players.add(p1);
        players.add(p2);
    }

    void start(){

        Scanner sc = new Scanner(System.in);
        int index = 0;
        while(true){
            board.displayBoard();
            Player current = players.get(index);
            System.out.println(current.getName() +" your Turn: enter position ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(board.makeMove(x, y, current)){
                if(board.checkWin(current.getMark())){
                    board.displayBoard();
                    System.out.println(current.getName() +" WON the MATCH");
                    return;
                }else if(board.getTotalCount() >= 9){
                    System.out.println("Game is Draw, play again");
                    return;
                }
            }else{
                //keep asking the player to make move
                continue;
            }
            index = 1 - index;
        }
    }
}


public class TicTacToeDemo {
    public static void main(String[] args) {
        Player p1 = new Player("Pradeep", Symbol.X);
        Player p2 = new Player("Tanishi", Symbol.O);
        Game game = new Game(p1, p2);
        game.start();
    }
}


/*
3x3 game with 2 players, with symbol X and O,
if diagonal or row or column matches all three same symbol that player will win

*/