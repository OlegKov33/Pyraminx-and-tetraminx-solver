class Main{
    public static void main(String[] args){
    
        // use scrambler to get a scramboled initialState that can then be used as initial or goal state
        Scrambler scrambler = new Scrambler();
        int[][] initialState = scrambler.scramble(1);
        int[][] goalState = {{0, 0, 0, 0, 0, 0},{1, 1, 1, 1, 1, 1},{2, 2, 2, 2, 2, 2},{3, 3, 3, 3, 3, 3}};
        
        

        // This code takes unsolved tetraminx and tried to solve it

        // Node initialNode = new Node(initialState, 0, goalState, "none", "none");
        // Node goalNode = new Node(goalState, 0, goalState, "none", "none");

        // Calculations solver = new Calculations(initialNode, goalNode);
        // solver.start();




        //This code takes the solved tetraminx and scrambles it

        Node scrambleNode = new Node(goalState, 0, goalState, "none", "none");
        Node scrambleGoalNode = new Node(initialState, 0, goalState, "none", "none");
        Calculations scramble = new Calculations(scrambleNode, scrambleGoalNode);
        scramble.start();
    }
}