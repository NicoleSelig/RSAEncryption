import java.io.*;


public class FileManager {
    
    public void createFile(String filename){
        try{
            File file = new File(filename);
            if (file.createNewFile()){
                System.out.println("File Created: " + file.getName());
            }
            else {
                System.out.println("File already exists.");
            }
        }
        catch(IOException e) {
            System.out.println("Error occured Creating the file.");
            e.printStackTrace();
        }
    }

    public void writeStringToFile(String str, String filename){
        try{
            FileWriter writer = new FileWriter(filename);
            writer.write(str);
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error occured writing to the file.");
            e.printStackTrace();
        }
    }
}
