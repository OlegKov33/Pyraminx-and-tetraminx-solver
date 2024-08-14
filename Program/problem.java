import java.util.LinkedList;
public class problem {

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
calculations calculations = new calculations();

//list of nodes that the program can explore, think of it as having an ability to choose where to go.
LinkedList<node> list_of_unexplored_nodes = new LinkedList<>();

node initial_state = new node();
node goal_state = new node();

//breaking point, is the node that will be shown if the program encounters a problem later on.
node breaking_point = new node();

//This is a javadoc. It is used to explan what the code does.
/**
 * @param start : initial state, a starting point.
 * @param end : goal state, a desirable outcome or an end result.
 */
problem(node start, node end){
    this.initial_state = start;
    this.goal_state = end;
    list_of_unexplored_nodes.add(initial_state);
    calculations.Add_Initial(start);
}


/**
 * This method will beguin searching and trying to get from initial state to goal state.
 */
public void start(){

    //If your initial state equal to your goal state, then you are done.
    if(isGoal(initial_state)){
        System.out.println("Goal found!");
        return;
    }


    //System.out.println("Matching the Base...");
    //crash number is used when you give unreachable node.
    int crash_number = 0;

    //Step (1)
    while(true){
        if(crash_number!=10){

            //If the first node in list of unexplored nodes equals to goal state:
            // keep that node, remove all other nodes and leave this loop.
            if(calculations.All_Bases(list_of_unexplored_nodes.getFirst().data_of_node, goal_state.data_of_node)){
                node tempNode = new node();
                tempNode = list_of_unexplored_nodes.getFirst();
                list_of_unexplored_nodes.clear();
                list_of_unexplored_nodes.add(tempNode);
                break;
            }


            //this will: find all possible moves, then sort the newly found moves and save them into the same array.
            list_of_unexplored_nodes = calculations.Sort_By_Base_And_Cost(  goal_state,
                                                                            calculations.Looking_And_Placing_Edges( list_of_unexplored_nodes.pop(), 1,
                                                                                                                    goal_state.data_of_node));
            crash_number++;
        }
        else{
            System.out.println("---\nYour initial state cannot match the goal states centers, please change your initial state and try again.\n---\n"+list_of_unexplored_nodes.getFirst());
            return;
        }
    }
    //System.out.println("Done...");
    //System.out.println("Checking bottom side...");

    //If you encounter an issue here, this will show you what node you put into the loop
    breaking_point = list_of_unexplored_nodes.getFirst();

    //Step (2)
    while(true){
        try {
            //checks if the first node has 0 displaced sides, if it does, we leave the loop.
            if(calculations.Correctly_Placed_Sides(list_of_unexplored_nodes.getFirst().data_of_node, goal_state.data_of_node, 0)){
                break;
            }
            
        } catch (Exception e) {
            System.out.println("The node ran out of possible moves\n");
            calculations.ConstructPATH(breaking_point);
            return;
        }


        //loops over all the nodes in list of explored nodes that have correctly placed edges.
        for(int i = 0; i<list_of_unexplored_nodes.size();i++){
            list_of_unexplored_nodes.addAll(calculations.Solving_For_Bottom_Side(list_of_unexplored_nodes.pop(), goal_state));
        }
    }

    //System.out.println("Done...");

    if(isGoal(list_of_unexplored_nodes.getFirst())){
        calculations.ConstructPATH(list_of_unexplored_nodes.getFirst());
        calculations.getLIST();
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
    int[] possible_collection_of_moves = {2,4,3,5, 0,5,1,4};
    LinkedList<node> temp = calculations.expanding_nodes(list_of_unexplored_nodes.get(0), possible_collection_of_moves);
    if(isGoal(temp.getLast())){
        calculations.ConstructPATH(temp.getLast());
        calculations.getLIST();
        return;
    }


    possible_collection_of_moves = new int[]{3,0,2,0, 3,0,2};
    temp = new LinkedList<node>(calculations.expanding_nodes(list_of_unexplored_nodes.get(0), possible_collection_of_moves));
    if(isGoal(temp.getLast())){
        calculations.ConstructPATH(temp.getLast());
        calculations.getLIST();
        return;
    }


    possible_collection_of_moves = new int[]{3,1,2,1, 3,1,2};
    temp = new LinkedList<node>(calculations.expanding_nodes(list_of_unexplored_nodes.get(0), possible_collection_of_moves));
    if(isGoal(temp.getLast())){
        calculations.ConstructPATH(temp.getLast());
        calculations.getLIST();
        return;
    }


    possible_collection_of_moves = new int[]{2,5,1,4, 0,3};
    temp = new LinkedList<node>(calculations.expanding_nodes(list_of_unexplored_nodes.get(0), possible_collection_of_moves));
    if(isGoal(temp.getLast())){
        calculations.ConstructPATH(temp.getLast());
        calculations.getLIST();
        return;
    }


    possible_collection_of_moves = new int[]{4,3,0,2, 1,5};
    temp = new LinkedList<node>(calculations.expanding_nodes(list_of_unexplored_nodes.get(0), possible_collection_of_moves));
    if(isGoal(temp.getLast())){
        calculations.ConstructPATH(temp.getLast());
        calculations.getLIST();
        return;
    }


    possible_collection_of_moves = new int[]{7,0,4,1, 5,6};
    temp = new LinkedList<node>(calculations.expanding_nodes(list_of_unexplored_nodes.get(0), possible_collection_of_moves));
    if(isGoal(temp.getLast())){
        calculations.ConstructPATH(temp.getLast());
        calculations.getLIST();
        return;
    }

    possible_collection_of_moves = new int[]{0,3,1,7, 1,6,0,2};
    temp = new LinkedList<node>(calculations.expanding_nodes(list_of_unexplored_nodes.get(0), possible_collection_of_moves));
    if(isGoal(temp.getLast())){
        calculations.ConstructPATH(temp.getLast());
        calculations.getLIST();
        return;
    }


    possible_collection_of_moves = new int[]{1,4,1,3, 0,2,0,5};
    temp = new LinkedList<node>(calculations.expanding_nodes(list_of_unexplored_nodes.get(0), possible_collection_of_moves));
    if(isGoal(temp.getLast())){
        calculations.ConstructPATH(temp.getLast());
        calculations.getLIST();
        return;
    }

    possible_collection_of_moves = new int[]{4,0,3,1, 2,5};
    temp = new LinkedList<node>(calculations.expanding_nodes(list_of_unexplored_nodes.get(0), possible_collection_of_moves));
    if(isGoal(temp.getLast())){
        calculations.ConstructPATH(temp.getLast());
        calculations.getLIST();
        return;
    }

    //If none of the algorithms work, you will get the latest node and a way to get to it.
    //after which, you could use it and solve it using online solver such as Grubiks

    System.out.println("The goal was not found, the latest node was:\n"+ list_of_unexplored_nodes.getFirst());
    calculations.getLIST();
    return;
}



/**
 * @param givenNode : is the node that you want to check against the goal state.
 * @return : If your node matches the goal state (RETURN TRUE), otherwise (RETURN FALSE)
 */
private boolean isGoal(node givenNode){
    for(int i = 0; i<givenNode.data_of_node.length;i++){
        for(int j = 0; j<givenNode.data_of_node[0].length;j++){
            if(givenNode.data_of_node[i][j] != goal_state.data_of_node[i][j]){
                return false;
            }
        }
    }
    return true;
}

}
//Author OlegKov33