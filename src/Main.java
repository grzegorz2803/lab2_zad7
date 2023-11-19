// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

    FileHandler fileHandler = new FileHandler();
    Thread authorThred = new Thread(()->{
       try{
           while (true){
               // wpisz linię lub k aby wyjść
               String line = System.console().readLine("Wpisz nową linię: (lub k aby wyjść)");
               if(line.equalsIgnoreCase("k")){
                   break;
               }
               fileHandler.writeLine(line);
           }
       } catch (InterruptedException e){
           e.printStackTrace();
       }
    });

    Thread writerThred = new Thread(()-> {
        try{
            while (true){
                fileHandler.readLine();
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    });
    authorThred.start();
    writerThred.start();
    try{
        authorThred.join();
        writerThred.join();
    }catch (InterruptedException e){
        e.printStackTrace();
    }
    }
}