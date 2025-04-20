package Crop.Harvest;

import Crop.CropModel;

public class HarvestModel extends CropModel {

    private int harvestID;
    private int cropID;
    private String crop;
    private String container;
    private int qty;
    private double profit;
    private String date;
    private String remarks;

    public int getHarvestID() {
        return harvestID;
    }

    public void setHarvestID(int harvestID) {
        this.harvestID = harvestID;
    }

    public int getCropID() {
        return cropID;
    }

    public void setCropID(int cropID) {
        this.cropID = cropID;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
