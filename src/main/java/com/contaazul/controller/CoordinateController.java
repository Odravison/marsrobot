package com.contaazul.controller;

import com.contaazul.utils.CalculatorMoves;
import com.contaazul.DAO.CoordinateRepository;
import com.contaazul.exceptions.BadMovementException;
import com.contaazul.models.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * The annotation @Service is to make Coordinatecontroler a Bean
 * that spring have management about
 *
 * The annotation @Transactional its to mark nested transactional
 * methods from the DAO's with DB
 */
@Service
@Transactional
public class CoordinateController {

    @Autowired
    private CoordinateRepository coordinateDAO;
    private CalculatorMoves calculatorMoves;


    public Coordinate move(String command) throws BadMovementException {
        Coordinate coordinate = coordinateDAO.findById(new Long(12341234));
        return calculatorMoves.getInstance().validateMovementAndMove(command,coordinate);
    }

    public Coordinate getCoordinate (){
        return coordinateDAO.findById(new Long(12341234));
    }
}
