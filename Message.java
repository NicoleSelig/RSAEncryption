import java.math.BigInteger;

public class Message {

    String m;
    int cipher;
    String cipherText;
    String plainText;

    public Message(String m){
        this.m = m;
    }

    /**
     * encode
     * @param m
     * @return
     * converts a string to base36
     */
    public static BigInteger encode(String m) {
       System.out.println(new BigInteger(m, 36).toString());
       return new BigInteger(m, 36);
    }

    /**
     * decode
     * @param bInteger
     * @return
     * converts a BigInteger number in base36 to it's string value
     */
    public static String decode(BigInteger bInteger) {
        String string = bInteger.toString(36);
        System.out.println(string);
        return string;
    }

    /**
     * fixString
     * @param str
     * @return
     * removes all spaces and punctuation from a string
     */
    public String fixString(String str) {
         String[] string = str.split("(!?)");
         return string.toString();
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