package it.polimi.ingsw.server.model;

/**
 * Class for each cell of GameBoard
 * @author Eugenio Facciolo
 */
public class Cell {

    private int row, column, level;
    private boolean isOccupied;
    private boolean dome;
    private Worker worker;

    /**
     * Builder of Cell
     * Set construction level at 0 , set Cell is free , set there is not a Dome
     * @param row the row of the cell
     * @param column the column of the cell
     */
    public Cell(int row, int column) {
        this.level = 0;
        this.isOccupied = false;
        this.dome = false;
        this.worker = null;
        this.row = row;
        this.column = column;
    }

    /**
     * Row of this cell
     * @return cell's row
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Column of this cell
     * @return cell's column
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * Construction level in this cell
     * @return construction level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * If Cell contains a Dome or a Worker
     * @return false if Cell is free , true otherwise
     */
    public boolean getisOccupied() {
        return this.isOccupied;
    }

    /**
     * If Cell contains a Dome
     * @return true if Cell have a dome, false otherwise
     */
    public boolean getDome() {
        return this.dome;
    }

    /**
     * Setter of construction level
     */
    public void levelUp() {
        if(this.level == 3){
            this.level++;
            setDome();
        } else
            this.level ++;
    }

    /**
     * Set a level down
     */
    public void levelDown() {
        this.level --;
    }

    /**
     * Setter if cell is occupied
     * @param condition false if Cell is free , true otherwise
     */
    public void setisOccupied(boolean condition) {this.isOccupied = condition;}

    /**
     * Put a dome in this cell
     */
    public void setDome() {
        this.isOccupied =true;
        this.dome = true;
    }

    /**
     *
     * @return the worker on the cell
     */
    public Worker getWorker() {
        return this.worker;
    }

    /**
     * Setter for worker on the cell
     *
     * @param worker sets on the cell
     */
    public void setWorker(Worker worker) {

            this.worker = worker;

            if(worker != null) {
                this.isOccupied = true;
                worker.setPosition(this);
            }
            else
                this.isOccupied = false;
    }

}
