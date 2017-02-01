package com.contaazul;

import com.contaazul.dao.RobotRepository;
import com.contaazul.models.Orientation;
import com.contaazul.models.Robot;
import com.contaazul.utils.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MarsrobotApplication {

    @Autowired
    private RobotRepository robotRepository;


    public static void main(String[] args) {
            SpringApplication.run(MarsrobotApplication.class, args);
    }

    /**
     * This annotation guarantee that this method only will be executed
     * after spring-boot starts up
     */
    @PostConstruct
    public void initializeDB(){
        Tuple<Integer, Integer> tupleS = new Tuple<>(0,0);
        Orientation orientationS = new Orientation(tupleS, "N");
        Robot robotS = new Robot(orientationS);
        robotRepository.save(robotS);
    }
}
