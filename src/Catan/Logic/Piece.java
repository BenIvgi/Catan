package Catan.Logic;

import java.awt.*;
public abstract class Piece {
    private static final double HEX_X_OFFSET_VAL = 2.1;
    private static final double HEX_Y_OFFSET_VAL = 2;
    // Declare Properties.
    protected Point pos;
    protected Color color;

    // Constructor.
    public Piece(Point pos, Color color){
        // Assign values to properties.
        this.pos = pos;
        this.color = color;
    }

    // Gettes.
    public Color getColor(){
        return this.color;
    }
    public Point getPos(){
        return this.pos;
    }

    // Setters.
    public void setColor(Color color){
        this.color = color;
    }

    // Given a point and a size, return the x and y values of 6 points of a hexagon of height "size".
    public static int[][] setToHexagon(Point pos, int size){
        // Set x values as middle with offsets, clockwise.
        int[] xpoints = {pos.x, (int)(pos.x + size/HEX_X_OFFSET_VAL), (int)(pos.x + size/HEX_X_OFFSET_VAL), pos.x,
                (int)(pos.x - size/HEX_X_OFFSET_VAL), (int)(pos.x - size/HEX_X_OFFSET_VAL)};
        // Set y values as middle with offsets, clockwise.
        int[] ypoints = {(int)(pos.y - size/HEX_Y_OFFSET_VAL), (int)(pos.y - size/(HEX_Y_OFFSET_VAL*2)),
                (int)(pos.y + size/(HEX_Y_OFFSET_VAL*2)), (int)(pos.y + size/HEX_Y_OFFSET_VAL),
                (int)(pos.y + size/(HEX_Y_OFFSET_VAL*2)), (int)(pos.y - size/(HEX_Y_OFFSET_VAL*2))};

        // Join two arrays to a 2D array of points.
        int[][] points = {xpoints, ypoints};

        return points;
    }

}
