/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kagan
 */
public class Customer {
    
    private int id;
    private String ad;
    private int bakiye;
    private boolean ispremium;
    private int totalspent;

    public Customer() {
    }

    public Customer(int id, String ad, int bakiye, boolean ispremium, int totalspent) {
        this.id = id;
        this.ad = ad;
        this.bakiye = bakiye;
        this.ispremium = ispremium;
        this.totalspent = totalspent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }

    public boolean isIspremium() {
        return ispremium;
    }

    public void setIspremium(boolean ispremium) {
        this.ispremium = ispremium;
    }

    public int getTotalspent() {
        return totalspent;
    }

    public void setTotalspent(int totalspent) {
        this.totalspent = totalspent;
    }
    
}
