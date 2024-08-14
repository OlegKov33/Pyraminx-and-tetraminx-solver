class main{
    public static void main(String[] args) {
    //colour scheme from grubiks
    //0 - red
    //1 - blue
    //2 - yellow
    //3 - green





//List of tested and working nodes (going from unsorted to sorted)
//work. forwards
    //int[][]data2 = {{0,0,0,0,0,0},{1,1,1,1,1,1},{2,2,2,2,2,2},{3,3,3,3,3,3}};
    //int[][]data2 = {{2,0,0,0,2,2},{1,1,3,3,0,1},{1,2,3,3,1,1},{0,3,3,2,2,0}};
    //int[][]data2 = {{1,1,3,0,0,0},{1,2,2,3,3,1},{0,0,0,2,1,1},{3,3,2,2,2,3}};
    //int[][] data2 = {{1,0,3,3,3,3},{0,1,1,1,0,0},{2,2,3,0,0,2},{2,1,1,3,2,2}};
    //int[][] data2 = {{2,2,2,1,1,0},{0,0,0,3,3,3},{3,1,1,2,1,1},{0,0,2,2,3,3}};
    //int[][] data2 = {{0,0,0,1,3,0},{1,1,0,1,3,3},{3,2,2,2,1,2},{2,0,1,3,2,3}};
    //int[][] data2 = {{0,2,1,1,0,2},{2,0,1,1,3,3},{3,1,1,3,3,2},{2,0,0,3,2,0}};
    //int[][]data2 ={{1,1,0,1,3,3},{2,2,2,2,2,3},{1,0,0,0,3,3},{0,0,3,1,1,2}};
    //int[][]data2 = {{1,1,0,0,1,2},{3,2,3,2,2,1},{1,0,0,3,3,3},{2,3,0,1,2,0}};
    //int[][] data2 = {{3,2,0,0,1,0},{2,0,2,3,3,1},{1,1,0,2,2,1},{0,3,1,2,3,3}};
    

//List of tested nodes that didn't work for various reasons.
//doesn't work
    //requires back turn
    //int[][] data2 = {{0,2,0,0,0,0},{1,0,2,1,3,1},{1,1,2,2,2,2},{3,3,1,3,3,3}};

//doesn't work, but works in reverse...(unsorted to sorted doesn't work.)(sorted to unsorted work.)
    //int[][] data2 ={{0,1,0,3,0,3},{2,2,3,1,2,0},{1,0,3,0,2,2},{1,1,3,3,1,2}};

//in reverse ERROR
    //int[][]data2 = {{1,1,0,0,1,2},{3,2,3,2,2,1},{1,0,0,3,3,3},{2,3,0,1,2,0}};
    //int[][] data2 = {{3,2,0,0,1,0},{2,0,2,3,3,1},{1,1,0,2,2,1},{0,3,1,2,3,3}};
//-----------------------------------------------------------------------------------//
//-----------------------------------------------------------------------------------//



    int[][]data = {{0,0,0,0,0,0},{1,1,1,1,1,1},{2,2,2,2,2,2},{3,3,3,3,3,3}};
    int[][]data2 = {{2,0,0,0,2,2},{1,1,1,1,1,1},{2,2,3,3,3,2},{0,3,3,3,0,0}};


    //Generator_Of_Node - creates a node that you could use to scramble or unscramble your pyraminx or tetraminx
    //if you wish to generate your cube, comment out the "data2" line above.
    //Generator_Of_Node TESTING = new Generator_Of_Node(data,1);int[][] data2 = TESTING.getData();


    //n1 is your goal state. (goal state is what you are trying to achieve, the end result)
    //n2 is your current state. (current state is your starting point)
    //NOTE: ONLY (1) INSTANCE OF (n1) AND (n2) CAN EXIST AT A TIME.
    //TO COMMENT AND UNCOMMENT SELECT A LINE OF CODE, PRESS AND HOLD CTRL AND PRESS (/)

    //TO SOLVE UNCOMMENT 2 LINES BELOW
    node n2 = new node("none", "none", data2, 0);
    node n1 = new node("none", "none", data, 0);


    //TO SCRAMBLE UNCOMMENT 2 LINES BELOW
    // node n2 = new node("none", "none", data, 0);
    // node n1 = new node("none", "none", data2, 0);


    //This is code starts the searching algorithm.
    problem test = new problem(n2,n1);test.start();
    }
}
//Author OlegKov33