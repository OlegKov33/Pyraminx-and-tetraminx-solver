import java.util.LinkedList;
import java.util.List;

public class Problem {

/*
 * In the problem class you need to provide two inputs, a starting point and an end point or
 * initial state and goal state respectively. The problem class will attempt to solve the problem using the following steps:
 * 1) matching all the centers
 * 2) sort the bottom side ensuring it has correctly placed edges
 * 3) use one of the given collection of moves to try and reach the goal state.
 *
 * NOTE: The goal state can be found in any of the (3) steps or wouldn't be found at all. Those steps
 * are what a beginner would use when trying to solve the puzzle.
 * There are also System.out.println commented out, they were and can be used to see exactly where you are in the problem.
 */



//you are creating an instance of a class calculations, calculations class is responsible for working things out
Calculations calculations = new Calculations();


//list of nodes that the program can explore, think of it as having an ability to choose where to go.
List<Node> listOfUnexploredNodes = new LinkedList<>();


Node initialState = new Node();
Node goalState = new Node();


//breaking point, is the node that will be shown if the program encounters a problem later on.
Node LastWorkingNode = new Node();


//This is a javadoc. It is used to explan what the code does.
/**
 * @param startNode : initial state, a starting point.
 * @param endNode : goal state, a desirable outcome or an end result.
 */
Problem(Node startNode, Node endNode){
    this.initialState = startNode;
    this.goalState = endNode;
    listOfUnexploredNodes.add(initialState);
    calculations.AddInitialState(startNode);
}


/**
 * This method will beguin searching and trying to get from initial state to goal state.
 */
public void start(){

    //If your initial state equal to your goal state, then you are done.
    if(isGoal(initialState)){
        System.out.println("Goal found!");
        return;
    }


    //crash number is used when you give unreachable node.
    int crashNumber = 0;

    //Step (1)
    while(true){
        if(crashNumber!=10){

            //If the first node in list of unexplored nodes equals to goal state:
            // keep that node, remove all other nodes and leave this loop.
            if(calculations.matchingBases(listOfUnexploredNodes.get(0).nodeState, goalState.nodeState)){
                Node tempNode = new Node();
                tempNode = listOfUnexploredNodes.get(0);
                listOfUnexploredNodes.clear();
                listOfUnexploredNodes.add(tempNode);
                break;
            }


            //this will: find all possible moves, then sort the newly found moves and save them into the same array.
            listOfUnexploredNodes = calculations.sortingNodesPriority( goalState,
                                                                        calculations.GenerateNewState(((LinkedList<Node>) listOfUnexploredNodes).pop(), 1,
                                                                        goalState.nodeState));
            crashNumber++;
        }
        else{
            System.out.println("---\nYour initial state cannot match the goal states centers, please change your initial state and try again.\n---\n"+listOfUnexploredNodes.get(0));
            return;
        }
    }


    //If you encounter an issue here, this will show you what node you put into the loop
    LastWorkingNode = listOfUnexploredNodes.get(0);

    //Step (2)
    while(true){
        try {
            //checks if the first node has 0 displaced sides, if it does, we leave the loop.
            if(calculations.correctlyPlacedSides(listOfUnexploredNodes.get(0).nodeState, goalState.nodeState, 0)){
                break;
            }
            
        } catch (Exception e) {
            System.out.println("The node ran out of possible moves\n");
            calculations.ConstructPath(LastWorkingNode);
            return;
        }


        //loops over all the nodes in list of explored nodes that have correctly placed edges.
        for(int i = 0; i<listOfUnexploredNodes.size();i++){
            listOfUnexploredNodes.addAll(calculations.solvingBottomSide(((LinkedList<Node>) listOfUnexploredNodes).pop(), goalState));
        }
    }

    //System.out.println("Done...");

    if(isGoal(listOfUnexploredNodes.get(0))){
        calculations.ConstructPath(listOfUnexploredNodes.get(0));
        calculations.getFoundNodesList();
        return;
    }


    /*
    5 pyraminx algorithms from speedcubing in order and in relation to my code
    [2,4,3,5, 0,5,1,4]
    [3,0,2,0, 3,0,2]
    [3,1,2,1, 3,1,2]
    [2,5,1,4, 0,3]
    [4,3,0,2, 1,5]
    */


    //Step (3)
    //possible collection of moves is what you would use to try and sort it.
    //if it doesn't work, we move on to another collection.
    int[] possibleMovesCollection = {2,4,3,5, 0,5,1,4};
    LinkedList<Node> temp = calculations.expandingMovesCollection(listOfUnexploredNodes.get(0), possibleMovesCollection);
    if(isGoal(temp.getLast())){
        calculations.ConstructPath(temp.getLast());
        calculations.getFoundNodesList();
        return;
    }


    possibleMovesCollection = new int[]{3,0,2,0, 3,0,2};
    temp = new LinkedList<Node>(calculations.expandingMovesCollection(listOfUnexploredNodes.get(0), possibleMovesCollection));
    if(isGoal(temp.getLast())){
        calculations.ConstructPath(temp.getLast());
        calculations.getFoundNodesList();
        return;
    }


    possibleMovesCollection = new int[]{3,1,2,1, 3,1,2};
    temp = new LinkedList<Node>(calculations.expandingMovesCollection(listOfUnexploredNodes.get(0), possibleMovesCollection));
    if(isGoal(temp.getLast())){
        calculations.ConstructPath(temp.getLast());
        calculations.getFoundNodesList();
        return;
    }


    possibleMovesCollection = new int[]{2,5,1,4, 0,3};
    temp = new LinkedList<Node>(calculations.expandingMovesCollection(listOfUnexploredNodes.get(0), possibleMovesCollection));
    if(isGoal(temp.getLast())){
        calculations.ConstructPath(temp.getLast());
        calculations.getFoundNodesList();
        return;
    }


    possibleMovesCollection = new int[]{4,3,0,2, 1,5};
    temp = new LinkedList<Node>(calculations.expandingMovesCollection(listOfUnexploredNodes.get(0), possibleMovesCollection));
    if(isGoal(temp.getLast())){
        calculations.ConstructPath(temp.getLast());
        calculations.getFoundNodesList();
        return;
    }


    possibleMovesCollection = new int[]{7,0,4,1, 5,6};
    temp = new LinkedList<Node>(calculations.expandingMovesCollection(listOfUnexploredNodes.get(0), possibleMovesCollection));
    if(isGoal(temp.getLast())){
        calculations.ConstructPath(temp.getLast());
        calculations.getFoundNodesList();
        return;
    }

    possibleMovesCollection = new int[]{0,3,1,7, 1,6,0,2};
    temp = new LinkedList<Node>(calculations.expandingMovesCollection(listOfUnexploredNodes.get(0), possibleMovesCollection));
    if(isGoal(temp.getLast())){
        calculations.ConstructPath(temp.getLast());
        calculations.getFoundNodesList();
        return;
    }


    possibleMovesCollection = new int[]{1,4,1,3, 0,2,0,5};
    temp = new LinkedList<Node>(calculations.expandingMovesCollection(listOfUnexploredNodes.get(0), possibleMovesCollection));
    if(isGoal(temp.getLast())){
        calculations.ConstructPath(temp.getLast());
        calculations.getFoundNodesList();
        return;
    }

    possibleMovesCollection = new int[]{4,0,3,1, 2,5};
    temp = new LinkedList<Node>(calculations.expandingMovesCollection(listOfUnexploredNodes.get(0), possibleMovesCollection));
    if(isGoal(temp.getLast())){
        calculations.ConstructPath(temp.getLast());
        calculations.getFoundNodesList();
        return;
    }

    //If none of the algorithms work, you will get the latest node and a way to get to it.
    //after which, you could use it and solve it using online solver such as Grubiks

    System.out.println("The goal was not found, the latest node was:\n"+ listOfUnexploredNodes.get(0));
    calculations.getFoundNodesList();
    return;
}



/**
 * @param givenNode : is the node that you want to check against the goal state.
 * @return : If your node matches the goal state (RETURN TRUE), otherwise (RETURN FALSE)
 */
private boolean isGoal(Node givenNode){
    for(int i = 0; i<givenNode.nodeState.length;i++){
        for(int j = 0; j<givenNode.nodeState[0].length;j++){
            if(givenNode.nodeState[i][j] != goalState.nodeState[i][j]){
                return false;
            }
        }
    }
    return true;
}

}
//Author OlegKov33