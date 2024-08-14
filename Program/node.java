public class node {
    //default values.
    String name_of_node = "none";
    String parent_of_node = "none";
    int[][] data_of_node = new int[4][6];
    int cost_of_node = 0;


    node(String name, String parent, int[][]data, int cost){
        this.name_of_node = name;
        this.parent_of_node = parent;
        this.data_of_node = data;
        this.cost_of_node = cost;
    }


    //empty constructor.
    node(){
    }


    //used in (calculations) class to get data of node.
    public int[] getData(int part){
        return this.data_of_node[part];
    }


    // TO UNCOMMENT OR COMMETN, SELECT A LINE OF CODE, PRESS AND HOLD (LEFT CTRL) AND PRESS (/)
    // NOTE THAT AT A TIME ONLY (1) @OVERRIDE MUST BE UNCOMMENTED.
    
    @Override
    public String toString() {
        String output = "";
        output=
        "Name: \t"+this.name_of_node+"\n"+
        "Parent: "+this.parent_of_node+"\n"+
        "Cost: \t"+this.cost_of_node+"\n"+
        "Data: \t";

        for(int i = 0; i<this.data_of_node.length;i++){
            for(int j =0;j<this.data_of_node[0].length;j++){
                output+=this.data_of_node[i][j];
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
    //     for(int i = 0; i<this.data_of_node.length;i++){
    //         for(int j =0;j<this.data_of_node[0].length;j++){
    //             switch (this.data_of_node[i][j]) {
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