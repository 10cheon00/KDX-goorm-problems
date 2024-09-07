import { useDispatch, useSelector } from "react-redux";
import { Link, useParams } from "react-router-dom";

import styles from "./DetailPage.module.scss";
import { add } from "../../store/cart/cart.slice";
import { useAppDispatch } from "../../hooks/redux";
import { fetchProductById } from "../../store/products/product.slice";
import { useEffect } from "react";

const DetailPage = () => {
  const { id } = useParams();

  const appDispatch = useAppDispatch();
  const getProduct = async (itemId: number) => {
    appDispatch(fetchProductById(itemId));
  };
  const product = useSelector((state) => state.productReducer.product);

  const dispatch = useDispatch();
  const addProductToCart = () => {
    dispatch(add(product));
  };

  useEffect(() => {
    getProduct(id);
  }, [id]);

  return (
    <div className="page">
      <div className="container">
        <div className={styles.card_wrapper}>
          <div className={styles.card_img}>
            <img src={product.image} />
          </div>
          <div className={styles.card_description}>
            <h1>{product.category}</h1>
            <h1>{product.title}</h1>
            <h2>{product.description}</h2>
            <h3>{`$ ${product.price}`}</h3>
            <button onClick={() => addProductToCart()}>장바구니에 담기</button>
            <Link to="/cart">장바구니로 이동</Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DetailPage;
