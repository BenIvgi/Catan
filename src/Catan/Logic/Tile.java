package Catan.Logic;

import java.awt.*;

public class Tile extends Piece{
    // Declare properties.
    private Resources type;
    private int number;
    private Polygon shape;
    private Settlement[] vertexes;
    private Road[] edges;
    private int size;

    //Constructor.
    public Tile(Resources type, int number, Color color, Point pos, int size){
        // Call super constructor of piece.
        super(pos, color);

        // Assign values to properties.
        this.type = type;
        this.size = size;
        this.number = number;

        //Init shape and Roads/Settlements arrays.
        this.shape = new Polygon(Piece.setToHexagon(this.pos, size)[0],
                Piece.setToHexagon(this.pos, size)[1], 6);
        this.vertexes = new Settlement[this.shape.npoints];
        this.edges = new Road[this.shape.npoints];
    }

    // Getters.
    public Resources getType(){
        return this.type;
    }
    public int getSize(){
        return this.size;
    }
    public int getNumber(){
        return this.number;
    }
    public Polygon getShape(){
        return this.shape;
    }
    public Settlement getSettlement(int i){
        return this.vertexes[i];
    }
    public Road getRoad(int i){
        return this.edges[i];
    }

    //Setters.
    public void setSettlement(int i, Settlement settlement){
        this.vertexes[i] = settlement;
    }
    public void setRoad(int i, Road road){
        this.edges[i] = road;
    }
}
