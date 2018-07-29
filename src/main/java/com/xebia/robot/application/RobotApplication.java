package com.xebia.robot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Heena Nagpal on 29-JULY-2018
 *
 */
@SpringBootApplication(scanBasePackages = "com.xebia.robot.application")
public class RobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotApplication.class, args);
    }

/*    public static void main(String[] args) {
        RobotService obj = new RobotServiceImpl();
        obj.walk(3.5);
        obj.carry(5);
        obj.walkAndCarry(1,13);
        obj.setCharging(70);
        obj.carry(12);
        obj.walkAndCarry(3, 2);
        obj.displayPrice(18);
    }*/
}
