import java.math.BigInteger;

public class KeyPair {
    
    BigInteger n;
    BigInteger k;

    public KeyPair(BigInteger n, BigInteger k) {
        this.n = n;
        this.k = k;
    }

    public BigInteger getK() {
        return k;
    }

    public BigInteger getN() {
        return n;
    }
}