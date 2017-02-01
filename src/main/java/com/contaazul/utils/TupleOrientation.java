package com.contaazul.utils;

public enum TupleOrientation {
    N("N", new Tuple<Integer, Integer>(0, 1),   "E", "W"),
    E("E", new Tuple<Integer, Integer>(1, 0),   "S", "N"),
    S("S", new Tuple<Integer, Integer>(0, -1), "W", "E"),
    W("W", new Tuple<Integer, Integer>(-1, 0), "N", "S");

    private final String orientation;
    private final Tuple<Integer, Integer> movementTuple;
    private final String turnRight;
    private final String turnLeft;

    TupleOrientation(String orientation, Tuple<Integer, Integer> movementTuple, String turnRight, String turnLeft){
        this.orientation = orientation;
        this.movementTuple = movementTuple;
        this.turnRight = turnRight;
        this.turnLeft = turnLeft;
    }

    public String getOrientation(){
        return this.orientation;
    }

    public Tuple<Integer, Integer> getMovementTuple(){
        return this.movementTuple;
    }

    public String getTurnRight() {
        return turnRight;
    }

    public String getTurnLeft() {
        return turnLeft;
    }
}
