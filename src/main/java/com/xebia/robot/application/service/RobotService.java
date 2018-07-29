package com.xebia.robot.application.service;

import com.xebia.robot.application.exception.RobotApplicationException;
import com.xebia.robot.application.models.Robot;
import com.xebia.robot.application.response.RobotActivityReponse;

/**
 * This interface defines general robot like features which will be exposed to public
 * 
 * @author Heena Nagpal on 29-JULY-2018
 */
public interface RobotService {
    
    RobotActivityReponse walk(double distance, Robot robot) throws RobotApplicationException;;

    RobotActivityReponse carry(int weight, Robot robot) throws RobotApplicationException;;

    RobotActivityReponse carryAndWalk(double distance, int weight, Robot robot) throws RobotApplicationException;;

    Float displayPrice(String barcode, Robot robot) throws RobotApplicationException;;
}
