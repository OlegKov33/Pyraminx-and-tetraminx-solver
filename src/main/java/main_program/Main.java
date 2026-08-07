package main_program;


import main_program.main_app.Calculations;
import main_program.main_app.Node;
import main_program.utils.Scrambler;

class Main{
    public static void main(String[] args){
    
        // use scrambler to get a scrambled initialState that can then be used as initial or goal state
        Scrambler scrambler = new Scrambler();
        int[][] initialState = scrambler.scramble(11);
        int[][] goalState = {{0, 0, 0, 0, 0, 0},{1, 1, 1, 1, 1, 1},{2, 2, 2, 2, 2, 2},{3, 3, 3, 3, 3, 3}};
        
        

        // This code takes unsolved tetra minx and tried to solve it

        Node initialNode = new Node(initialState, 0, 
            goalState, "none", "none");

        Calculations solver = new Calculations(initialNode);
        solver.start();




        //This code takes the solved tetra minx and scrambles it

        // Node scrambleNode = new Node(initialState, 0, goalState, "start", "none");
        // Calculations scramble = new Calculations(scrambleNode);
        // scramble.start();
    }
}