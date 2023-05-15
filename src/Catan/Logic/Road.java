package Catan.Logic;

import java.awt.*;
import java.awt.geom.Line2D;

public class Road extends PlayablePiece{
    // Declare properties.
    private Line2D shape;
    private Settlement s1;
    private Settlement s2;

    // Constructor.
    public Road(Point pos, Color color, int size, Settlement s1, Settlement s2){
        // Call super constructor.
        super(pos, color);
        this.s1 = s1;
        this.s2 = s2;

        // Create shape from 2 edge settlements.
        this.shape = CalculateDist();
    }

    // Getters.
    public Line2D getShape(){
        return this.shape;
    }

    public Settlement getS1(){return this.s1;}

    public Settlement getS2(){return this.s2;}

    // Calculate line from 2 settlements.
    private Line2D CalculateDist(){
       // Assign 4 corners of s1 and 4 corners of s2 to 8 new points.
        Point a1 = new Point((int)this.s1.getShape().getMinX(), (int)this.s1.getShape().getMinY());
        Point b1 = new Point((int)this.s1.getShape().getMaxX(), (int)this.s1.getShape().getMinY());
        Point c1 = new Point((int)this.s1.getShape().getMinX(), (int)this.s1.getShape().getMaxY());
        Point d1 = new Point((int)this.s1.getShape().getMaxX(), (int)this.s1.getShape().getMaxY());
        Point a2 = new Point((int)this.s2.getShape().getMinX(), (int)this.s2.getShape().getMinY());
        Point b2 = new Point((int)this.s2.getShape().getMaxX(), (int)this.s2.getShape().getMinY());
        Point c2 = new Point((int)this.s2.getShape().getMinX(), (int)this.s2.getShape().getMaxY());
        Point d2 = new Point((int)this.s2.getShape().getMaxX(), (int)this.s2.getShape().getMaxY());

        // Declare final points.
        Point f1 = a1, f2 = a2;

        // Arrange 2 sets of points in 2 arrays.
        Point[] arr1 = {a1, b1, c1, d1}, arr2 = {a2, b2, c2, d2};

        // declare starting min distance from some point from s1 to soe other point in s2 to be max distance.
        double mindist = Double.MAX_VALUE;

        // look for min dist.
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr2.length; j++){
                if(arr1[i].distance(arr2[j]) < mindist){
                    mindist = arr1[i].distance(arr2[j]);
                    f1 = arr1[i];
                    f2 = arr2[j];
                }
            }
        }

        // Return a line constructed from the shortest distance from s1 to s2.
        return new Line2D.Double(f1.getX(),f1.getY(), f2.getX(), f2.getY());

    }
}
