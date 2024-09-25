import { createSlice } from "@reduxjs/toolkit";

const FilterType = {
  NONE: "",
  PRIORITY_HIGH: "high",
  PRIORITY_LOW: "low",
  LATEST: "latest",
  CREATED: "created",
  EDITED: "edited",
} as const;

interface FilterState extends Object {
  filterType: typeof FilterType[keyof typeof FilterType]
}

const initialState: FilterState = {
  filterType: FilterType.NONE
}

const filterSlice = createSlice({
  name: "filter",
  initialState,
  reducers: {
    setFilter: (state, { payload }) => {
      state.filterType = payload;
    },
  },
});

export const { setFilter } = filterSlice.actions;

export default filterSlice.reducer;

export { FilterType }