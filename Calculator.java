import java.math.BigInteger;

public class Calculator {
    
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    public BigInteger subtract(BigInteger a, BigInteger b){
        return a.subtract(b);
    }

    public int compare(BigInteger a, BigInteger b){
        int c = a.compareTo(b);
        switch(c){
            case -1:
                System.out.println(a + " < " + b);
            case 0:
                System.out.println(a + " = " + b);
            case 1: 
                System.out.println(a + " > " + b);
        }
        return c;
    }

    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    } 
    
    public BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);  
    }

    public BigInteger inverseMod(BigInteger a, BigInteger b){
        return a.modInverse(b);
    }

    //Euler Totient Function
    public BigInteger eulerTotient(BigInteger p, BigInteger q) {
       BigInteger pMinusOne = subtract(p, BigInteger.valueOf(1));
       BigInteger qMinusOne = subtract(q, BigInteger.valueOf(1));
       return multiply(pMinusOne, qMinusOne);
    }

}