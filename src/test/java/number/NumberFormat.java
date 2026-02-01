package number;

public class NumberFormat {


    public static void main(String[] args){
        System.out.println(NumberFormat.format("二千三百"));
        System.out.println(NumberFormat.format("六千四百五十四万二千三百二十一"));
        System.out.println(NumberFormat.format("六千零四万二千三百"));
        System.out.println(NumberFormat.format("六千零五十四万二千三百"));
        System.out.println(NumberFormat.format("一万零三百"));
        System.out.println(NumberFormat.format("零"));
        System.out.println(NumberFormat.format("一千"));
        System.out.println(NumberFormat.format("一万"));
        System.out.println(NumberFormat.format("三百万五千零八十四"));
//        System.out.println(NumberFormat.format("三百万五千零八十四负"));
//        System.out.println(NumberFormat.format("三百万五五"));
//        System.out.println(NumberFormat.format("三百万万"));
        // 零的情况没处理，先千后百没处理
        System.out.println(NumberFormat.format("三百万一千"));
    }

    // 这里最好使用map
    private static final char[] CHINA_NUMBER = new char[]{'零', '一', '二', '三','四','五','六', '七', '八', '九'};
    // 万千百十
    private static final char[] CHINA_NUMBER_FLAG = new char[]{'十', '百', '千', '万'};
    private static final long[] CHINA_NUMBER_FLAG_NUMBER = new long[]{10L,100L,1000L};

    private static final char W_FLAG = '万';

    public static Long format(String chinaNumber) {
        verifyNumber(chinaNumber);
        long realNumber = 0L;
        long tempNumber = 0L;
        for (int i = 0; i < chinaNumber.length(); i++) {
            char c = chinaNumber.charAt(i);
            if (W_FLAG == c) {
                realNumber = (tempNumber+realNumber) * 10000;
                tempNumber = 0L;
                continue;
            }
            for (int j = 0; j < CHINA_NUMBER.length; j++) {
                if (CHINA_NUMBER[j] == c) {
                    tempNumber += j;
                    break;
                }
            }
            for (int j = 0; j < CHINA_NUMBER_FLAG.length; j++) {
                if (CHINA_NUMBER_FLAG[j] == c) {
                    tempNumber *= CHINA_NUMBER_FLAG_NUMBER[j];
                    realNumber+=tempNumber;
                    tempNumber = 0L;
                    break;
                }
            }
        }
        if (tempNumber != 0) {
            realNumber += tempNumber;
        }
        return realNumber;
    }

    private static void verifyNumber(String chinaNumber) {
        for (int i = 0; i < chinaNumber.length(); i++) {
            boolean isValid = false;
            char c = chinaNumber.charAt(i);
            for (int j = 0; j < CHINA_NUMBER.length; j++) {
                if (CHINA_NUMBER[j] == c) {
                    isValid = true;
                    break;
                }
            }
            for (int j = 0; j < CHINA_NUMBER_FLAG.length; j++) {
                if (CHINA_NUMBER_FLAG[j] == c) {
                    isValid = true;
                    break;
                }
            }
            if(!isValid) {
                throw new IllegalArgumentException("Invalid china number");
            }
        }

        // left true right false
        boolean isLeftRight = true;
        boolean isW = false;
        for (int i = 0; i < chinaNumber.length(); i++) {
            char c = chinaNumber.charAt(i);
            if (c == CHINA_NUMBER[0]) {
                continue;
            }
            // 万可以跟在任何后面，但只能出现一次，万后面只能是数字
            if (c == CHINA_NUMBER_FLAG[3]) {
                if (isW) {
                    throw new IllegalArgumentException("Invalid 万");
                }
                isLeftRight = true;
                isW = true;
                continue;
            }
            if (isChinaNumber(c) && !isLeftRight) {
                throw new IllegalArgumentException("Invalid china number");
            }
            if (isChinaFlagNumber(c) && isLeftRight) {
                throw new IllegalArgumentException("Invalid china number");
            }
            isLeftRight = !isLeftRight;
        }
    }

    private static boolean isChinaNumber(char c) {
        for (int j = 0; j < CHINA_NUMBER.length; j++) {
            if (CHINA_NUMBER[j] == c) {
                return true;
            }
        }
        return false;
    }

    private static boolean isChinaFlagNumber(char c) {
        for (int j = 0; j < CHINA_NUMBER_FLAG.length; j++) {
            if (CHINA_NUMBER_FLAG[j] == c) {
                return true;
            }
        }
        return false;
    }
}
