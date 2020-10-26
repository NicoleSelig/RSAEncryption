import java.math.BigInteger;
import java.util.Arrays;

public class Message {

    String m;
    String cipherText;
    String plainText;

    public Message(String m) {
        this.m = m;
    }

    public Message(){

    }

    /**
     * encode
     * 
     * @param m
     * @return converts a string to base36
     */
    public BigInteger toBase36() {
        m = fixString(m);
        BigInteger base36 = new BigInteger(m, 36);
        return base36;
    }

    //this still doesnt work properly
    /**
     * decode
     * @param bInteger
     * @return
     * converts a BigInteger number in base36 to it's string value
     */
    public String toBase10(BigInteger bInteger) {
        BigInteger base10 = new BigInteger(bInteger.toString(), 10);
        return base10.toString();
    }

    /**
     * fixString
     * @param str
     * @return
     * removes all spaces and punctuation from a string
     */
    private String fixString(String m) {
         String[] string = m.split("(!?)");
         System.out.println("fixed string: " + String.join("", string).toLowerCase());

         return String.join("", string).toLowerCase();
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