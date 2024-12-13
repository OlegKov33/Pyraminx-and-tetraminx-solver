class Main{
    public static void main(String[] args) {
        
    //TO COMMENT AND UNCOMMENT SELECT A LINE OF CODE, PRESS AND HOLD CTRL AND PRESS (/)
    //colour scheme from grubiks to code.
    //0 - red
    //1 - blue
    //2 - yellow
    //3 - green


    int[][] initialStateData = {{2,0,0,0,2,2},{1,1,1,1,1,1},{2,2,3,3,3,2},{0,3,3,3,0,0}};


    // Create your own node state with random node generator below.
    // RandomNodeGenerator nodeGenerator = new RandomNodeGenerator(initialStateData,1);
    // int[][] outputNodeState = nodeGenerator.getNodeData();


    //TO SOLVE UNCOMMENT 2 LINES BELOW
    Node startNode = new Node("none", "none", initialStateData, 0);
    Node goalNode = new Node();
    

    //This is code starts the searching algorithm.
    Problem test = new Problem(startNode, goalNode);
    test.start();
    }
}
//Author OlegKov33