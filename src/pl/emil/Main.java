package pl.emil;

import java.util.*;

public class Main {

    public static void main(String[] args) {


//        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
//        System.out.println("Podaj a: ");
//        double a = scanner.nextDouble();
//        System.out.println("Podaj b: ");
//        double b = scanner.nextDouble();
//        System.out.println("Podaj c: ");
//        double c = scanner.nextDouble();
//        System.out.println("Podaj d: ");
//        double d = scanner.nextDouble();
//        System.out.println("Podaj Pk: ");
//        double pk = scanner.nextDouble();
//        System.out.println("Podaj Pm: ");
//        double pm = scanner.nextDouble();


//        System.out.println("test substring: ");
//        String str = "Java1234";
//        String actualValue = str.substring(0, 4);
//        System.out.println(actualValue);
//
//        System.out.println("test metoda krzyzowanie: ");
//        String wynik = Obliczenia.krzyżowanieChromosomow("01101", "10011");
//        System.out.println(wynik);
//        String kon="01101", por ="10011";
//        int cos = Math.round((kon.length()+por.length())/4);
//        String pierwszaCzesc = kon.substring(0, cos);
//        String drugaCzesc = por.substring(0, cos);
//        System.out.println(pierwszaCzesc);
//        System.out.println(drugaCzesc);
//        String kur = kon.replace(pierwszaCzesc, drugaCzesc);
//        System.out.println(cos);
//        System.out.println(wynik);


        int a = 1,
                b = 2,
                c = 4,
                d = 14;
        double Pm = 0.4;


        int chromosom = 0;

        ArrayList<Integer> osobnikiPoczatkowe = new ArrayList<>(); //lista która przechowuje osobników

        //losowa inicjalizacja początkowej populacji
        for (int i = 0; i < 6; i++) {
            chromosom = random.nextInt(32);
            osobnikiPoczatkowe.add(chromosom);
            System.out.println("osobnik poczatkowy: " + chromosom);
        }

        int nrIteracji = 0;
        String najelpszaWartosc = "0";
        boolean stop;
        ArrayList<Integer> aktualnePotomstwo = new ArrayList<>();
        ArrayList<String> potomkowiePoNormalizacji = new ArrayList<>();

//        double pk = 0.8; // do przeniesienia na poczatek po zmianie
//        double selekcja = osobnikiPoczatkowe.size()* pk;
//        int selekcjaCalkowita = (int)Math.round(selekcja);
//
//        if (selekcjaCalkowita % 2 != 0) {
//            selekcjaCalkowita++;
//        }
//        if (selekcjaCalkowita > aktualnePotomstwo.size()) {
//            selekcjaCalkowita = aktualnePotomstwo.size();
//        }
//        if (selekcjaCalkowita == 0) {
//            selekcjaCalkowita = 2;
//        }
        int selekcjaCalkowita = osobnikiPoczatkowe.size();

//        double mutacja = osobnikiPoczatkowe.size()* pk;
//        int mutacjaCalkowita = (int)Math.round(selekcja);
//
//        if (mutacjaCalkowita % 2 != 0) {
//            mutacjaCalkowita++;
//        }
//        if (mutacjaCalkowita > aktualnePotomstwo.size()) {
//            mutacjaCalkowita = aktualnePotomstwo.size();
//        }
//        if (mutacjaCalkowita == 0) {
//            mutacjaCalkowita = 2;
//        }



        do {

            if (potomkowiePoNormalizacji.size() > 0) {
             //   potomkowiePoNormalizacji.clear();
                aktualnePotomstwo.clear();
                for (String s : potomkowiePoNormalizacji) {
                    aktualnePotomstwo.add(Integer.parseInt(s, 2));
                }
            } else {
                aktualnePotomstwo = osobnikiPoczatkowe;
            }

            ArrayList<String> potomkowieArrayList = new ArrayList<>();
            ArrayList<String> potomkowiePoMutacjiArrayList = new ArrayList<>();
            double[] osobnikiWartosciTab = new double[aktualnePotomstwo.size()];
            ArrayList<String> rodziceBinArrayList = new ArrayList<>(); // lista wybranycodziców do krzyżowania




            //losowanie rodziców

            for(int i=0; i<aktualnePotomstwo.size(); i++){
                osobnikiWartosciTab[i] = Obliczenia.obliczWartoscFunkcji(aktualnePotomstwo.get(i));
            }
            for(int i=0; i<aktualnePotomstwo.size(); i++){
                rodziceBinArrayList.add(Integer.toBinaryString(aktualnePotomstwo.get(Obliczenia.losowanieRodziców(osobnikiWartosciTab))));
            }
       //     System.out.println();

//            for (var element :
//                    rodziceBinArrayList) {
//                System.out.println("potomek: " + element);
//            }



            //kojarzenie w pary osobników i krzyżowanie

            for (int i=1; i<selekcjaCalkowita; i=i+2){
                potomkowieArrayList.add(Obliczenia.krzyżowanieChromosomow(rodziceBinArrayList.get(i), rodziceBinArrayList.get(i-1)));
                potomkowieArrayList.add(Obliczenia.krzyżowanieChromosomow(rodziceBinArrayList.get(i-1), rodziceBinArrayList.get(i)));
            }

            for (String element : potomkowieArrayList){
                potomkowiePoMutacjiArrayList.add(Obliczenia.mutacja(element, Pm));
            }
            int o=0;
            for (String element : potomkowiePoMutacjiArrayList) {
                o++;
                System.out.println("Potomek po mutacji " + o + ": " + element);
            }


            potomkowiePoNormalizacji.clear();
            for (int i = 0; i< potomkowiePoMutacjiArrayList.size(); i++) {
                String ityElement = Obliczenia.dodajZeraDoOsobnika(potomkowiePoMutacjiArrayList.get(i));
                potomkowiePoNormalizacji.add(ityElement);
                System.out.println("Potomek po normalizacji " + i + ": " + Integer.parseInt(ityElement, 2));

            }

            for (String element : potomkowiePoNormalizacji) {
                if (Integer.parseInt(element,2) > Integer.parseInt(najelpszaWartosc, 2)) {
                    najelpszaWartosc = element;
                }
            }

            stop = Obliczenia.warunekStopu(nrIteracji, najelpszaWartosc);
            nrIteracji++;

            System.out.println("aktualnePotomstwo.size(): " + aktualnePotomstwo.size());
            System.out.println("potomkowieArrayList.size(): " + potomkowieArrayList.size());
            System.out.println("potomkowiePoMutacjiArrayList.size(): " + potomkowiePoMutacjiArrayList.size());
            System.out.println("osobnikiWartosciTab: " + osobnikiWartosciTab.length);
            System.out.println("potomkowiePoNormalizacji.size()" + potomkowiePoNormalizacji.size());
            System.out.println("rodziceBinArrayList.size(): " + rodziceBinArrayList.size());

        } while (stop);


        int najlepszyOsobnik = 0;
        for(int i = 0; i <potomkowiePoNormalizacji.size(); i++) {
            if (Obliczenia.obliczWartoscFunkcji(Integer.parseInt(potomkowiePoNormalizacji.get(i))) > najlepszyOsobnik) {
                najlepszyOsobnik = Integer.parseInt(potomkowiePoNormalizacji.get(i));
            }
        }

     //   System.out.println("Najlepsza wartosc: " + Integer.parseInt(najelpszaWartosc,2));

        //wyświetlanie początkowej puli
        System.out.println("Pula początkowa: ");
        for (int i = 0; i < osobnikiPoczatkowe.size(); i++) {
            System.out.print(osobnikiPoczatkowe.get(i));
            if (i != osobnikiPoczatkowe.size() - 1) {
                System.out.print(", ");
            }
            if (i == osobnikiPoczatkowe.size() - 1) {
                System.out.println();
            }
        }

        System.out.println("ilość iteracji: " + nrIteracji);
        System.out.println("Maksymalna wartosc funkcji w przedziale: " + Obliczenia.maxWartoscFunkcji());

        System.out.println("Najlepszy osobnik: " + Obliczenia.wyliczWynik(potomkowiePoNormalizacji));
    }
}