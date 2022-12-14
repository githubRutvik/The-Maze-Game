package main.mazeGame;


import main.mazeObjects.GameObject; 
import main.mazeObjects.Movable;
import main.mazeObjects.Player;
import main.mazeObjects.Position;
import main.mazeMap.MazeMap;

import java.util.ArrayList;
import java.util.Collections;

import static main.mazeMap.MazeMap.EMPTY_SPACE;

/**
 * This class will manage the game objects in the game map.
 *
 */
public class GameMap {

    private final int height;
    private final int width;
    private char[][] map;
    private ArrayList<Integer> random_X;
    private ArrayList<Integer> random_Y;


    GameMap(int mazeHeight, int mazeWidth) {
        this.map = new MazeMap(mazeHeight, mazeWidth).generate();
        this.height = map.length;
        this.width = map[0].length;
        randomize_X();
        randomize_Y();
    }


    char[][] getMap() {
        return map;
    }

    
    public int getHeight() {
        return height;
    }


    public int getWidth() {
        return width;
    }

    /**
     * Here we add the game object icons in the Maze Map.
     */
    void addToMap(GameObject object) {
        map[object.getY()][object.getX()] = object.getIcon();
    }

    /**
     * Object icon removal from the Maze
     */
    void removeFromMap(GameObject object) {
        map[object.getY()][object.getX()] = EMPTY_SPACE;
    }

    /**
     * Generates a list of valid random x-coordinates in the Maze
     * Ensures that there is no duplication of coordinates in the Maze
     */
    private void randomize_X() {
        this.random_X = new ArrayList<>();
        for (int i = 2; i < this.width; i += 4) this.random_X.add(i);
        Collections.shuffle(this.random_X);
    }

    /**
     * Generates a list of valid random x-coordinates in the Maze
     * Ensures that there is no duplication of coordinates in the Maze
     */
    private void randomize_Y() {
        this.random_Y = new ArrayList<>();
        for (int i = 1; i < this.height; i += 2) this.random_Y.add(i);
        Collections.shuffle(this.random_Y);
    }

    /**
     * The returned objects generated by random x and y coordinates are passed through the constructor
     * and then they are randomly scatered over the Maze.
     */
    Position getRandomPosition() {
        return new Position(random_X.remove(0), random_Y.remove(0));
    }


    boolean validateMovement(Player player, int direction) {
        switch (direction) {
            case Movable.DIRECTION_UP:
                return (player.getY() > 1 && this.map[player.getY() - 1][player.getX()] == EMPTY_SPACE);
            case Movable.DIRECTION_DOWN:
                return (player.getY() < height - 2 && this.map[player.getY() + 1][player.getX()] == EMPTY_SPACE);
            case Movable.DIRECTION_LEFT:
                return (player.getX() > 2 && this.map[player.getY()][player.getX() - 2] == EMPTY_SPACE);
            case Movable.DIRECTION_RIGHT:
                return (player.getX() < width - 3 && this.map[player.getY()][player.getX() + 2] == EMPTY_SPACE);
            default:
                return false;
        }
    }

}
