package LiveStock.Disposal;

import LiveStock.LSModel;

public class DisposalModel extends LSModel{
    private int lsID;
    private int disID;
    private double profit;
    private String date;
    private String remarks;

    public int getLsID() {
        return lsID;
    }

    public void setLsID(int lsID) {
        this.lsID = lsID;
    }

    public int getDisID() {
        return disID;
    }

    public void setDisID(int disID) {
        this.disID = disID;
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
}
