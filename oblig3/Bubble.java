class Bubble extends Sorter {

    void sort() {
        for (int i = 0; leq(i, n - 2); i++) {
            for (int j = 0; leq(j, n - i - 2); j++) {
                if (gt(A[j], A[j + 1])) {
                    swap(j, j + 1);
                }
            }
        }
    }

    String algorithmName() {
        return "bubble";
    }
}
