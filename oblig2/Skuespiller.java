import java.util.HashMap;
import java.util.Map;

public class Skuespiller {
    String nmId;
    String navn;
    Map<String, Film> filmer;
    boolean besokt;
    float distanse;

    Skuespiller forrigeS;
    Film forrigeF;

    Skuespiller(String nmId, String navn) {
        this.nmId = nmId;
        this.navn = navn;
        this.besokt = false;

        filmer = new HashMap<>();

        distanse = Float.MAX_VALUE;

        forrigeS = null;
        forrigeF = null;
    }

    void leggTilFilm(String id, Film film) {
        filmer.put(id, film);
    }

    void settForrigePeker(Film f, Skuespiller s) {
        this.forrigeF = f;
        this.forrigeS = s;
    }

    void settIkkeBesokt() {
        distanse = Float.MAX_VALUE;
        besokt = false;
        forrigeF = null;
        forrigeS = null;
    }

    float getDistanse() {
        return distanse;
    }
}
