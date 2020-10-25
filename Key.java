import java.math.BigInteger;

public class Key {

    BigInteger p;
    BigInteger q;
    BigInteger key;
    public long encryptionKey;
    public BigInteger decryptionKey;
    public BigInteger n;
    public BigInteger phiOfN;
    private BigInteger gcd;

    public Key(BigInteger p, BigInteger q){
         this.p = p;
         this.q = q;
    }

    public void generateKey() {
        Calculator calc = new Calculator();
        //chooses primes p, q --choose at random 200 digits
        //will generate random numbers for this later
        BigInteger p = new BigInteger("2876082342");
        BigInteger q = new BigInteger("2123633638");

        //n = p*q
        n = calc.multiply(p, q);
        //phi(n) = (p-1)(q-1)
        phiOfN = calc.eulerTotient(p, q);
        
        //will make random e later ..probablePrime(int bitlength, Random rnd)
        //choose e with gcd(e, phiOfN) = 1
        encryptionKey = 5;
       
        gcd = calc.gcd(BigInteger.valueOf(encryptionKey), phiOfN);
        System.out.println("gcd: " + gcd);
        if(gcd.intValue() == 1) {
            System.out.println("The random prime equals 1");
        }
        else {
            System.out.println("The random prime does not equal 1");
        }
        decryptionKey = BigInteger.valueOf(encryptionKey).modInverse(phiOfN);
    }

    public BigInteger getDecryptionKey() {
        return decryptionKey;
    }

    public long getEncryptionKey() {
        return encryptionKey;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getPhiOfN() {
        return phiOfN;
    }

    
    
}