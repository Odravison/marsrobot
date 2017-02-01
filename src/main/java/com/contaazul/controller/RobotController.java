package com.contaazul.controller;

import com.contaazul.dao.RobotRepository;
import com.contaazul.exceptions.BadMovementException;
import com.contaazul.models.Orientation;
import com.contaazul.models.Robot;
import com.contaazul.utils.CommandVerify;
import com.contaazul.utils.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private RobotRepository robotRepository;

    /**
     * This method use findAll() method from JPARepositories to
     * get the only one Robot inserted.
     * @return
     */

    public Robot getOneRobot(){
        Robot robot = null;
        for (Robot r :
                robotRepository.findAll()) {
            robot = r;
            break;
        }

        return robot;
    }

    /**
     * This method return as Json where is the actual location of robot.
     * @return Coordinate
     */
    @RequestMapping(method = RequestMethod.GET,
                    value = "/rest/mars/whereami")
    public ResponseEntity<Orientation> whereami (){

        return new ResponseEntity<Orientation>(getOneRobot().getCurrentOrientation(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "/rest/mars/{command}")
    public ResponseEntity<Orientation> move (@PathVariable("command") String command){
        Tuple<Integer, Integer> tupleS = new Tuple<>(0,0);
        Orientation orientationS = new Orientation(tupleS, "N");
        Robot robotS = new Robot(orientationS);

        robotRepository.save(robotS);


        Robot robot = getOneRobot();
        Orientation orientation = null;

        try{
            command = command.toUpperCase();
            CommandVerify.checkCommand(command);

            for (int i = 0; i < command.length(); i++){
                if (((String) ""+command.charAt(i)).equalsIgnoreCase("M")){
                    orientation = robot.move();
                }
                else {
                    orientation = robot.changeOrientation(((String) ""+command.charAt(i)));
                }
            }

            robotRepository.save(robot);
            return new ResponseEntity<Orientation>(orientation, HttpStatus.OK);
        } catch (BadMovementException e){
            return new ResponseEntity<Orientation>(HttpStatus.BAD_REQUEST);
        }
    }
}
