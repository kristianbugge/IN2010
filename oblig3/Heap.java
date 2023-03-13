public class Heap extends Sorter{

    void sort() {
        buildMaxHeap(n);
        for (int i = n - 1; geq(i, 0); i--) {
            swap(i, 0);
            bubbleDown(0, i);
        }
    }

    private void buildMaxHeap(int n) {
        for (int i = n / 2; geq(i, 0); i--) {
            bubbleDown(i, n);
        }
    }

    private void bubbleDown(int i, int n) {
        int storste = i;
        int venstre = 2*i + 1;
        int hoyre = 2*i + 2;

        if (lt(venstre, n) && leq(A[storste], A[venstre])) {
            int tmp = storste;
            storste = venstre;
            venstre = tmp;
        }

        if (lt(hoyre, n) && lt(A[storste], A[hoyre])) {
            int tmp = storste;
            storste = hoyre;
            hoyre = tmp;
        }

        if (!eq(i, storste)) {
            swap(i, storste);
            bubbleDown(storste, n);
        }
    }

    String algorithmName() {
        return "heap";
    }
}
