package main_program.utils;

import main_program.main_app.Node;

import java.util.ArrayList;

import java.util.List;


public class Rotator {

    private int[] sideOne;
    private int[] sideTwo;
    private int[] sideThree;
    private int[] sideFour;

    public Rotator(Node inputNode){
        setAllSides(inputNode);
    }

    public void setAllSides(Node givenNode) {
        sideOne = givenNode.getNodeSide(0);
        sideTwo = givenNode.getNodeSide(1);
        sideThree = givenNode.getNodeSide(2);
        sideFour = givenNode.getNodeSide(3);
    }


    public List<int[][]> rotateAll(Node inputNode) {
        setAllSides(inputNode);

        List<int[][]> resultList = new ArrayList<>(8);
        resultList.add(rotateFrontTopToRight(inputNode.getNodeState()));
        resultList.add(rotateFrontTopToLeft(inputNode.getNodeState()));
        resultList.add(rotateFrontRightSideTowards(inputNode.getNodeState()));
        resultList.add(rotateFrontRightSideAway(inputNode.getNodeState()));

        resultList.add(rotateFrontLeftSideTowards(inputNode.getNodeState()));
        resultList.add(rotateFrontLeftSideAway(inputNode.getNodeState()));
        resultList.add(rotateBackSideToRight(inputNode.getNodeState()));
        resultList.add(rotateBackSideToLeft(inputNode.getNodeState()));

        return resultList;
    }

    public int[][] rotateFrontTopToRight(int[][] inputState) {

        int[][] newState = copyState(inputState);

        for (int j = 0; j < 3; j++) {
            newState[0][j] = sideThree[j];
            newState[1][j] = sideOne[j];
            newState[2][j] = sideTwo[j];
        }

        return newState;
    }

    public int[][] rotateFrontTopToLeft(int[][] inputState) {

        int[][] newState = copyState(inputState);
        for (int j = 0; j < 3; j++) {
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
        return newState;
    }

    // >>>
    public int[][] rotateFrontRightSideTowards(int[][] inputState) {

        int[][] newState = copyState(inputState);

        for (int j = 0; j < 3; j++) {
            if (j + 4 != 6) {
                newState[0][j + 2] = sideTwo[j + 4]; //array length is 0-5, will throw err.
                newState[1][j + 4] = sideFour[j];

            } else {
                newState[0][j + 2] = sideTwo[0];
                newState[1][0] = sideFour[j];
            }

            newState[3][j] = sideOne[j + 2];
        }

        return newState;
    }

    // <<<
    public int[][] rotateFrontRightSideAway(int[][] inputState) {

        int[][] newState = copyState(inputState);

        for (int j = 0; j < 3; j++) {
            newState[0][j + 2] = sideFour[j];
            if (j + 4 != 6) {
                newState[1][j + 4] = sideOne[j + 2];//same here
                newState[3][j] = sideTwo[j + 4];
            } else {
                newState[1][0] = sideOne[j + 2];
                newState[3][j] = sideTwo[0];
            }
        }
        return newState;
    }

    // right(back side >>>)
    public int[][] rotateFrontLeftSideTowards(int[][] inputState) {

        int[][] newState = copyState(inputState);

        for (int j = 0; j < 3; j++) {
            if (j + 4 != 6) {
                newState[0][j + 4] = sideThree[j + 2];
                newState[2][j + 2] = sideFour[j + 4];
                newState[3][j + 4] = sideOne[j + 4];
            } else {
                newState[0][0] = sideThree[j + 2];
                newState[2][j + 2] = sideFour[0];
                newState[3][0] = sideOne[0];
            }
        }

        return newState;
    }

    public int[][] rotateFrontLeftSideAway(int[][] inputState) {

        int[][] newState = copyState(inputState);

        for (int j = 0; j < 3; j++) {
            if (j + 4 != 6) {
                newState[0][j + 4] = sideFour[j + 4];
                newState[2][j + 2] = sideOne[j + 4];
                newState[3][j + 4] = sideThree[j + 2];
            } else {
                newState[0][0] = sideFour[0];
                newState[2][j + 2] = sideOne[0];
                newState[3][0] = sideThree[j + 2];
            }
        }

        return newState;
    }

    // left(back side >>>)
    public int[][] rotateBackSideToRight(int[][] inputState) {

        int[][] newState = copyState(inputState);
        for (int j = 0; j < 3; j++) {
            if (j + 4 != 6) {
                newState[1][j + 2] = sideThree[j + 4];
                newState[2][j + 4] = sideFour[j + 2];
            } else {
                newState[1][j + 2] = sideThree[0];
                newState[2][0] = sideFour[j + 2];
            }
            newState[3][j + 2] = sideTwo[j + 2];
        }
        return newState;
    }

    public int[][] rotateBackSideToLeft(int[][] inputState) {

        int[][] newState = copyState(inputState);

        for (int j = 0; j < 3; j++) {
            if (j + 4 != 6) {
                newState[3][j + 2] = sideThree[j + 4];//proper
                newState[2][j + 4] = sideTwo[j + 2];//new

            } else {
                newState[3][j + 2] = sideThree[0];//proper
                newState[2][0] = sideTwo[j + 2];//new
            }
            newState[1][j + 2] = sideFour[j + 2];//new
        }
        return newState;
    }

    private int[][] copyState(int[][] inputState) {
        int[][] result = new int[4][6];
        for (byte i = 0; i < 4; i++) {
            result[i] = inputState[i].clone();
        }
        return result;
    }

}
