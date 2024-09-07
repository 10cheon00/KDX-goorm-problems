import {
  createAsyncThunk,
  current,
  createSlice,
  PayloadAction,
} from "@reduxjs/toolkit";
import axios from "axios";
import { IProduct } from "../products/products.type";
import { useDispatch } from "react-redux";

type CartState = {
  products: IProduct[];
  totalPrice: number;
  user: string;
};

const initialState: CartState = {
  products: localStorage.getItem("cartProducts")
    ? JSON.parse(localStorage.getItem("cartProducts") || "")
    : [],
  totalPrice: 0,
  user: localStorage.getItem("user")
    ? JSON.parse(localStorage.getItem("user") || "")
    : "",
};

const CART_URL = "https://fakestoreapi.com/carts";

const getTotalPrice = createAsyncThunk(
  "cart/getTotalPrice",
  async (_, { getState }) => {
    const userId = getState().cartReducer.user.id;
    // 임시로 userId값을 1로 고정시킴
    const response = await axios.get(`${CART_URL}/user/1`);
    return response.data;
  }
);

const deleteFromCart = createAsyncThunk(
  "cart/deleteFromCart",
  async (productId: number) => {
    // fakestoreapi에 요청하면, 데이터베이스에 저장된 카트를 지우는게 아닌것같다.
    // 그냥 요청하면 지우는 시늉만하고 아무것도 하지 않는듯.
    const response = await axios.delete(`${CART_URL}/${productId}`);
    return productId;
  }
);

const purchase = createAsyncThunk("cart/purchase", async () => {
  // 어떤 요청을 보내는 코드가 있을텐데 api상으로는 그런게 없어보인다.
  return true;
});

export const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    add: (state, action) => {
      const product = state.products.find(
        (product) => product.id === action.payload.id
      );
      if (product === undefined) {
        const newProduct = {
          ...action.payload,
          quantity: 1,
          total: action.payload.price,
        };
        state.products.push(newProduct);
      } else {
        product.quantity += 1;
        product.total = product.price * product.quantity;
      }
      localStorage.setItem("cartProducts", JSON.stringify(state.products));
    },
    clear: (state) => {
      localStorage.setItem("cartProducts", "");
      return initialState;
    },
    increaseQuantityById: (state, action) => {
      state.products.map((product) => {
        if (product.id === action.payload) {
          product.quantity++;
          product.total = product.price * product.quantity;
        }
        return product;
      });
    },
    decreaseQuantityById: (state, action) => {
      state.products.map((product) => {
        if (product.id === action.payload) {
          product.quantity--;
          product.total = product.price * product.quantity;
        }
        return product;
      });
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(deleteFromCart.fulfilled, (state, action) => {
        state.products = state.products.filter(
          (product) => product.id !== action.payload
        );
        localStorage.setItem("cartProducts", JSON.stringify(state.products));
      })
      .addCase(getTotalPrice.fulfilled, (state) => {
        state.totalPrice = state.products.reduce(
          (acc, cur) => acc + cur.total,
          0
        );
      })
      .addCase(purchase.fulfilled, (state) => {
        localStorage.setItem("cartProducts", JSON.stringify(initialState));
        return initialState;
      });
  },
});

export const { add, clear, increaseQuantityById, decreaseQuantityById } =
  cartSlice.actions;
export { getTotalPrice, deleteFromCart, purchase };

export default cartSlice.reducer;
