
# Shortest Path PacMan Maze

A Java Swing Project that take two points inside a maze (boolean matrix) and output the shortest path, including the possibilities of teleport.

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)



## Usage

In order to setup the entry and leaving point you have to change the end and start variable

```java
    static int[] end = new int[]{3, 3};
    static int[] start = new int[]{9, 16};
```

You can add teleport by setting the matrix:

```java
     static int[][][] teleport = {
        {
                {9, 0},        
                {9, 18}        
        },
        {
                {9, 18},       
                {9, 0}          
        },
```
## Screenshots

- The green blocks are those who were visited by the alghoritm
- The red one is the start and finish
- The black ones is the shortest path finded

![App Screenshot](screen.png)

