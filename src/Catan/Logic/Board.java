package Catan.Logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
    // Constants.
    private static final int VERTEX_SIZE = 10;
    private static final int EDGE_SIZE = 10;
    private static final int TILE_SIZE = 110;
    private static final int TILE_Y_OFFSET = 25;
    private static final Point BOARD_START  = new Point(650, 200);
    // Declare properties.
    private Settlement[] vertexes;
    private Road[] edges;
    private Tile[] tiles;

    // Constructor.
    public Board(int numVertex, int numEdge, int numTile){
        // Assign values to properties.
        this.vertexes = new Settlement[numVertex];
        this.edges = new Road[numEdge];
        this.tiles = new Tile[numTile];
    }

    // Gettes.s
    public Settlement getVertex(int i){
        return this.vertexes[i];
    }
    public Road getEdge(int i){
        return this.edges[i];
    }
    public Tile getTile(int i){
        return this.tiles[i];
    }
    public int getTileCount(){ return this.tiles.length;}
    public int getVertexCount(){ return this.vertexes.length;}
    public int getEdgeCount(){ return this.edges.length;}

    // Setters.
    public void setVertex(int i, Settlement vertex){
        this.vertexes[i] = vertex;
    }
    public void setEdge(int i, Road edge){
        this.edges[i] = edge;
    }
    public void setTile(int i, Tile tile){
        this.tiles[i] = tile;
    }


    // Initialize board.
    public void initBoard(){
        // Initialize board components (except for edges, as it relies on code beforehand).
        initTiles();
        initVertexes();

        // Set each tile's vertexes.
        for(int i = 0; i < this.tiles.length; i++){
            for(int j = 0; j < this.tiles[i].getShape().npoints; j++){
                this.tiles[i].setSettlement(j, this.vertexes[getSettlementKey(i, j)]);
            }
        }

        // Initialize edges.
        initEdges();

        // Set each tile's edges.
        for(int i = 0; i < this.tiles.length; i++){
            for(int j = 0; j < this.tiles[i].getShape().npoints; j++){
                this.tiles[i].setRoad(j, this.edges[getRoadKey(i, j)]);
            }
        }
    }

    // Initialize vertexes.
    private void initVertexes(){
        // For every tile.
        for(int i = 0; i < this.tiles.length; i++){
            // For every vertex.
            for(int j = 0; j < this.tiles[i].getShape().npoints; j++){
                // Assign a new settlement to the middle of the vertex.
                this.vertexes[getSettlementKey(i, j)] = new Settlement(new Point(this.tiles[i].getShape().xpoints[j]-(VERTEX_SIZE/2),
                        this.tiles[i].getShape().ypoints[j]-(VERTEX_SIZE/2)), null, VERTEX_SIZE);
            }
        }
    }

    // Initialize tiles.
    private void initTiles(){
        // Get 2 lists of random resources and random numbers to assign to each tile.
        ArrayList<Resources>randres = initResources();
        ArrayList<Integer>randnum = initNumbers();

        // For every tile assign a resource & a number.
        for(int i = 0; i < this.tiles.length; i++){
            Resources type = randres.get(i);
            int number = randnum.get(i).intValue();

            // assign a color according to resource.
            Color color;
            switch (type){
                case WOOD:
                {
                    color = Color.green.darker();
                    break;
                }
                case BRICKS:
                {
                    color = Color.RED;
                    break;
                }
                case WHEAT:
                {
                    color = Color.YELLOW;
                    break;
                }
                case WOOL:
                {
                    color = Color.GREEN;
                    break;
                }
                case IRON:
                {
                    color = Color.LIGHT_GRAY;
                    break;
                }
                default:
                {
                    color = Color.BLACK;
                }
            }

            // Create new tile.
            this.tiles[i] = new Tile(type, number, color,getPos(i), TILE_SIZE);
        }
    }

    // Initialize resources distribution.
    private ArrayList<Resources> initResources(){
        // Create a list of resouces, add according amount of each resource.
        ArrayList<Resources> arr = new ArrayList<Resources>();

        // BRICKS - 3.
        arr.add(Resources.BRICKS);
        arr.add(Resources.BRICKS);
        arr.add(Resources.BRICKS);

        // IRON - 3.
        arr.add(Resources.IRON);
        arr.add(Resources.IRON);
        arr.add(Resources.IRON);

        // WHEAT - 4.
        arr.add(Resources.WHEAT);
        arr.add(Resources.WHEAT);
        arr.add(Resources.WHEAT);
        arr.add(Resources.WHEAT);

        // WOOL - 4.
        arr.add(Resources.WOOL);
        arr.add(Resources.WOOL);
        arr.add(Resources.WOOL);
        arr.add(Resources.WOOL);

        // WOOD - 4.
        arr.add(Resources.WOOD);
        arr.add(Resources.WOOD);
        arr.add(Resources.WOOD);
        arr.add(Resources.WOOD);

        // randomize the distribution.
        Collections.shuffle(arr);

        // Place an empty resource tile in the middle.
        Resources temp = arr.get(9);
        arr.set(this.tiles.length/2, Resources.EMPTY);
        arr.add(temp);

        return arr;
    }

    // Initialize numbers distribution.
    private ArrayList<Integer> initNumbers(){
        // Create numbers array.
        ArrayList<Integer> nums = new ArrayList<Integer>();

        // Add each number amount accordingly.
        // 2 - 1.
        nums.add(2);

        // 3 - 2.
        nums.add(3);
        nums.add(3);

        // 4 - 2.
        nums.add(4);
        nums.add(4);

        // 5 - 2.
        nums.add(5);
        nums.add(5);

        // 6 - 2.
        nums.add(6);
        nums.add(6);

        // 8 - 2.
        nums.add(8);
        nums.add(8);

        // 9 - 2.
        nums.add(9);
        nums.add(9);

        // 10 - 2.
        nums.add(10);
        nums.add(10);

        // 11 - 2.
        nums.add(11);
        nums.add(11);

        // 12 - 1.
        nums.add(12);

        // randomize the distribution.
        Collections.shuffle(nums);

        // set the middle tile's number to 7.
        Integer temp = nums.get(9);
        nums.add(temp);
        nums.set(9, 7);
        return nums;
    }

    // Initialize the edges.
    private void initEdges(){
        // for every tile.
        for(int i = 0; i < this.tiles.length; i++){
            // for every edge.
            for(int j = 0; j < this.tiles[i].getShape().npoints; j++){
                // Place a new road in the array.
                this.edges[getRoadKey(i, j)] = new Road(this.tiles[i].getSettlement(j).pos,
                        null, EDGE_SIZE, this.tiles[i].getSettlement(j),
                        this.tiles[i].getSettlement((j+1)%this.tiles[i].getShape().npoints));
            }
        }
    }

    // Get the index of a given edge in a tile inside the edges array.
    private int getRoadKey(int i, int j){
        switch (i){
            case 0:{
                switch (j){
                    case 0: return 1;
                    case 1: return 7;
                    case 2: return 12;
                    case 3: return 11;
                    case 4: return 6;
                    case 5: return 0;
                }
            }
            case 1:{
                switch (j){
                    case 0: return 3;
                    case 1: return 8;
                    case 2: return 14;
                    case 3: return 13;
                    case 4: return 7;
                    case 5: return 2;
                }
            }
            case 2:{
                switch (j){
                    case 0: return 5;
                    case 1: return 9;
                    case 2: return 16;
                    case 3: return 15;
                    case 4: return 8;
                    case 5: return 4;
                }
            }
            case 3:{
                switch (j){
                    case 0: return 11;
                    case 1: return 19;
                    case 2: return 25;
                    case 3: return 24;
                    case 4: return 18;
                    case 5: return 10;
                }
            }
            case 4:{
                switch (j){
                    case 0: return 13;
                    case 1: return 20;
                    case 2: return 27;
                    case 3: return 26;
                    case 4: return 19;
                    case 5: return 12;
                }
            }
            case 5:{
                switch (j){
                    case 0: return 15;
                    case 1: return 21;
                    case 2: return 29;
                    case 3: return 28;
                    case 4: return 20;
                    case 5: return 14;
                }
            }
            case 6:{
                switch (j){
                    case 0: return 17;
                    case 1: return 22;
                    case 2: return 31;
                    case 3: return 30;
                    case 4: return 21;
                    case 5: return 16;
                }
            }
            case 7:{
                switch (j){
                    case 0: return 24;
                    case 1: return 34;
                    case 2: return 40;
                    case 3: return 39;
                    case 4: return 33;
                    case 5: return 23;
                }
            }
            case 8:{
                switch (j){
                    case 0: return 26;
                    case 1: return 35;
                    case 2: return 42;
                    case 3: return 41;
                    case 4: return 34;
                    case 5: return 25;
                }
            }
            case 9:{
                switch (j){
                    case 0: return 28;
                    case 1: return 36;
                    case 2: return 44;
                    case 3: return 43;
                    case 4: return 35;
                    case 5: return 27;
                }
            }
            case 10:{
                switch (j){
                    case 0: return 30;
                    case 1: return 37;
                    case 2: return 46;
                    case 3: return 45;
                    case 4: return 36;
                    case 5: return 29;
                }
            }
            case 11:{
                switch (j){
                    case 0: return 32;
                    case 1: return 38;
                    case 2: return 48;
                    case 3: return 47;
                    case 4: return 37;
                    case 5: return 31;
                }
            }
            case 12:{
                switch (j){
                    case 0: return 41;
                    case 1: return 50;
                    case 2: return 55;
                    case 3: return 54;
                    case 4: return 49;
                    case 5: return 40;
                }
            }
            case 13:{
                switch (j){
                    case 0: return 43;
                    case 1: return 51;
                    case 2: return 57;
                    case 3: return 56;
                    case 4: return 50;
                    case 5: return 42;
                }
            }
            case 14:{
                switch (j){
                    case 0: return 45;
                    case 1: return 52;
                    case 2: return 59;
                    case 3: return 58;
                    case 4: return 51;
                    case 5: return 44;
                }
            }
            case 15:{
                switch (j){
                    case 0: return 47;
                    case 1: return 53;
                    case 2: return 61;
                    case 3: return 60;
                    case 4: return 52;
                    case 5: return 46;
                }
            }
            case 16:{
                switch (j){
                    case 0: return 56;
                    case 1: return 63;
                    case 2: return 67;
                    case 3: return 66;
                    case 4: return 62;
                    case 5: return 55;
                }
            }
            case 17:{
                switch (j){
                    case 0: return 58;
                    case 1: return 64;
                    case 2: return 69;
                    case 3: return 68;
                    case 4: return 63;
                    case 5: return 57;
                }
            }
            case 18:{
                switch (j){
                    case 0: return 60;
                    case 1: return 65;
                    case 2: return 71;
                    case 3: return 70;
                    case 4: return 64;
                    case 5: return 59;
                }
            }
            default: return -1;
        }
    }

    // Get the index of a given vertex in a tile inside the vertexes array.
    private int getSettlementKey(int i, int j){
        switch (i){
            case 0:{
                switch (j){
                    case 0: return 0;
                    case 1: return 4;
                    case 2: return 8;
                    case 3: return 12;
                    case 4: return 7;
                    case 5: return 3;
                }
            }
            case 1:{
                switch (j){
                    case 0: return 1;
                    case 1: return 5;
                    case 2: return 9;
                    case 3: return 13;
                    case 4: return 8;
                    case 5: return 4;
                }
            }
            case 2:{
                switch (j){
                    case 0: return 2;
                    case 1: return 6;
                    case 2: return 10;
                    case 3: return 14;
                    case 4: return 9;
                    case 5: return 5;
                }
            }
            case 3:{
                switch (j){
                    case 0: return 7;
                    case 1: return 12;
                    case 2: return 17;
                    case 3: return 22;
                    case 4: return 16;
                    case 5: return 11;
                }
            }
            case 4:{
                switch (j){
                    case 0: return 8;
                    case 1: return 13;
                    case 2: return 18;
                    case 3: return 23;
                    case 4: return 17;
                    case 5: return 12;
                }
            }
            case 5:{
                switch (j){
                    case 0: return 9;
                    case 1: return 14;
                    case 2: return 19;
                    case 3: return 24;
                    case 4: return 18;
                    case 5: return 13;
                }
            }
            case 6:{
                switch (j){
                    case 0: return 10;
                    case 1: return 15;
                    case 2: return 20;
                    case 3: return 25;
                    case 4: return 19;
                    case 5: return 14;
                }
            }
            case 7:{
                switch (j){
                    case 0: return 16;
                    case 1: return 22;
                    case 2: return 28;
                    case 3: return 33;
                    case 4: return 27;
                    case 5: return 21;
                }
            }
            case 8:{
                switch (j){
                    case 0: return 17;
                    case 1: return 23;
                    case 2: return 29;
                    case 3: return 34;
                    case 4: return 28;
                    case 5: return 22;
                }
            }
            case 9:{
                switch (j){
                    case 0: return 18;
                    case 1: return 24;
                    case 2: return 30;
                    case 3: return 35;
                    case 4: return 29;
                    case 5: return 23;
                }
            }
            case 10:{
                switch (j){
                    case 0: return 19;
                    case 1: return 25;
                    case 2: return 31;
                    case 3: return 36;
                    case 4: return 30;
                    case 5: return 24;
                }
            }
            case 11:{
                switch (j){
                    case 0: return 20;
                    case 1: return 26;
                    case 2: return 32;
                    case 3: return 37;
                    case 4: return 31;
                    case 5: return 25;
                }
            }
            case 12:{
                switch (j){
                    case 0: return 28;
                    case 1: return 34;
                    case 2: return 39;
                    case 3: return 43;
                    case 4: return 38;
                    case 5: return 33;
                }
            }
            case 13:{
                switch (j){
                    case 0: return 29;
                    case 1: return 35;
                    case 2: return 40;
                    case 3: return 44;
                    case 4: return 39;
                    case 5: return 34;
                }
            }
            case 14:{
                switch (j){
                    case 0: return 30;
                    case 1: return 36;
                    case 2: return 41;
                    case 3: return 45;
                    case 4: return 40;
                    case 5: return 35;
                }
            }
            case 15:{
                switch (j){
                    case 0: return 31;
                    case 1: return 37;
                    case 2: return 42;
                    case 3: return 46;
                    case 4: return 41;
                    case 5: return 36;
                }
            }
            case 16:{
                switch (j){
                    case 0: return 39;
                    case 1: return 44;
                    case 2: return 48;
                    case 3: return 51;
                    case 4: return 47;
                    case 5: return 43;
                }
            }
            case 17:{
                switch (j){
                    case 0: return 40;
                    case 1: return 45;
                    case 2: return 49;
                    case 3: return 52;
                    case 4: return 48;
                    case 5: return 44;
                }
            }
            case 18:{
                switch (j){
                    case 0: return 41;
                    case 1: return 46;
                    case 2: return 50;
                    case 3: return 53;
                    case 4: return 49;
                    case 5: return 45;
                }
            }
            default: return -1;
        }
    }

    // Get a given tile's position on the board.
    private Point getPos(int i){
        // Set placing values.
        int startX = BOARD_START.x;
        int startY = BOARD_START.y;
        int diffX = TILE_SIZE;
        int diffY = TILE_SIZE - TILE_Y_OFFSET;

        // for each tile return its position.
        switch (i)
        {
            case 0: return new Point(startX, startY);
            case 1: return new Point(startX + diffX, startY);
            case 2: return new Point(startX + diffX*2, startY);
            case 3: return new Point(startX - (int)(diffX*0.5), startY + diffY);
            case 4: return new Point(startX + (int)(diffX*0.5), startY + diffY);
            case 5: return new Point(startX + (int)(diffX*1.5), startY + diffY);
            case 6: return new Point(startX + (int)(diffX*2.5), startY + diffY);
            case 7: return new Point(startX - diffX, startY + diffY*2);
            case 8: return new Point(startX, startY + diffY*2);
            case 9: return new Point(startX + diffX, startY + diffY*2);
            case 10: return new Point(startX + diffX*2, startY + diffY*2);
            case 11: return new Point(startX + diffX*3, startY + diffY*2);
            case 12: return new Point(startX - (int)(diffX*0.5), startY + diffY*3);
            case 13: return new Point(startX + (int)(diffX*0.5), startY + diffY*3);
            case 14: return new Point(startX + (int)(diffX*1.5), startY + diffY*3);
            case 15: return new Point(startX + (int)(diffX*2.5), startY + diffY*3);
            case 16: return new Point(startX, startY+diffY*4);
            case 17: return new Point(startX + diffX, startY+diffY*4);
            case 18: return new Point(startX + diffX*2, startY+diffY*4);
            default: return null;

        }
    }

}
