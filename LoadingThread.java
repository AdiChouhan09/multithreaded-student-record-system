public class LoadingThread extends Thread {
    private final String message;

    public LoadingThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.print("\r" + message);
                for (int j = 0; j <= i; j++) {
                    System.out.print(".");
                }
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            interrupt();
        }
        System.out.println();
    }
}
