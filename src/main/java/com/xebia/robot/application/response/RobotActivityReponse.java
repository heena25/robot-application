package com.xebia.robot.application.response;

import java.io.Serializable;

import com.xebia.robot.application.models.Robot;

/**
 * Class usedfor the response of the application. 
 * @author Heena Nagpal on 29-JULY-2018.
 */
public class RobotActivityReponse implements Serializable {
    private static final long serialVersionUID = 5915025479501574515L;
    
    private Robot robot;
    private String reponse;
    public Robot getRobot() {
        return robot;
    }
    public void setRobot(Robot robot) {
        this.robot = robot;
    }
    public String getReponse() {
        return reponse;
    }
    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
