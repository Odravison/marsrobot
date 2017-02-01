package com.contaazul.controller;

import com.contaazul.exceptions.BadMovementException;
import com.contaazul.models.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * Created by odravison on 31/01/17.
 */

/**
 * This is a rest controler to serve as interface between
 * WEB and the CoordinateController
 */

@RestController
public class RobotController {

    /**
     * An instance management by Spring.
     */
    @Autowired
    private CoordinateController coordinateController;


    /**
     * This method return as Json where is the actual location of robot.
     * @return Coordinate
     */

    @RequestMapping(method = RequestMethod.GET,
                    value = "/rest/mars/whereami")
    public ResponseEntity<Coordinate> whereami (){
        Coordinate coordinate = coordinateController.getCoordinate();
        return new ResponseEntity<Coordinate>(coordinate, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "/rest/mars/{command}")
    public ResponseEntity<Coordinate> move (@PathVariable("command") String command){
        try{
            Coordinate coordinate = coordinateController.move(command);
            return new ResponseEntity<Coordinate>(coordinate, HttpStatus.OK);
        } catch (BadMovementException e){
            return new ResponseEntity<Coordinate>(HttpStatus.BAD_REQUEST);
        }
    }
}
