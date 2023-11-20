import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Writer implements Runnable{
    private FileProcessor fileProcessor;
    private Lock lock;
    private Condition writerCondition;

    public Writer(FileProcessor fileProcessor, Lock lock, Condition writerCondition) {
        this.fileProcessor = fileProcessor;
        this.lock = lock;
        this.writerCondition = writerCondition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            writerCondition.await();
            fileProcessor.writeToFile();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
