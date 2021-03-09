package game;

import gameobjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {

    public GameObject[][] gameObjects;
    public GameObject clickedObject;
    private Random random;
    private int randomRow;
    private int randomCol;
    private int totalScoreCounter = 0;
    CustomCollection<GameObject> snakeTiles;

    public GameBoard(){
        this.gameObjects = new GameObject[BoardTile.TILE_SIDE_COUNT][BoardTile.TILE_SIDE_COUNT];
        snakeTiles = new CustomCollection<>();
        random = new Random();

        setGameObjectsOnRandomPositions();

        this.setSize(800,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    /**
     * Метод за визуализиране на всички полета и игрови обекти върху дъската.
     * @param g Graphics object
     */
    @Override
    public void paint(Graphics g) {
        for(int row = 0; row < gameObjects.length; row++){
            for(int col = 0; col < gameObjects.length; col++){
                this.renderGameBoardTiles(g, row, col);
                this.renderGamePiece(g, row, col);
            }
        }
    }

    /**
     * Метод, при извикването на който се инициализират всички игрови обекти върху дъската на случайни позиции.
     */
    public void setGameObjectsOnRandomPositions(){
        setFoodOnRandomPositions();
        setObstaclesOnRandomPositions();
        setSnakeTile();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimensionBasedOnCoordinates(e.getX());

        if(this.clickedObject != null) {
            GameObject snake = this.clickedObject;

            if(this.gameObjects[row][col] == null){
                this.moveGameObject(row, col, snake);
            }

            if(this.gameObjects[row][col].getId().equals("Food")) {
                this.moveGameObjectOnFoodTile(row, col, snake);
                totalScoreCounter += 10;
                if(totalScoreCounter == 300){
                    dispose();
                }
            }
            if (this.gameObjects[row][col].getId().equals("Obstacle")){
                dispose();
            }
        }
        this.repaint();

        if (this.hasBoardObject(row, col)) {
            this.clickedObject = this.getBoardObject(row, col);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Метод за инициализиране на змията,
     * която се намира в един от четирите ъгъла на дъската.
     */
    private void setSnakeTile(){
        int randomSnakeTileCoordinates = random.nextInt(4);
        switch (randomSnakeTileCoordinates){
            case 0:
                gameObjects[0][0] = new Snake(0,0);
                snakeTiles.add(gameObjects[0][0]);
                break;
            case 1:
                gameObjects[0][7] = new Snake(0,7);
                snakeTiles.add(gameObjects[0][7]);
                break;
            case 2:
                gameObjects[7][0] = new Snake(7,0);
                snakeTiles.add(gameObjects[7][0]);
                break;
            case 3:
                gameObjects[7][7] = new Snake(7,7);
                snakeTiles.add(gameObjects[7][7]);
                break;
        }
    }

    /**
     * Метод за инициализиране на препятствията на случайни позиции върху дъската.
     */
    public void setObstaclesOnRandomPositions(){

        for(int i = 0; i < 10; i++){

            randomRow = random.nextInt(8);
            randomCol = random.nextInt(8);

            if(gameObjects[randomRow][randomCol] == null){
                gameObjects[randomRow][randomCol] = new Obstacle(randomRow, randomCol);
            }else{
                i--;
            }
        }
    }

    /**
     * Метод за инициализиране на храната на случайни позиции върху дъската.
     */
    public void setFoodOnRandomPositions(){

        for(int i = 0; i < 30; i++){

            randomRow = random.nextInt(8);
            randomCol = random.nextInt(8);

            if(gameObjects[randomRow][randomCol] == null){
                gameObjects[randomRow][randomCol] = new Food(randomRow, randomCol);
            }else{
                i--;
            }
        }
    }

    /**
     * Метод за визуализиране на полетата на дъската.
     * @param g Graphics object
     * @param row - редът, на който трябва да се постави поле на дъската
     * @param col - колоната, на която трябва да се постави поле на дъската
     */
    private void renderGameBoardTiles(Graphics g, int row, int col){
        BoardTile boardTile = new BoardTile(row, col, Color.decode("#D5F5E3"));
        boardTile.render(g);
    }

    /**
     * Метод за визуализиране на игровите обекти, ако има такива на съответния ред и колона.
     * @param g Graphics object
     * @param row - редът, на който трябва да се визуализира обекта
     * @param col - колоната, на която трябва да се визуализира обекта
     */
    private void renderGamePiece(Graphics g, int row, int col) {

        if(this.hasBoardObject(row, col)) {
            GameObject object = this.getBoardObject(row, col);
            object.render(g);
        }
    }

    /**
     * Метод за придвижване на змията.
     * @param row - редът, на който змията трябва да се премести
     * @param col - колоната, на която змията трябва да се премести
     * @param snake - фигурата, която ще бъде преместена
     */
    private void moveGameObject(int row, int col, GameObject snake) {

        int snakeOriginalRow = snake.getRow();
        int snakeOriginalCol = snake.getCol();

        snake.move(row, col);

        this.gameObjects[snake.getRow()][snake.getCol()] = this.clickedObject;
        this.gameObjects[snakeOriginalRow][snakeOriginalCol] = null;
        this.clickedObject = null;
    }

    /**
     * Метод, при извикването на който змията се премества на поле от дъската върху което има храна
     * и след изяждането й, змията нараства с 1 квадратче.
     */
    private void moveGameObjectOnFoodTile(int row, int col, GameObject snake) {

        int snakeOriginalRow = snake.getRow();
        int snakeOriginalCol = snake.getCol();

        snake.move(row, col);

        this.gameObjects[snake.getRow()][snake.getCol()] = this.clickedObject;
        this.gameObjects[snakeOriginalRow][snakeOriginalCol] = new Snake(snakeOriginalRow, snakeOriginalCol);
        this.snakeTiles.add(this.gameObjects[snakeOriginalRow][snakeOriginalCol]);
        this.clickedObject = null;
    }

    /**
     * Метод, който връща обекта, който се намира на съответния ред и колона.
     * @param row - редът, на който се намира обекта
     * @param col - колоната, на която се намира обекта
     * @return object
     */
    private GameObject getBoardObject(int row, int col) {
        return this.gameObjects[row][col];
    }

    /**
     * Метод, който проверява дали на съответния ред и колона има обект.
     * @param row - редът, на който се проверява дали има обект
     * @param col - колоната, на която се проверява дали има обект
     * @return true, ако има обект и false, ако няма обект на този ред и колона
     */
    private boolean hasBoardObject(int row, int col) {
        return this.getBoardObject(row, col) != null;
    }

    private int getBoardDimensionBasedOnCoordinates(int coordinates) {
        return coordinates / BoardTile.BOARD_TILE_SIZE;
    }
}