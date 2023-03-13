class Insertion extends Sorter {

    void sort() {
        for(int i = 1; lt(i, n); i++) {
            int forrige = i - 1;

            while (forrige >= 0 && gt(A[forrige], A[forrige + 1])) {
                swap(forrige, forrige + 1);
                forrige--;
            }
        }
    }

    String algorithmName() {
        return "insertion";
    }
}
