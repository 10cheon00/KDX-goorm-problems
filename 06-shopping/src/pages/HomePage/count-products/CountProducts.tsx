import { IProduct } from "../../../store/products/products.type";
import styles from "./CountProducts.module.scss";

type Products = {
  products: Array<IProduct>;
};

const CountProducts = ({ products }: Products) => {
  return (
    <div className={styles.count_products}>
      <p>Showing: {products.length} items</p>
    </div>
  );
};

export default CountProducts;
