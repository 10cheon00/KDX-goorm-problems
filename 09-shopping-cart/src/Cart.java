import java.util.*;

public class Cart {
    private final Map<Integer, Map.Entry<Product, Integer>> cart;

    public Cart() {
        this.cart = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        Map.Entry<Product, Integer> cartItem = cart.getOrDefault(product.hashCode(),
                new AbstractMap.SimpleEntry<>(product, 0));
        cartItem.setValue(cartItem.getValue() + quantity);
        cart.put(product.hashCode(), cartItem);
        System.out.println("담은 상품 : " + product.name() + " " + quantity + "개");
    }

    public void showItems() {
        for (Map.Entry<Integer, Map.Entry<Product, Integer>> entry : this.cart.entrySet()) {
            Product product = entry.getValue().getKey();
            int quantity = entry.getValue().getValue();
            System.out.println(product.name() + " " + quantity + "개 : " + product.price() * quantity + "원");
        }
    }

    public void removeProduct(Product product, int quantity) {
        Map.Entry<Product, Integer> entry = cart.get(product.hashCode());
        entry.setValue(entry.getValue() - quantity);
        if (entry.getValue() <= 0) {
            cart.remove(product.hashCode());
        } else {
            cart.put(product.hashCode(), entry);
        }
    }
}
