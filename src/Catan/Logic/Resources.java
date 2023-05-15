package Catan.Logic;
// An enum describing the obtainable materials in the game.
public enum Resources {
    WOOD(0),
    BRICKS(1),
    WHEAT(2),
    WOOL(3),
    IRON(4),
    EMPTY(5);
    private int index;

    // Assign numerical value to enum.
    Resources(int index){
        this.index = index;
    }

    // Get value.
    public int getIndex(){
        return this.index;
    }


}
