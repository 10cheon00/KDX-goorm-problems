import React, { useEffect, useState } from "react";
import CardList from "./card-list/CardList";
import CountProducts from "./count-products/CountProducts";
import FiltersCategory from "./filter-category/FiltersCategory";
import { useAppDispatch } from "../../hooks/redux";
import { useSelector } from "react-redux";
import {
  fetchAllProduct,
  fetchAllProductByCategory,
} from "../../store/products/products.slice";
import { CategoriesName } from "../../store/categories/categories.type";
import CardSkeleton from "./card-skeleton/CardSkeleton";

const HomePage = () => {
  const appDispatch = useAppDispatch();
  const isLoading = useSelector((state) => state.productsReducer.isLoading);
  const products = useSelector((state) => state.productsReducer.products);
  const category = useSelector((state) => state.categoriesReducer);

  useEffect(() => {
    if (category === CategoriesName.All) {
      appDispatch(fetchAllProduct());
    } else {
      appDispatch(fetchAllProductByCategory(category));
    }
  }, [category]);

  return (
    <div className="page">
      <div className="container">
        <h1>Products</h1>
        <FiltersCategory />
        <CountProducts products={products} />
        {isLoading ? <CardSkeleton /> : <CardList products={products} />}
      </div>
    </div>
  );
};

export default HomePage;
