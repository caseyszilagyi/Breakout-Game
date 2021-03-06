# Game Plan
### Casey Szilagyi

## Interesting Breakout Variants
- I saw a variant where you were able to choose the speed of the ball and it resulted in a higher point multiplier the faster the ball was going.
  I think this was cool because it gives the user more control over exactly how they want to play, and adds an interesting risk/reward aspect to the game.

- I like the devilish version of the game because it doesn't involve a static background. 
  Instead, the scene of the game is constantly moving.
  This makes it much more interesting for the player because it is harder to know what to expect.

- I liked that jet ball and some of the other variants had moving bricks, it made it more interesting.
I would definitely like to attempt to make some type of movement, whether it be the bricks or the surrounding scene.
  
## Paddle Ideas
- Change the speed of the ball depending on where on the paddle the ball hits

- Warp from one side of the screen to the other.

- Change the direction of the ball depending on where on the paddle the ball hits,
this gives the user some control over the direction, otherwise they have no control over what happens.

## Block Ideas
- Blocks that drop powerups is the main idea as to how I would incorporate powerups into the game

- I also think I will add blocks that take multiple hits to be destroyed, because it adds to the difficulty of the game substantially

- I would also like to have blocks with power ups built in, that way the user can plan their gameplay a little more.
The blocks would show what powerup they have.

- Blocks that when destroyed, destroy all the blocks in the same column/row (not sure which, maybe both? Depends on the level)

## Power-up Ideas
- An upgrade/downgrade paddle size powerup.
  A bigger paddle is obviously better, and I think it would be cool to also have the reverse so that the user has to try and avoid certain "power ups"

- A power that reverses the direction of the keys.
So the left arrow key makes the paddle go right and the right makes it go left.

- A power up that allows the ball to pass through all blocks and completely destroy them for 1 paddle hit.
So when you get the powerup, you get 1 paddle hit for the ball to pass through everything, and the next paddle hit is back to normal.
  
## Cheat Key Ideas
- Make the paddle span the whole screen so that the bouncing ball can just be watched

- Make all of the bricks only take 1 hit to clear

- Slow the speed of the ball down

- A permanent power ball, so that it can go through any brick and destroy it

## Level Descriptions
- I think the first level I'm going to build is going to be a 'jail cell' type of level,
where there are a few cubes and the middle of each cube has a power up, but the power up is surrounded by blocks that take more hits to break
  
a= high durability block
x= power up block


aaa     aaa     aaa     aaa
axa     axa     axa     axa
aaa     aaa     aaa     aaa

- The second level I'm going to try to make some type of movable block.
These blocks will be towards the bottom, and will make it harder to predict what will happen.
  Above these blocks, there will simply be various blocks that drop power ups

b = some kind of regular block that drops powerups
m = moving block

bbbb bbbbbb bbbbbb bbbbb bbb

bbbb bbbbbb bbbbbb bbbbb bbb

m       m         m       m

    m         m       m

- The third level I want to make is just a big cube, with a diagonal streak of blocks that destroy the entire row that they are in.
Every time one of these blocks is destroyed, the ball will speed up, making hitting the remaining ones more difficult
  
c = regular block
r = powerup block that desroys entire row

cccccr

ccccrc

cccrcc

ccrccc

crcccc

rccccc


(there will be more rows of blocks because the blocks will be more rectangular than this)


## Class Ideas
- "Game" - This class will be abstract and therefore will have to be extended to create a specific game.
Will essentially be the engine with which to run games, and will handle the game loop and deal with collisions and different objects involved in the game.
  One useful method will be a "CheckCollisions" method, which will see if there are any objects that are colliding in the current frame
  
- "SpriteManager" - This class will be the manager that deals with all of the different objects and their collisions.
One useful method will be the a "RemoveSprites" method, which removes all sprites (such as bricks) that are no longer needed
  
- "Sprite" - This class will be abstract, and will simply represent an object in the game.
One useful method will be the "collide" method, which checks if a different object is in contact with the current one.
  
- "BreakoutGame" - This class will extend the Game class, and will simply add things that make the game actually function.
The game class is designed as the engine, but the BreakoutGame class will deal with everything that makes it specific to breakout.
  One useful method will be the "Initialize Level" method, which will create the bricks, ball, and paddle.
  
- "BreakoutDriver" - This class will essentially make an instance of a BreakoutGame, and then call it.
The only useful method will be the "start" method that calls BreakoutGame methods.
  
- "Brick" - Will implement the Sprite class, and make a brick object that can be destroyed.
One useful method will be the "lowerHealth" method, which will reduce the health of the brick by 1"

  
