import styles from "./CardList.module.scss";
import CardItem from "./card-item/CardItem";
import { IProduct } from "../../../store/products/products.type";

type Products = {
    products: Array<IProduct>
}

const CardList = ({products}: Products) => {
  return (
    <ul className={styles.card_list}>
      {products.map((product: IProduct) => {
        return <CardItem key={product.id} item={product} />;
      })}
    </ul>
  );
};

export default CardList;
