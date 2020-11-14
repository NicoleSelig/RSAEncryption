/**FileManager Class
 * @author Nicole Selig
 * This class handles all functions pertaining to working with files, with the exception of saving keys
 */

import java.io.*;
import java.math.BigInteger;
import java.nio.file.*;
import java.util.*;

public class FileManager {
    
    /**
     * createFile
     * @param filename
     * 
     */
    public void createFile(String filename){
        try{
            File file = new File(filename);
            if (file.createNewFile()){
                System.out.println("File Created: " + file.getName());
            }
            else {
                deleteFileContent(filename);
                //System.out.println("Saving to Existing file...");
            }
        }
        catch(IOException e) {
            System.out.println("Error occured Creating the file.");
            e.printStackTrace();
        }
    }

    /**
     * getMessageFromFile
     * @param fileName
     * @return
     * Reads the message file and returns the string value
     */
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

    /**
     * getBigIntegerFromFile
     * @param filename
     * @return
     * Reads the file and returns the big integer value
     */
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

    /**deleteFileContent
     * deletes all contents of a file
     */
    private void deleteFileContent(String fileName) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.close();
    }

    /**
     * getPrivateKeys
     * @param filename
     * @return
     * @throws FileNotFoundException
     * gets the private keys from a file and stores them into a KeyPair object
     */
    public KeyPair getPrivateKeys(String filename) throws FileNotFoundException {
        BigInteger n = BigInteger.ZERO;
        BigInteger d = BigInteger.ZERO;
        Scanner sc = new Scanner(new FileInputStream(filename));
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

    /**
     * writeMessageToFile
     * @param m
     * @param filename
     * writes a string value to a file
     */
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

    /**
     * getPublicKeys
     * @return
     * @throws FileNotFoundException
     * gets the public keys from their file and stores them in a keypair object
     */
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
