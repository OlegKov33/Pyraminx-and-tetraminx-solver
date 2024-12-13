import java.util.HashMap;

class ArrayElementAppearance {
    public static void main(String[] args) {
        String[] arrayForSorting = new String[10];
        for(int i = 0; i<arrayForSorting.length;i++){
            arrayForSorting[i] = Double.toString(Math.floor((Math.random()*10)));
        }
        ArrayElementCounter.start(arrayForSorting);
    }


    class ArrayElementCounter{
        public static void start(String[] givenArray){
            String currentElement = givenArray[0];
            int amountAppeared = 1;
            HashMap<String, Integer> uniqueElements = new HashMap<String, Integer>();


            System.out.println(" iterating: "+ givenArray[0]);
            for(int i = 1; i<givenArray.length;i++){
                
                if(givenArray[i].equals(currentElement)){
                    amountAppeared+=1;
                }
                else{
                    uniqueElements.merge(currentElement, amountAppeared, Integer::sum);
                    currentElement = givenArray[i];
                    amountAppeared = 1;
                }
                System.out.println(" iterating: "+ givenArray[i]);
            }
            uniqueElements.merge(currentElement, amountAppeared, Integer::sum);
            System.out.println(uniqueElements);
        }
    }
}

