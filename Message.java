import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Message {

    String m;
    int cipher;
    String cipherText;
    String plainText;

    public Message(String m){
        this.m = m;
    }

    private int convertToBase36(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        String BigIntStr = new BigInteger(1, bytes).toString(36);
        int base36 = Integer.parseInt(BigIntStr);
        return base36;
    }

    public int encrypt(String m) {
        //her message M is an integer < n
        //with gcd(M,n) = 1
        int m36 = convertToBase36(m);
        //E(m) = M^e mod n
        cipher = 0;
        return cipher;
    }

    public int decrypt(int cypher){
        //c^d mod n == (M^e mod n)^d mod n == M ^ ed mod n == M^(1 + k * phi(n) mod n)
        return 0;
    }

    public String getCipherText() {
        return cipherText;
    }

    public String getPlainText() {
        return plainText;
    }

    @Override
    public String toString() {
        return cipherText + "\r\n\r\n" + plainText + "r\n"; 
    }
}