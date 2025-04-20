
package Crop;

import Beneficiary.BeneModel;


public class CropModel extends BeneModel{
    private int beneID;
    private int cropID;
    private String crop;
    private double area;
    private String variety;
    private String classification;
    private String date;
    private String remarks;

    public int getBeneID() {
        return beneID;
    }

    public void setBeneID(int beneID) {
        this.beneID = beneID;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getCropID() {
        return cropID;
    }

    public void setCropID(int cropID) {
        this.cropID = cropID;
    }
    
}
