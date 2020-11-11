import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;



public class RSA {
    static BigInteger d;
    static BigInteger n;
    static BigInteger p;
    static BigInteger q;

    // ecryptions

    // c the cipher text

    // decryption
    // c^d modn = (M^e mod n)^d mod n

    // 10 digit example
    public static void main(String[] args) throws FileNotFoundException {
        KeyGenerator keyGen = new KeyGenerator();

        // Create a private key, and save it to a file.
        // Create a public key from a private key, and save it to a file.
        keyGen.generateKeys();

        getPrivateKeys();
        System.out.println("p " + p);
        System.out.println("q " + q);
        System.out.println("n " + n);
        System.out.println("d " + d);

        // Load a key (public or private) from a file specified by the user.
        // Encrypt/decrypt a text file (using RSA) with the currently loaded key.

        System.out.println("\n\nencrypting....");
       // BigInteger ciphertext = encrypt("wearetired", n, e);
        // System.out.println("cipherText = " + ciphertext.toString());
        // System.out.println("cipherText to base36 = " + ciphertext.toString(36));
        // System.out.println("\n\ndecrypting..");
        // //BigInteger plainBI = decrypt(ciphertext, d, n);
        // System.out.println("plaintext = " + plainBI.toString());
        // System.out.println("plaintext to base36 = " + plainBI.toString(36));
    }

    static BigInteger encrypt(String message, BigInteger n, BigInteger e) {
        // Alice's message M is an integer < n with gcd (M,n) = 1 --highly unlikely but
        // check anyway
        Message m = new Message(message);
        Calculator calc = new Calculator();
        BigInteger bim = m.toBase36();
        System.out.println(message + " converted to Base36: " + bim.toString());

        if (!isGood(bim, n)) {
            System.out.println("Message is bigger than the key and not a relative prime");
        } else {
            System.out.println("Message is smaller than the key and a relative prime! yay!");
        }
        // Key key = new Key();
        // key.generateKeys();
        // n = key.getN();
        // e = key.getEncryptionKey();

        // E(m) = M^e mod n
        return calc.powerMod(bim, e, n);
    }

    static BigInteger decrypt(BigInteger c, BigInteger d, BigInteger n) {
        // c^d modn
        Calculator calc = new Calculator();
        return calc.powerMod(c, d, n);
    }

    private static void getPrivateKeys() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("key.privk"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.indexOf("n") != -1) {
                n = sc.nextBigInteger();
            }
            else if (line.indexOf("d") != -1) {
                d = sc.nextBigInteger();
            }
            else if (line.indexOf("p") != -1) {
                p = sc.nextBigInteger();
            }
            else if (line.indexOf("q") != -1) {
                q = sc.nextBigInteger();
            }
        }
    }

    private static boolean isGood(BigInteger m, BigInteger n) {
        Calculator calc = new Calculator();
        if ((calc.compare(n, m) == -1) && (calc.gcd(m, n).intValue() == 1))
        { 
            System.out.println("n > m");
            System.out.println("gcd = 1");
            return true;
        }
        else return false;
    }
}