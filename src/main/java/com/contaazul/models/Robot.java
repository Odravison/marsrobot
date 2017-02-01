package com.contaazul.models;

import com.contaazul.exceptions.BadMovementException;
import com.contaazul.utils.TupleOrientation;
import com.contaazul.utils.Tuple;

import javax.persistence.*;

@Entity
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Orientation currentOrientation;

    public Robot() {
    }

    public Robot(Orientation initialPosition) {
        this.currentOrientation = initialPosition;
    }

    /**
     * This method move the robot on yourself axis;
     * Example: If the current orientation is "N" and the angle received
     * was "R", means that the current orientation will be "E"
     * @param sideToTurn
     * @return
     */
    public Orientation changeOrientation(String sideToTurn) {
        if(sideToTurn.equalsIgnoreCase("L")){
          this.currentOrientation.setCurrentOrientation(TupleOrientation.valueOf(this.currentOrientation.getCurrentOrientation()).getTurnLeft());
        }
        else {
            this.currentOrientation.setCurrentOrientation(TupleOrientation.valueOf(this.currentOrientation.getCurrentOrientation()).getTurnRight());
        }

        return this.currentOrientation;
    }

    /**
     * This method move the robot to a next location based in you orientation;
     * Example: If the current position is (0,2) and the current orientation is "N" (NORTH)
     * the next position will be (0,3).
     * @return 
     * @throws BadMovementException
     */
    public Orientation move() throws BadMovementException {

        Tuple<Integer, Integer> auxTuple = Tuple.sumTuples(this.currentOrientation.getCurrentPosition(),
                                                            TupleOrientation
                                                                    .valueOf(this.currentOrientation.getCurrentOrientation())
                                                                    .getMovementTuple());


        if (    (auxTuple.getX() >= 0 && auxTuple.getX() <= 4 ) &&
                (auxTuple.getY() >= 0 && auxTuple.getY() <= 4)      ){

            this.currentOrientation.setCurrentPosition(auxTuple);

            return this.currentOrientation;


        }

        throw new BadMovementException("BAD MOVEMENT REQUESTED");

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    public void setCurrentOrientation(Orientation currentOrientation) {
        this.currentOrientation = currentOrientation;
    }
}
