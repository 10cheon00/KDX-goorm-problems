import { FC } from "react";
import { IProduct } from "../../../../store/products/products.type";
import styles from "./CardItem.module.scss";
import { Link } from "react-router-dom";
import { useAppDispatch } from "../../../../hooks/redux";
import { add } from "../../../../store/cart/cart.slice";
import { useDispatch } from "react-redux";

type CardItemProps = {
  item: IProduct;
};

const CardItem: FC<CardItemProps> = ({ item }) => {
  const appDispatch = useDispatch();

  return (
    <li className={styles.card_item}>
      <Link to={`/product/${item.id}`}>
        <img src={item.image} />
      </Link>
      <h5>{item.title}</h5>
      <div>
        <button onClick={() => appDispatch(add(item))}>장바구니에 추가</button>
        <p>{`$ ${item.price}`}</p>
      </div>
    </li>
  );
};

export default CardItem;
