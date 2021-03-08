package gameobjects;

import java.awt.*;

public class Food extends GameObject {
    public Food(int row, int col) {
        super(row, col, "Food", Color.decode("#F1948A"));
    }
}