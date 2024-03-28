<h1 align="center">Darwin World</h1>

<h3 align="center"> Description </h3>
Simulation of evolution in the world inhabited by animals.

Each day of the simulation consists of the following actions in the given order:

- removing dead animals
- moves of animals
- grass consumption
- reproduction
- growth of new grass

Each animal has: its own genotype, which defines the animal's way of movement, energy, which is responsible for whether the animal can participate in reproduction.
  
Rules on the map: 
If two or more animals are in the same field where grass grows, the animal with the greatest energy eats the grass, the same principle applies to breeding, you can choose two ways for new grass to appear: 1. grass appears more often at the equator of the map, i.e. a horizontal strip in the middle, 
2. grass appears more often in fields where some animals have died. 

Reproduction also has its rules: 
When a new animal appears, it receives copies of their genes in proportion to the amount of energy of its parents, randomly either the left part of the first parent and the right part of the second one or vice versa, and there are also random mutations that cause the change of some genes into random others and you can choose an option that will cause some of the genes to be exchanged. 

Animals can move around the map at will (depending on their genotype, of course), but one field per move, when an animal goes beyond the map from the right or left side, it is moved to its opposite end, and when it tries to enter the map from above or from the bottom, it will be turned the other way and stay in place. 

The simulation lasts until all the animals die. 
Additionally, you can run many simulations at once and they run multi-threaded.
The simulation can be paused at any time. During the pause, you can click on a given animal and view its statistics. After clicking on the animal, it is "tracked" and its statistics will be displayed while the simulation is running until it is paused again. 
During the pause, you can also highlight places where grass appears more often and places where there are animals with the genotype that is most common on the map. 
Map statistics are saved after each day and before starting the simulation you can choose to save these statistics to a CSV file after the simulation is finished. 

Detailed data on the simulation configuration can be found in the photos below, but these instructions are written in Polish. 

This project was created by Mateusz Sacha and Łukasz Zegar for an Object oriented programming course at AGH University of Kraków.

<h3 align="center"> Tutorial do Menu Symulacji </h3>
<img align="center" width="1514" alt="Input" src="https://github.com/lvk4z/ProjektPO/assets/126269821/9e1fbf34-e9b8-46d8-b3b3-defcc9ee0115">

<h3 align="center"> Tutorial do Wyświetlanej Symulacji </h3>
<img align="center" width="1514" alt="mapa" src="https://github.com/lvk4z/ProjektPO/assets/126269821/0bf83d86-d7f7-49a6-9c7e-abf9c72a841c">

