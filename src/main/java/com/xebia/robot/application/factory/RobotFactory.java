package com.xebia.robot.application.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xebia.robot.application.exception.RobotApplicationException;
import com.xebia.robot.application.models.Robot;
import com.xebia.robot.application.models.Robot.Color;

/**
 * Class is used as the factory of the robots, containg the robots in the MAP.
 * 
 * @author Heena Nagpal on 29-JULY-2018.
 */
@Component
public class RobotFactory {
    // This is the in memory list of the robots for the durability and state maintenance of the
    // application it's preferred
    // to maintain this list in database or the file
    private static Map<String, Robot> currentApplicationRobots = new HashMap<String, Robot>();
    private static List<String> idealRobots = new ArrayList<>();

    @Value("${max.robot.limit}")
    private int maxRobotLimit;

    public static void markRobotIdeal(String robotName) {
        idealRobots.add(robotName);
    }

    public static void markRobotBusy(String robotName) {
        idealRobots.remove(robotName);
    }

    public Robot createNewRobot() throws RobotApplicationException {
        if (currentApplicationRobots.size() < maxRobotLimit) {
            Robot robot = new Robot(UUID.randomUUID().toString(), Robot.DEFAULT_CHARGING_STATUS_PERCENTAGE, Color.GREEN,
                    Robot.DEFAULT_MAX_WEIGHT_CAPCITY_IN_KG, Robot.DEFAULT_MAX_WALK_CAPACITY_IN_KM);
            currentApplicationRobots.put(robot.getName(), robot);
            markRobotBusy(robot.getName());
            return robot;
        } else {
            throw new RobotApplicationException("MAX robot creation limit reached.");
        }
    }

    public Robot createNewRobot(Robot robot) throws RobotApplicationException {
        if (currentApplicationRobots.size() < maxRobotLimit) {
            currentApplicationRobots.put(robot.getName(), robot);
            markRobotBusy(robot.getName());
            return robot;
        } else {
            throw new RobotApplicationException("MAX robot creation limit reached.");
        }
    }

    public Robot getExistingRobot(String robotName) throws RobotApplicationException {
        if (currentApplicationRobots.containsKey(robotName)) {
            if (idealRobots.contains(robotName)) {
                Robot robot = currentApplicationRobots.get(robotName);
                markRobotBusy(robotName);
                return robot;
            } else {
                throw new RobotApplicationException("Robot with Name : " + robotName + " is busy.");
            }
        } else {
            throw new RobotApplicationException("No robot with name " + robotName + " exists.");
        }
    }

    /**
     * Method will return the ideal robot.
     * 
     * @return
     */
    public Robot idealRobot() {
        String robotName = idealRobots.get(0);
        markRobotBusy(robotName);
        return currentApplicationRobots.get(robotName);
    }

    /**
     * Set charging of the robot in the application.
     * 
     * @param robotName
     * @param charging
     * @return
     * @throws RobotApplicationException
     */
    public Robot setChargingOftheRobot(String robotName, int charging) throws RobotApplicationException {
        if (currentApplicationRobots.containsKey(robotName)) {
            Robot robot = currentApplicationRobots.get(robotName);
            if (robot.getChargingStatus() + charging > 100) {
                int extraCharging = robot.getChargingStatus() + charging - 100;
                throw new RobotApplicationException("Robot overcharged to " + extraCharging);
            }
            robot.setChargingStatus(robot.getChargingStatus() + charging);
            return robot;
        } else {
            throw new RobotApplicationException("Robot charging can't be set, as robot with the Name : " + robotName + " is not prsent.");
        }
    }
}
