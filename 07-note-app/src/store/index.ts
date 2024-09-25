import { combineReducers, configureStore } from "@reduxjs/toolkit";
import menuReducer from "./menu/menuSlice";
import modalReducer from "./modal/modalSlice";
import notesListReducer from "./notesList/notesListSlice";
import tagsReducer from "./tags/tagsSlice";
import filterReducer from "./filter/filterSlice";
import storage from "redux-persist/lib/storage";
import { FLUSH, PAUSE, PERSIST, persistReducer, persistStore, PURGE, REGISTER, REHYDRATE } from "redux-persist";

const persistConfig = {
  key: "root",
  storage,
};

const combinedReducers = combineReducers({
  menu: menuReducer,
  modal: modalReducer,
  tags: tagsReducer,
  notesList: notesListReducer,
  filter: filterReducer
});

const persistedReducer = persistReducer(persistConfig, combinedReducers);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
      // }).concat(logger),
    }),
});

export const persistedStore = persistStore(store); 
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
