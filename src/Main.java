import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    private static final String[] unrepeated = {
            "",
            "nje",
            "dy",
            "tre",
            "kater",
            "pese",
            "gjashte",
            "shtate",
            "tete",
            "nente",
            "dhjete",
            "njembedhjete",
            "dymbedhjete",
            "trembedhjete",
            "katermbedhjete",
            "pesembedhjete",
            "gjashtembedhjete",
            "shtatembedhjete",
            "tetembedhjete",
            "nentembedhjete"
    };

    private static final String[] tens = {
            "",
            "dhjete",
            "njezet",
            "tridhjete",
            "katerdhjete",
            "pesedhjete",
            "gjashtedhjete",
            "shtatedhjete",
            "tetedhjete",
            "nentedhjete"
    };

    private static String convertLessThanOneThousand(int number) {
        String soFar;
        if (number % 100 < 20) {
            soFar = unrepeated[number % 100];
            number /= 100;
        } else {
            soFar = unrepeated[number % 10];
            number /= 10;
            soFar = soFar.isEmpty() ? tens[number % 10] : tens[number % 10] + " e " + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return soFar.isEmpty() ? unrepeated[number] + " qind" : unrepeated[number] + " qinde e " + soFar;
    }


    private static String convert(long number) {
        if (number == 0) return "zero";
        String snumber;
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);
        int milliards = Integer.parseInt(snumber.substring(0, 3));
        int millions = Integer.parseInt(snumber.substring(3, 6));
        int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
        int thousands = Integer.parseInt(snumber.substring(9, 12));
        String milliard;
        if (milliards == 0) {
            milliard = "";
        } else {
            milliard = convertLessThanOneThousand(milliards)
                    + (millions + hundredThousands + thousands > 0 ? " miliarde e " : " miliarde");
        }
        String result = milliard;
        String million;
        if (millions == 0) {
            million = "";
        } else {
            million = convertLessThanOneThousand(millions)
                    + (hundredThousands + thousands > 0 ? " milion e " : " milion");
        }
        result = result + million;
        String hundredThousand;
        if (hundredThousands == 0) {
            hundredThousand = "";
        } else {
            hundredThousand = convertLessThanOneThousand(hundredThousands)
                    + (thousands > 0 ? " mije e " : "mije");
        }
        result = result + hundredThousand;

        String thousand;
        thousand = convertLessThanOneThousand(thousands);
        result = result + thousand;
        return result;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        double num = scan.nextDouble();
        int euro = (int) Math.floor(num);
        int cent = (int) Math.ceil((num - euro) * 100.0f);
        String conversion = "";
        if (euro > 0) {
            conversion += (convert(euro) + (cent > 0 ? " euro e " : " euro"));
        }
        if (cent > 0) {
            conversion += convert(cent) + " cent";
        }
        System.out.println(conversion);
    }
}
