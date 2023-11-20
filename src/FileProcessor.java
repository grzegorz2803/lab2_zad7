import java.io.*;

public class FileProcessor {
    private  String fileName;
    private  String content;

    public  FileProcessor(String fileName){
        this.fileName = fileName;
    }
    public synchronized void readFromFIle() throws IOException, InterruptedException{
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine())!=null){
                sb.append(line).append("\n");
            }
            content = sb.toString();
            System.out.println("Autor: Odczytał zawartość pliku");
        }
    }
    public synchronized void writeToFile() throws  IOException, InterruptedException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write("Pisarz napisał nową linie. \n");
            System.out.println("Pisarz wpisał nową linie");
        }
    }
    public synchronized String getContent(){
        return  content;
    }
}
