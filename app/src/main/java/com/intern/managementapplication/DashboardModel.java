package com.intern.managementapplication;

public class DashboardModel {

    String id,pname,pbudget,cname,cnumber,category,date,paid,releasedon;

    public DashboardModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPbudget() {
        return pbudget;
    }

    public void setPbudget(String pbudget) {
        this.pbudget = pbudget;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getReleasedon() {
        return releasedon;
    }

    public void setReleasedon(String releasedon) {
        this.releasedon = releasedon;
    }

    public DashboardModel(String id, String pname, String pbudget, String cname, String cnumber, String category, String date, String paid, String releasedon) {
        this.id = id;
        this.pname = pname;
        this.pbudget = pbudget;
        this.cname = cname;
        this.cnumber = cnumber;
        this.category = category;
        this.date = date;
        this.paid = paid;
        this.releasedon = releasedon;
    }
}
