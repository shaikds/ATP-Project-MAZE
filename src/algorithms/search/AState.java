package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.LinkedList;

public abstract class AState {
    public enum mColor {WHITE, GRAY, BLACK}


    private mColor color;
    private int value;
    private AState parent;
    private int cost;
    LinkedList<AState> neighbors;


    // Constructor with position. no State, without a position.
    public AState(int value) {
        if (value < 0) throw new IllegalArgumentException("AState:Value can not be negative");
        this.value = value;
        this.cost = 0;
        this.color = mColor.WHITE;
        neighbors = new LinkedList<>();
    }


    // Getters

    public mColor getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public AState getParent() {
        return parent;
    }

    public int getCost() {
        return cost;
    }

    public LinkedList<AState> getNeighbors() {
        return neighbors;
    }

    // Setters

    public void setColor(mColor color) {
        this.color = color;
    }


    public void setValue(int value) {
        this.value = value;
    }

    public void setParent(AState parent) {
        this.parent = parent;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    // Add a neighbor to neighbors list by given State
    public void addNeighbor(AState neighbor) {
        neighbors.add(neighbor);
    }

    // remove a neighbor from neighbors list by given State
    public void removeNeighbor(AState neighbor) {
        neighbors.remove(neighbor);
    }
}
