# Pyraminx-and-tetraminx-solver
A simple program used to solve and scramble tetraminx and pyraminx puzzles

> [!Note]
> The only difference between pyraminx and tetraminx is that pyraminx has corner pieces while tetraminx doesn’t. In this repository, I will be referring to tetraminx as pyraminx simply because it is more popular and easier to imagine.

### Instructions on usage:
1. Download an Integrated Development Environment(IDE) of your choice, such as Visual Studio Code, NetBeans, IntelliJ IDEA, Eclipse IDE or another that can run Java OR set up Java Development Kit(JDK) and run it through the command prompt.
2. [Git Clone](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository) the repository or download it with buttons **<>Code** -> **Download**.
3. Open up Main.java file and run.
* **If you wish to solve** your pyraminx, please remove the "**//**" comment lines(13 - 17), comment lines(24 - 27), and replace the int[][] initialState = ...; line with your pyraminx configuration.
* If you are unsure how to set your configuration, please look at the drawn examples of how the pyraminx looks like. Start from the top right and rotate clockwise, repeat the same for the other 2 sides, but be careful with the bottom side.
* **If you wish to scramble** your pyraminx, please remove the "**//**" comment lines(24 - 17), comment lines(13 - 17). On line 6, set the number of turns you wish to try and solve for inside the **scrambler.scramble(_HERE_)**, and run the program.



### Here is an example of what you can expect to see when you run the app
**If you run it as is, you will get the following image:**
![Scramble code](https://github.com/user-attachments/assets/80d7f687-6c5d-449d-be21-f1472d0975b7)

> **And in terms of pyraminx, here is what you would need to do:**

<img src="https://github.com/user-attachments/assets/f3f078be-73d9-4813-981a-99d9ce1f627c" width="400" height="400">
<img src="https://github.com/user-attachments/assets/4c9b3c4b-b998-49aa-8c3f-697bb0887a4a" width="400" height="400">

>**Congratulations! You have successfully scrambled a pyraminx!**

**If you wish to solve it, you need to run the following code and follow the console instructions once again:**
![Solve Code](https://github.com/user-attachments/assets/b0a4af92-bd63-4e11-8f2e-ad54b93da718)

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

If you were to take your pyraminx and look at any side's 4th cell you would see that they all connect to the 4th side **edges**.

If you wish to upload your own scramble, you need to upload it correctly, in the shown case:
* My side 1 would: correspond to the number 0
* My side 2 would: correspond to the number 1
* My side 3 would: correspond to the number 2
* My side 4 would: correspond to the number 3

If you upload it incorrectly and try and run it, you will get an error saying:
> [!CAUTION]
> The inputs are not solvable.

However, there also another error messages:

> [!CAUTION]
> The goal was not found, please check your inputs again.

* In the first message, the algorithm was not able to start because the inputs you gave mismatched. E.g. 5 greens, 1 red, 9 yellow and 9 blue, which is not possible to solve.
* In the second message, your uploaded inputs weren't properly set.


### Developer's notes
After managing to create a fully functional A* pathfinding algorithm, I wanted to make it faster and optimal, but unfortunately, I wasn't able to come up with anything better. Despite my **compareTo** method being able to solve the problem unlike my first attempt, it still wasn't optimal and therefore, after asking DeepSeek about it, I gained 2 versions. The first version was optimal and more efficient than my code but the second option was also optimal but more efficient than the first... at least at first glance. When tested, between 2 DeepSeeks models, the first one was far more reliable because after running 5 different tests it was able to stay consistent and optimal:

**(Cost - number of turns until goal state), (Counter - number of while loop iterations), (Unexplored List - number of nodes generated)**

**deep v2**


Cost - 12, counter - 85699, unexplored list - 455265

Cost - 2, counter - 4, unexplored list - 26

Cost - 4, counter - 31171, unexplored list - 169962

Cost - 7, counter - 3646, unexplored list - 19390

Cost - 13, counter - 51742, unexplored list - 279379


**deep v1**


Cost - 4, counter - 3572, unexplored list - 19853

Cost - 2, counter - 4, unexplored list - 26

Cost - 4, counter - 474, unexplored list - 2605

Cost - 7, counter - 10742, unexplored list - 58784

Cost - 10, counter - 209110, unexplored list - 1027022


**my method**


Cost - 4, counter - 175, unexplored list - 993

Cost - 3, counter - 8, unexplored list - 48

Cost - 4, counter - 28, unexplored list - 163

Cost - 8, counter - 57861, unexplored list - 304817

Cost - 10, counter - 56958, unexplored list - 300945

*
Lastly, my method had the following unexpected error, where the program thinks it needs 2 turns to solve the problem, when really it requires only 1 turn. I am unsure how it occurred or why, but when tested with DeepSeeks V1, the error didn't appear.
![image](https://github.com/user-attachments/assets/407ce7f3-c0d0-4860-87fd-691f6a90e29e)
