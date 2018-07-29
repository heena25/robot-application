package com.xebia.robot.application.monitor.impl;

import org.springframework.stereotype.Service;

import com.xebia.robot.application.models.Robot;
import com.xebia.robot.application.models.Robot.Color;
import com.xebia.robot.application.monitor.MonitorRobotHealth;

/**
 * This class checks the robot health in term of power and weight capacity. Also as it extends the
 * BasicRObotHealth class, we can extend it in future.
 * 
 * @author Heena Nagpal on 29-JULY-2018
 *
 */

@Service
public class MonitorRobotHealthImpl implements MonitorRobotHealth {
    /**
     * Method used to check if the robot can carry input weight or not
     */
    @Override
    public boolean canCarry(int weight) {
        if (weight > 10) {
            System.out.println("Overweight");
            System.out.println("************************************************");
            return false;
        }
        return true;
    }

    /**
     * Method used to check the Robot, have sufficient power or not.
     */
    @Override
    public boolean powerCheck(Robot r, int requiredCharging) {
        int availbleCharging = r.getChargingStatus();
        if (availbleCharging < requiredCharging) {
            System.out.println("Insufficient Charging For Task");
            System.out.println("************************************************");
            return false;
        } else {
            int remaining = availbleCharging - requiredCharging;
            r.setChargingStatus(remaining);
            if (remaining < 15) {
                r.setHeadLightColor(Color.RED);
                System.out.println("*********************************************");
            }
            return true;
        }
    }
}
