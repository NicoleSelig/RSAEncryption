/**Message Class
 * Message Constructor Class
 */

import java.math.BigInteger;

public class Message {

    //class variables
    String m;
    BigInteger biText;
    String plainText;
    BigInteger cipherText;

    /**
     * Message object (String)
     * @param m
     * object for string messages...automatically converst to BigInteger
     */
    public Message(String m) {
        this.m = fixString(m);
        this.biText = fromStringtoBI();
    }

    /**
     * Message object (BigInteger)
     * @param ciphertext
     * object for BigInteger messages
     */
    public Message(BigInteger ciphertext){
        this.cipherText = ciphertext;
    }

    /**
     * encode 
     * @param m
     * @return 
     * converts a string to base36
     */
    public BigInteger fromStringtoBI() {
        BigInteger base36 = new BigInteger(m, 36);
        return base36;
    }

   
    /**
     * decode
     * @param bInteger
     * @return
     * converts a BigInteger number in base36 to it's string value
     */
    public String fromBIToString(BigInteger bInteger) {
        return bInteger.toString(36);
    }

    /**
     * fixString
     * @param str
     * @return
     * removes all spaces and punctuation from a string
     */
    private String fixString(String m) {
         m = m.replaceAll("[^A-Za-z]+", "").toLowerCase();
         //System.out.println("fixed string: " + m);
         return m;
    }

    /**
     * getCipherText
     * @return
     */
    public BigInteger getCipherText() {
        return cipherText;
    }

    /**
     * getPlainText
     * @return
     */
    public String getPlainText() {
        return plainText;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return cipherText + "\r\n\r\n" + plainText + "r\n"; 
    }
    
    /**
     * getM
     * @return
     */
    public String getM() {
        return m;
    }

}