package com.contaazul;

import com.contaazul.dao.RobotRepository;
import com.contaazul.models.Orientation;
import com.contaazul.models.Robot;
import com.contaazul.utils.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarsrobotApplication {

    @Autowired
    private RobotRepository robotRepository;


    public static void main(String[] args) {

        SpringApplication.run(MarsrobotApplication.class, args);

    }
}
