package Hazard;

public class HazardModel {
    private int hazardID;
    private String desc;
    private double latt;
    private double longt;

    public int getHazardID() {
        return hazardID;
    }

    public void setHazardID(int hazardID) {
        this.hazardID = hazardID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getLatt() {
        return latt;
    }

    public void setLatt(double latt) {
        this.latt = latt;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }
    
}
