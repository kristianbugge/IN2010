import java.util.ArrayList;
import java.util.Scanner;


class Node {
        int data;
        Node venstre;
        Node hoyre;

        Node(int i) {
            data = i;
            venstre = null;
            hoyre = null;
        }
}

class BinaerTre {

    ArrayList<Integer> losning = new ArrayList<>();

    Node finnLosning (ArrayList<Integer> tall, int forste, int siste) {

        if (forste > siste) {
            return null;
        }

        int midt = (forste + siste) / 2;

        Node node = new Node(tall.get(midt));
        losning.add(node.data);

        node.venstre = finnLosning(tall, forste, midt - 1);
        node.hoyre = finnLosning(tall, midt + 1, siste);


        return node;
    }

    public static void main(String[] args) {
        BinaerTre bt = new BinaerTre();

        Scanner in = new Scanner(System.in);

        ArrayList<Integer> tall = new ArrayList<>();

        System.out.println("Hvor mange tall i sortert array?");
        int antall = in.nextInt();
        int teller = 0;

        while (teller < antall) {
            tall.add(in.nextInt());
            teller++;
        }
        bt.finnLosning(tall, 0, tall.size() - 1);

        String formatertString = bt.losning.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "");

        System.out.println(formatertString);
    }

}

