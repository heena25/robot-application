package com.xebia.robot.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xebia.robot.application.exception.RobotApplicationException;
import com.xebia.robot.application.models.Robot;
import com.xebia.robot.application.monitor.MonitorRobotHealth;
import com.xebia.robot.application.response.RobotActivityReponse;
import com.xebia.robot.application.service.RobotService;

/**
 * Class is used for implementing the robot service for the different robot functionality.
 * 
 * @author Heena Nagpal on 29-JULY-2018.
 */
@Service
public class RobotServiceImpl implements RobotService {
    static int availbleCharging;
    
    @Autowired
    private MonitorRobotHealth monitorRobotHealth;

    @Override
    public RobotActivityReponse walk(double km, Robot robot) throws RobotApplicationException {
        availbleCharging = robot.getChargingStatus();
        int requiredCharging = (int) (km * 1000 / 50);
        if (monitorRobotHealth.powerCheck(robot, requiredCharging)) {
            RobotActivityReponse robotActivityReponse = new RobotActivityReponse();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Walked " + km + "km | Charging Used " + requiredCharging + "% | Remaining  " + robot.getChargingStatus() + "%");
            stringBuilder.append(", Robot HeadLight Color " + robot.getHeadLightColor());
            robotActivityReponse.setRobot(robot);
            robotActivityReponse.setReponse(stringBuilder.toString());
            return robotActivityReponse;
        } else {
            throw new RobotApplicationException("More charging required for the activity.");
        }
    }

    @Override
    public RobotActivityReponse carry(int weight, Robot robot) throws RobotApplicationException {
        if (monitorRobotHealth.canCarry(weight)) {
            int requiredCharging = 2 * weight;
            availbleCharging = robot.getChargingStatus();
            if (monitorRobotHealth.powerCheck(robot, requiredCharging)) {
                RobotActivityReponse robotActivityReponse = new RobotActivityReponse();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(
                        "Carried " + weight + "kg | Charging Used " + requiredCharging + "% | Remaining  " + robot.getChargingStatus() + "%");
                stringBuilder.append(", Robot HeadLight Color " + robot.getHeadLightColor());
                robotActivityReponse.setRobot(robot);
                robotActivityReponse.setReponse(stringBuilder.toString());
                return robotActivityReponse;
            } else {
                throw new RobotApplicationException("More charging required for the activity.");
            }
        } else {
            throw new RobotApplicationException("Can't carry the desired weight : " + weight);
        }
    }

    @Override
    public RobotActivityReponse carryAndWalk(double dist, int weight, Robot robot) throws RobotApplicationException {
        if (monitorRobotHealth.canCarry(weight)) {
            int requiredCharging = (int) (dist * 1000 / 50) + 2 * weight;
            availbleCharging = robot.getChargingStatus();
            if (monitorRobotHealth.powerCheck(robot, requiredCharging)) {
                RobotActivityReponse robotActivityReponse = new RobotActivityReponse();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Walked " + dist + "km and Carried " + weight + "kg | Charging Used " + requiredCharging + "% | Remaining  "
                        + robot.getChargingStatus() + "%");
                stringBuilder.append(", Robot HeadLight Color " + robot.getHeadLightColor());
                robotActivityReponse.setRobot(robot);
                robotActivityReponse.setReponse(stringBuilder.toString());
                return robotActivityReponse;
            } else {
                throw new RobotApplicationException("More charging required for the activity.");
            }
        } else {
            throw new RobotApplicationException("Can't carry the desired weight : " + weight);
        }
    }

    @Override
    public Float displayPrice(String barCode, Robot robot) throws RobotApplicationException {
        if (barCode.startsWith("ABCD")) {
            return (float) 100.00;
        } else {
            throw new RobotApplicationException("Scan failure.");
        }
    }
}
