import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


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

    public String getMessageFromFile(String fileName) {
        String concatStr = "";
        try {
            File file = new File(fileName);
            Path filePath = Paths.get(fileName);
            
            if(file.exists()){
                List<String> lines = Files.readAllLines(filePath);
                
                for (String line : lines) {
                    concatStr += line;
                } 
            }  
      
        } catch (IOException e) {
            System.out.println("error reading the file");
        }
        return concatStr;
    }
    
    public BigInteger getBigIntegerFromFile(String filename){
        File file = new File (filename);
        BigInteger n = BigInteger.ZERO;
    try {
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            n = scan.nextBigInteger();
        }
        scan.close();  
    } catch (Exception e) {
        e.printStackTrace();
    }
    return n;
    
    }
    private void deleteFileContent(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.close();
    }

    public KeyPair getPrivateKeys() throws FileNotFoundException {
        BigInteger n = BigInteger.ZERO;
        BigInteger d = BigInteger.ZERO;
        Scanner sc = new Scanner(new FileInputStream("key.privk"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.indexOf("n") != -1) {
                n = sc.nextBigInteger();
            }
            else if (line.indexOf("d") != -1) {
                d = sc.nextBigInteger();
            }
        }
        KeyPair privKeys = new KeyPair(n,d);
        return privKeys;
    }

    public void writeMessageToFile(String m, String filename){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(m, 0, m.length());
            writer.close();
            
        } catch (IOException e) {
            System.out.println("could not write message to a file");
            e.printStackTrace();
        }
    }

    public KeyPair getPublicKeys() throws FileNotFoundException {
        BigInteger n = BigInteger.ZERO;
        BigInteger e = BigInteger.ZERO;
        Scanner sc = new Scanner(new FileInputStream("key.pubk"));
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.indexOf("n") != -1){
                n = sc.nextBigInteger();
            }
            else if (line.indexOf("e") != -1) {
                e = sc.nextBigInteger();
            }
        }
        KeyPair pubkeys = new KeyPair(n, e);
        return pubkeys;
    }
}
