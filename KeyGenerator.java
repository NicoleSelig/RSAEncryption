/**
 * KeyGenerator Class
 * @author Nicole Selig
 * The Key Generator class handles all of key generation functions. It also saves these keys to a file. 
 */

import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class KeyGenerator {
    
    //class variables
    private BigInteger p;
    private BigInteger q;
    private BigInteger phiOfN;
    public BigInteger encryptionKey;
    public BigInteger decryptionKey;
    public BigInteger n;
    BigInteger gcd = BigInteger.ZERO;

    //class imports
    FileManager fm = new FileManager();
    Calculator calc = new Calculator();
    Random rnd = new Random();

    /**
     * generateKeys
     * @param digits
     * generates all of the needed keypairs based a on digit input from the user 
     */
    public void generateKeys(int digits) {
        System.out.println("\ngenerating keys...please wait...");

        // keep generating keys as long as the GCD of e and phiOfN is not 1
        while (!gcd.equals(BigInteger.ONE)) {

            //convert digits to bitlength estimate
            int bits = (int)(digits*3.2);  //average of 3.2 bits per digit
            //System.out.println("bits = " + bits);

            //generate random 200 digit primes
            p = BigInteger.probablePrime(bits,rnd); // a 200 digit number is 668 bits
            q = BigInteger.probablePrime(bits, rnd);

            // n = p*q
            n = calc.multiply(p, q);

            // phi(n) = (p-1)(q-1) -- euler totient function
            phiOfN = calc.eulerTotient(p, q);

            //POLLARD's METHOD...MAYBE LATER
            // n = BigInteger.probablePrime(bits, rnd);
            // PrimeFactor pm = new PrimeFactor(n);
            // p = pm.p;
            // q = pm.q;

            // choose a random encryption key
            encryptionKey = BigInteger.probablePrime(bits, rnd); 

            //verify that the keys can be used
            // find the greatest common denominator of (e, phiOfN)
            gcd = calc.gcd(encryptionKey, phiOfN);
            if (gcd.intValue() == 1)
                System.out.println("Keys Are Good!");
            else
                System.out.println("Inadequate keypairs. Regenerating...please wait...");
        }
        gcd = BigInteger.ZERO; //reinitialize gcd after completion

        //get the decryption key from the encryption key
        decryptionKey = encryptionKey.modInverse(phiOfN);

        //publish keys to respective files
        fm.createFile("key.pubk");
        fm.createFile("key.privk");
        try {
            savePrivateKeys("key.privk");
            savePublicKeys("key.pubk");
            System.out.println("\nKeys Saved\n");
        } catch (FileNotFoundException e) {
            System.out.println("could not save keys. file does not exist");
            e.printStackTrace();
        }
    }
    
    /**
     * savePublicKeys
     * @param filename
     * @throws FileNotFoundException
     * save the public keys to a file
     */
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

    /**
     * savePrivateKeys
     * @param filename
     * save the private keys to a file
     */
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

    /**
     * getEncryptionKey()
     * @return
     */
    public BigInteger getEncryptionKey() {
        return encryptionKey;
    }

    /**
     * getN
     * @return
     */
    public BigInteger getN() {
        return n;
    }

    /**
     * getPhiOfN()
     * @return
     */
    public BigInteger getPhiOfN() {
        return phiOfN;
    }

   

    
    
}