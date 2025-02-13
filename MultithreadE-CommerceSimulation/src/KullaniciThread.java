class KullaniciThread extends Thread {
    private int userId;
    private String username;
    private boolean paused; // Duraklatma bayrağı
    private final Object lock = new Object(); // Senkronizasyon için bir kilit nesnesi

    public KullaniciThread(int userId, String username) {
        this.userId = userId;
        this.username = username;
        this.paused = false; // Başlangıçta thread çalışır durumda
    }

    @Override
    public void run() {
        try {
            while (true) { // Thread'in çalışmaya devam etmesi için sonsuz döngü
                synchronized (lock) {
                    // Eğer thread duraklatılmışsa beklemeye geç
                    while (paused) {
                        System.out.println("Thread duraklatıldı: " + username);
                        lock.wait(); // Bekleme durumuna geç
                    }
                }

                // Thread'in ana işleyişi
                System.out.println("Kullanıcı " + username + " sisteme giriş yaptı. ID: " + userId);

                // Kullanıcı ekranını aç
                MusteriScreen musteriScreen = new MusteriScreen(userId, username);
                musteriScreen.setVisible(true);

                break; // Thread işini bitirince döngüden çık
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Thread'i durdurmak için çağırılacak metot
    public void pauseThread() {
        synchronized (lock) {
            paused = true;
        }
    }

    // Thread'i devam ettirmek için çağırılacak metot
    public void resumeThread() {
        synchronized (lock) {
            paused = false;
            lock.notify(); // Bekleyen thread'i uyandır
        }
    }
}