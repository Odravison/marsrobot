package com.contaazul.models;


import com.contaazul.utils.Tuple;

import javax.persistence.*;

@Entity
public class Orientation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Tuple<Integer, Integer> currentPosition;
    private String currentOrientation;

    public Orientation() {
    }

    public Orientation(Tuple<Integer, Integer> currentPosition, String currentOrientation) {
        this.currentPosition = currentPosition;
        this.currentOrientation = currentOrientation;
    }

    public Tuple<Integer, Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Tuple<Integer, Integer> currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getCurrentOrientation() {
        return currentOrientation;
    }

    public void setCurrentOrientation(String currentOrientation) {
        this.currentOrientation = currentOrientation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
