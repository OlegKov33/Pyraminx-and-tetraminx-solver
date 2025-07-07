//This class generates a new node using an input as its starting point.

import java.util.ArrayList;
import java.util.List;

public class Scrambler {
    int[][]nodeState = new int[4][6];
    Node innerNode;
    ArrayList<Node> tempList = new ArrayList<>();


    Scrambler(){
        innerNode = new Node();
    }

    public int[][] getNodeData() {
        return nodeState;
    }


    // scrambles by turnsNumber times
    public int[][] scramble(int turnsNumber){
        tempList.add(innerNode);

        for(int i = 0; i < turnsNumber; i++){
            tempList.addAll(exploringNode(tempList.remove(tempList.size()-1)));
            int randomNumber = (int) Math.floor( Math.random() * tempList.size() );

            innerNode = tempList.get(randomNumber);
            tempList.removeAll(tempList); tempList.add(innerNode);
        }

        showNode(innerNode.getNodeState());
        return innerNode.getNodeState();
    }


    // Shows the node in a ready to use state: Use int[][] nodeName = CTRL + V the code in console
    private void showNode(int[][] inputState){
        String output = "{";
            for (int i = 0; i < inputState.length; i++) {
                output+="{";
                for (int j = 0; j < inputState[0].length; j++) {
                    output += inputState[i][j] + ",";
                }
                output = output.substring(0, output.length()-1);
                output+="},";
            }
            output = output.substring(0, output.length()-1);
            output+="};";
            System.out.println(output);
    }


    // Creates all possible moves from a given node. (can be used else where)
    public ArrayList<Node> exploringNode(Node givenNode) {
        int[][] goalState = givenNode.getGoalState();
        int[][] newState = setNewState(givenNode.getNodeState());

        List<Node> returnList = new ArrayList<>();
        int[] sideOne = givenNode.getNodeSide(0);
        int[] sideTwo = givenNode.getNodeSide(1);
        int[] sideThree = givenNode.getNodeSide(2);
        int[] sideFour = givenNode.getNodeSide(3);

        //Top gains side 2 (<<< rotation)
            for(int j = 0; j<3;j++){
                /*Example:
                    * Side (0)     Side(1)     Side(2)
                    * 000000       111111      222222
                    * will be:
                    * 111000       222111      000222
                    */
                newState[0][j] = sideTwo[j];
                newState[1][j] = sideThree[j];
                newState[2][j] = sideOne[j];
            }
            returnList.add(new Node(newState, givenNode.getCost() + 1, goalState, 
                    "none", "none"));
        newState = setNewState(givenNode.getNodeState());


        //Top gains side 3 (>>> rotation)
        
            for(int j = 0; j<3;j++){
                newState[0][j] = sideThree[j];
                newState[1][j] = sideOne[j];
                newState[2][j] = sideTwo[j];
            }
            returnList.add(new Node(newState, givenNode.getCost() + 1, goalState, 
                    "none", "none"));
        newState = setNewState(givenNode.getNodeState());


        //Right side gains side 2 (<<< rotation)
        
            for(int j = 0; j<3;j++){
                if(j+4 != 6){
                newState[0][j+2] = sideTwo[j+4]; //array length is 0-5, will throw err.
                newState[1][j+4] = sideFour[j];
                }else{
                newState[0][j+2] = sideTwo[0];
                newState[1][0] = sideFour[j];
                
            }
            newState[3][j] = sideOne[j+2];
            }
            returnList.add(new Node(newState, givenNode.getCost() + 1, goalState, 
                    "none", "none"));
        newState = setNewState(givenNode.getNodeState());


        //Right side gains side 4(>>> rotation)
        
            for(int j = 0; j<3;j++){
                newState[0][j+2] = sideFour[j];
                if(j+4 != 6){
                newState[1][j+4] = sideOne[j+2];//same here
                newState[3][j] = sideTwo[j+4];
            }else{
                newState[1][0] = sideOne[j+2];
                newState[3][j] = sideTwo[0];
            }
            }
            returnList.add(new Node(newState, givenNode.getCost() + 1, goalState, 
                    "none", "none"));
        newState = setNewState(givenNode.getNodeState());


        //Left side gains side 3 (>>> rotation)
        
            for(int j = 0; j<3;j++){
                if(j+4 != 6){
                newState[0][j+4] = sideThree[j+2];
                newState[2][j+2] = sideFour[j+4];
                newState[3][j+4] = sideOne[j+4];
            }else{
                newState[0][0] = sideThree[j+2];
                newState[2][j+2] = sideFour[0];
                newState[3][0] = sideOne[0];
            }
            }
            returnList.add(new Node(newState, givenNode.getCost() + 1, goalState, 
                    "none", "none"));
        newState = setNewState(givenNode.getNodeState());


        //left side gains side 4 (<<< rotation)
        
            for(int j = 0; j<3;j++){
                if(j+4 != 6){
                newState[0][j+4] = sideFour[j+4];
                newState[2][j+2] = sideOne[j+4];
                newState[3][j+4] = sideThree[j+2];
            }else{
                newState[0][0] = sideFour[0];
                newState[2][j+2] = sideOne[0];
                newState[3][0] = sideThree[j+2];
            }
            }
            returnList.add(new Node(newState, givenNode.getCost() + 1, goalState, 
                    "none", "none"));
        newState = setNewState(givenNode.getNodeState());


        // back side >>> right turn
        
            for(int j = 0; j<3;j++){
                if(j+4 != 6){
                newState[1][j+2] = sideThree[j+4];
                newState[2][j+4] = sideFour[j+2];
            }else{
                newState[1][j+2] = sideThree[0];
                newState[2][0] = sideFour[j+2];
            }
            newState[3][j+2] = sideTwo[j+2];
            }
            returnList.add(new Node(newState, givenNode.getCost() + 1, goalState, 
                    "none", "none"));
        newState = setNewState(givenNode.getNodeState());


        // back side <<< left turn
        
            for(int j = 0; j<3;j++){
                if(j+4 !=6){
                newState[3][j+2] = sideThree[j+4];//proper
                newState[2][j+4] = sideTwo[j+2];//new
                
            }else{
                newState[3][j+2] = sideThree[0];//proper
                newState[2][0] = sideTwo[j+2];//new
            }
            newState[1][j+2] = sideFour[j+2];//new
            }
            returnList.add(new Node(newState, givenNode.getCost() + 1, goalState, 
                    "none", "none"));
        newState = setNewState(givenNode.getNodeState());
        
        return (ArrayList<Node>) returnList;
    }


    // used by exploringNode
    private int[][]setNewState(int[][] inputState){
        int[][] newState = new int[4][6];
        for(byte k = 0; k < 4; k++){
            newState[k] = inputState[k].clone();
        }
        return newState;
    }

}
//Author OlegKov33