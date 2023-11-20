import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Writer implements Runnable{
    private FileProcessor fileProcessor;
    private Lock lock;
    private Condition writerCondition;
    private Scanner scanner;

    public Writer(FileProcessor fileProcessor, Lock lock, Condition writerCondition, Scanner scanner) {
        this.fileProcessor = fileProcessor;
        this.lock = lock;
        this.writerCondition = writerCondition;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        try {
            while (true) {
                lock.lock();
                writerCondition.await();
                System.out.println("Podaj nową linie: ");
                String newLine = scanner.nextLine();
                if(newLine.equals("exit")){
                    System.exit(1);
                }
                fileProcessor.writeToFile(newLine);
                lock.unlock();
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
