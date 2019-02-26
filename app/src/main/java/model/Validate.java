package model;

import java.text.DecimalFormat;

public class Validate {
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

}
