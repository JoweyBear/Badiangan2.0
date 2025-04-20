
package FMember;

import Beneficiary.BeneModel;


public class FMemberModel extends BeneModel{
    private int fmID;
    private int beneID;
    private String fname;
    private String mname;
    private String lname;
    private String rel;
    private int age;
    private String sex;
    private String educ;
    private String occ;
    private String remarks;

    public int getBeneID() {
        return beneID;
    }

    public void setBeneID(int beneID) {
        this.beneID = beneID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEduc() {
        return educ;
    }

    public void setEduc(String educ) {
        this.educ = educ;
    }

    public String getOcc() {
        return occ;
    }

    public void setOcc(String occ) {
        this.occ = occ;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getFmID() {
        return fmID;
    }

    public void setFmID(int fmID) {
        this.fmID = fmID;
    }
}
