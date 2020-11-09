import java.math.BigInteger;
import java.util.Random;


public class KeyGenerator{

    private BigInteger p;
    private BigInteger q;
    private BigInteger phiOfN;
    public  BigInteger encryptionKey;
    public  BigInteger decryptionKey;
    public  BigInteger n;
    
    BigInteger gcd = BigInteger.valueOf(0);

    Calculator calc = new Calculator();
    Random rnd = new Random();

    public void generateKeys() {
        System.out.println("generating keys..");
        
        //keep generating keys as long as the GCD of e and phiOfN is not 1
        while (!gcd.equals(BigInteger.ONE)){
            
            //generate random 200 digit primes
            p = BigInteger.probablePrime(668, rnd); //a 200 digit number is 668 bits
            q = BigInteger.probablePrime(668, rnd);
            //BigInteger p = new BigInteger("2123633639");
            //BigInteger q = new BigInteger("2876082343");

            //n = p*q
            n = calc.multiply(p, q);
    
            //phi(n) = (p-1)(q-1) -- euler totient function
            phiOfN = calc.eulerTotient(p, q);
            
            //choose a random encryption key
            encryptionKey = BigInteger.probablePrime(3, rnd);
            //encryptionKey = BigInteger.valueOf(5);
           
            //find the greatest common denominator of (e, phiOfN)
            gcd = calc.gcd(encryptionKey, phiOfN);
            System.out.println("gcd: \n" + gcd);
        
            if (gcd.intValue() == 1) 
                System.out.println("Keys Are Good!");
            else
                System.out.println("Inadequate keypairs. Regenerating...");
        }
        
        decryptionKey = encryptionKey.modInverse(phiOfN);
        
        //System.out.println("n = \n" + n);
        //ystem.out.println("phiOfN = \n" + phiOfN);
        System.out.println("decryption key = " + decryptionKey);
        System.out.println("encryption key = " + encryptionKey);

        //Bob Publishes keypair (e,n)
    }

    public BigInteger getDecryptionKey() {
        return decryptionKey;
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