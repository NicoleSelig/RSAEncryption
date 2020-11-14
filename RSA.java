/**
 * RSA Class
 * @author Nicole Selig
 * main driver class
 */
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class RSA {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        //class imports
        FileManager fm = new FileManager();
        KeyGenerator keyGen = new KeyGenerator();

        //class variables
        String fileString = "";
        Message msg;
        boolean notEnoughDigits = true;

        //welcome
        System.out.println("");
        System.out.println("RSA CryptoTool\n");
        System.out.println("Enter Q anytime to quit program.\n");
        
        //ask user whether to encrypt or decrypt
        System.out.println("Encrypt (E) or Decrypt(D) ?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        //while running
        boolean programRunning = true;
        while((input != "Q" || input != "q") && programRunning) {
            switch(input){
            case "e":
            case "E": //Encryption
                //get from user which file they would like to encrypt
                System.out.println("Which file would you like to encrypt?");
                fileString = sc.nextLine();

                //get the message from the file
                msg = new Message(fm.getMessageFromFile(fileString));

                System.out.println("\nYour message is " + msg.biText.toString().length() + " digits in length.");

                System.out.println("\nHow many key digits?");
                int digits = Integer.parseInt(sc.nextLine());

                //generate all the keys and save them to a file 
                keyGen.generateKeys(digits);
                
                //get the public keys
                KeyPair pubKeys = fm.getPublicKeys();
 
                //make sure that the user made the key large enough to encrypt their file
                while(notEnoughDigits){
                    if (!isGood(msg.biText, pubKeys.getN())) {
                        System.out.println("Your message is bigger than the key!\nYour key is " + pubKeys.getN().toString().length() + " digits\nI suggest more than " + msg.biText.toString().length() + " digits for your key..." + "\nHow many digits?");
                        digits = Integer.parseInt(sc.nextLine());

                        keyGen.generateKeys(digits);
                        pubKeys = fm.getPublicKeys();
                    } else {
                        System.out.println("\nMessage is OK for encryption");
                        BigInteger ciphertext = encrypt(msg.getM(), pubKeys.n, pubKeys.k);
                        String cipherString = ciphertext.toString();
                        
                        fm.createFile("cipher.txt");
                        fm.writeMessageToFile(cipherString, "cipher.txt");
                        //System.out.println("cipherText = " + ciphertext.toString());
                        
                        notEnoughDigits = false;
                        programRunning = false;
                    }
                }
                System.out.println("\nMessage Encrypted. Check your cipher.txt file. Goodbye!");
                break;
            case "d":
            case "D": //Decryption
                System.out.println("\nWhich file would you like to decrypt?");
                fileString = sc.nextLine();
                Message ciphertext = new Message(fm.getBigIntegerFromFile(fileString));
                //System.out.println(ciphertext.cipherText.toString());

                System.out.println("Enter the name of your keyFile: ");
                String keyFileStr = sc.nextLine();
                
                KeyPair privKeys = fm.getPrivateKeys(keyFileStr);
                String plaintext = decrypt(ciphertext.cipherText, privKeys.k, privKeys.n);
                //System.out.println(plaintext);

                fm.createFile("plain.txt");
                fm.writeMessageToFile(plaintext, "plain.txt");
                
                System.out.println("Message decrypted. Check your plain.txt file. Goodbye!");
                programRunning = false;
                break;
            }
        }
        sc.close(); //close scanner
    }

    /**
     * encrypt
     * @param message
     * @param n
     * @param e
     * @return
     * encrypts the message E(m) = M^e mod n
     */
    static BigInteger encrypt(String message, BigInteger n, BigInteger e) {
        System.out.println("encrypting....");
        Message m = new Message(message);
        Calculator calc = new Calculator();
        BigInteger bim = m.fromStringtoBI();
        //System.out.println(message + " converted to Base36: " + bim.toString());
        return calc.powerMod(bim, e, n);
    }

    /**
     * decrypt
     * @param c
     * @param d
     * @param n
     * @return
     * decrypts the message C^d mod n
     */
    static String decrypt(BigInteger c, BigInteger d, BigInteger n) {
        System.out.println("decrypting..");
        Calculator calc = new Calculator();
        BigInteger PlainBI = calc.powerMod(c,d,n);
        // c^d modn
        String plaintext = PlainBI.toString(36);
        //System.out.println(plaintext);
        return plaintext;
    }

    /**
     * isGood
     * @param m
     * @param n
     * @return
     * checks if the n value is less than the message value, and if the greatest common denomonator
     * is one
     */
    private static boolean isGood(BigInteger m, BigInteger n) {
        Calculator calc = new Calculator();
        if ((m.compareTo(n) == -1 ) && (calc.gcd(m, n).intValue() == 1))
        { 
            // 
            return true;
        }
        else return false;
    }
}