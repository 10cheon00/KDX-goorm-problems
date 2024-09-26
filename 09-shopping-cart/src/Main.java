import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Product> productSet = new HashSet<>();
        List<Product> products = retrieveProductsFromCsv();
        productSet.addAll(products);

        System.out.println("고유한 상품 목록");
        for(Product product : productSet) {
            System.out.println(product.name() + " : " + product.price() + "원");
        }

        Cart myCart = new Cart();

        myCart.addProduct(products.get(0), 1);
        myCart.addProduct(products.get(3), 2);
        myCart.addProduct(products.get(2), 1);
        myCart.addProduct(products.get(1), 3);
        myCart.addProduct(products.get(0), 2);

        System.out.println("카트에 담긴 상품 목록");
        myCart.showItems();

        myCart.removeProduct(products.get(1), 3); // will remove product.
        myCart.removeProduct(products.get(2), 2); // can't remove product, quantity is greater than cart's
        myCart.removeProduct(products.get(0), 3);
        myCart.showItems();
    }

    private static List<Product> retrieveProductsFromCsv() {
        String COMMA_DELIMITER = ",";
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("products.csv"))) {
            String line;
            br.readLine(); // pass header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                products.add(new Product(values[0], Integer.parseInt(values[1])));
            }
            return products;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
