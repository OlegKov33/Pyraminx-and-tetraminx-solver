# Pyraminx-and-tetraminx-solver
> [!Note]
> The only difference between pyraminx and tetraminx is that pyraminx has corner pieces while tetraminx doesn’t. In this repository, I will be referring to tetraminx as pyraminx simply because it is more popular and easier to imagine.

### Background
This is my attempt to create an AI algorithm that can be used to both scramble and solve the pyraminx puzzle. Usually in order to solve it you would need to use a pathfinding algorithm such as A*, breadth-first search, depth-first search or some other algorithm. However, in my case, I attempted to solve it as a beginner would, which is by following a set of steps that include: matching all centre pieces for each side, sorting one side fully with all edge pieces correctly placed and using a set of moves such as (F, R, U, R’, U’ F’) to finish the puzzle.

Because of the way I designed the algorithm, it had a couple of limitations such as: 
1)	It didn’t have a standard. What I mean by that is in Step (1) I use A* as when I explore possible nodes, I then sort them and explore the most promising one. In the Step (2) I use a form of iterative deepening where I explore all possible nodes while pruning non-promising nodes in the process. 
2)	If the algorithm requires more than 4 turns in Step (2) it will fail, which will mean that you have to use something like grubiks to solve the rest of the pyraminx.
3)	If you give a wrong format and the algorithm isn’t able to find the correct number, the code will stop and print a likely problem such as “The node ran out of possible moves”

On the other hand, the algorithm is able to find you a solution in a reasonable amount of cases and it has been tested on 10,20, 50 and 100 turn scrambles.


### Here is how you can try it yourself:
1.	Install Visual Studio Code, NetBeans, IntelliJ IDEA or some other software that can run Java projects.
2.	Install the extensions in VSC for Java on the left sidebar fifth icon down or press (Ctrl+Shift+X) together
3.	Copy the files in this GitHub repository, follow this guide [here](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository)
4.	Run the main.java or press F5
Alternatively, uncomment and comment lines of code in main.java if you wish to scramble, unscramble or create a custom problem
To solve uncomment lines (43-4, 58-9) or (43, 47, 58-59)
To scramble it uncomment lines (43-44, 63-4) or (43, 47, 63-4)

### Here is an example of what you can expect to see when you run the app
**If you run it as is, you will get the following image:**
![Scramble code](https://github.com/user-attachments/assets/459f508d-3dc1-4654-a358-4a195431ea78)

> **And in terms of pyraminx, here is what you would need to do:**

<img src="https://github.com/user-attachments/assets/f3f078be-73d9-4813-981a-99d9ce1f627c" width="400" height="400">
<img src="https://github.com/user-attachments/assets/4c9b3c4b-b998-49aa-8c3f-697bb0887a4a" width="400" height="400">

>**Congratulations! You have successfully scrambled a pyraminx!**

**If you wish to solve it, you need to run the following code and follow the console instructions once again:**
![Solve Code](https://github.com/user-attachments/assets/f3f358dc-da3b-4871-a1c2-2649c796601a)

> **And in terms of pyraminx, here is what you need to do:**

<img src="https://github.com/user-attachments/assets/09b3eef1-55c3-4e9c-857e-6f3383b89588" width="400" height="400">
<img src="https://github.com/user-attachments/assets/8ebce793-ed4c-463a-a14e-96b0723834fb" width="400" height="400">

> **Congratulations! You have solved a pyraminx, well-done!**

### Here is how you can input your own pyraminx:
The pyraminx has 4 unique sides, in code I call them 0-3, however here I will call them sides 1-4. 
* Here is the image of sides 1 and 2

<img src="https://github.com/user-attachments/assets/8ebce793-ed4c-463a-a14e-96b0723834fb" width="400" height="400">

* Here is the image of sides 2 and 3
<img src="https://github.com/user-attachments/assets/ec4636d9-cf2a-43a7-aa83-1719362641ee" width="400" height="400">

*Here is the image of sides 1 and 4  

<img src="https://github.com/user-attachments/assets/822703a6-6e1e-4c92-8f54-5a27169acbcf" width="400" height="400">

*Confused? Let me explain:
Each side has 6 cells on each side. The **odd** cells are called <ins>"**Centers**"</ins> and **even** cells are called <ins>**Edges**</ins>.
![image](https://github.com/user-attachments/assets/3c35603a-55f1-47d1-b436-e838f0f1d8a7)

In terms of 4th side, it looks like this in relation to the other sides:  
![image](https://github.com/user-attachments/assets/e3e61b28-2895-49c9-bbbe-44aa5423078d)

If you were to take your pyraminx and look at any side's 4th cell you will see that they all connect to the 4th side **edges**.

If you wish to upload your own scramble, you need to upload it correctly, in the shown case:
* My side 1 would: correspond to the number 0
* My side 2 would: correspond to the number 1
* My side 3 would: correspond to the number 2
* My side 4 would: correspond to the number 3

If you upload it incorrectly and try and run it, you will get an error saying:
> [!CAUTION]
> Your initial state cannot match the goal states centers, please change your initial state and try again.

However, there are other error messages such as:

> [!CAUTION]
> The goal was not found, the latest node was:
> and
> The node ran out of possible moves

* In the first message, your uploaded state was in the wrong format for the code to solve perhaps instead of giving (0...,1...,2...,3...) you gave(0...,2...,3...,1...)
* In the second message the algorithm was able to solve the bottom side but was unable to further solve the puzzle.
* In the third message the algorithm was unable a solution with 4 move scramble.


### 3 Steps
The code has 3 main steps which it is following:
1) Step (1) - Match all the **centre** pieces on all sides
2) Step (2) - Solve the bottom layer using 4 moves that the "Solving_For_Bottom_Side" method creates.
> [!NOTE]
> When you are asking my code to generate all possible states that it can be from a given state, you are looking at 8 possibilities, if you are asking for 4 moves, you are doing this equation: 8^4 = 4096 moves that will be generated and if you want to generate 6, you are looking at 262144 moves...
3) Step (3) - The algorithm will use one of its "possible_collection_of_moves" to try and get to the goal state, which if it does you will get a full path from your initial state to your goal state.

### In terms of algorithms 
1) Step (1) - Is using A* as it focuses on getting the best node, and tries exploring it as well as sorting the array in case that node isn't efficient.
2) Step (2) - Is using iterative deepening alongside pruning. You expand a node up to 4 moves, if the 2nd moves aren't ideal from the "Solving_For_Bottom_Side" point of view they get removed. After which you try and put them back into solved position while pruning the ones that are not sorted and those that are worse than or equal to their parent.
3) Step (3) - Using user input to turn the pyraminx in a way that the user has programmed, and either finding a goal state or saying it hasn't been found and here's what the last state looked like: ...

