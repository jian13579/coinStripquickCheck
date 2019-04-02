public final class Counter {
    private int count;

    public Counter() {
    }

    Counter increment() {
        ++count;
        return this;
    }

    Counter decrement() {
        --count;
        return this;
    }

    int count() {
        return count;
    }
}