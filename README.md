# Path following algorithm in ASCII Map

Problem description [here](https://github.com/softwaresauna/code-challenge)

## Solution  

Program takes ASCII map as an input, finds the position of character @. 
Then follows the path and stops when character x is reached. 

Program will output
* Collected letters (The letters form the same location will not be collected)
* Path as characters
* Error with msg if something is wrong

## Algorithm 

Program adopts input to match existing and proven algorithm for path search on graph.
The Algorithm is Depth-first search, more info on it [here](https://en.wikipedia.org/wiki/Depth-first_search)

The Program has three layers
* First layer. It is responsible for getting input form user.
* Second layer. It converts user input into graph.
* Third layer. It applies Depth-first search on graph.

Each layer has own interface and they implementations are well tested.

Application uses method factories to instantiate particular interface implementation 
Example: Input from file can be replaced with input from console.   

## Star tests

## Start application

### User guide

### Docs

TODO 
- testovi za ovo što imam
- validacija
- logiranje ? 
- read me
- upute 

