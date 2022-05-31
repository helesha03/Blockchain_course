package task_set_2;

import java.math.BigInteger;

public class HexInt {
    public static enum Endian {Little, Big};
    private String hexString;

    //nexStrin is of a format 0xDDDDDDD
    public HexInt(String hexString) {
        if (hexString.substring(0,2).equalsIgnoreCase("0x")) {
            this.hexString = hexString.substring(2);
        } else throw new IllegalArgumentException("String is NOT in 0xDDD form");

    }

    public BigInteger toInt(Endian endian) {
        BigInteger res = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(16);
        if (endian == Endian.Little) {
            for (int i = 0; i < hexString.length(); i++) {
                //System.out.println(Integer.valueOf(hexString.substring(i, i+1), 16));
                res = res.add(base.pow(i).multiply(BigInteger.valueOf(Integer.valueOf(hexString.substring(i, i+1), 16))));
            }
        } else if (endian == Endian.Big) {
            for (int i = 0; i < hexString.length(); i++) {
                //System.out.println(Integer.valueOf(hexString.substring(i, i+1), 16));
                int baseInd = hexString.length()-i;
                res = res.add(base.pow(i).multiply(BigInteger.valueOf(Integer.valueOf(hexString.substring(baseInd-1, baseInd), 16))));
            }
        } else {
            throw new NumberFormatException("Wrong Endian Value");
        }
        return res;
    }

    public int byteSize() {
        return this.hexString.length() / 2;
    }

    @Override
    public String toString() {
        return "0x"+hexString;
    }

    public static HexInt toHex(BigInteger num, int numBytes, Endian endian) {
        String baseValue = num.toString(16);
        StringBuilder builder = new StringBuilder();
        builder.append("0x");
        if (endian == Endian.Little) {
            builder.append(baseValue);
            for (int i = 0; i < 2*numBytes-baseValue.length(); i++) {
                builder.append('0');
            }
        } else if (endian == Endian.Big) {
            for (int i = 0; i < 2*numBytes-baseValue.length(); i++) {
                builder.append('0');
            }
            builder.append(baseValue);
        } else {
            throw new NumberFormatException("Wrong Endian Value");
        }
        return new HexInt(builder.toString());
    }
}
