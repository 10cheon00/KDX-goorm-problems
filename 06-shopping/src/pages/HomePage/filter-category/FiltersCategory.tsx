import React from "react";
import { CategoriesName } from "../../../store/categories/categories.type";
import CategoryTab from "./category-tab/CategoryTab";
import styles from "./FiltersCategory.module.scss";

const FiltersCategory = () => {
  return (
    <div className={styles.filter_category}>
      {Object.values(CategoriesName).map((value) => {
        return <CategoryTab key={value} text={value} categoryName={value} />;
      })}
    </div>
  );
};

export default FiltersCategory;
