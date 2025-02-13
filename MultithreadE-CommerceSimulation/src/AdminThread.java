class AdminThread extends Thread {
    private int adminId;
    private String username;

    public AdminThread(int adminId, String username) {
        this.adminId = adminId;
        this.username = username;
    }

    @Override
    public void run() {
        try {
            System.out.println("Admin " + username + " sisteme giriş yaptı. ID: " + adminId);

            // Admin ekranını aç
            Anasayfa adminScreen = new Anasayfa(adminId, username);
            adminScreen.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
