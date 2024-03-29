import java.math.BigInteger;

public class Calculator {
  
    /**
     * add
     * @param a
     * @param b
     * @return
     * adds two big integers
     */
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    /**
     * subtract
     * @param a
     * @param b
     * @return
     * subtracts two big integers
     */
    public BigInteger subtract(BigInteger a, BigInteger b){
        return a.subtract(b);
    }

    /**
     * multiply
     * @param a
     * @param b
     * @return
     * multiplies two big integers
     */
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    } 

    /**
     * powerMod
     * @param a
     * @param exponent
     * @param mod
     * @return
     * computes a^b mod n
     */
    public BigInteger powerMod(BigInteger a ,BigInteger exponent, BigInteger mod) {
        return a.modPow(exponent, mod);
    }

    /**
     * inverse Mod
     * @param a
     * @param b
     * @return
     * computes a^(-1) mod n
     */
    public BigInteger inverseMod(BigInteger a, BigInteger b){
        return a.modInverse(b);
    }
    
    /**
     * gcd
     * @param a
     * @param b
     * @return
     * finds the greatest common divisor of 2 big integers
     */
    public BigInteger gcd(BigInteger a, BigInteger b) {
        //System.out.println("finding the gcd");
        return a.gcd(b);  
    }


    /**
     * isPrime
     * @param a
     * @return
     * returns 'true' if the BigInteger is prime
     */
    public Boolean isPrime(BigInteger a) {
       return a.isProbablePrime(1); //setting certainty as 1 makes sure we are certain
    }

    /**
     * eulerTotient
     * @param p
     * @param q
     * @return
     * computes Euler Totient function 
     * (p-1)(q-1)
     */
    public BigInteger eulerTotient(BigInteger p, BigInteger q) {
       //System.out.println("factoring...");
       BigInteger pMinusOne = subtract(p, BigInteger.valueOf(1));
       BigInteger qMinusOne = subtract(q, BigInteger.valueOf(1));
       return multiply(pMinusOne, qMinusOne);
    }

    /**
     * compare
     * @param a
     * @param b
     * @return
     * compares two big integers
     */
    public int compare(BigInteger a, BigInteger b){
        int c = a.compareTo(b);
        switch(c){
            case -1:
                //System.out.println(a + " < " + b);
            case 0:
                //System.out.println(a + " = " + b);
            case 1: 
                //System.out.println(a + " > " + b);
        }
        System.out.println(c);
        return c;
    }

   

}