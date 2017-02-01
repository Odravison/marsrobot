package com.contaazul.utils;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class Tuple<X, Y> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public final Integer x;
    public final Integer y;

    public Tuple() {
        this.x = null;
        this.y = null;
    }

    public Tuple(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public static Tuple<Integer, Integer> sumTuples(Tuple<Integer, Integer> tuple1, Tuple<Integer, Integer> tuple2){
        Integer x = tuple1.getX() + tuple2.getX();
        Integer y = tuple1.getY() + tuple2.getY();

        return new Tuple<Integer, Integer>(x,y);
    }
}
