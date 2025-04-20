package LiveStock;

import Beneficiary.BeneModel;

public class LSModel extends BeneModel {
    private int lsID;
    private int beneID;
    private String ls;
    private String classi;
    private int heads;
    private int age;
    private String exp;
    private String remarks;

    public int getLsID() {
        return lsID;
    }

    public void setLsID(int lsID) {
        this.lsID = lsID;
    }

    public int getBeneID() {
        return beneID;
    }

    public void setBeneID(int beneID) {
        this.beneID = beneID;
    }

    public String getLs() {
        return ls;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public String getClassi() {
        return classi;
    }

    public void setClassi(String classi) {
        this.classi = classi;
    }

    public int getHeads() {
        return heads;
    }

    public void setHeads(int heads) {
        this.heads = heads;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
