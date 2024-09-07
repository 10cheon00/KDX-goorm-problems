import { FC } from "react";
import { useDispatch } from "react-redux";
import { IProduct } from "../../../../store/products/products.type";
import { Link } from "react-router-dom";
import {
  decreaseQuantityById,
  deleteFromCart,
  increaseQuantityById,
} from "../../../../store/cart/cart.slice";
import { AiOutlineDelete } from "react-icons/ai";

import styles from "./CartItem.module.scss";
import { useAppDispatch } from "../../../../hooks/redux";

type CartItemProps = {
  item: IProduct;
};

const CartItem: FC<CartItemProps> = ({ item }) => {
  const dispatch = useDispatch();
  const appDispatch = useAppDispatch();
  const increaseQuantity = () => {
    dispatch(increaseQuantityById(item.id));
  };

  const decreaseQuantity = () => {
    dispatch(decreaseQuantityById(item.id));
  };

  return (
    <div className={styles.cart_item}>
      <Link to={`/product/${item.id}`}>
        <img src={item.image} />
      </Link>
      <div className={styles.cart_description}>
        <h3>{item.category}</h3>
        <h2>{item.title}</h2>
        <h2>
          <span>
            {item.price} x {item.quantity} = $ {item.total.toFixed(2)}
          </span>
        </h2>
      </div>
      <div className={styles.cart_count}>
        <div>
          <button onClick={() => decreaseQuantity()}>-</button>
          <span>{item.quantity}</span>
          <button onClick={() => increaseQuantity()}>+</button>
        </div>
      </div>
      <div className={styles.cart_delete}>
        <AiOutlineDelete onClick={() => appDispatch(deleteFromCart(item.id))}/>
      </div>
    </div>
  );
};

export default CartItem;
