package com.xebia.robot.application.models;

import java.io.Serializable;

/**
 * Model represents the robot model
 * 
 * @author Heena Nagpal on 29-JULY-2018
 *
 */
public class Robot implements Serializable {
    private static final long serialVersionUID = 1710693003599894209L;

    public enum Color {
        GREEN, RED
    }

    public static final int DEFAULT_CHARGING_STATUS_PERCENTAGE = 100;
    public static final int DEFAULT_MAX_WEIGHT_CAPCITY_IN_KG = 10;
    public static final int DEFAULT_MAX_WALK_CAPACITY_IN_KM = 5;

    private String name;
    private int chargingStatus;
    private Color headLightColor;
    private int weightCapcity;
    private int walkCapcity;
    
    public Robot(){
    }

    public Robot(String name, int chargingStatus, Color headLightColor, int weightCapcity, int walkCapcity) {
        super();
        this.name = name;
        this.chargingStatus = chargingStatus;
        this.headLightColor = headLightColor;
        this.weightCapcity = weightCapcity;
        this.walkCapcity = walkCapcity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChargingStatus() {
        return chargingStatus;
    }

    public void setChargingStatus(int chargingStatus) {
        this.chargingStatus = chargingStatus;
    }

    public Color getHeadLightColor() {
        return headLightColor;
    }

    public void setHeadLightColor(Color headLightColor) {
        this.headLightColor = headLightColor;
    }

    public int getWeightCapicity() {
        return weightCapcity;
    }

    public void setWeightCapicity(int weightCapcity) {
        this.weightCapcity = weightCapcity;
    }

    public int getWalkCapcity() {
        return walkCapcity;
    }

    public void setWalkCapcity(int walkCapcity) {
        this.walkCapcity = walkCapcity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + chargingStatus;
        result = prime * result + ((headLightColor == null) ? 0 : headLightColor.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + walkCapcity;
        result = prime * result + weightCapcity;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Robot other = (Robot) obj;
        if (chargingStatus != other.chargingStatus)
            return false;
        if (headLightColor != other.headLightColor)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (walkCapcity != other.walkCapcity)
            return false;
        if (weightCapcity != other.weightCapcity)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Robot [name=" + name + ", chargingStatus=" + chargingStatus + ", headLightColor=" + headLightColor + ", weightCapcity="
                + weightCapcity + ", walkCapcity=" + walkCapcity + "]";
    }
}
