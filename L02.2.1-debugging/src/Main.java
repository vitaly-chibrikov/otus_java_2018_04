public class Main {
    public static void main(String[] args) throws InterruptedException {
        int i=10;
        int j=200;
        while (i<j) {
            i++;
            Printer.print("increasing i to "+i);
            Thread.sleep(1000);
        }

    }
}
