import java.util.ArrayList;
import java.util.Scanner;

public class Teque {
    ArrayList<Integer> A = new ArrayList<>();

    void push_back(Integer x) {
        A.add(x);
    }

    void push_front(Integer x) {
        A.add(0, x);
    }

    void push_middle(Integer x) {
        int i = (A.size() + 1) / 2;
        A.add(i, x);
    }

    void get(int i) {
        System.out.println(A.get(i));
    }

    public static void main(String[] args) {
        Teque t = new Teque();
        Scanner in = new Scanner(System.in);
        int operasjoner = Integer.parseInt(in.nextLine());
        int teller = 0;
        while (teller < operasjoner) {
            String linje = in.nextLine();
            String[] alleData = linje.split(" ");
            switch (alleData[0]) {
                case "push_back":
                    t.push_back(Integer.parseInt(alleData[1]));
                    teller++;
                    break;
                case "push_front":
                    t.push_front(Integer.parseInt(alleData[1]));
                    teller++;
                    break;
                case "push_middle":
                    t.push_middle(Integer.parseInt(alleData[1]));
                    teller++;
                    break;

                    case "get":
                    t.get(Integer.parseInt(alleData[1]));
                    teller++;
                    break;
            }
        }
    }
}