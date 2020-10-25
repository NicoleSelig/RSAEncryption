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
        System.out.println("ax = b mod m -- x = " + (b.intValue()%m.intValue())/a.intValue() );

        //2
        System.out.println("2:");
    }
}
