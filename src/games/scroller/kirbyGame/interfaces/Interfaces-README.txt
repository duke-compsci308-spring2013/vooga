Here is where you should define the interfaces you want your sprites to implement. Remember, 
interfaces serve as a way to group sprites when handling collisions. (eg. If you want a Koopa 
and a Turtle to be handled the same way when they collide with a player, you should consider 
having them implement an IEnemy interface.) The reason interfaces are in the actual game and 
not the game framework is because interfaces are a game specific thing. The game designer should 
define what methods they want their interfaces to implement as well as what interfaces (groupings) 
they envision for their sprites. 

