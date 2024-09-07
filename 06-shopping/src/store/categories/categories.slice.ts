import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { CategoriesName } from "./categories.type";

const initialState = CategoriesName.All

export const categoriesSlice = createSlice({
  name: "category",
  initialState,
  reducers: {
    setCategory: (state, action) => {
        return action.payload
    },
  },
});

export const { setCategory } = categoriesSlice.actions;

export default categoriesSlice.reducer;
