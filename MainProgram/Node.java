public class Node {
    //default values.
    String nodeName = "none";
    String nodeParent = "none";
    int[][] nodeState = new int[4][6];
    int nodeCost = 0;


    Node(String givenName, String parent, int[][]givenNodeState, int givenCost){
        this.nodeName = givenName;
        this.nodeParent = parent;
        this.nodeState = givenNodeState;
        this.nodeCost = givenCost;
    }


    // Empty constructor. Solved tetraminx
    Node(){
        int[][] defaultNodeState = {{0,0,0,0,0,0},{1,1,1,1,1,1},{2,2,2,2,2,2},{3,3,3,3,3,3}};
        nodeState = defaultNodeState;
    }


    //used in (calculations) class to get data of node.
    public int[] getNodeState(int side){
        return this.nodeState[side];
    }


    // TO UNCOMMENT OR COMMETN, SELECT A LINE OF CODE, PRESS AND HOLD (LEFT CTRL) AND PRESS (/)
    // NOTE THAT AT A TIME ONLY (1) @OVERRIDE MUST BE UNCOMMENTED.
    
    @Override
    public String toString() {
        String output = "";
        output=
        "Name: \t"+this.nodeName+"\n"+
        "Parent: "+this.nodeParent+"\n"+
        "Cost: \t"+this.nodeCost+"\n"+
        "State: \t";

        for(int i = 0; i<this.nodeState.length;i++){
            for(int j =0;j<this.nodeState[0].length;j++){
                output+=this.nodeState[i][j];
            }
            output+="\n\t";
        }output+="\n";
        return output;
    }


    //This does letters - colour scheme from grubiks
    //0 - red
    //1 - blue
    //2 - yellow
    //3 - green

    // @Override
    // public String toString(){
    //     String output = "\n";
    //     for(int i = 0; i<this.nodeState.length;i++){
    //         for(int j =0;j<this.nodeState[0].length;j++){
    //             switch (this.nodeState[i][j]) {
    //                 case 0:
    //                 output+="R";
    //                 break;
                
    //                 case 1:
    //                 output+="B";
    //                 break;

    //                 case 2:
    //                 output+="Y";
    //                 break;

    //                 case 3:
    //                 output+="G";
    //                 break;
    //             }
    //         }
    //         output+="\n";
    //     }
    //     return output;
    // }

}
//Author OlegKov33