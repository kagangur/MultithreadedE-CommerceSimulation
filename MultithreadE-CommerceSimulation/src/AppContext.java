import java.util.ArrayList;

public class AppContext {
    public static boolean isAdminProcessing = false;  // Admin işlemi kontrolü
    public static ArrayList<KullaniciThread> userThreads = new ArrayList<>();  // Kullanıcı thread'lerini saklama

    // Tüm kullanıcı thread'lerini duraklat
    public static void pauseAllUserThreads() {
        for (KullaniciThread thread : userThreads) {
            thread.pauseThread(); // Her bir thread için pause metodu çağrılır
        }
        System.out.println("Tüm kullanıcı thread'leri duraklatıldı.");
        
    }

    // Tüm kullanıcı thread'lerini yeniden başlat
    public static void resumeAllUserThreads() {
        for (KullaniciThread thread : userThreads) {
            thread.resumeThread(); // Her bir thread için resume metodu çağrılır
        }
        System.out.println("Tüm kullanıcı thread'leri yeniden başlatıldı.");
    }

    // Kullanıcı thread'lerini AppContext'e eklemek için metot
    public static void addUserThread(KullaniciThread thread) {
        userThreads.add(thread);
    }

    // Kullanıcı thread'lerini AppContext'ten kaldırmak için metot
    public static void removeUserThread(KullaniciThread thread) {
        userThreads.remove(thread);
    }
}