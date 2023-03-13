import java.util.*;

public class MapBehandler {
    Map<String, Skuespiller> skuespillerMap;
    Map<String, Film> filmMap;
    Map<Film, ArrayList<Skuespiller>> filmSkuespillerMap;

    MapBehandler(Map<String, Film> filmMap, Map<String, Skuespiller> skuespillerMap, Map<Film, ArrayList<Skuespiller>> filmSkuespillerMap) {
        this.filmMap = filmMap;
        this.skuespillerMap = skuespillerMap;
        this.filmSkuespillerMap = filmSkuespillerMap;

    }


    void tellNoder() {
        System.out.println(skuespillerMap.size() + "\n");
    }

    void tellKanter() {
        int antKanter = 0;
        for (Film film : filmSkuespillerMap.keySet()) {
            int temp = filmSkuespillerMap.get(film).size();
            antKanter += (temp * (temp - 1)) / 2;                          //formel for å få riktig antall kanter gitt mapsene jeg har valgt å bruke
        }
        System.out.println(antKanter+ "\n");
    }

    void finnKortesteVei(String fra, String til) {  //bredde først-søk
        PriorityQueue<Skuespiller> pq = new PriorityQueue<>(new distanceComparator());  //må implementere en distance comparator
                                                                                        //for å ha en variabel til bredde først-søket
        float distanse;

        Skuespiller start = skuespillerMap.get(fra);
        Skuespiller slutt = skuespillerMap.get(til);

        start.distanse = 0;

        leggTilKo(pq, start);

        while (pq.size() > 0) {
            Skuespiller forrige = pq.poll();
            distanse = forrige.distanse + 1;

            for (Film film : forrige.filmer.values()) {
                for (Skuespiller skuespiller : filmSkuespillerMap.get(film)) {
                    if (!skuespiller.besokt) {
                        skuespiller.distanse = distanse;
                        leggTilKo(pq, skuespiller);
                        skuespiller.settForrigePeker(film, forrige);

                    }
                }
            }
        }
        System.out.println(skrivVei(slutt, false));
        pq.clear();
    }

    void finnChillesteVei(String fra, String til) {
        PriorityQueue<Skuespiller> pq = new PriorityQueue<>(new distanceComparator());

        Skuespiller start = skuespillerMap.get(fra);
        Skuespiller slutt = skuespillerMap.get(til);

        start.distanse = 0;

        leggTilKo(pq, start);

        while (pq.size() > 0) {
            Skuespiller forrige = pq.poll();
            for (Film film : forrige.filmer.values()) {
                for (Skuespiller skuespiller : filmSkuespillerMap.get(film)) {
                    float ratingVekt = forrige.distanse + 10 - film.rating;
                    if((ratingVekt) < skuespiller.distanse) {
                        skuespiller.distanse = ratingVekt;
                        skuespiller.settForrigePeker(film, forrige);
                        leggTilKo(pq, skuespiller);
                    }
                }
            }
        }
        System.out.println(skrivVei(slutt, true));
        pq.clear();
    }

    void finnKomponenter() {
        PriorityQueue<Skuespiller> pq = new PriorityQueue<>(new distanceComparator());
        float distanse;

        HashMap<Integer, Integer> komponenter = new HashMap<>();

        for(Skuespiller skuespiller : skuespillerMap.values()) {
            if (!skuespiller.besokt) {
                leggTilKo(pq, skuespiller);
                int kompStoerrelse = 1;
                while (pq.size() > 0) {
                    Skuespiller forrige = pq.poll();
                    distanse = forrige.distanse + 1;

                    for (Film film : forrige.filmer.values()) {
                        for (Skuespiller s : filmSkuespillerMap.get(film)) {
                            if (!s.besokt) {
                                s.distanse = distanse;
                                leggTilKo(pq, s);
                                kompStoerrelse++;
                            }
                        }
                    }
                }
                if(komponenter.containsKey(kompStoerrelse)) {
                    komponenter.put(kompStoerrelse, komponenter.get(kompStoerrelse) + 1);
                } else {
                    komponenter.put(kompStoerrelse, 1);
                }
            }
        }
        StringBuilder utskrift = new StringBuilder();

        ArrayList<Integer> sorteringsliste = new ArrayList<>(komponenter.keySet()); //en sortert liste til utskriften
        sorteringsliste.sort(Integer::compareTo);

        for(int i = sorteringsliste.size()-1 ; i>= 0; i--) {
            utskrift.append("\n").append(komponenter.get(sorteringsliste.get(i))).append(" components of size ").append(sorteringsliste.get(i));
        }
        System.out.println(utskrift);
    }

    void leggTilKo(PriorityQueue<Skuespiller> pq, Skuespiller s) {
        pq.offer(s);
        s.besokt = true;
    }

    String skrivVei(Skuespiller slutt, boolean chill) {
        StringBuilder utskrift = new StringBuilder();
        Skuespiller neste = slutt;

        List<Skuespiller> sUtvei = new ArrayList<>();
        List<Film> fUtvei = new ArrayList<>();

        float weight = 0;

        while (neste != null) {
            sUtvei.add(skuespillerMap.get(neste.nmId));
            if (neste.forrigeF != null) {
                fUtvei.add(filmMap.get(neste.forrigeF.ttid));
                weight += 10 - neste.forrigeF.rating;
            }
            neste = neste.forrigeS;
        }
        for (int i = sUtvei.size() - 1; i > 0; i--) {
            utskrift.append(sUtvei.get(i).navn).append("\n").append("===[ ");
            utskrift.append(fUtvei.get(i - 1).navn).append(" (").append(fUtvei.get(i - 1).rating).append(") ] ===> ");

        }
        utskrift.append(slutt.navn).append("\n");
        String formatertFloat = String.format("%.1f", weight);
        if(chill) utskrift.append("Total weight: ").append(formatertFloat).append("\n");

        for (Skuespiller s : skuespillerMap.values()) {         // Resetter verdier i skuespiller-objektene til neste kall
            s.settIkkeBesokt();
        }
        return utskrift.toString();
    }

    static class distanceComparator implements Comparator<Skuespiller> {
        @Override
        public int compare(Skuespiller x, Skuespiller y) {
            return Float.compare(x.getDistanse(), y.getDistanse());
        }
    }
}
