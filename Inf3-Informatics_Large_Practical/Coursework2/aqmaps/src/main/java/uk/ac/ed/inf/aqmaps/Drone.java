package uk.ac.ed.inf.aqmaps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Drone {

    protected int moves = 0;
    protected Position currPos;

    /**
     * initialize the Drone by Position
     * @param currPos
     */
    public Drone(Position currPos) {
        this.currPos = currPos;
    }

    
    /**
     * 
     * Moves the drone to the next position and update the number of moves
     * @param nextPos
     */ 
    protected void move(Position nextPos) {
        currPos = nextPos;
        moves++;
    }

    // Checks 
    /**
     * 
     * @return boolean to check whether the drone can move (not exceeded the allowed 150 moves)
     * True means didn't exceed, false means the drone moves is greater than 150
     */
    protected boolean checkMoves() {
        return moves < 150;
    }
}
