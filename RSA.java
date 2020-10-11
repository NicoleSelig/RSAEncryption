import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class RSA {
    
    //key generation
    //chooses prime p,g
    //n = p, q
    
    //Euler Totient function
    //alpha(n) = (p-1)(q-1)
    
    //chose e with g, d (e, Alpha(n)) = 1
    //d = e^ -1 mod alpha(n)
    
    //Bob publishes (e,n)


    //ecryptions
    //E(m) = M^e mod n
    //c the cipher text

    //decryption
    //c^d modn = (M^e mod n)^d mod n

    //10 digit example 
    public static void main (String[] args){

        //will generate random numbers for this later
        BigInteger p = new BigInteger("2876082342");
        BigInteger q = new BigInteger("2123633638");
        
        Key key = new Key(p,q);

        key.generateKey();
    }
    
    private void encrypt(String m) {
        //m is an integer < n
        //how to turn m into an integer
        //with gcd(M,n) == 1
        //view as a number in base 36.
    }

    private String convertToBase36(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        String base36 = new BigInteger(1, bytes).toString(36);
        return base36;
    }
    



}