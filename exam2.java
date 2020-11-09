import java.math.BigInteger;

public class exam2 {
    public static void main (String[] args){

        System.out.println("CODE FOR EXAM 2:");
        
        //1
        System.out.println("1:");
        BigInteger a = BigInteger.valueOf(61);
        BigInteger b = BigInteger.valueOf(79);
        BigInteger m = BigInteger.valueOf(157);

        Calculator calc = new Calculator();
        System.out.println("gcd(a,b) = " + calc.gcd(a, b)); 
        System.out.println("b^-1 mod m = " + calc.inverseMod(b, m));
        System.out.println("a^b mod m = " + calc.powerMod(a, b, m));
        System.out.println("ax = b mod m -- x = " + (b.intValue() % m.intValue()) / a.intValue() );

        System.out.println("bmodm = " + (79 % 157) / 61);


        BigInteger e = BigInteger.valueOf(7);
        BigInteger n = BigInteger.valueOf(11173553);
        int ciphertext =  26066180;
        BigInteger phiOfN = calc.eulerTotient(BigInteger.valueOf(101), BigInteger.valueOf(1106293));
        System.out.println(phiOfN);
        BigInteger decryptionKey = e.modInverse(phiOfN);
        System.out.println("decryptionKey = " + decryptionKey);
        RSA rsa = new RSA();
        BigInteger plain = rsa.decrypt(BigInteger.valueOf(ciphertext), decryptionKey, n);
        System.out.println(plain.toString(36));

        e = BigInteger.valueOf(38);
        n = BigInteger.valueOf(55);
        System.out.println(calc.powerMod(BigInteger.valueOf(54), e, n));

        System.out.println(54 % 5);
        System.out.println(54 % 11);
    }
}
