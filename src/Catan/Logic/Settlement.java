package Catan.Logic;

import java.awt.*;
import java.awt.geom.Rectangle2D;
public class Settlement extends PlayablePiece{
    // Declare properties.
    private int level;
    private Rectangle shape;

    //Constructor.
    public Settlement(Point pos, Color color, int size){
        // Call super constructor.
        super(pos, color);

        // Assign values to properties.
        this.shape = new Rectangle(pos.x, pos.y, size/2, size/2);
        this.shape.setSize(size, size);

        // Init level.
        this.level = 0;
    }

    // Getters.
    public int getLevel(){
        return this.level;
    }
    public Rectangle2D getShape(){
        return this.shape;
    }

    // Increment level by 1.
    public void upgrade(){
        this.level++;
    }


}
