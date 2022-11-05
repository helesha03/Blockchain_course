package mysha1_new;
import java.util.Scanner;

public class MySHA1_new {

    public static void main(String[] args) {
        System.out.println("Insert something to be hashed");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        System.out.println();
        
        System.out.println("Original Text: " + word);
        System.out.println("Hashed: " + hash(BinaryMessageToWords(word)));
    }
   
    // Message in binary representation   
    public static String convertToBinary(String word) {

        byte[] bytes = word.getBytes();
        StringBuilder binary = new StringBuilder();

        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        
        return binary.toString();
    }
    
    // Create the 512-bit blocks (as String) of original message in binary representation  
    public static String ExtendedBinaryMessage(String word) {
    
        String binary = convertToBinary(word).replaceAll("\\s","");
        int length = binary.length();
        StringBuilder extended_binary = new StringBuilder(binary);
        extended_binary.append('1');
        String length_part = Integer.toBinaryString(length);

        int l = length + 1;
       
        while (l % 512 != 448) {
          extended_binary.append('0');
          l++;
        }
        
        for (int i = 0; i < 64 - length_part.length(); i++) {
            extended_binary.append('0');
        }
        extended_binary.append(length_part);
        return extended_binary.toString();
    }
    
    // Only for checking  (length of obtained string as several 512-bit blocks)
    public static int ExtendedBinaryMessageLength(String word) {
        String extended_binary = ExtendedBinaryMessage(word);
        return extended_binary.length();
    }
    
    
    // Separate the string into the 32-bit "words"   
    public static int[] BinaryMessageToWords(String word) {
        String m = ExtendedBinaryMessage(word);
        int[] mArray = new int[m.length()/32];
        StringBuilder m1 = new StringBuilder(m);
        for (int i = 0; i < m.length(); i+=32) {
            mArray[i/32] = Integer.valueOf(m1.substring(i+1, i+32),2);
            if(m.charAt(i) == '1'){
                mArray[i/32] |= 0X80000000;
            }
        }
        return mArray;
    }
    
    
    private static int leftrotate(int x, int shift) { //leftrotate function
        return ((x << shift) | (x >>> (32 - shift)));  //>>> is an UNSIGNED shift compared >> which is not
    }
    
    // Needed constants for Sha-1 algorithm
    private static int h1 = 0x67452301;
    private static int h2 = 0xEFCDAB89;
    private static int h3 = 0x98BADCFE;
    private static int h4 = 0x10325476;
    private static int h5 = 0xC3D2E1F0;
    private static int k1 = 0x5A827999;
    private static int k2 = 0x6ED9EBA1;
    private static int k3 = 0x8F1BBCDC;
    private static int k4 = 0xCA62C1D6;
      
    // Creating hash value out of all of 512-bit blocks
    private static String hash(int[] z) {
        
        int integer_count = z.length;
        int[] intArray = new int[80];
        int j;

        for(int i = 0; i < integer_count; i += 16) {
            for(j = 0; j <= 15; j++)
                intArray[j] = z[j+i];
            for ( j = 16; j <= 79; j++ ) {
                //w[i] = (w[i-3] xor w[i-8] xor w[i-14] xor w[i-16]) leftrotate 1
                intArray[j] = leftrotate(intArray[j - 3] ^ intArray[j - 8] ^ intArray[j - 14] ^ intArray[j - 16], 1);
            }

            //  calculate A,B,C,D,E:
            int A = h1;
            int B = h2;
            int C = h3;
            int D = h4;
            int E = h5;
            int t = 0;

            for ( int x = 0; x <= 19; x++ ) {
                t = leftrotate(A,5)+((B&C)|((~B)&D))+E+intArray[x]+k1;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }
            for ( int b = 20; b <= 39; b++ ) {
                t = leftrotate(A,5)+(B^C^D)+E+intArray[b]+k2;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }
            for (int c = 40; c <= 59; c++ ) {
                t = leftrotate(A,5)+((B&C)|(B&D)|(C&D))+E+intArray[c]+k3;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }
            for ( int d = 60; d <= 79; d++ ) {
                t = leftrotate(A,5)+(B^C^D)+E+intArray[d]+k4;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }

            h1+=A; h2+=B; h3+=C; h4+=D; h5+=E;

        }

        String h1Length = Integer.toHexString(h1);
        String h2Length = Integer.toHexString(h2);
        String h3Length = Integer.toHexString(h3);
        String h4Length = Integer.toHexString(h4);
        String h5Length = Integer.toHexString(h5);
        
        //Integer.toHexString does not include extra leading 0's
        if(h1Length.length() < 8) {
            StringBuilder h1L = new StringBuilder(h1Length);
            h1L.insert(0,0);
            h1Length = h1L.toString();
        } else if(h2Length.length() < 8) {
            StringBuilder h2L = new StringBuilder(h2Length);
            h2L.insert(0,0);
            h2Length = h2L.toString();
        } else if(h3Length.length() < 8) {
            StringBuilder h3L = new StringBuilder(h3Length);
            h3L.insert(0,0);
            h3Length = h3L.toString();
        } else if(h4Length.length() < 8) {
            StringBuilder h4L = new StringBuilder(h4Length);
            h4L.insert(0,0);
            h4Length = h4L.toString();
        } else if(h5Length.length() < 8) {
            StringBuilder h5L = new StringBuilder(h5Length);
            h5L.insert(0,0);
            h5Length = h5L.toString();
        }

        //result
        String hh = h1Length + h2Length + h3Length + h4Length + h5Length;
        return hh;
    }
}
