import java.util.PriorityQueue;
import java.util.Scanner;

class Heap {

    PriorityQueue<Integer> pk = new PriorityQueue<>();

    void lagBinaerTre(PriorityQueue<Integer> pk) {
        PriorityQueue<Integer> heap1 = new PriorityQueue<>();
        PriorityQueue<Integer> heap2 = new PriorityQueue<>();

        int koe = pk.size();

        if (koe > 2) {
            int midt = (koe-1)/2;
            int teller = 0;
            while (teller < midt) {
                heap1.offer(pk.poll());
                teller++;
            }

            System.out.println(pk.poll());
            heap2 = pk;

            lagBinaerTre(heap1);
            lagBinaerTre(heap2);
        } else {
            while (!pk.isEmpty()) {
                System.out.println(pk.poll());
            }
        }

    }

    public static void main(String[] args) {
        Heap heap = new Heap();

        Scanner in = new Scanner(System.in);

        System.out.println("Hvor mange tall i sortert array?");
        int antall = in.nextInt();
        int teller = 0;

        while (teller < antall) {
            heap.pk.offer(in.nextInt());
            teller++;
        }
        heap.lagBinaerTre(heap.pk);
    }
}
