package com.contaazul.dao;

import com.contaazul.models.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by odravison on 01/02/17.
 */
public interface RobotRepository extends JpaRepository<Robot, Long>{

}
