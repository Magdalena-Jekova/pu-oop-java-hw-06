package game;

import java.awt.*;

public class BoardTile {

    public static final int BOARD_TILE_SIZE = 100;
    public static final int TILE_SIDE_COUNT = 8;
    protected int row;
    protected int col;
    protected Color color;

    public BoardTile(int row, int col, Color color){
        this.row   = row;
        this.col   = col;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Метод за визуализиране на полетата на дъската.
     * @param g Graphics object
     */
    public void render(Graphics g){
        int boardTileX = this.col * BOARD_TILE_SIZE;
        int boardTileY = this.row * BOARD_TILE_SIZE;

        g.setColor(Color.GRAY);
        g.fillRect(boardTileX, boardTileY, BOARD_TILE_SIZE, BOARD_TILE_SIZE);

        g.setColor(getColor());
        g.fillRect(boardTileX + 1, boardTileY + 1, BOARD_TILE_SIZE - 3, BOARD_TILE_SIZE - 3);
    }
}