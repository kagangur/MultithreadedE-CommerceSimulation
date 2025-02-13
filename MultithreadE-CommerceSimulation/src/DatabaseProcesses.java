
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kagan
 */
public class DatabaseProcesses {
    
    private Connection con = null;
    
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    
   public double getProductPrice(int productId) {
    String query = "SELECT Price FROM Products WHERE ProductID = ?";
    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getDouble("Price");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0.0; // Hata durumunda 0 döner
}
   
   public int getProductStock(int productId) {
    String query = "SELECT Stock FROM products WHERE ProductID = ?";
    int stock = 0;

    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            stock = resultSet.getInt("Stock");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return stock;
}

   
   public void addLog(int customerId, int orderId, String logType, String details) {
    String query = "INSERT INTO logs (CustomerID, OrderID, LogType, LogDate, LogDetails) VALUES (?, ?, ?, NOW(), ?)";

    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, customerId);
        preparedStatement.setInt(2, orderId); // Eğer sipariş oluşmamışsa 0 gönderebilirsiniz
        preparedStatement.setString(3, logType);
        preparedStatement.setString(4, details);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
public ArrayList<Order> getPendingOrders() {
    ArrayList<Order> orders = new ArrayList<>();
    String query = "SELECT o.OrderID, o.CustomerID, o.ProductID, o.Quantity, o.TotalPrice, o.OrderDate, o.OrderStatus, " +
                   "c.CustomerType, c.CustomerName, " +
                   "TIMESTAMPDIFF(SECOND, o.OrderDate, NOW()) AS WaitingTime, " +
                   "CASE WHEN c.CustomerType = 'Premium' THEN 15 + TIMESTAMPDIFF(SECOND, o.OrderDate, NOW()) * 0.5 " +
                   "     ELSE 10 + TIMESTAMPDIFF(SECOND, o.OrderDate, NOW()) * 0.5 END AS PriorityScore " +
                   "FROM Orders o " +
                   "JOIN Customers c ON o.CustomerID = c.CustomerID " +
                   "WHERE o.OrderStatus = 'Pending' " +
                   "ORDER BY PriorityScore DESC";

    try {
        preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int orderId = resultSet.getInt("OrderID");
            int customerId = resultSet.getInt("CustomerID");
            int productId = resultSet.getInt("ProductID");
            int quantity = resultSet.getInt("Quantity");
            double totalPrice = resultSet.getDouble("TotalPrice");
            String orderDate = resultSet.getString("OrderDate");
            String customerType = resultSet.getString("CustomerType");
            String customerName = resultSet.getString("CustomerName");
            long waitingTime = resultSet.getLong("WaitingTime");
            int priorityScore = resultSet.getInt("PriorityScore");

            // Sipariş nesnesini oluştur ve listeye ekle
            Order order = new Order(orderId, customerId, productId, customerName + " - Product-" + productId, quantity, orderDate, customerType, priorityScore, waitingTime, totalPrice);
            orders.add(order);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orders;
}
    

    public String getCustomerName(int customerId) {
    String query = "SELECT CustomerName FROM customers WHERE CustomerID = ?";
    String customerName = "";

    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, customerId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            customerName = resultSet.getString("CustomerName");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return customerName;
}

   public int addOrder(int customerId, int productId, int quantity, double totalPrice, String status) {
    String query = "INSERT INTO Orders (CustomerID, ProductID, Quantity, TotalPrice, OrderDate, OrderStatus) VALUES (?, ?, ?, ?, NOW(), ?)";
    int generatedOrderId = -1; // Oluşturulan OrderID'yi tutmak için değişken

    try {
        preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, customerId);
        preparedStatement.setInt(2, productId);
        preparedStatement.setInt(3, quantity);
        preparedStatement.setDouble(4, totalPrice);
        preparedStatement.setString(5, status);

        int affectedRows = preparedStatement.executeUpdate();

        // Eğer ekleme başarılıysa, oluşturulan OrderID'yi al
        if (affectedRows > 0) {
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedOrderId = rs.getInt(1); // İlk sütundaki otomatik oluşturulan ID'yi al
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return generatedOrderId; // Oluşturulan OrderID'yi döner
}


public String getOrderStatus(int musteriid, int productid) {
    String query = "SELECT OrderStatus FROM orders WHERE CustomerID = ? and ProductID = ?";
    String orderStatus = "";

    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, musteriid);  // İlk parametreyi set et
        preparedStatement.setInt(2, productid); // İkinci parametreyi set et
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            orderStatus = resultSet.getString("OrderStatus"); // Sipariş durumunu al
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orderStatus; // Sipariş durumunu döndür
}

   public int getUserId(String username) {
    int userId = -1;  // Default olarak -1 döndüreceğiz, eğer kullanıcı bulunmazsa
    String query = "SELECT CustomerID FROM User WHERE Username = ?";
    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, username);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            userId = resultSet.getInt("CustomerID");  // Kullanıcı ID'sini alıyoruz
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return userId;
}
public int getAdminId(String username) {
    int adminId = -1;  // Default olarak -1 döndüreceğiz, eğer admin bulunmazsa
    String query = "SELECT CustomerID FROM User WHERE Username = ? AND IsAdmin = TRUE";  // Admin rolünü kontrol ediyoruz
    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, username);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            adminId = resultSet.getInt("CustomerID");  // Adminin ID'sini alıyoruz
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return adminId;
}
 
    public boolean login(String username, String password) {
        String query = "SELECT * FROM User WHERE Username = ? AND Password = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int customerId = resultSet.getInt("CustomerID");
                boolean isAdmin = resultSet.getBoolean("IsAdmin");

                System.out.println("Giriş başarılı!");
                System.out.println("CustomerID: " + customerId);
                System.out.println("IsAdmin: " + isAdmin);

                if(isAdmin){
                                    return false; // Kullanıcı doğrulandı

                }else{
                                return true; // Kullanıcı doğrulandı

                }
            } else {
                System.out.println("Giriş başarısız! Kullanıcı adı veya şifre hatalı.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getCustomerType(int customerID) {
        String query = "SELECT CustomerType FROM customers WHERE CustomerID = ?";
        String customerType = null;

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, customerID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    customerType = resultSet.getString("CustomerType");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving CustomerType: " + e.getMessage());
        }

        return customerType != null ? customerType : "Unknown"; // Eğer null dönerse 'Unknown'
    }
    
    
    public boolean loginforadmin(String username, String password) {
        String query = "SELECT * FROM User WHERE Username = ? AND Password = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                boolean isAdmin = resultSet.getBoolean("IsAdmin");

                System.out.println("Giriş başarılı!");
                System.out.println("IsAdmin: " + isAdmin);

                return isAdmin; // Kullanıcı doğrulandı
            } else {
                System.out.println("Giriş başarısız! Kullanıcı adı veya şifre hatalı.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try {
            preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                double price = resultSet.getDouble("Price");
                int stock = resultSet.getInt("Stock");


                // Product nesnesi oluştur ve listeye ekle
                Product product = new Product(productId, productName, price, stock);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return products;
    }
    
    public boolean addProduct(String productName, int stock, double price) {
        String query = "INSERT INTO products (ProductName, Stock, Price) VALUES (?, ?, ?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, productName);
            preparedStatement.setInt(2, stock);
            preparedStatement.setDouble(3, price);

            int result = preparedStatement.executeUpdate();
            return result > 0; // Ekleme başarılıysa true döner
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Ürün güncelleme fonksiyonu
     */
    public boolean updateProduct(int productId, String productName, int stock, double price) {
        String query = "UPDATE products SET ProductName = ?, Stock = ?, Price = ? WHERE ProductID = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, productName);
            preparedStatement.setInt(2, stock);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, productId);

            int result = preparedStatement.executeUpdate();
            return result > 0; // Güncelleme başarılıysa true döner
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean updateTotalSpent(int customerId, double amountSpent) {
    String query = "UPDATE customers SET TotalSpent = TotalSpent + ? WHERE CustomerID = ?";

    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setDouble(1, amountSpent); // Harcanan tutarı ekle
        preparedStatement.setInt(2, customerId);     // Kullanıcıyı seç

        int result = preparedStatement.executeUpdate();
        return result > 0; // Güncelleme başarılıysa true döner
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public ArrayList<Log> getAllLogs() {
    ArrayList<Log> logs = new ArrayList<>();
    String query = "SELECT * FROM logs ORDER BY LogDate DESC"; // Son eklenen logları çekmek için

    try {
        preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int logId = resultSet.getInt("LogID");
            int customerId = resultSet.getInt("CustomerID");
            int orderId = resultSet.getInt("OrderID");
            String logType = resultSet.getString("LogType");
            String logDate = resultSet.getString("LogDate");
            String logDetails = resultSet.getString("LogDetails");

            // Log nesnesini oluştur ve listeye ekle
            Log log = new Log(logId, customerId, orderId, logType, logDate, logDetails);
            logs.add(log);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return logs;
}

    
    public double getCustomerBudget(int customerId) {
    String query = "SELECT Budget FROM customers WHERE CustomerID = ?";
    double budget = 0.0;

    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, customerId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            budget = resultSet.getDouble("Budget");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return budget;
}
    
    public String getProductName(int productID) {
    String query = "SELECT ProductName FROM products WHERE ProductID = ?";
    String productname = null;

    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, productID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            productname = resultSet.getString("ProductName");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productname;
}


    /**
     * Ürün silme fonksiyonu
     */
    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE ProductID = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, productId);

            int result = preparedStatement.executeUpdate();
            return result > 0; // Silme başarılıysa true döner
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean updateCustomerBudget(int customerId, double newBudget) {
    String query = "UPDATE customers SET Budget = ? WHERE CustomerID = ?";
    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setDouble(1, newBudget);
        preparedStatement.setInt(2, customerId);

        int result = preparedStatement.executeUpdate();
        return result > 0; // Başarıyla güncellendiyse true döner
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public boolean updateProductStock(int productId, int newStock) {
    String query = "UPDATE products SET Stock = ? WHERE ProductID = ?";
    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, newStock);
        preparedStatement.setInt(2, productId);

        int result = preparedStatement.executeUpdate();
        return result > 0; // Başarıyla güncellendiyse true döner
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public boolean updateOrderStatus(int orderId, String status) {
    String query = "UPDATE orders SET OrderStatus = ? WHERE OrderID = ?";
    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, orderId);

        int result = preparedStatement.executeUpdate();
        return result > 0; // Başarıyla güncellendiyse true döner
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public int getOrderId(int customerId, int productId) {
    String query = "SELECT OrderID FROM orders WHERE CustomerID = ? AND ProductID = ? ORDER BY OrderDate DESC LIMIT 1";
    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, customerId);
        preparedStatement.setInt(2, productId);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("OrderID"); // Sipariş ID'sini döndür
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Eğer sipariş bulunmazsa -1 döner
}
    
    public int getProductId(String productName) {
    String query = "SELECT ProductID FROM products WHERE ProductName = ?";
    try {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, productName);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("ProductID"); // Ürün ID'sini döndür
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Eğer ürün bulunmazsa -1 döner
}
    
/**
 * DatabaseProcesses sınıfına veri tabanını başlatma fonksiyonunu ekliyoruz.
 * Eğer `customers` tablosu boşsa rastgele müşteri eklenmesini sağlayacak.
 */
public void initializeDatabase() {
    String checkCustomersQuery = "SELECT COUNT(*) AS Count FROM customers";
    String insertCustomerQuery = "INSERT INTO customers (CustomerID, CustomerName, Budget, CustomerType, TotalSpent) VALUES (?, ?, ?, ?, ?)";
    String insertUserQuery = "INSERT INTO user (CustomerID, Username, Password, IsAdmin) VALUES (?, ?, ?, ?)";
    int minCustomers = 5;
    int maxCustomers = 10;
    int minBudget = 500;
    int maxBudget = 3000;

    try {
        // Müşteri tablosunu kontrol et
        preparedStatement = con.prepareStatement(checkCustomersQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int customerCount = resultSet.getInt("Count");

        if (customerCount == 0) {
            // Rastgele müşteri sayısını belirle
            int customerToAdd = (int) (Math.random() * (maxCustomers - minCustomers + 1)) + minCustomers;

            int premiumCustomers = 2 + (int) (Math.random() * (customerToAdd - 2)); // En az 2, en fazla toplam müşteri kadar

            for (int i = 0; i < customerToAdd; i++) {
                // Rastgele müşteri bilgileri oluştur
                int customerId = i + 1;

                // RandomName sınıfından bilgiler alınıyor
                String customerName = RandomName.generateFullName(
                    new RandomName().firstNames, 
                    new RandomName().lastNames
                );
                String[] nameParts = customerName.split(" ");
                String username = RandomName.generateUsername(nameParts[0], nameParts[1]);
                String password = RandomName.generatePassword(nameParts[0], nameParts[1], new RandomName().numbers);

                int budget = (int) (Math.random() * (maxBudget - minBudget + 1)) + minBudget;
                String customerType = i < premiumCustomers ? "Premium" : "Standard"; // İlk iki müşteri Premium
                double totalSpent = 0.0;

                // Müşteriyi ekle
                preparedStatement = con.prepareStatement(insertCustomerQuery);
                preparedStatement.setInt(1, customerId);
                preparedStatement.setString(2, customerName);
                preparedStatement.setInt(3, budget);
                preparedStatement.setString(4, customerType);
                preparedStatement.setDouble(5, totalSpent);
                preparedStatement.executeUpdate();

                // Kullanıcı bilgileri oluştur ve ekle
                preparedStatement = con.prepareStatement(insertUserQuery);
                preparedStatement.setInt(1, customerId);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setBoolean(4, false); // Varsayılan olarak admin değil
                preparedStatement.executeUpdate();
            }
            System.out.println(customerToAdd + " müşteri başarıyla eklendi. Premium Müşteriler: " + premiumCustomers);
        } else {
            System.out.println("Veritabanı zaten dolu. Başlatma işlemi yapılmadı.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}    
        
        
    public DatabaseProcesses(){
         String url = "jdbc:mysql://" + DB.host + ":" + DB.port + "/" + DB.dbname+ "?useUnicode=true&characterEncoding=utf8";
         
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseProcesses.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            con = DriverManager.getConnection(url, DB.username, DB.password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcesses.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    
}



