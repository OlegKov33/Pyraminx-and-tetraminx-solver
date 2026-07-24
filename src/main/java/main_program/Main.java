package main_program;


import main_program.main_app.Calculations;
import main_program.main_app.Node;
import main_program.utils.Scrambler;

class Main{
    public static void main(String[] args){
    
        // use scrambler to get a scrambled initialState that can then be used as initial or goal state
        Scrambler scrambler = new Scrambler();
        int[][] initialState = scrambler.scramble(1);
        int[][] goalState = {{0, 0, 0, 0, 0, 0},{1, 1, 1, 1, 1, 1},{2, 2, 2, 2, 2, 2},{3, 3, 3, 3, 3, 3}};
        
        

        // This code takes unsolved tetra minx and tried to solve it

        // main_app.Node initialNode = new main_app.Node(initialState, 0, goalState, "none", "none");
        // main_app.Node goalNode = new main_app.Node(goalState, 0, goalState, "none", "none");

        // main_app.Calculations solver = new main_app.Calculations(initialNode, goalNode);
        // solver.start();




        //This code takes the solved tetra minx and scrambles it

        Node scrambleNode = new Node(goalState, 0, goalState, "none", "none");
        Node scrambleGoalNode = new Node(initialState, 0, goalState, "none", "none");
        Calculations scramble = new Calculations(scrambleNode, scrambleGoalNode);
        scramble.start();
    }

    // TODO
    // so the kotlin version must use numbers 0-3 as colour selection on GUI
    // and the rest of the code, can be done as is.
}