import java.util.*;

public class Kattunge {

    ArrayList<Integer> utvei = new ArrayList<>();

    private ArrayList<Integer> finnUtvei(Integer start, HashMap<Integer, ArrayList<Integer>> tre) {
        utvei.add(start);
        for (Map.Entry<Integer, ArrayList<Integer>> entry: tre.entrySet()) {
            Integer key = entry.getKey();
            for (Integer i : entry.getValue()) {
                if (start == i) {
                    finnUtvei(key, tre);
                }
            }

        }
        return utvei;
    }

    public static void main(String[] args){
        Kattunge k = new Kattunge();
        Scanner in = new Scanner(System.in);
        HashMap<Integer, ArrayList<Integer>> tre = new HashMap<Integer, ArrayList<Integer>>();
        int start = 0;
        String linje = "";
        while (!linje.equals("-1")) {
            linje = in.nextLine();
            String[] alleData = linje.split(" ");
            if ( alleData.length == 1 && !alleData[0].equals("-1")) {
                start = Integer.parseInt(alleData[0]);
            } else {
                ArrayList<Integer> tall = new ArrayList<>();
                for (int i = 1; i < alleData.length; i++) {
                    tall.add(Integer.parseInt(alleData[i]));
                }
                tre.put(Integer.parseInt(alleData[0]), tall);
            }
        }
        k.finnUtvei(start, tre);

        String formatertString = k.utvei.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "");

        System.out.println(formatertString);
    }
}
