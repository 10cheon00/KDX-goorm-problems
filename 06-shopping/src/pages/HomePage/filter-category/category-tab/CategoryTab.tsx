import { FC } from "react";
import { CategoriesName } from "../../../../store/categories/categories.type";
import { useDispatch, useSelector } from "react-redux";
import { setCategory } from "../../../../store/categories/categories.slice";

import styles from "./CategoryTab.module.scss";

type CategoryTabProps = {
  text: string;
  categoryName: CategoriesName;
};

const CategoryTab: FC<CategoryTabProps> = ({ text, categoryName }) => {
  const category = useSelector((state) => state.categoriesReducer);
  const dispatch = useDispatch();

  return (
    <button
      className={`${styles.category_button} ${
        category === categoryName ? styles.active_category : ""
      }`}
      onClick={() => dispatch(setCategory(categoryName))}
    >
      <span>{text}</span>
    </button>
  );
};

export default CategoryTab;
