
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author kagan
 */
public class MusteriScreen extends javax.swing.JFrame {
    DatabaseProcesses processes = new DatabaseProcesses();
    DefaultTableModel model;
    private int userId;
    private String username;
    private int previousProductCount = 0;
    private int currentProductCount = 0;
    /**
     * Creates new form MusteriScreen
     */
    public MusteriScreen(int userId, String username) {
        this.userId = userId;
        this.username = username;
        initComponents();
        model = (DefaultTableModel) urunTable.getModel();
        showProducts();
        uyarilabel.setVisible(false);
        setTitle(username.toUpperCase());
        startCheckTimer();
    }

    private MusteriScreen() {
     initComponents();
    }
    
    public void showProducts(){      //model e tüm tarifleri gönderdiğimiz kısım
        model.setRowCount(0);
    
        ArrayList<Product> products = new ArrayList<Product>();
        products = processes.getProducts();
        int currentProductCount = products.size();
        
        if(products != null){
            for(Product product : products){
                Object[] next = {product.getId(),product.getAd(),product.getFiyat()};
                model.addRow(next);
            }
        }

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        urunTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        adetSpinner = new javax.swing.JSpinner();
        satinalbutton = new javax.swing.JButton();
        uyarilabel = new javax.swing.JLabel();
        cikisyap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        urunTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ürün Adı", "Fiyat"
            }
        ));
        jScrollPane1.setViewportView(urunTable);

        jLabel1.setText("Adet:");

        adetSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));

        satinalbutton.setText("Satın Al");
        satinalbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                satinalbuttonActionPerformed(evt);
            }
        });

        uyarilabel.setForeground(new java.awt.Color(255, 0, 0));
        uyarilabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uyarilabel.setText("Satın Alma İşlemi Başarılı.");

        cikisyap.setText("Çıkış Yap");
        cikisyap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisyapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(adetSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(satinalbutton)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cikisyap, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uyarilabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adetSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(92, 92, 92)
                        .addComponent(satinalbutton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(uyarilabel)
                        .addContainerGap())
                    .addComponent(cikisyap, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void satinalbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satinalbuttonActionPerformed
    synchronized (this) { // Kritik bölge için senkronizasyon
        if (AppContext.isAdminProcessing) {
            JOptionPane.showMessageDialog(this, "Admin işlem yapıyor, lütfen bekleyin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            return; // İşlemi durdur
        }
        int userId = this.userId; // Kullanıcı ID'si
        String customerType = processes.getCustomerType(userId); // Müşteri türü (Normal/Premium)
        int productId = getSelectedProductId(); // Seçilen ürün ID'si
        int quantity = (int) adetSpinner.getValue(); // Sipariş miktarı
        double productPrice = processes.getProductPrice(productId);
        double totalPrice = productPrice * quantity;

        int stock = processes.getProductStock(productId);
        double budget = processes.getCustomerBudget(userId);
        String errorReason = null;

        // 1. Kontroller (Tek noktada)
        if (quantity > 5) {
            errorReason = "Maksimum 5 adet satın alabilirsiniz.";
        } else if (stock < quantity) {
            errorReason = "Yetersiz stok.";
        } else if (budget < totalPrice) {
            errorReason = "Yetersiz bütçe.";
        }

            if(urunTable.getSelectedRow() == -1){
            uyarilabel.setText("Lütfen ürün seçiniz.");
            uyarilabel.setVisible(true);
            return;
            }
            
        
                // 2. Sipariş durumu
        String status = (errorReason != null) ? "Failed" : "Pending";
        int orderId = processes.addOrder(userId, productId, quantity, totalPrice, status);

        // 3. Loglama ve kullanıcıya geri bildirim
        if (errorReason != null) {
            processes.addLog(userId, orderId, "Warning", "Sipariş başarısız. Sebep: " + errorReason);
            JOptionPane.showMessageDialog(this, "Sipariş başarısız. Sebep: " + errorReason, "Hata", JOptionPane.ERROR_MESSAGE);
        } else {
            processes.addLog(userId, orderId, "Info", "Sipariş admin onayına gönderildi.");
            JOptionPane.showMessageDialog(this, "Siparişiniz admin onayına gönderildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
        }
        
        

    }
    }//GEN-LAST:event_satinalbuttonActionPerformed

    private void cikisyapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisyapActionPerformed
        this.setVisible(false);  // Şu anki ekranı kapatıyoruz
    }//GEN-LAST:event_cikisyapActionPerformed

    private int getSelectedProductId() {
    int selectedRow = urunTable.getSelectedRow();  // Kullanıcının seçtiği satırın index'ini alıyoruz
    if (selectedRow != -1) {  // Eğer bir satır seçildiyse
        // Seçilen satırdaki ürün ID'sini alıyoruz (ID'nin tablodaki ilk sütunda olduğunu varsayıyoruz)
        return (int) urunTable.getValueAt(selectedRow, 0);  // 0, ID sütununun index'idir
    }
    return -1;  // Eğer hiçbir satır seçilmediyse -1 döndür
}
    
    
        private void startCheckTimer() {
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            ArrayList<Product> products = new ArrayList<Product>();
            products = processes.getProducts();
            int currentProductCount = products.size();
            if (currentProductCount != previousProductCount) {
                showProducts();
            }
                
                //showProducts();
                
            }
        }, 0,5000); 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MusteriScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MusteriScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MusteriScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MusteriScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new MusteriScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner adetSpinner;
    private javax.swing.JButton cikisyap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JButton satinalbutton;
    private javax.swing.JTable urunTable;
    private javax.swing.JLabel uyarilabel;
    // End of variables declaration//GEN-END:variables
}
