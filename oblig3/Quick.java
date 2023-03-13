class Quick extends Sorter {

    void sort() {
        quickSort(0, n - 1);
    }

    void quickSort(int start, int slutt) {
        if(leq(start, slutt)) {
            int pIndex = partition(start, slutt);
            quickSort(start, pIndex - 1);
            quickSort(pIndex + 1, slutt);
        }
    }

    int partition(int start, int slutt) {
        int pivot = A[slutt];
        int hoyre = slutt - 1;
        int venstre = start;

        while (leq(venstre, hoyre)) {
            while (leq(venstre, hoyre) && leq(A[venstre], pivot)) {
                venstre++;
            }
            while (geq(hoyre, venstre) && geq(A[hoyre], pivot)) {
                hoyre--;
            }
            if (lt(venstre, hoyre)) swap(venstre, hoyre);
        }
        swap(venstre, slutt);
        return venstre;
    }

    String algorithmName() {
        return "quick";
    }
}
