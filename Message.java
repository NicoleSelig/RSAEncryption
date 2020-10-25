import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Message {

    String m;
    int cipher;
    String cipherText;
    String plainText;

    public Message(String m){
        this.m = m;
    }

    public String convertToBase36(String str) throws NumberFormatException {
        str = str.replaceAll("[^A-Za-z]+", "").toLowerCase();
        System.out.println(str);
        int[] num = new int[str.length()];
        for(int i = 0; i < str.length(); i++){
            num = str.chars().toArray();
        }
        
        return num.toString();
    }

    public static BigInteger encode(String m) {
       System.out.println(new BigInteger(m, 36).toString());
       return new BigInteger(m, 36);
    }

    public static String decode(BigInteger bInteger) {
        String string = bInteger.toString(36);
        System.out.println(string);
        return string;
    }

    public String[] fixString(String str) {
        return str.split("(!?)");
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

    public static void main (String [] args){

       BigInteger bi = encode("iamtired");
       decode(bi);
       
    }
}