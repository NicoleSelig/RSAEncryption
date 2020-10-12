import java.math.BigInteger;

public class RSA {
    
    //key generation
    //chooses prime p,g
    //n = p, q
    
    //Euler Totient function
    //phi(n) = (p-1)(q-1)
    
    //chose e with g, d (e, phi(n)) = 1
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
       //will generate random numbers for this later
       
       Key key = new Key(p,q);
       key.generateKey();

       Calculator calc = new Calculator();
       System.out.println("is PHiOfN Prime?: " + calc.isPrime(key.getPhiOfN())); 
       System.out.println("PhiOfN: " + key.getPhiOfN());
       System.out.println("Decryption Key: " + key.getDecryptionKey());

       //bob publishes
       System.out.println("N: " + key.getN()); //6107745207129020196
       System.out.println("Encryption Key: " + key.getEncryptionKey()); //5   
    }
    
    

   
    



}