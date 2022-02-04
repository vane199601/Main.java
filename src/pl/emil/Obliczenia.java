package pl.emil;

import java.util.ArrayList;
import java.util.Random;

public class Obliczenia {

    private static int a=1, b=1, c=1, d=1;

    private static int nrNajlepszejIteracji = 0;
    private static String najlepszaDotychczasWartosc = "0";
    public Obliczenia(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public static double obliczWartoscFunkcji (int x){
        double zwrot = a*Math.pow(x,3)+b*Math.pow(x,2)+c*x+d;
        return zwrot;
    }

    public static boolean warunekStopu(int nrIteracji ,String najelpszaWartoscWIteracji){
        if (Integer.parseInt(najlepszaDotychczasWartosc, 2) < Integer.parseInt(najelpszaWartoscWIteracji, 2)) {
            najlepszaDotychczasWartosc = najelpszaWartoscWIteracji;
            nrNajlepszejIteracji = nrIteracji;
            return true;
        } else return !(najlepszaDotychczasWartosc == najelpszaWartoscWIteracji && nrIteracji - nrNajlepszejIteracji > 431);
    }

    public static String krzyżowanieChromosomow (String a, String b){
        int indeksPrzeciecia = Math.round((a.length()+b.length())/4);
        String drugaCzesc = a.substring(indeksPrzeciecia);
        String pierwszaCzescB = b.substring(0, indeksPrzeciecia);
        String potomek = pierwszaCzescB+drugaCzesc;
        return potomek;
    }

    public static int losowanieRodziców(double[] wts) {
        int wybranyRodzic = 0;
        double total = wts[0];
        Random rnd = new Random();
        for( int i = 1; i < wts.length; i++ ) {
            total += wts[i];
            if(rnd.nextDouble() <= (wts[i] / total)) wybranyRodzic = i;
        }
        return wybranyRodzic;
    }

    public static String mutacja(String potomek, double Pm){
        Random random = new Random();
        int losowanie = random.nextInt(potomek.length());
        double los2 = random.nextDouble();
        if(los2>1-Pm) {
            if (losowanie > 0) {
                if (potomek.charAt(losowanie) == '0') {
                    return potomek.substring(0, losowanie - 1) + '1' + potomek.substring(losowanie + 1);
                }
                if (potomek.charAt(losowanie) == '1') {
                    return potomek.substring(0, losowanie - 1) + '0' + potomek.substring(losowanie + 1);
                }
            } else {
                if (potomek.charAt(losowanie) == '0') {
                    return '1' + potomek.substring(losowanie + 1);
                }
                if (potomek.charAt(losowanie) == '1') {
                    return '0' + potomek.substring(losowanie + 1);
                }
            }
        }
        return potomek;
    }

    public static double wartośćTablicyMax (double tab[]) {
        int dlugosc = tab.length;
        double max = tab[0];
        for (int i = 0; i < dlugosc; i++) {
            if (tab[i] > max)
                max = tab[i];
        }
        return max;
    }
    public static double wartośćTablicyMin (double tab[]) {
        int dlugosc = tab.length;
        double min = tab[0];
        for (int i = 0; i < dlugosc; i++) {
            if (tab[i] < min)
                min = tab[i];
        }
        return min;
    }

    public static String dodajZeraDoOsobnika(String element) {

            for (int i = 0; i <5; i++) {
                if (element.length() < 5) {
                    element = "0" + element;
                }
            }
        return element;
    }

    public static int wyliczWynik(ArrayList<String> lista) {
        return 21;
    }

    public static int maxWartoscFunkcji() {
        return 4856;
    }
}


