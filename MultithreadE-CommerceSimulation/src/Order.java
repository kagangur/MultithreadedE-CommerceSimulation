import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private int id;
    private int productid;
    private int musteriid;
    private String productname;
    private int adet;
    private String time;  // Siparişin oluşturulma zamanı (timestamp)
    private String customerType; // Müşteri türü (Normal/Premium)
    private int priorityScore; // Dinamik öncelik skoru
    private long waitingTime; // Bekleme süresi (saniye cinsinden)
    private double totalprice;

    public Order() {}

    public Order(int id, int musteriid, int productid, String productname, int adet, String time, String customerType, int priorityScore, long waitingTime, double totalprice) {
        this.id = id;
        this.musteriid = musteriid;
        this.productid = productid;
        this.productname = productname;
        this.adet = adet;
        this.time = time;
        this.customerType = customerType;
        this.priorityScore = priorityScore;
        this.waitingTime = waitingTime;
        this.totalprice = totalprice;
    }

    // Tarihten bekleme süresi hesaplama metodu
    private long calculateWaitingTime(String orderTime) {
        try {
            // Date formatı ile tarih string'ini Date nesnesine çeviriyoruz
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date orderDate = sdf.parse(orderTime);
            long currentTime = System.currentTimeMillis();
            
            // Bekleme süresi saniye cinsinden
            return (currentTime - orderDate.getTime()) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    // Öncelik skoru hesaplama metodu
    public int calculatePriorityScore(long currentTime) {
        int baseScore = customerType.equals("Premium") ? 15 : 10; // Premium: 15, Normal: 10
        return baseScore + (int) (waitingTime * 0.5); // Dinamik öncelik skoru
    }

    // Getter ve Setter metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getproductid() {
        return productid;
    }

    public void setproductid(int productid) {
        this.productid = productid;
    }

    public int getMusteriid() {
        return musteriid;
    }

    public void setMusteriid(int musteriid) {
        this.musteriid = musteriid;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(int priorityScore) {
        this.priorityScore = priorityScore;
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(long waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + ", Customer ID: " + musteriid + ", Product: " + productname +
               ", Quantity: " + adet + ", Priority: " + priorityScore + ", Waiting Time: " + waitingTime;
    }
}
