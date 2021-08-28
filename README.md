# Path following algorithm for ASCII Map

Problem description [here](https://github.com/softwaresauna/code-challenge)

## Solution  

Program takes ASCII map as an input, finds the position of start character. 
Then follows the path and stops when end character is reached. 

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
Example: Input from terminal can be replaced with default input.   

## Run tests

run `mvn test`

## Run application

First you need to package application. Run `mvn clean package` 

Then run `java -jar target/path-find.jar`

### How pass optional arguments

-f for file path 
-s start character
-e end character 

Example

`java -jar target/path-find.jar -f src/test/resources/path/intersections.txt -s @ -e x`

It loads file form `src/test/resources/path/intersections.txt` start character is set to @
and end character is set to x

### User guide

It is in user-guide/PathFollowingAlgorithmASCIIMap.pdf

## Author

Name: Ivan Gavlik

Contact info ivangavlik963@gmail.com


