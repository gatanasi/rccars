# Remote Controlled Cars

### Description
Cars are placed on a 15 by 15 grid at particular co-ordinates heading north, and the simple commands Left, right and forward are transmitted to them. The commands must be executed and the final position calculated.
The following examples should be used to demonstrate your code:

### How to Build and Run the Project
This project uses the [Maven](http://maven.apache.org/) build system, you can build the project locally just by typing the following in the console:
```
./mvn package
```
The output of the Maven build is located in the  `target/` directory.

Note that this goal runs all JUnit tests included in the solution.

When using Eclipse, I recommend simply importing the project via the
File->Import->Existing Maven Project. Select the directory
corresponding to this solution.

To run the project, you can execute the generated jar by typing the following:
```
./java -jar RC-Cars.jar
```
You will be asked to enter the Car initial position and the commands to be executed in the following format `X,Y:Commands`

### Explanation of the solution
The domain model is divided in the following main classes:
- Car: represents the remote controlled cars
- Grid: represents the grid where cars are placed
- Position: represents the car's position in the grid
- Command: abstraction for the commands that can be sent to the cars

The RemoteControlledUnit interface was created in order to provide easy extensibility for further remote controlled objects.

Command and Factory patterns were used to achieve a clean solution with loose coupling.

### Assumptions
- Input data will be entered through System.in
- Grid size can be defined only during creation
- Multiple cars are allowed in the grid
- Minimum position is (1,1)
- When a car reaches the grid limit and a forward command is sent, it's position will remain unchanged

### Further improvements
- As the model allows multiple cars in the grid, a collision detector should be implemented