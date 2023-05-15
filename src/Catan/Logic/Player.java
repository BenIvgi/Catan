package Catan.Logic;

import java.awt.Color;
public class Player {
    // Constants.
    private static final int POINTS_FOR_WIN = 10;
    
    // Declare properties.
    private String name;
    private Color color;
    private int[] inventory;
    private int ptcount;

    // Constructor.
    public Player(String name, Color color){
        // Assign values to properties.
        this.name = name;
        this.color = color;

        // Init point count.
        this.ptcount = 0;

        // Init inventory.
        this.inventory = new int[Resources.values().length];
        for(int i = 0; i < inventory.length; i++) {
            inventory[i] = 0;
        }

        // Allow players to purchase 2 settlements when the game starts.
        openingBoost();
    }

    // Getters.
    public int getAmnt(Resources type){
        return this.inventory[type.getIndex()];
    }
    public String getName(){
        return this.name;
    }
    public Color getColor(){
        return this.color;
    }
    public int getPtcount(){
        return this.ptcount;
    }

    // Give player the resources needed to place 2 settlements.
    public void openingBoost(){
        this.inventory[Resources.BRICKS.getIndex()]+= 2;
        this.inventory[Resources.WOOL.getIndex()]+= 2;
        this.inventory[Resources.WOOD.getIndex()]+= 2;
        this.inventory[Resources.WHEAT.getIndex()]+= 2;
    }

    // Take away the price of a settlement from the inventory.
    public void placeSettlement(){
        this.inventory[Resources.BRICKS.getIndex()]--;
        this.inventory[Resources.WOOL.getIndex()]--;
        this.inventory[Resources.WOOD.getIndex()]--;
        this.inventory[Resources.WHEAT.getIndex()]--;
        this.ptcount++;
    }


    // Increment any amount of material specified in arg.
    public void incResource(Resources type){
        this.inventory[type.getIndex()]++;
    }

    // Return true if the count of each element needed for a settlement is more than zero, false otherwise.
    public boolean canBuildSettlement(){
        return (this.inventory[Resources.BRICKS.getIndex()] >= 1 &&
        this.inventory[Resources.WOOL.getIndex()] >= 1 &&
        this.inventory[Resources.WOOD.getIndex()] >= 1 &&
        this.inventory[Resources.WHEAT.getIndex()] >= 1);
    }

    // Take away the price of a road from the inventory.
    public void placeRoad(){
        this.inventory[Resources.BRICKS.getIndex()]--;
        this.inventory[Resources.WOOD.getIndex()]--;
    }

    // Return true if the count of each element needed for a road is more than zero, false otherwise.
    public boolean canBuildRoad(){
        return (this.inventory[Resources.BRICKS.getIndex()] >= 1 &&
                this.inventory[Resources.WOOD.getIndex()] >= 1);
    }

    // Check if player won.
    public boolean isWin() {
        return this.ptcount >= POINTS_FOR_WIN;
    }
}
