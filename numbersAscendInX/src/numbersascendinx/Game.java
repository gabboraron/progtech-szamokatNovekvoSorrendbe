package numbersascendinx;

/**
 *
 * @author Sándor
 */

import java.util.Random;
import java.util.Vector;

class Game {
    private Vector tiles = new Vector();
    private int rsize;
    private int csize;
    //public int currentPlayer;
    //public Player player1;
    //public Player player2;

    public Game(int Rsize, int Csize) {
        //this.currentPlayer = 1;
        //this.player1 = new Player();
        //this.player2 = new Player();
        this.rsize = Rsize;
        this.csize = Csize;
        
        initGamePanel(Rsize, Csize);
        System.out.println("Tiles: \n" + tiles);
    }

    private void initGamePanel(int Rsize, int Csize) {
        System.out.println("GAME PANEL SIZE: Row: " + Rsize + " Col: " + Csize);
        for(int ridx=0; ridx<Rsize; ++ridx){
            for(int cidx=0; cidx<Csize; ++cidx){
                coordinate currentCoord = new coordinate(ridx, cidx);
                Tile tile = new Tile(this, randInt(0,80),currentCoord);
                tiles.add(tile);
            }
        }
    }
    
    /**
     * Random number generator between min-max range.
     * @param min
     * @param max
     * @return 
     */
    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

      
    /**
     * Search by coordinates in the vector of terrain.
     * @return a Tile from terrain vector in the specific coordinates
     */
    public Tile getTileBycoordinates(coordinate coord){
        Tile t = new Tile(this, 0, coord);
        int idx = 0;
        boolean found = false;
        while((idx<tiles.size()) && (found == false)){
            Tile tmp = (Tile) tiles.get(idx);
            if(tmp.getCoord().equals(coord)){
                t = tmp;
                found = true;
                return tmp;
            }
            ++idx;
        }
        return t;
    }
    
    
    /**
     * Search by coordinates in the vector of terrain.
     * @return an int, the id of the tile in tiles Vector.
     */
    private int getTileIDBycoordinates(coordinate coord){
        int idx = 0;
        boolean found = false;
        while((idx<tiles.size()) && (found == false)){
            Tile tmp = (Tile) tiles.get(idx);
            if(tmp.getCoord().equals(coord)){
                found = true;
                return idx;
            }
            ++idx;
        }
        return idx-1;
    }
        
    /**
     * Get if all tiles is in ascending. Return true if this is true, false, if isn't.
     * @return 
     */
    public boolean isWinner(){
        boolean isWin = true;
        
        int fst = -1;
        int next = ((Tile) tiles.get(0)).getValue();
        for(int idx = 1; idx<tiles.size(); ++idx){
            if(fst>next)
                isWin = false;
            fst = ((Tile) tiles.get(idx-1)).getValue();
            next = ((Tile) tiles.get(idx)).getValue();
        }
        System.out.println("isWin: " + isWin);        
        return isWin;
    }

    /**
     * Manage click on the coordinate which is parameter.
     * @param coordinate 
     */
    void clickOn(coordinate coord) {
        System.out.println("CLICK ON: " + coord);
        
        ( (Tile) tiles.get(getTileIDBycoordinates(coord))).incTileValue();
        ( (Tile) getTileBycoordinates(new coordinate(coord.x-1,coord.y-1))).incTileValue();
        ( (Tile) getTileBycoordinates(new coordinate(coord.x-2,coord.y-2))).incTileValue();
        ( (Tile) getTileBycoordinates(new coordinate(coord.x+1,coord.y+1))).incTileValue();
        ( (Tile) getTileBycoordinates(new coordinate(coord.x+2,coord.y+2))).incTileValue();
        ( (Tile) getTileBycoordinates(new coordinate(coord.x-1,coord.y+1))).incTileValue();
        ( (Tile) getTileBycoordinates(new coordinate(coord.x-2,coord.y+2))).incTileValue();
        ( (Tile) getTileBycoordinates(new coordinate(coord.x+1,coord.y-1))).incTileValue();
        ( (Tile) getTileBycoordinates(new coordinate(coord.x+2,coord.y-2))).incTileValue();
        
    }
    
    /**
     * Create new game, generate new game table, etc.
     */
    void newGame(){
        tiles.clear();
        initGamePanel(rsize, csize);
        System.out.println("Tiles: \n" + tiles);
    }
}
