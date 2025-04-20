
package EvacSite;


public class EvacModel {
    private int evacID;
    private String name;
    private double latt;
    private double longt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getEvacID() {
        return evacID;
    }

    public void setEvacID(int evacID) {
        this.evacID = evacID;
    }
}
