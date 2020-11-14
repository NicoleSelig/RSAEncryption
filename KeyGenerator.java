import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class KeyGenerator {

    private BigInteger p;
    private BigInteger q;
    private BigInteger phiOfN;
    public BigInteger encryptionKey;
    public BigInteger decryptionKey;
    public BigInteger n;

    BigInteger gcd = BigInteger.valueOf(0);

    FileManager fm = new FileManager();
    Calculator calc = new Calculator();
    Random rnd = new Random();

    public void generateKeys(String digits) {
        System.out.println("generating keys..");

        // keep generating keys as long as the GCD of e and phiOfN is not 1
        while (!gcd.equals(BigInteger.ONE)) {

            //convert digits to bits
            long longdigit = Long.parseLong(digits);
            int bits = BigInteger.valueOf(longdigit).bitLength();
            
            // generate random 200 digit primes
            p = BigInteger.probablePrime(bits,rnd); // a 200 digit number is 668 bits
            q = BigInteger.probablePrime(bits, rnd);
            // BigInteger p = new BigInteger("2123633639");
            // BigInteger q = new BigInteger("2876082343");

            // n = p*q
            n = calc.multiply(p, q);

            // phi(n) = (p-1)(q-1) -- euler totient function
            phiOfN = calc.eulerTotient(p, q);

            // choose a random encryption key
            encryptionKey = BigInteger.probablePrime(3, rnd);
            // encryptionKey = BigInteger.valueOf(5);

            // find the greatest common denominator of (e, phiOfN)
            gcd = calc.gcd(encryptionKey, phiOfN);
            System.out.println("gcd: \n" + gcd);

            if (gcd.intValue() == 1)
                System.out.println("Keys Are Good!");
            else
                System.out.println("Inadequate keypairs. Regenerating...");
        }

        decryptionKey = encryptionKey.modInverse(phiOfN);

        // System.out.println("n = \n" + n);
        // ystem.out.println("phiOfN = \n" + phiOfN);
        System.out.println("decryption key = " + decryptionKey);
        System.out.println("encryption key = " + encryptionKey);

        //publish keys to respective files
        fm.createFile("key.pubk");
        fm.createFile("key.privk");
        try {
            savePrivateKeys("key.privk");
            savePublicKeys("key.pubk");
            System.out.println("Keys Saved");
        } catch (FileNotFoundException e) {
            System.out.println("could not save keys. file does not exist");
            e.printStackTrace();
        }
    }

    private void savePublicKeys(String filename) throws FileNotFoundException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("n");
            writer.newLine();
            writer.write(n.toString());
            writer.newLine();
            writer.write("e");
            writer.newLine();
            writer.write(encryptionKey.toString());
            writer.close();
            
        } catch (IOException e) {
            System.out.println("could not write public keys to a file");
            e.printStackTrace();
        }
        
    }

    private void savePrivateKeys(String filename){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write("n");
            writer.newLine();
            writer.write(n.toString());
            writer.newLine();
            writer.write("d");
            writer.newLine();
            writer.write(decryptionKey.toString());
            writer.newLine();
            writer.write("p");
            writer.newLine();
            writer.write(p.toString());
            writer.newLine();
            writer.write("q");
            writer.newLine();
            writer.write(q.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("could not save private keys to file.");
            e.printStackTrace();
        }
    }


    public BigInteger getEncryptionKey() {
        return encryptionKey;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getPhiOfN() {
        return phiOfN;
    }

   

    
    
}