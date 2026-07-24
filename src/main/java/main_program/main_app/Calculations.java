package main_program.main_app;


import main_program.utils.Rotator;

import java.util.*;

public class Calculations {
    // take input, take output
    // check if input variables match output variables in terms of data numbers 1:1
    // take first node and add it to the queue
    // start looping through queue exploring nodes that are:
    // a) not been explored before
    // b) seem to be the most closely to the end
    // before exploring nodes, check if the one you are about to explore matches the goal
    private final Node startingNode;
    private final Node finishingNode;
    private final Queue<Node> unexploredNodes = new PriorityQueue<>();
    private final Set<String> exploredStates = new HashSet<>();
    private final Map<String, Node> pathNodes = new HashMap<>();
    private boolean notFoundGoal = true;

    public Calculations(Node inputStartingNode,
                        Node inputFinishingNode) {

        startingNode = inputStartingNode;
        finishingNode = inputFinishingNode;
        unexploredNodes.add(startingNode);
    }

    //TODO change this method to return a list of states till the end
    public List<Node> start() {

        // checks if the input is equal to the goal
        if (startingNode.printNode().equals(finishingNode.printNode())) {
            System.out.println("The input is equal to the goal!");
            return null;
        }

        // checks if the input is valid by counting numbers
        if (!isSolvable()) {
            System.out.println("The inputs are not solvable.");
            return null;
        }

        while (notFoundGoal) {
            if (unexploredNodes.isEmpty()) {
                return null;
            }
            // adds a node to a list if visited nodes, used to determine if the node was explored already
            exploredStates.add(unexploredNodes.peek().printNode());

            // adds a node to a hash list, but unlike the list above, will be used for path construction later
            pathNodes.put(unexploredNodes.peek().getName(), unexploredNodes.peek());

            // removes and explores the most promising node according to compareTo method in main_app.Node.java class
            unexploredNodes.addAll(exploringNode(unexploredNodes.poll()));


            // If the node equals to the goal, tell the user 
            if (unexploredNodes.peek().printNode().equals(finishingNode.printNode())) {
                System.out.println("Goal found!\nCost of node : " + unexploredNodes.peek().getCost()
                        + "\nTotal nodes explored: " + exploredStates.size() + "\nGenerated nodes: " + unexploredNodes.size() + "\n");
                pathNodes.put(unexploredNodes.peek().getName(), unexploredNodes.peek());

                return constructPath(unexploredNodes.peek());

            }
            //if you didn't find the goal within 23,000 nodes, there is a problem.
            if (exploredStates.size() > 23000) {
                notFoundGoal = false;
                System.out.println("The goal was not found, please check your inputs again.");
                return null;
            }
        }

        return null;
    }


    // generates all possible turns from a given node
    public Queue<Node> exploringNode(Node givenNode) {

        Queue<Node> returnList = new PriorityQueue<>();
        Rotator stateRotator = new Rotator(givenNode);
        List<int[][]> listOfNewStates = stateRotator.rotateAll(givenNode);

        for (int[][] state : listOfNewStates) {
            if (!exploredStates.contains(stateToName(state))) {
                returnList.add(
                        new Node(state, givenNode.getCost() + 1, state,
                                stateToName(state), givenNode.getName())
                );
            }
        }
        return returnList;

    }

    // used by exploring main_app.Node to set new node names to their states, similar to main_app.Node.java printNode method
    private String stateToName(int[][] inputState) {
        StringBuilder output = new StringBuilder();
        for (byte i = 0; i < 4; i++) {
            for (byte j = 0; j < 6; j++) {
                output.append(inputState[i][j]);
            }
        }
        return output.toString();
    }


    // checks if there is an equal number of bases and edges for each color (it must be 3 and 3 for each)
    private boolean isSolvable() {
        int numberOfBases = 0;
        int numberOfEdges = 0;

        for (int colourNumber = 0; colourNumber < 4; colourNumber++) {
            for (int side = 0; side < 4; side++) {
                for (int edgeOrBase = 0; edgeOrBase < 3; edgeOrBase++) {
                    if (startingNode.getNodeState()[side][edgeOrBase * 2] == colourNumber) {
                        numberOfEdges++;
                    }
                    if (startingNode.getNodeState()[side][edgeOrBase * 2 + 1] == colourNumber) {
                        numberOfBases++;
                    }
                }
            }

            for (int side = 0; side < 4; side++) {
                for (int edgeOrBase = 0; edgeOrBase < 3; edgeOrBase++) {
                    if (finishingNode.getNodeState()[side][edgeOrBase * 2] == colourNumber) {
                        numberOfEdges--;
                    }
                    if (finishingNode.getNodeState()[side][edgeOrBase * 2 + 1] == colourNumber) {
                        numberOfBases--;
                    }
                }
            }
            if (numberOfBases != 0 || numberOfEdges != 0) {
                return false;
            }
        }
        return true;
    }


    //goes from back to fount:  goal >> node before goal ... initial node
    public List<Node> constructPath(Node finalNode) {
        List<Node> nodesPathArray = new ArrayList<>();
        nodesPathArray.add(finalNode);
        String tempParentNode = finalNode.getParent();


        while (!tempParentNode.equals("none")) {
            nodesPathArray.add(pathNodes.get(tempParentNode));
            tempParentNode = nodesPathArray.getLast().getParent();
        }
        nodesPathArray.add(pathNodes.get(tempParentNode));
        return nodesPathArray;
        //turnOrder(nodesPathArray);
    }

    // uses the path nodes to determine the turn order going from initial node to goal node
    private void turnOrder(List<Node> nodesPathArray) {

        for (int i = nodesPathArray.size() - 1; i > 0; i--) {
            if ((i + 1) % 4 == 0) {
                //used for readability only.
                System.out.println();
            }
            movesInstructions(nodesPathArray.get(i - 1).getNodeState(), nodesPathArray.get(i).getNodeState());
        }
    }

    private void movesInstructions(int[][] parentNode, int[][] currentNode) {
        //currentNode is current state
        //parentNode is node before currentNode (closer to initial state)
        if (currentNode[0][1] == parentNode[1][1]) {
            System.out.println("Next move is turning top side right >>>");
        }
        if (currentNode[0][1] == parentNode[2][1]) {
            System.out.println("Next move is turning top side left <<<");
        }


        if (currentNode[0][3] == parentNode[1][5]) {
            System.out.println("Next move is turning right side away from you >>>");
        }
        if (currentNode[0][3] == parentNode[3][1]) {
            System.out.println("Next move is turning right side towards you <<<");
        }


        if (currentNode[0][5] == parentNode[2][3]) {
            System.out.println("Next move is turning left side away from you <<<");
        }
        if (currentNode[0][5] == parentNode[3][5]) {
            System.out.println("Next move is turning left side towards you >>>");
        }


        if (currentNode[1][3] == parentNode[2][5]) {
            System.out.println("Next move is turning back side left <<<");
        }
        if (currentNode[1][3] == parentNode[3][3]) {
            System.out.println("Next move is turning back side right >>>");
        }
    }
}
