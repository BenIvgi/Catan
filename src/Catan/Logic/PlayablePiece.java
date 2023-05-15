package Catan.Logic;

import java.awt.*;

public abstract class  PlayablePiece extends Piece {
    // Declare properties.
    protected boolean isOccupied;
    protected Player owner;

    // Constructor.
    public PlayablePiece(Point pos, Color color){
        // Call super constructor.
        super(pos, color);

        // Set piece to be not occupied.
        this.isOccupied = false;
        this.owner = null;
    }

    // Getters.
    public boolean getIsOccupied(){
        return this.isOccupied;
    }

    public Player getOwner(){
        return this.owner;
    }

    //Setters.

    public void setOwner(Player p){
        this.owner = p;
    }

    public void occupy(){
        this.isOccupied = true;
    }
}
