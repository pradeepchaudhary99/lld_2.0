import java.util.*;

enum Color {
    WHITE, BLACK
}

class Position {
    int row, col;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

abstract class Piece {
    Color color;

    Piece(Color color) {
        this.color = color;
    }

    abstract boolean canMove(Board board, Position start, Position end);

    boolean isValidDestination(Board board, Position end) {
        Cell dest = board.getCell(end);
        return dest.isEmpty() || dest.piece.color != this.color;
    }
}

// ---------------- PIECES ----------------

class King extends Piece {
    King(Color color) { super(color); }

    public boolean canMove(Board board, Position start, Position end) {
        int dx = Math.abs(start.row - end.row);
        int dy = Math.abs(start.col - end.col);

        return dx <= 1 && dy <= 1 && isValidDestination(board, end);
    }
}

class Queen extends Piece {
    Queen(Color color) { super(color); }

    public boolean canMove(Board board, Position start, Position end) {
        int dx = Math.abs(start.row - end.row);
        int dy = Math.abs(start.col - end.col);

        if (dx == dy || dx == 0 || dy == 0) {
            return board.isPathClear(start, end) && isValidDestination(board, end);
        }
        return false;
    }
}

class Rook extends Piece {
    Rook(Color color) { super(color); }

    public boolean canMove(Board board, Position start, Position end) {
        if (start.row == end.row || start.col == end.col) {
            return board.isPathClear(start, end) && isValidDestination(board, end);
        }
        return false;
    }
}

class Bishop extends Piece {
    Bishop(Color color) { super(color); }

    public boolean canMove(Board board, Position start, Position end) {
        if (Math.abs(start.row - end.row) == Math.abs(start.col - end.col)) {
            return board.isPathClear(start, end) && isValidDestination(board, end);
        }
        return false;
    }
}

class Knight extends Piece {
    Knight(Color color) { super(color); }

    public boolean canMove(Board board, Position start, Position end) {
        int dx = Math.abs(start.row - end.row);
        int dy = Math.abs(start.col - end.col);

        return dx * dy == 2 && isValidDestination(board, end);
    }
}

class Pawn extends Piece {
    Pawn(Color color) { super(color); }

    public boolean canMove(Board board, Position start, Position end) {
        int direction = (color == Color.WHITE) ? -1 : 1;

        // Forward move
        if (start.col == end.col) {
            if (end.row - start.row == direction && board.getCell(end).isEmpty()) {
                return true;
            }
        }

        // Capture
        if (Math.abs(start.col - end.col) == 1 &&
                end.row - start.row == direction &&
                !board.getCell(end).isEmpty() &&
                board.getCell(end).piece.color != this.color) {
            return true;
        }

        return false;
    }
}

// ---------------- BOARD ----------------

class Cell {
    Position position;
    Piece piece;

    Cell(int row, int col) {
        this.position = new Position(row, col);
    }

    boolean isEmpty() {
        return piece == null;
    }
}

class Board {
    private Cell[][] grid;

    Board() {
        grid = new Cell[8][8];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                grid[i][j] = new Cell(i, j);

        // Pawns
        for (int j = 0; j < 8; j++) {
            grid[1][j].piece = new Pawn(Color.BLACK);
            grid[6][j].piece = new Pawn(Color.WHITE);
        }

        // Rooks
        grid[0][0].piece = new Rook(Color.BLACK);
        grid[0][7].piece = new Rook(Color.BLACK);
        grid[7][0].piece = new Rook(Color.WHITE);
        grid[7][7].piece = new Rook(Color.WHITE);

        // Knights
        grid[0][1].piece = new Knight(Color.BLACK);
        grid[0][6].piece = new Knight(Color.BLACK);
        grid[7][1].piece = new Knight(Color.WHITE);
        grid[7][6].piece = new Knight(Color.WHITE);

        // Bishops
        grid[0][2].piece = new Bishop(Color.BLACK);
        grid[0][5].piece = new Bishop(Color.BLACK);
        grid[7][2].piece = new Bishop(Color.WHITE);
        grid[7][5].piece = new Bishop(Color.WHITE);

        // Queens
        grid[0][3].piece = new Queen(Color.BLACK);
        grid[7][3].piece = new Queen(Color.WHITE);

        // Kings
        grid[0][4].piece = new King(Color.BLACK);
        grid[7][4].piece = new King(Color.WHITE);
    }

    Cell getCell(Position pos) {
        return grid[pos.row][pos.col];
    }

    boolean isPathClear(Position start, Position end) {
        int dx = Integer.compare(end.row, start.row);
        int dy = Integer.compare(end.col, start.col);

        int x = start.row + dx;
        int y = start.col + dy;

        while (x != end.row || y != end.col) {
            if (!grid[x][y].isEmpty()) return false;
            x += dx;
            y += dy;
        }
        return true;
    }

    void movePiece(Position start, Position end) {
        Cell src = getCell(start);
        Cell dest = getCell(end);

        dest.piece = src.piece;
        src.piece = null;
    }

    void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j].isEmpty()) {
                    System.out.print(". ");
                } else {
                    System.out.print(getSymbol(grid[i][j].piece) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private char getSymbol(Piece piece) {
        char c = 'P';

        if (piece instanceof King) c = 'K';
        else if (piece instanceof Queen) c = 'Q';
        else if (piece instanceof Rook) c = 'R';
        else if (piece instanceof Bishop) c = 'B';
        else if (piece instanceof Knight) c = 'N';

        return piece.color == Color.WHITE ? c : Character.toLowerCase(c);
    }
}

// ---------------- GAME ----------------

class Player {
    String name;
    Color color;

    Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }
}

class Game {
    private Board board;
    private Player p1, p2;
    private Player current;

    Game(Player p1, Player p2) {
        this.board = new Board();
        this.p1 = p1;
        this.p2 = p2;
        this.current = p1;
    }

    void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            board.printBoard();
            System.out.println(current.name + "'s turn (" + current.color + ")");

            System.out.print("Enter move (startRow startCol endRow endCol): ");
            int sr = sc.nextInt();
            int scCol = sc.nextInt();
            int er = sc.nextInt();
            int ec = sc.nextInt();

            Position start = new Position(sr, scCol);
            Position end = new Position(er, ec);

            if (!makeMove(start, end)) {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    boolean makeMove(Position start, Position end) {
        Cell src = board.getCell(start);

        if (src.isEmpty()) return false;

        Piece piece = src.piece;

        if (piece.color != current.color) return false;

        if (!piece.canMove(board, start, end)) return false;

        board.movePiece(start, end);
        switchTurn();
        return true;
    }

    void switchTurn() {
        current = (current == p1) ? p2 : p1;
    }
}

// ---------------- MAIN ----------------

public class ChessGameLLD {
    public static void main(String[] args) {
        Player p1 = new Player("Player1", Color.WHITE);
        Player p2 = new Player("Player2", Color.BLACK);

        Game game = new Game(p1, p2);
        game.start();
    }
}