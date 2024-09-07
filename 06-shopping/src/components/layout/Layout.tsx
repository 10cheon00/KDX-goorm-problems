import React from "react";
import { Outlet } from "react-router-dom";
import Header from "../header/Header";
import Footer from "../footer/Footer";

import styles from "./Layout.module.scss";

const Layout = (props: any) => {
  return (
    <div className={styles.layout}>
      <Header />
        <Outlet />
        {props.children}
      <Footer />
    </div>
  );
};

export default Layout;
