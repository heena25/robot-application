package com.xebia.robot.application.exception;

/**
 * Exception class for the robot application.
 * 
 * @author Heena Nagpal on 29-JULY-2018.
 */
public class RobotApplicationException extends Exception {
    private static final long serialVersionUID = -6781915508759424133L;

    public RobotApplicationException() {
        super();
    }

    public RobotApplicationException(String arg0) {
        super(arg0);
    }

    public RobotApplicationException(Throwable arg0) {
        super(arg0);
    }

    public RobotApplicationException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
}
