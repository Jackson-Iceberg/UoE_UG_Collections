package uk.ac.ed.inf.aqmaps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Drone {

    protected int moves = 0;
    protected Position currPos;

//    // Final overall flight path of the drone
//    List<Position> flightPath = new ArrayList<Position>();
    // String which is used to the output: .txt file (Result)
   // public String txtString = "";

    public Drone(Position currPos) {
        this.currPos = currPos;
    }

    // Moves the drone to the next position and update the number of moves
    protected void move(Position nextPos) {
        currPos = nextPos;
        moves++;
    }

    // Checks whether the drone can move (not exceeded the allowed 150 moves)
    protected boolean checkMoves() {
        return moves < 150;
    }
}
