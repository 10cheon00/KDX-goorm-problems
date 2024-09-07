import { createSlice } from "@reduxjs/toolkit";

const initialState = localStorage.getItem("user")
  ? JSON.parse(localStorage.getItem("user") || "")
  : { email: "", token: "", id: "" };

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    save: (state, action) => {
      state.email = action.payload.email;
      state.token = action.payload.token;
      state.id = action.payload.uid;
      localStorage.setItem("user", JSON.stringify(state))
    },
    remove: (state) => {
      localStorage.setItem("user", JSON.stringify({ email: "", token: "", id: "" }))
      return initialState;
    },
  },
});

export const { save, remove } = userSlice.actions;

export default userSlice.reducer;
