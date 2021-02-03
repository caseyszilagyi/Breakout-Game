# Design
### Author: Casey Szilagyi


##Design Goals##
-The design was intended to make it easy to add objects to the game. The different
types of objects present in the game (bricks, balls, paddle) are structured through
an inheritance hierarchy that should make it easier to make a new type of brick or ball

-The design was also inteded to make it easy to incorporate these objects into the collision
detection of the game. All of the objects in the game were checked for collisions each time
the game loop played. Since the rules are already specified for the different type of objects
in the game, it makes it easy to add any individual object into the game

-The game loop was designed to call methods that accomplish everything that needs to be done
in the program. Therefore, adding new functionality should fall under the category of something
that is already being done in the game loop, which should make it easier to implement through
a submethod call.

##High level Design##
-The core class is the Game class, which implements the game loop that is used to play the game.
This class calls methods to update the values (score/lives/levels) of the game, check for collisions,
and update the position of all the objects in the game. The game class just contains the
general functionality of what a game does, while the BreakoutGame class actually deals with making
the objects of the game. The BreakoutDriver class simply makes an instance of the BreakoutGame
class and calls the necessary functions to play the game.

-Each object of the game is a GameObject, and there is an inheritance hierarchy that determines
the properties and methods that each one has. Each GameObject has a variable that shows whether
it is alive, an update method that moves it based on the velocity, and a collide method that
dictates what to do in the event of a collision. The update method is called for every loop,
and the collide method is called for each pair of objects to determine if they have collided
and to do something if they have.

-The BreakoutGame class deals with cheat codes through an event handler. PowerUps are dealt with
through the PowerUpManager that alters different aspects of the state of the game based on a random
number generator.

-There are a few other classes as well. The LevelHandler class deals with the different scenes
that are used between/before/during levels to determine what should be shown. The LevelCreator
class actually makes the levels by making the paddle and balls, as well as the bricks through
reading in a text file.


##Assumptioins/Decisions##
- The assumption was made that all that would ever needed to be done in a game could be encompassed
into the ideas of updating the game properties, updating object positions, and checking for 
  collisions. These are all things that are built into the game loop, so if any other type of
  functionality needed to be created, it would need to be done in relation to one of those
  things, or the game loop would need to be changed.
  
- 


##How to Add New Features##
- Adding a type of brick requires extending the brick class and specifying different methods
or properties that make that brick different from a normal brick. Additionally, the
  LevelCreator file needs to be altered in order to determine what letter/number in the text
  file corresponds to the brick.
  
- Adding a new powerup requires changing around the probabilities of the powerups of the same
type (good/bad) to account for a new one. Then, a method call needs to be made to implement
  the powerup, and also set up a timer that reverts the game back to its original state when the
  power up is done.
  
- Adding a level requires making a new text file, and putting that text file in the arraylist of
text files at the beginning of the BreakoutGame file.
  
- Adding a cheat code requires adding a conditional to the event handler in the BreakoutGame file.
Within that conditional, the effect of the cheat code has to be specified. It is realtively
  easy to add simple cheat coodes, but more complex ones may require more variables to be added
  to alter the state of different GameObjects.

