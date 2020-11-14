import java.math.BigInteger;
import java.security.SecureRandom;

public class PrimeFactor {

    BigInteger p;
    BigInteger q;

    SecureRandom random = new SecureRandom();

    public PrimeFactor(BigInteger n){
       pollardsRho(n);
    }
    
    private void pollardsRho(BigInteger n){
        
        if(n.isProbablePrime(20)) {
            //System.out.println("n = " + n.toString());
            BigInteger p = rho(n);
            pollardsRho(p);
            pollardsRho(n.divide(p));
        }
    }

   private BigInteger rho(BigInteger n){
        System.out.println("Rho-ing...");
        BigInteger divisor;
        BigInteger c = new BigInteger(n.bitLength(), random);
        BigInteger x = new BigInteger(n.bitLength(), random);
        BigInteger y = x;

        //is it divisible by 2?
        if (n.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0)
            return BigInteger.TWO;
        
        do {
            //System.out.println("tortoise....");
            x = x.multiply(x).mod(n).add(c).mod(n);

            //System.out.println("hare...");
            y = y.multiply(y).mod(n).add(c).mod(n);
            y = y.multiply(y).mod(n).add(c).mod(n);

            //|x-y|
            divisor = x.subtract(y).gcd(n);
        }
        while((divisor.compareTo(n)) == 0);

        return divisor;
   }
}
