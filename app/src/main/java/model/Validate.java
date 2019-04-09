package model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Validate implements Serializable {
    private double userResult;
    private double machineResult;



    public Validate(double userResult, double machineResult) {
        this.userResult = userResult;
        this.machineResult = machineResult;
    }

    public double getMachineResult() {
        return machineResult;
    }

    public double getUserResult() {
        return userResult;
    }

    public boolean validateOperation(){
        return Double.compare(machineResult,userResult) == 0?true:false;

    }

    @Override
    public String toString() {
        return "Answer => " + machineResult + " ||\n" + "Your answer => " + userResult;

    }
}
