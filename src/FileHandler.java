import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileHandler {
    private String fileContent = "";
    private Lock lock = new ReentrantLock();
    private Condition authorCondition = lock.newCondition();
    private Condition writerCondition = lock.newCondition();

    private void writeLine(String line) throws InterruptedException{
        lock.lock();
        try{
            writerCondition.await(); // autor czeka na odczyt poprzedniej lini
            fileContent +=line +"\n";
            System.out.println("Autor wpisał: " + line); // autor wpisuje kolejną linie
            authorCondition.signal(); // informujemy że linia została dodana
        }finally {
            lock.unlock();
        }
    }
}
