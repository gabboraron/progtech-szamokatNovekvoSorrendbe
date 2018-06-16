package numbersascendinx;

/**
 *
 * @author Sándor
 */

import java.util.Objects;

public class Tile {
    private int value;
    private coordinate coord;
    private Game game;
        
    public Tile(Game game, int value, coordinate coord) {
        this.game = game;
        this.coord = coord;
        this.value = value;
    }
    
    /**
     * Get the label what is on the tile in game panel
     * @return 
     */
    public String getLabelOfTile(){
        return Integer.toString(value);
    }
    
    /**
     * Increment value of the tile.
     */
    public void incTileValue(){
        ++value;
    }
    
    /**
     * Decrement value of this tile. 
     */
    public void decTileValue(){
        --value;
    }
    
    @Override
    public String toString() {
        return String.format(coord + " value:" + value + "\n");
    }

    /**
     * Get Coordinates of tile
     * @return 
     */
    public coordinate getCoord() {
        return coord;
    }

    /**
     * Get value of this tile.
     * @return 
     */
    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.value;
        hash = 83 * hash + Objects.hashCode(this.coord);
        hash = 83 * hash + Objects.hashCode(this.game);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tile other = (Tile) obj;
        if (this.value != other.value) {
            return false;
        }
        if (!Objects.equals(this.coord, other.coord)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        return true;
    }
    
    
    
}
