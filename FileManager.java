import java.io.*;
import java.math.BigInteger;


public class FileManager {
    
    public void createFile(String filename){
        try{
            File file = new File(filename);
            if (file.createNewFile()){
                System.out.println("File Created: " + file.getName());
            }
            else {
                System.out.println("File already exists.");
                deleteFileContent(filename);
                System.out.println("Deleted File Content");
            }
        }
        catch(IOException e) {
            System.out.println("Error occured Creating the file.");
            e.printStackTrace();
        }
    }

    private void deleteFileContent(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.close();
    }
}
