import java.io.*;
import java.util.Scanner;

public class FileProcessor {
    private  String fileName;
    private  String content;

    public  FileProcessor(String fileName){
        this.fileName = fileName;
    }
    public synchronized void readFromFile() throws IOException, InterruptedException {
        try (FileReader fileReader = new FileReader(fileName);
             Scanner scanner = new Scanner(fileReader)) {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }
            String[] lines = sb.toString().split("\n");
            System.out.println("Odczytano: "+lines[lines.length-1]);
            Thread.sleep(5000);
        }
    }


    public synchronized void writeToFile(String newLine) throws  IOException, InterruptedException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(newLine+"\n");
            System.out.println("Wpisano: "+newLine);
        }
    }
    public synchronized String getContent(){
        return  content;
    }
}
