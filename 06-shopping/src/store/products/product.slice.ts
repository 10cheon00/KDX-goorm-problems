import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { IProduct } from "./products.type";

type ProductType = {
    product: IProduct;
    isLoading: boolean;
    error: string;
}

const initialState: ProductType = {
    product: {} as IProduct,
    isLoading: false,
    error: ""
}

const STORE_URL = "https://fakestoreapi.com/products"

const fetchProductById = createAsyncThunk("product/fetchProductById", async (productId: number) => {
    const response = await axios.get(`${STORE_URL}/${productId}`)
    return response.data;
})

export const productSlice = createSlice({
    name: 'product',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(fetchProductById.pending, (state) => {
            state.isLoading = true;
        }).addCase(fetchProductById.fulfilled, (state, action) => {
            state.product = action.payload;
            state.isLoading = false;
        }).addCase(fetchProductById.rejected, (state) => {
            Object.assign(state, initialState);
            state.error = "error!"
        })
    }
})

export { fetchProductById }

export default productSlice.reducer;