package com.xebia.robot.application.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.robot.application.exception.RobotApplicationException;
import com.xebia.robot.application.factory.RobotFactory;
import com.xebia.robot.application.models.Robot;
import com.xebia.robot.application.response.RobotActivityReponse;
import com.xebia.robot.application.service.RobotService;

@RestController
@RequestMapping("/api/robot/v1.0")
public class RobotController {

    @Autowired
    private RobotService robotService;

    @Autowired
    private RobotFactory robotFactory;

    @PostMapping(path = "/create-default-robot")
    @ExceptionHandler(RobotApplicationException.class)
    public ResponseEntity<Robot> createNewRobot() throws RobotApplicationException {
        Robot robot = robotFactory.createNewRobot();
        RobotFactory.markRobotIdeal(robot.getName());
        return new ResponseEntity<Robot>(robot, CREATED);
    }

    @PostMapping(path = "/create-robot")
    @ExceptionHandler(RobotApplicationException.class)
    public ResponseEntity<Robot> createNewRobot(@RequestBody Robot robot) throws RobotApplicationException {
        Robot createRobot = robotFactory.createNewRobot(robot);
        RobotFactory.markRobotIdeal(createRobot.getName());
        return new ResponseEntity<Robot>(createRobot, CREATED);
    }

    @PostMapping(path = "/walk/{robotName}/{distance}")
    @ExceptionHandler(RobotApplicationException.class)
    public ResponseEntity<RobotActivityReponse> walk(@PathVariable String robotName, @PathVariable double distance) throws RobotApplicationException {
        Robot robot = robotFactory.getExistingRobot(robotName);
        RobotActivityReponse robotActivityReponse = robotService.walk(distance, robot);
        RobotFactory.markRobotIdeal(robot.getName());
        return new ResponseEntity<RobotActivityReponse>(robotActivityReponse, HttpStatus.OK);
    }

    @PostMapping(path = "/set-charging/{robotName}/{percentage}")
    @ExceptionHandler(RobotApplicationException.class)
    public ResponseEntity<Robot> setCharging(@PathVariable String robotName, @PathVariable Integer percentage) throws RobotApplicationException {
        return new ResponseEntity<Robot>(robotFactory.setChargingOftheRobot(robotName, percentage), HttpStatus.OK);
    }

    @PostMapping(path = "/carry/{robotName}/{weight}")
    @ExceptionHandler(RobotApplicationException.class)
    public ResponseEntity<RobotActivityReponse> carry(@PathVariable String robotName, @PathVariable Integer weight) throws RobotApplicationException {
        Robot robot = robotFactory.getExistingRobot(robotName);
        RobotActivityReponse robotActivityReponse = robotService.carry(weight, robot);
        RobotFactory.markRobotIdeal(robot.getName());
        return new ResponseEntity<RobotActivityReponse>(robotActivityReponse, HttpStatus.OK);
    }

    @PostMapping(path = "/carry-and-walk/{robotName}/{distance}/{weight}")
    @ExceptionHandler(RobotApplicationException.class)
    public ResponseEntity<RobotActivityReponse> carryAndWalk(@PathVariable String robotName, @PathVariable double distance, @PathVariable Integer weight)
            throws RobotApplicationException {
        Robot robot = robotFactory.getExistingRobot(robotName);
        RobotActivityReponse robotActivityReponse =  robotService.carryAndWalk(distance, weight, robot);
        RobotFactory.markRobotIdeal(robot.getName());
        return new ResponseEntity<RobotActivityReponse>(robotActivityReponse, HttpStatus.OK);
    }

    @GetMapping(path = "/scan-bar-code/{robotName}/{barcode}")
    @ExceptionHandler(RobotApplicationException.class)
    public ResponseEntity<Float> scanBarCode(@PathVariable String robotName, @PathVariable String barcode) throws RobotApplicationException {
        Robot robot = robotFactory.getExistingRobot(robotName);
        Float value = robotService.displayPrice(barcode, robot);
        RobotFactory.markRobotIdeal(robot.getName());
        return new ResponseEntity<Float>(value, HttpStatus.OK);
    }
}
