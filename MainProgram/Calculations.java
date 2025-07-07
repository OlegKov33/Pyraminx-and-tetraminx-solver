import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Calculations {
    private Node startingNode;
    private Node finishingNode;
    private Queue<Node> unexploredNodes = new PriorityQueue<Node>();
    private Set<String> exploredStates = new HashSet<>();
    private HashMap<String, Node> pathNodes = new HashMap<>();

    Calculations(Node inputStartingNode, Node inputFinishingNode){
        startingNode = inputStartingNode;
        finishingNode = inputFinishingNode;
    }

    public void start(){
        // checks if the input is valid by counting numbers
        if(!isSolvable()){
            System.out.println("The inputs are not solvable.");
            return;
        }

        // checks if the input is equal to the goal
        if(startingNode.printNode().equals(finishingNode.printNode())){
            System.out.println("The input is equal to the goal!");
            return;
        }


        unexploredNodes.add(startingNode);
        boolean notFoundGoal = true;



        while(notFoundGoal){

            // adds a node to a list if visited nodes, used to determine if the node was explored already
            exploredStates.add(unexploredNodes.peek().printNode());

            // adds a node to a hash list, but unlike the list above, will be used for path construction
            pathNodes.put(unexploredNodes.peek().getName(), unexploredNodes.peek());

            // removes and explores the most promising node acording to compareTo method in Node.java class
            unexploredNodes.addAll(exploringNode(unexploredNodes.poll()));


            // If the node equals to the goal, tell the user 
            if(unexploredNodes.peek().printNode().equals(finishingNode.printNode())){
                System.out.println("Goal found!\nCost of node : " + unexploredNodes.peek().getCost()
                +"\nTotal nodes explored: "+ exploredStates.size()+"\nGenerated nodes: "+unexploredNodes.size()+"\n");
                pathNodes.put( unexploredNodes.peek().getName(), unexploredNodes.peek());
                constructPath( unexploredNodes.peek() );
                notFoundGoal = false;
            }
            //if you didn't find the goal within 23,000 nodes, there is a problem.
            if( exploredStates.size() > 23000 ){
                notFoundGoal = false;
                System.out.println("The goal was not found, please check your inputs again.");
            }
        }
    }



    // used by exploringNode to reset newState
    private int[][]setNewState(int[][] inputState){
        int[][] newState = new int[4][6];
        for(byte k = 0; k < 4; k++){
            newState[k] = inputState[k].clone();
        }
        return newState;
    }

    // used by exploring Node to set new node names to their states, similar to Node.java printNode method
    private String stateToName(int[][] inputState){
        String output = "";
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 6; j++){
                output += inputState[i][j];
            }
        }
        return output;
    }


    // generates all possible turns from a given node
    public Queue<Node> exploringNode(Node givenNode) {
        int[][] goalState = givenNode.getGoalState();
        int[][] newState = setNewState(givenNode.getNodeState());
        Node newNode;


    Queue<Node> returnList = new PriorityQueue<Node>();
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
        newNode = new Node(newState, givenNode.getCost() + 1, goalState, 
                stateToName(newState), givenNode.getName());
        if(! exploredStates.contains(newNode.printNode())){
            returnList.add(newNode);
        }
        
    newState = setNewState(givenNode.getNodeState());


    //Top gains side 3 (>>> rotation)
    
        for(int j = 0; j<3;j++){
            newState[0][j] = sideThree[j];
            newState[1][j] = sideOne[j];
            newState[2][j] = sideTwo[j];
        }
        newNode = new Node(newState, givenNode.getCost() + 1, goalState, 
                stateToName(newState),givenNode.getName());
        if(! exploredStates.contains(newNode.printNode())){
            returnList.add(newNode);
        }
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
        newNode = new Node(newState, givenNode.getCost() + 1, goalState, 
                stateToName(newState), givenNode.getName());
        if(! exploredStates.contains(newNode.printNode())){
            returnList.add(newNode);
        }
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
        newNode = new Node(newState, givenNode.getCost() + 1, goalState, 
                stateToName(newState), givenNode.getName());
        if(! exploredStates.contains(newNode.printNode())){
            returnList.add(newNode);
        }
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
        newNode = new Node(newState, givenNode.getCost() + 1, goalState, 
                stateToName(newState), givenNode.getName());
        if(! exploredStates.contains(newNode.printNode())){
            returnList.add(newNode);
        }
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
        newNode = new Node(newState, givenNode.getCost() + 1, goalState, 
                stateToName(newState), givenNode.getName());
        if(! exploredStates.contains(newNode.printNode())){
            returnList.add(newNode);
        }
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
        newNode = new Node(newState, givenNode.getCost() + 1, goalState, 
                stateToName(newState), givenNode.getName());
        if(! exploredStates.contains(newNode.printNode())){
            returnList.add(newNode);
        }
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
        newNode = new Node(newState, givenNode.getCost() + 1, goalState, 
                stateToName(newState), givenNode.getName());
        if(! exploredStates.contains(newNode.printNode())){
            returnList.add(newNode);
        }
    newState = setNewState(givenNode.getNodeState());

    return (PriorityQueue<Node>) returnList;
}


    // checks if there is an equal number of bases and edges for each color (it must be 3 and 3 for each)
    private boolean isSolvable(){
        int numberOfBases = 0;
        int numberOfEdges = 0;

        for(int k = 0; k < 4; k++){
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 3; j++){
                    if(startingNode.getNodeState()[i][j*2] == k){
                        numberOfEdges ++;
                    }
                    if(startingNode.getNodeState()[i][j*2 + 1] == k){
                        numberOfBases ++;
                    }
                }
            }
            
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 3; j++){
                    if(finishingNode.getNodeState()[i][j*2] == k){
                        numberOfEdges --;
                    }
                    if(finishingNode.getNodeState()[i][j*2 + 1] == k){
                        numberOfBases --;
                    }
                }
            }
            if(numberOfBases != 0 || numberOfEdges != 0){
                return false;
            }
        }
        return true;
    }


    //goes from back to fount:  goal >> node before goal ... initial node
    public void constructPath(Node finalNode) {
        ArrayList<Node> nodesPathArray = new ArrayList<>();
        nodesPathArray.add(finalNode);
        String tempParentNode = "";
        tempParentNode = finalNode.getParent();


        while(tempParentNode != "none"){
            nodesPathArray.add( pathNodes.get(tempParentNode) );
            tempParentNode = nodesPathArray.get( nodesPathArray.size()-1 ).getParent();
        }
        nodesPathArray.add( pathNodes.get(tempParentNode) );
        turnOrder(nodesPathArray);
    }

    // uses the path nodes to determine the turn order going from initial node to goal node
    private void turnOrder(ArrayList<Node> nodesPathArray){

        for(int i = nodesPathArray.size()-1; i > 0; i--){
            if( (i+1) % 4 == 0){
                //used for readability only.
                System.out.println();
            }
            movesInstructions(nodesPathArray.get(i-1).getNodeState(), nodesPathArray.get(i).getNodeState());
        }
    }

    private void movesInstructions(int[][] parentNode, int[][] currentNode) {
        //currentNode is current state
        //parentNode is node before currentNode (closer to initial state)
        if(currentNode[0][1] == parentNode[1][1]){
            System.out.println("Next move is turning top side right >>>");
        }
        if(currentNode[0][1] == parentNode[2][1]){
            System.out.println("Next move is turning top side left <<<");
        }


        if(currentNode[0][3] == parentNode[1][5]){
            System.out.println("Next move is turning right side away from you >>>");
        }
        if(currentNode[0][3] == parentNode[3][1]){
            System.out.println("Next move is turning right side towards you <<<");
        }


        if(currentNode[0][5] == parentNode[2][3]){
            System.out.println("Next move is turning left side away from you <<<");
        }
        if(currentNode[0][5] == parentNode[3][5]){
            System.out.println("Next move is turning left side towards you >>>");
        }


        if(currentNode[1][3] == parentNode[2][5]){
            System.out.println("Next move is turning back side left <<<");
        }
        if(currentNode[1][3] == parentNode[3][3]){
            System.out.println("Next move is turning back side right >>>");
        }
    }
}
