import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { IProduct } from "./products.type";

const STORE_URL: string = "https://fakestoreapi.com/products";

type ProductsType = {
  products: IProduct[];
  isLoading: boolean;
  error: string;
};

const initialState: ProductsType = {
  products: [],
  isLoading: false,
  error: "",
};

const fetchAllProduct = createAsyncThunk("products/fetchProducts", async () => {
  const response = await axios.get(STORE_URL);
  return response.data;
});

const fetchAllProductByCategory = createAsyncThunk(
  "product/fetchByCategory",
  async (category: string) => {
    const response = await axios.get(
      `${STORE_URL}/category/${category.toLowerCase()}`
    );
    return response.data;
  }
);

export const productsSlice = createSlice({
  name: "products",
  initialState,
  reducers: {},
  // reducer를 추가하면 프로미스의 진행 상태에 따라서 리듀서를 실행할 수 있습니다.
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllProduct.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(fetchAllProduct.fulfilled, (state, action) => {
        state.products = action.payload;
        state.isLoading = false;
      })
      .addCase(fetchAllProduct.rejected, (state) => {
        state = initialState;
        state.error = "error!";
        state.isLoading = false;
      })
      .addCase(fetchAllProductByCategory.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(fetchAllProductByCategory.fulfilled, (state, action) => {
        state.products = action.payload;
        state.isLoading = false;
      })
      .addCase(fetchAllProductByCategory.rejected, (state) => {
        state = initialState;
        state.error = "error!";
        state.isLoading = false;
      });
  },
});

export { fetchAllProduct, fetchAllProductByCategory };

export default productsSlice.reducer;
