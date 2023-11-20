import java.text.FieldPosition;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args){
        String fileName = "file.txt";
        FileProcessor fileProcessor = new FileProcessor(fileName);
        Lock lock = new ReentrantLock();
        Condition writerCondition = lock.newCondition();
        Scanner scanner = new Scanner(System.in);

        Thread authorThread = new Thread(new Author(fileProcessor, lock, writerCondition));
        Thread writerThread = new Thread(new Writer(fileProcessor, lock, writerCondition,scanner));

        writerThread.start();
        authorThread.start();

        try{
            writerThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        scanner.close();
    }
}
