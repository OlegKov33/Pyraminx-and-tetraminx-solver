package main_program.main_app;

public class Node implements Comparable<Node>{
    private final int[][] state;
    private final int[][] goalState;
    private final int cost;
    private String name;
    private String parent;


    public Node(){
        state = new int[][]{{0, 0, 0, 0, 0, 0},{1, 1, 1, 1, 1, 1},{2, 2, 2, 2, 2, 2},{3, 3, 3, 3, 3, 3}};
        goalState = new int[][]{{0, 0, 0, 0, 0, 0},{1, 1, 1, 1, 1, 1},{2, 2, 2, 2, 2, 2},{3, 3, 3, 3, 3, 3}};
        cost = 0;
    }


    public Node(int[][] inputState, int inputCost,
                int[][] inputGoalState, String inputName,
                String inputParent){

        this.state = inputState;
        this.cost = inputCost;
        this.goalState = inputGoalState;
        this.name = inputName;
        this.parent = inputParent;
    }
    
    // checks the equality of nodes
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Node.class){
            return false;
        }


        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 6; j++){
                if( ( (Node) obj).getNodeSide(i)[j] != state[i][j] ){
                    return false;
                }
            }
        }
        return true;
    }
    
    // used in similar way as toString. However, it's used for naming nodes and in path finding
    public String printNode(){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 6; j++){
                output.append( state[i][j] );
            }
        }
        return output.toString();
    }


    public int[] getNodeSide(int inputSide){
        return state[inputSide];
    }

    public int[][] getNodeState(){
        return state;
    }

    public int getCost(){
        return cost;
    }

    public int[][] getGoalState(){
        return goalState;
    }

    public String getName(){
        return name;
    }
    
    public String getParent(){
        return parent;
    }

    // MY COMPARE-TO version. Does not guarantee an optimal solution.
    @Override
    public int compareTo(Node inputNode) {
        int correctlyAlignedCells = 0;
        int inputCorrectlyAlignedCells = 0;

        // checks current nodes number of displaced sides.
        for(byte i = 0 ; i < 3; i++){
            if( this.state[i][0] == goalState[i][0]){
                correctlyAlignedCells --; //test
                if( this.state[ (i - 2) * -1 ][2] == goalState[ (i - 2) * -1 ][2]){
                    correctlyAlignedCells += 2;
                }
            }
            if( this.state[3][i * 2] == goalState[3][i * 2]){
                correctlyAlignedCells --; // test
                if( this.state[i][5] == goalState[i][5]){
                    correctlyAlignedCells += 2;
                }
            }
        }


        // checks the inputNodes number of displaced sides.
        for(byte i = 0 ; i < 3; i++){
            if( inputNode.getNodeState()[i][0] == inputNode.getGoalState()[i][0]){
                inputCorrectlyAlignedCells --; // test
                if( inputNode.getNodeState()[ (i - 2) * -1 ][2] == inputNode.getGoalState()[ (i - 2) * -1 ][2]){
                    inputCorrectlyAlignedCells += 2;
                }
            }
            if( inputNode.getNodeState()[3][i * 2] == inputNode.getGoalState()[3][i * 2]){
                inputCorrectlyAlignedCells --; //test
                if( state[i][5] == goalState[i][5]){
                    inputCorrectlyAlignedCells += 2;
                }
            }
        }

        // the more the cost, the less appealing the node should appear
        correctlyAlignedCells -= (cost ) ; //test
        inputCorrectlyAlignedCells -= (inputNode.cost ); //test
    return Integer.compare(inputCorrectlyAlignedCells, correctlyAlignedCells);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 6; j++){
                builder.append(state[i][j]);
            }
            builder.append(" ");
        }
        return builder.toString();
    }

    // //DEEPSEEKS COMPARETO version. MUST BE USED WITH HEURISTIC V1 or HEURISTIC V2 below!
    
    // @Override
    // public int compareTo(main_app.Node o) {
    //     // Manhattan distance heuristic (sum of all facelet mismatches)
    //     int thisHeuristic = this.cost + this.calculateHeuristic();
    //     int otherHeuristic = o.cost + o.calculateHeuristic();
    //     return Integer.compare(thisHeuristic, otherHeuristic);
    // }


    // // DEEPSEEK HEURISTIC V1. After testing, appears to be the only heuristic 
    // // capable of finding optimal path. Tested on 1 - 10 turns.

    // private int calculateHeuristic() {
    //     int mismatch = 0;
    //     for (int i = 0; i < 4; i++) {
    //         for (int j = 0; j < 6; j++) {
    //             if (this.state[i][j] != this.goalState[i][j]) {
    //                 mismatch++;
    //             }
    //         }
    //     }
    //     return mismatch;
    // }



    // // DEEPSEEK HEURISTIC V2. After testing, appears to be inconsistent,
    // // capable of delivering the most optimal solution with efficient time but also
    // // unable to solve certain states, resulting in non optimal time and not optimal solution

    // private int calculateHeuristic() {
    // int totalDistance = 0;
    // for (int i = 0; i < 4; i++) {
    //     for (int j = 0; j < 6; j++) {
    //         int value = this.state[i][j];
    //         // Find where this value *should* be in the goal state
    //         for (int goalI = 0; goalI < 4; goalI++) {
    //             for (int goalJ = 0; goalJ < 6; goalJ++) {
    //                 if (goalState[goalI][goalJ] == value) {
    //                     // Add the distance between (i,j) and (goalI, goalJ)
    //                     totalDistance += Math.abs(i - goalI) + Math.abs(j - goalJ);
    //                 }
    //             }
    //         }
    //     }
    // }
    // return totalDistance / 6; // Normalize to avoid overcounting
    // }
}
