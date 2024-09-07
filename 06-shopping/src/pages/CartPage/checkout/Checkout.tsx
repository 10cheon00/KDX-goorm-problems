import { useSelector } from "react-redux";
import styles from "./Checkout.module.scss";
import { useAppDispatch } from "../../../hooks/redux";
import { purchase } from "../../../store/cart/cart.slice";

const Checkout = () => {
  const totalPrice = useSelector((state) => state.cartReducer.totalPrice);
  const appDispatch = useAppDispatch();

  return (
    <div className={styles.checkout}>
      <div>
        <p>합계 : $ {totalPrice.toFixed(2)}</p>
        <button
          className={styles.checkout_button}
          onClick={() => appDispatch(purchase())}
        >
          계산하기
        </button>
      </div>
    </div>
  );
};

export default Checkout;
