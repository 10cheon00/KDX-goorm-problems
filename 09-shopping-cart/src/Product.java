public record Product(String name, int price, int key) {
    private static int keyCounter = 0;

    public Product(String name, int price) {
        this(name, price, keyCounter++);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return this.key;
    }
}