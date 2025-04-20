package Registration;

public class RegModel {
    private int adminID;
    private int beneID;
    private String stat;
    private String caseReg;
    private String date;

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public int getBeneID() {
        return beneID;
    }

    public void setBeneID(int beneID) {
        this.beneID = beneID;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getCaseReg() {
        return caseReg;
    }

    public void setCaseReg(String caseReg) {
        this.caseReg = caseReg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
