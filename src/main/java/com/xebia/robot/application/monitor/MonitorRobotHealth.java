package com.xebia.robot.application.monitor;

import com.xebia.robot.application.models.Robot;

/**
 * This interface defines Basic robot health monitoring methods.
 * 
 * @author Heena Nagpal on 29-JULY-2018
 */
public interface MonitorRobotHealth {

    boolean canCarry(int weight);

    boolean powerCheck(Robot r, int charging);

}
