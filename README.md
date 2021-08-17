# Arkanoid Game  
1. [Introduction](#introduction)
2. [Dependencies](#dependencies)  
3. [Installation](#installation)
4. [Simulation](#simulation)


## Introduction
As part of the OOP course we were asked to implement a version of the old and familiar Arkanoid game.</br>
The game contains multiple stages with increasing difficulty levels and varied backgrounds.</br>
The game is implemented in Java using OOP principles:
* Use of polymorphism and inheritance.
* Familiarity with basic design patterns in OOP like the Observer pattern.
* Using java collections and data structures.
* Creating game levels with different difficulties.
* keep open/closed principle states.
* Working with GUI.

## Dependencies
* Windows / Linux / macOS
* Git
* Keyboard that contains: Spacebar, "P" button, right and left arrows.
* Apache Ant
## Installation
1. Clone the repository:  
    ```
    $ git clone https://github.com/ben6066/Arkanoid.git
    ```
2. Install [Apache Ant](https://ant.apache.org/bindownload.cgi)
3. [Apache Ant Installation Video Windows 10](https://www.youtube.com/watch?v=3eaW81yYIqY&t=353s&ab_channel=xscourse)
4. Open CMD/Git Bash in the cloned directory and run one of the following commands:
    ```
    $ ant run // Runs all the stages one after one
    $ ant run -Dargs="1 2 4" // Runs specific stages (1, 2 and 4)
    ```
5. Press the "P" button in order to pause the game and "Space" to resume.

## Simulation
![Arkanoid GIF](https://user-images.githubusercontent.com/58342591/129721598-06b924f3-4489-45d3-8f63-75ac38a94ed0.gif)
