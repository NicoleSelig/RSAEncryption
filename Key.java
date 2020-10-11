import java.math.BigInteger;

public class Key {

    BigInteger p;
    BigInteger q;
    BigInteger key;
    BigInteger encryptionKey;
    BigInteger decryptionKey;
    BigInteger n;

    public Key(BigInteger p, BigInteger q){
         this.p = p;
         this.q = q;
    }

    public void generateKey(){
        Calculator calc = new Calculator();
        //chooses primes p, q --choose at random 200 digits
        //n = p*q
        BigInteger n = p.multiply(q);
        //phi(n) = (p-1)(q-1)
        BigInteger phiOfN = calc.eulerTotient(p, q);
        System.out.println(n);
        //will make random e later ..probablePrime(int bitlength, Random rnd)
        //choose e with gcd(e, phiOfN) = 1
        encryptionKey = calc.gcd(BigInteger.valueOf(5), phiOfN);
        if(encryptionKey.intValue() == 1) {
            System.out.println("The random prime equals 1");
        }
        else {
            System.out.println("The random prime does not equal 1");
        }
        System.out.println(encryptionKey);
        decryptionKey = encryptionKey.modInverse(phiOfN);
        System.out.println(decryptionKey);
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

    
    
}