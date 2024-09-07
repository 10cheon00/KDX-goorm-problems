import React from "react";
import { useAppSelector } from "../../../../../hooks/redux";
import NavCartItem from "./nav-cart-item/NavCartItem";
import styles from "./NavCartList.module.scss";

const NavCartList = () => {
  const { products } = useAppSelector((state) => state.cartReducer);
  return (
    <div className={styles.nav_cart_list}>
      {products.map((item: any) => (
        <NavCartItem key={item.id} item={item} />
      ))}
    </div>
  );
};

export default NavCartList;
