import java.math.BigInteger;
import java.util.Random;

public class Key {

    BigInteger p;
    BigInteger q;
    BigInteger key;
    public static BigInteger encryptionKey;
    public static BigInteger decryptionKey;
    public static BigInteger n;
    BigInteger phiOfN;
    BigInteger gcd;

   

    public Key() {

    }

    public void generateKeys() {
        Calculator calc = new Calculator();
        //chooses primes p, q --choose at random 200 digits
        Random rnd = new Random();

        gcd = BigInteger.valueOf(0);

        while (!gcd.equals(BigInteger.ONE)){
            BigInteger p = BigInteger.probablePrime(668, rnd); //a 200 digit number is 668 bits
            BigInteger q = BigInteger.probablePrime(668, rnd);
            
            //BigInteger p = new BigInteger("2123633639");
            //BigInteger q = new BigInteger("2876082343");

            //n = p*q
            n = calc.multiply(p, q);
            System.out.println("n = \n" + n);

            //phi(n) = (p-1)(q-1) -- euler totient function
            phiOfN = calc.eulerTotient(p, q);
            System.out.println("phiOfN = \n" + phiOfN);
            
            //will make random e later ..probablePrime(int bitlength, Random rnd)
            //choose e with gcd(e, phiOfN) = 1
            encryptionKey = BigInteger.probablePrime(3, rnd);
            //encryptionKey = BigInteger.valueOf(5);
            System.out.println("encryption key = " + encryptionKey);
        
            gcd = calc.gcd(encryptionKey, phiOfN);
            System.out.println("gcd: \n" + gcd);
        
            if (gcd.intValue() == 1) {
                System.out.println("The random prime equals 1");
            }
            else
            {
                System.out.println("The random prime does not equal 1");
            }
        }
        
        
        decryptionKey = encryptionKey.modInverse(phiOfN);
        System.out.println("decryption key = " + decryptionKey);

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