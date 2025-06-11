package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.Divinity;

/**
 * Class of Worker
 * @author Alberto Leo
 */
public class Worker {

    private int index;
    private String color;
    private Divinity divinity;
    private Cell position;

    /**
     * Builder of Worker
     * @param n index of this Worker
     * @param color to distinguish opposing workers from allied workers
     */
    public Worker(int n, String color) {

        this.color=color;
        this.index = n;
        this.divinity = null;
    }

    /**
     * @return index of Worker
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * @return Name of divinity chosen by Player that add behaviour to this Worker
     */
    public Divinity getDivinity(){
        return divinity;
    }

    /**
     * @return the color of the worker
     */
    public String getColor() {
        return this.color;
    }

    /**
     * @return the position
     */
    public Cell getPosition() {
        return this.position;
    }

    /**
     * Setter of Divinity chosen by Player to add a behaviour to this Worker
     * @param divinity the divinity choose
     */
    public void setDivinity(Divinity divinity){
        this.divinity= divinity;
    }

    /**
     * Setter for the position of the worker
     *
     * @param position new position
     */
    public void setPosition(Cell position) {
        this.position = position;
    }

}
