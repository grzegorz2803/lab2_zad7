import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Author implements Runnable{
    private FileProcessor fileProcessor;
    private Lock lock;
    private Condition writerCondition;

    public Author(FileProcessor fileProcessor, Lock lock, Condition writerCondition){
        this.fileProcessor = fileProcessor;
        this.lock = lock;
        this.writerCondition = writerCondition;
    }

    @Override
    public void run() {
        try {
            while (true) {
                lock.lock();

                fileProcessor.readFromFile();
                writerCondition.signal();
                lock.unlock();
                Thread.sleep(1000);
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        }
}
