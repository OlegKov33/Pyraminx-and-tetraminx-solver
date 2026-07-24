package main_program.utils;
//This class generates a new node using an input as its starting point.


import main_program.main_app.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scrambler {
    private final int[][]nodeState = new int[4][6];
    private final Node innerNode;

    public Scrambler(){
        innerNode = new Node();
    }

    public Scrambler(Node inputNode){
        innerNode = inputNode;
    }

    // scrambles by turnsNumber times
    public int[][] scramble(int turnsNumber){

        Rotator turnRotator = new Rotator(innerNode);
        List<int[][]> stateList = new ArrayList<>(turnsNumber+1);
        int[][] result = innerNode.getNodeState();
        stateList.add(result);

        for (int i = 0; i < turnsNumber; i++){

            int randomNumber = (int)Math.floor(Math.random()*8);

            switch (randomNumber){

                case 0:
                    result = turnRotator.rotateFrontTopToRight(result);
                    stateList.add(result);
                    break;
                case 1:
                    result = turnRotator.rotateFrontTopToLeft(result);
                    stateList.add(result);
                    break;
                case 2:
                    result = turnRotator.rotateFrontRightSideTowards(result);
                    stateList.add(result);
                    break;
                case 3:
                    result = turnRotator.rotateFrontRightSideAway(result);
                    stateList.add(result);
                    break;


                case 4:
                    result = turnRotator.rotateFrontLeftSideTowards(result);
                    stateList.add(result);
                    break;
                case 5:
                    result = turnRotator.rotateFrontLeftSideAway(result);
                    stateList.add(result);
                    break;
                case 6:
                    result = turnRotator.rotateBackSideToRight(result);
                    stateList.add(result);
                    break;
                case 7:
                    result = turnRotator.rotateBackSideToLeft(result);
                    stateList.add(result);
                    break;
            }
        }

        // used for display ONLY
        for(int[][] state : stateList){
            showNode(state);
        }
        return result;
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

}
//Author OlegKov33