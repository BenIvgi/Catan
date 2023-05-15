package Catan.Logic;

import java.awt.*;

public class Game {
    // Constants.
    private static final int CUBE_SIZE = 6;
    // Declare properties.
    private Player[] players;
    private Board board;
    private int clock;
    // Constructor
    public Game(int numPlayers, int numVertex, int numEdge, int numTile){
        // Init players array and board, set clock to 0.
        this.players = new Player[numPlayers];
        this.board = new Board(numVertex, numEdge, numTile);
        this.clock = 0;
    }

    // Getters.
    public int getNumPlayers(){
        return this.players.length;
    }
    public Player getCurrentPlayer() {
        return this.players[this.clock%getNumPlayers()];

    }
    public int getClock(){
        return this.clock;
    }
    public Board getBoard(){
        return this.board;
    }

    // Increment clock value.
    public void shiftClock(){
        this.clock++;
    }

    // Start game.
    public void Start(){
        this.board.initBoard();
    }

    // Initialize players array.
    public void InitPlayers(String[] pNames, Color[] pColors){
        for(int i = 0; i < this.players.length; i++){
            this.players[i] = new Player(pNames[i], pColors[i]);
        }
    }

    // Roll the dice.
    public int roll(){
        // Create a possibilities array, where every combination of values appears as much as it's likable to occure.
        int[] odds = new int[CUBE_SIZE*CUBE_SIZE];
        for(int i =1; i < CUBE_SIZE+1; i++){
            for(int j = 1; j < CUBE_SIZE+1; j++){
                odds[(i-1)*CUBE_SIZE+(j-1)] = i+j;
            }
        }
        // Return a random summed value of 2 cubes.
        return odds[(int)(Math.random()*(CUBE_SIZE*CUBE_SIZE))];
    }

    // Place a settlement.
    public void PlaceSettlement(int i){
        // Occupy settlement.
        this.board.getVertex(i).occupy();

        // Set owner.
        this.board.getVertex(i).setOwner(this.getCurrentPlayer());

        // Change settlement level to 1.
        this.board.getVertex(i).upgrade();

        // Set settlement color to owner color.
        this.board.getVertex(i).setColor(this.board.getVertex(i).getOwner().getColor());

        // Take materials from player.
        this.getCurrentPlayer().placeSettlement();
    }

    // Place a road.
    public void PlaceRoad(int i){
        // Occupy road.
        this.board.getEdge(i).occupy();

        // Set owner.
        this.board.getEdge(i).setOwner(this.getCurrentPlayer());

        // Set road color to owner color.
        this.board.getEdge(i).setColor(this.board.getEdge(i).getOwner().getColor());

        // Take materials from player.
        this.getCurrentPlayer().placeRoad();
    }

    // Check if a road can be placed.
    public boolean canPlaceRoad(int i){
        // Return false if road is occupied.
        if(this.getBoard().getEdge(i).getIsOccupied()) return false;

        // Get road edge settlements.
        Settlement s1 = this.getBoard().getEdge(i).getS1();
        Settlement s2 = this.getBoard().getEdge(i).getS2();

        // If any of the edge settlements belong to the player, return true.
        if((s1.getIsOccupied() && s1.getOwner() == getCurrentPlayer()) ||
                (s2.getIsOccupied() && s2.getOwner() == getCurrentPlayer())) return true;

        // for every edge on the map, check if shares a settlement with the wanted road, and is occupied by the player.
        for(int j = 0; j < this.getBoard().getEdgeCount(); j++){
            if((this.getBoard().getEdge(j).getS1() == s1 ||
                    this.getBoard().getEdge(j).getS1() == s2 ||
                    this.getBoard().getEdge(j).getS2() == s1 ||
                    this.getBoard().getEdge(j).getS2() == s2) &&
                    (this.getBoard().getEdge(j).getIsOccupied() &&
                            this.getBoard().getEdge(j).getOwner() == this.getCurrentPlayer())){
                return true;
            }
        }
        return false;
    }

    // Check if a settlement can be placed.
    public boolean canPlaceSettlement(int i){
        // For every tile in the board.
        for(int j =0; j < this.getBoard().getTileCount(); j++){
            // For every settlement in each tile.
            for(int k = 0; k < this.getBoard().getTile(j).getShape().npoints; k++){
                // Check if contains the wanted settlement.
                if(this.getBoard().getTile(j).getSettlement(k) == this.getBoard().getVertex(i)){
                    // Check if 2 neighboring settlements are occupied, then return false.
                    if(this.getBoard().getTile(j).getSettlement((k-1+this.getBoard().getTile(j).getShape().npoints)%
                            this.getBoard().getTile(j).getShape().npoints).getIsOccupied() ||
                            this.getBoard().getTile(j).getSettlement((k+1+this.getBoard().getTile(j).getShape().npoints)%
                                    this.getBoard().getTile(j).getShape().npoints).getIsOccupied()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
