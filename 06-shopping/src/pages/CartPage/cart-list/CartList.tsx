import { useSelector } from "react-redux";
import styles from "./CartList.module.scss";
import CartItem from "./cart-item/CartItem";
import Checkout from "../checkout/Checkout";

const CartList = () => {
  const products = useSelector((state) => state.cartReducer.products);
  return (
    <div className={styles.cart_list}>
      <ul>
        {products.map((product) => {
          return <li key={product.id}>
            <CartItem item={product} />
          </li>;
        })}
      </ul>
      <Checkout />
    </div>
  );
};

export default CartList;
