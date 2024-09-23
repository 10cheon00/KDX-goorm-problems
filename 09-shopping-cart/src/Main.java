import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Product> productSet = new HashSet<>();

        Product p1 = new Product("커피", 3500);
        Product p2 = new Product("주먹밥", 1700);
        Product p3 = new Product("라면", 2000);
        Product p4 = new Product("도시락", 6500);

        productSet.add(p1);
        productSet.add(p2);
        productSet.add(p3);
        productSet.add(p4);

        System.out.println("고유한 상품 목록");
        for(Product product : productSet) {
            System.out.println(product.name() + " : " + product.price() + "원");
        }

        Cart myCart = new Cart();

        myCart.addProduct(p1, 1);
        myCart.addProduct(p1, 1);
        myCart.addProduct(p2, 3);
        myCart.addProduct(p2, 2);
        myCart.addProduct(p3, 4);
        System.out.println("카트에 담긴 상품 목록");
        myCart.showItems();

        myCart.removeProduct(p1, 3); // will remove product p1.
        myCart.removeProduct(p2, 2);
        myCart.removeProduct(p3, 3);

        System.out.println("카트에 담긴 상품 목록");
        myCart.showItems();
    }
}
