# Java Pac-Man

## Description  
A simple clone of the classic Pac-Man arcade game, built with pure Java and Swing.  
Control Pac-Man through a grid-based maze, eat all the pellets, avoid (or outsmart) the ghosts, and rack up as many points as you can before losing all three lives!

---

## Features  
- **Grid-Based Movement**: Pac-Man and ghosts move exactly one cell at a time, never clipping through walls.  
- **Ghost AI**: Randomized patrol behavior with chance to turn at intersections.  
- **Pellets (Food)**: Consume all the pellets to reset the maze.  
- **Lives System**: You start with three lives; collisions with a ghost reset you to the spawn point until you run out.  
- **Game Over & Restart**: When your lives hit zero, a “GAME OVER” screen appears. Press any key to restart.  
- **Clean Architecture**: SOLID principles, Factory for entity creation, Observer for input, MVC-style game panel.

---

## Project Structure
src/
├─ factory/ # EntityFactory: creates Wall, Food, PacMan, Ghost
│ └─ EntityFactory.java
├─ entities/ # Core game objects
│ ├─ Entity.java
│ ├─ MovableEntity.java
│ ├─ Wall.java
│ ├─ Food.java
│ ├─ PacMan.java
│ └─ Ghost.java
├─ input/ # Keyboard input handler (Observer)
│ └─ InputHandler.java
├─ ui/ # Heads-up display: score and lives
│ └─ ScoreBoard.java
├─ MapLoader.java # Parses the tileMap into walls, food, ghosts, Pac-Man
├─ GamePanel.java # Main game loop + rendering (View + Controller)
└─ MainFrame.java # Entry point: creates the JFrame and starts GamePanel

resources/
└─ *.png # Sprite images for walls, Pac-Man, ghosts


Controls
Arrow Keys — Move Pac-Man up, down, left, or right
Any Key (after Game Over) — Restart the level

##How It Works
MapLoader
Reads a String[] tileMap, where
X and O are walls,
spaces are pellets (food),
P is Pac-Man’s spawn,
b, o, p, r are ghosts of different colors.
Populates lists of walls, foods, ghosts, and the single PacMan instance.

EntityFactory
Loads sprite images and constructs game entities on demand.

GamePanel
Implements the game loop via a Swing Timer.
Calls each entity’s update() method (movement, collision, AI) and then repaint().
Renders walls, pellets, ghosts, Pac-Man, and the HUD.

MovableEntity
Base class for anything that moves (Pac-Man, ghosts).
Handles position, velocity, collision with walls, and reset logic.

PacMan & Ghost
Override update() to implement grid-aligned movement and turning logic.
PacMan reacts to keyboard input via setDesiredDirection().
Ghost picks random directions at intersections or when blocked.

ScoreBoard
Tracks score and lives.
On ghost collision:
If lives remain, resets positions;
If no lives remain, flags Game Over and stops the timer.

Contribution
Feel free to open issues or submit pull requests! Ideas for expansion:
More sophisticated ghost AI (BFS, A* pursuit algorithms)
Power-up pellets (make ghosts “edible”)
Multiple levels with different mazes
Sound effects and animations
High-score saving and display

