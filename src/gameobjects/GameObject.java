package gameobjects;

import java.awt.*;

public class GameObject {

    public static final int OBJECT_TILE_SIZE = 50;
    protected int row;
    protected int col;
    protected Color color;
    protected String id;

    public GameObject(int row, int col, String id, Color color){
        this.row   = row;
        this.col   = col;
        this.id    = id;
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Метод за визуализиране на игровите обекти.
     * @param g Graphics object
     */
    public void render(Graphics g) {
        int objectTileX = this.col * (OBJECT_TILE_SIZE*2);
        int objectTileY = this.row * (OBJECT_TILE_SIZE*2);

        g.setColor(getColor());
        g.fillRect(objectTileX + 20, objectTileY + 30, OBJECT_TILE_SIZE, OBJECT_TILE_SIZE);
    }

    /**
     * Метод за придвижване на змията.
     * @param row - редът, на който змията ще се премести
     * @param col - колоната, на която змията ще се премести
     */
    public void move(int row, int col){
        this.row = row;
        this.col = col;
    }
}