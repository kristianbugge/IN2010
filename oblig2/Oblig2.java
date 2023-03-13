import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Oblig2 {

    public static void main(String[] args) { //lager hashmaps fra filene i main-metoden
        File skuespillerFil = new File("src/actors.tsv");
        File filmFil = new File("src/movies.tsv");
        Map<String, Skuespiller> skuespillerMap = new HashMap<>();
        Map<String, Film> filmMap = new HashMap<>();
        Map<Film, ArrayList<Skuespiller>> filmSkuespillerMap = new HashMap<>();
        try {
            Scanner in = new Scanner(filmFil);
            String linje;
            while (in.hasNext()) {
                linje = in.nextLine();
                String[] alleData = linje.split("\t");
                Film film = new Film(alleData[0], alleData[1], Float.parseFloat(alleData[2]));
                filmMap.put(alleData[0], film);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Scanner in = new Scanner(skuespillerFil);

            String linje;
            while (in.hasNext()) {
                linje = in.nextLine();
                String[] alleData = linje.split("\t");
                Skuespiller sksplr = new Skuespiller(alleData[0], alleData[1]);
                skuespillerMap.put(alleData[0], sksplr);
                for (int i = 2; i < alleData.length; i++) {
                    Film film = filmMap.get(alleData[i]);
                    if(film != null) {
                        sksplr.leggTilFilm(alleData[i], film);
                        if (filmSkuespillerMap.containsKey(film)) {
                            filmSkuespillerMap.get(film).add(sksplr);
                        } else {
                            ArrayList<Skuespiller> skuespillers = new ArrayList<>();
                            skuespillers.add(sksplr);
                            filmSkuespillerMap.put(film, skuespillers);
                            }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        MapBehandler mb = new MapBehandler(filmMap, skuespillerMap, filmSkuespillerMap);

        System.out.println("Oppgave 1:\n");
        mb.tellNoder();
        mb.tellKanter();

        System.out.println("Oppgave 2: \n");
        mb.finnKortesteVei("nm2255973","nm0000460");
        mb.finnKortesteVei("nm0424060","nm0000243");
        mb.finnKortesteVei("nm4689420","nm0000365");
        mb.finnKortesteVei("nm0000288","nm0001401");
        mb.finnKortesteVei("nm0031483","nm0931324");

        System.out.println("Oppgave 3: \n");
        mb.finnChillesteVei("nm2255973","nm0000460");
        mb.finnChillesteVei("nm0424060","nm0000243");
        mb.finnChillesteVei("nm4689420","nm0000365");
        mb.finnChillesteVei("nm0000288","nm0001401");
        mb.finnChillesteVei("nm0031483","nm0931324");

        System.out.println("Oppgave 4: \n");
        mb.finnKomponenter();
    }
}
