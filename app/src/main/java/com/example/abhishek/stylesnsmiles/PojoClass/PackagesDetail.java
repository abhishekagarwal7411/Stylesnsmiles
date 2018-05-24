package com.example.abhishek.stylesnsmiles.PojoClass;

public class PackagesDetail {
    String pcktitle,pckone,pcktwo,pckthree,pckfour,pckprice;
    public PackagesDetail() {

    }

    public PackagesDetail(String pcktitle,String pckone,String pcktwo,String pckthree,String pckfour,String pckprice) {
        this.pcktitle = pcktitle;
        this.pckone = pckone;
        this.pcktwo = pcktwo;
        this.pckthree = pckthree;
        this.pckfour = pckfour;
        this.pckprice = pckprice;


    }

    public String getPcktitle() {
        return pcktitle;
    }

    public void setPcktitle(String pcktitle) {
        this.pcktitle = pcktitle;
    }

    public String getPckone() {
        return pckone;
    }

    public void setPckone(String pckone) {
        this.pckone = pckone;
    }

    public String getPcktwo() {
        return pcktwo;
    }

    public void setPcktwo(String pcktwo) {
        this.pcktwo = pcktwo;
    }

    public String getPckthree() {
        return pckthree;
    }

    public void setPckthree(String pckthree) {
        this.pckthree = pckthree;
    }

    public String getPckfour() {
        return pckfour;
    }

    public void setPckfour(String pckfour) {
        this.pckfour = pckfour;
    }

    public String getPckprice() {
        return pckprice;
    }

    public void setPckprice(String pckprice) {
        this.pckprice = pckprice;
    }
}
