package gameobjects;

import java.awt.*;

public class Obstacle extends GameObject {
    public Obstacle(int row, int col) {
        super(row, col, "Obstacle", Color.decode("#5499C7"));
    }
}