package com.example.abhishek.stylesnsmiles.PojoClass;

/**
 * Created by ABHI on 7/05/18.
 */
public class Packages {
    private String heading;

    private String txt1;
    private String txt2;
    private String txt3;
    private String txt4;
    private String txt5;
    private int price;
    public Packages() {
    }

    public Packages(String heading,String txt1,String txt2,String txt3,String txt4,String txt5, int price) {
        this.heading = heading;
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.txt3 = txt3;
        this.txt4 = txt4;
        this.txt5 = txt5;
        this.price = price;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String getTxt2() {
        return txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public String getTxt3() {
        return txt3;
    }

    public void setTxt3(String txt3) {
        this.txt3 = txt3;
    }

    public String getTxt4() {
        return txt4;
    }

    public void setTxt4(String txt4) {
        this.txt4 = txt4;
    }

    public String getTxt5() {
        return txt5;
    }

    public void setTxt5(String txt5) {
        this.txt5 = txt5;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
