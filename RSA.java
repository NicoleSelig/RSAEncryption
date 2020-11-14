import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.Scanner;

public class RSA {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        FileManager fm = new FileManager();
        KeyGenerator keyGen = new KeyGenerator();
        String fileString = "";
        Message msg;

        System.out.println("");
        System.out.println("RSA CryptoTool\n");
        System.out.println("Enter Q anytime to quit program.\n");
        
        System.out.println("Encrypt (E) or Decrypt(D) ?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        boolean programRunning = true;
        while((input != "Q" || input != "q") && programRunning) {
            switch(input){
            case "e":
            case "E":
                System.out.println("choose Encrypt");
                System.out.println("How many key digits?");
                int digits = Integer.parseInt(sc.nextLine());
                keyGen.generateKeys(digits);
                KeyPair pubKeys = fm.getPublicKeys();

                System.out.println("Which file would you like to encrypt?");
                fileString = sc.nextLine();
                msg = new Message(fm.getMessageFromFile(fileString));
                
                boolean notEnoughDigits = true;
                while(notEnoughDigits){
                    if (!isGood(msg.fromStringtoBI(), pubKeys.getN())) {
                        System.out.println("Your message is bigger than the key! You need more digits...\nHow many digits?");
                        digits = Integer.parseInt(sc.nextLine());
                        notEnoughDigits = true;
                    } else {
                        System.out.println("Message is OK for encryption");
                        BigInteger ciphertext = encrypt(msg.getM(), pubKeys.n, pubKeys.k);
                        String cipherString = ciphertext.toString();
                        fm.createFile("cipher.txt");
                        fm.writeMessageToFile(cipherString, "cipher.txt");
                        System.out.println("cipherText = " + ciphertext.toString());
                        notEnoughDigits = false;
                        programRunning = false;
                    }
                }
                System.out.println("Message Encrypted. Check your cipher.txt file. Goodbye!");
                break;
            case "d":
            case "D":
                System.out.println("choose Decrypt");

                System.out.println("Which file would you like to decrypt?");
                fileString = sc.nextLine();
                Message ciphertext = new Message(fm.getBigIntegerFromFile(fileString));
                System.out.println(ciphertext.cipherText.toString());

                KeyPair privKeys = fm.getPrivateKeys();
                String plaintext = decrypt(ciphertext.cipherText, privKeys.k, privKeys.n);
                System.out.println(plaintext);
                fm.createFile("plain.txt");
                fm.writeMessageToFile(plaintext, "plain.txt");
                System.out.println("Message decrypted. Check your plain.txt file. Goodbye!");
                programRunning = false;
                break;
            }
        }
        sc.close();
        
        // fm.getPublicKeys();

        // System.out.println("Which file did you want to encrypt?");
        // String filename = sc.nextLine();
        // Message message = new Message(fm.getMessageFromFile(filename));
        // BigInteger ciphertext;
         
        
        
    
       
      

        
        // Create a private key, and save it to a file.
        // Create a public key from a private key, and save it to a file.
       

        // Load a key (public or private) from a file specified by the user.
     
        // Encrypt/decrypt a text file (using RSA) with the currently loaded key.
       
       

        // BigInteger ciphertext = encrypt("wearetired", n, e);
        
        // System.out.println("cipherText to base36 = " + ciphertext.toString(36));
      
        
        // System.out.println("plaintext = " + plainBI.toString());
        // System.out.println("plaintext to base10 = " + fxMsg.fromBIToString(plainBI));
    }

    static BigInteger encrypt(String message, BigInteger n, BigInteger e) {
        System.out.println("encrypting....");
        // Alice's message M is an integer < n with gcd (M,n) = 1 --highly unlikely but
        // check anyway
        Message m = new Message(message);
        Calculator calc = new Calculator();
        BigInteger bim = m.fromStringtoBI();
        System.out.println(message + " converted to Base36: " + bim.toString());

        // Key key = new Key();
        // key.generateKeys();
        // n = key.getN();
        // e = key.getEncryptionKey();

        // E(m) = M^e mod n
        return calc.powerMod(bim, e, n);
    }

    static String decrypt(BigInteger c, BigInteger d, BigInteger n) {
        System.out.println("decrypting..");
        Calculator calc = new Calculator();
        BigInteger PlainBI = calc.powerMod(c,d,n);
        // c^d modn
        String plaintext = PlainBI.toString(36);
        System.out.println(plaintext);
        return plaintext;
    }

    // // recieves the users choice chooses a crypt
    // static void chooseCrypt(Scanner sc) {
    //     FileManager fm = new FileManager();
    //     String menuItem = sc.next();
    //     switch(menuItem) {
    //         case "e":
    //         case "E":
    //             System.out.println("choose Encrypt");
    //             System.out.println("What is the name of the file you want to encrypt?");
    //             String filename = sc.nextLine();
    //             Message message = new Message(fm.getMessageFromFile(filename));
            
    //             break;
    //         case "d":
    //         case "D":
    //             System.out.println("choose Decrypt");
    //             break;
    //         case "q":
    //         case "Q":
    //             System.out.println("Good Bye!");
    //             System.exit(0);
    //             break;
    //         default:
    //             System.out.println("Invalid Choice. Try Again");
    //             chooseCrypt(sc);
         
    // }
//}

  

  

    private static boolean isGood(BigInteger m, BigInteger n) {
        Calculator calc = new Calculator();
        if ((m.compareTo(n) == -1 ) && (calc.gcd(m, n).intValue() == 1))
        { 
            System.out.println("m < n");
            System.out.println("gcd = 1");
            return true;
        }
        else return false;
    }
}