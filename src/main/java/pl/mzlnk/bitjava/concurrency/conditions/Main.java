package pl.mzlnk.bitjava.concurrency.conditions;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank(4, 1000D);

        for(int i = 0; i < 4; i++) {
            Transfer transfer = new Transfer(bank, i);
            Thread t = new Thread(transfer);
            t.start();
        }

    }

}
