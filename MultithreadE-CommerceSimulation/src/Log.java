/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kagan
 */
public class Log {
    
    private int id;
    private int customerid;
    private String kategori;
    private boolean ispremium;
    private String productname;
    private int adet;
    private String time;
    private String result;
    private String logType;
    private String logDate;
    private String logDetails;
    private int orderId;

    public Log(int id, int customerid, int orderId, String logType, String logDate, String logDetails) {
        this.id = id;
        this.customerid = customerid;
        this.orderId = orderId;
        this.logType = logType;
        this.logDate = logDate;
        this.logDetails = logDetails;
        
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Log() {
    }

    public Log(int id, int customerid, String kategori, boolean ispremium, String productname, int adet, String time, String result) {
        this.id = id;
        this.customerid = customerid;
        this.kategori = kategori;
        this.ispremium = ispremium;
        this.productname = productname;
        this.adet = adet;
        this.time = time;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public boolean isIspremium() {
        return ispremium;
    }

    public void setIspremium(boolean ispremium) {
        this.ispremium = ispremium;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
}
