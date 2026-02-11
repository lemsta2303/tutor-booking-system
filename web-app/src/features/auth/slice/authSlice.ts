import { tokenStorageHelpers } from "src/features/auth/helpers/tokenStorageHelpers.ts";
import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import type { RootState } from "src/app/store/store.ts";

type AuthState = {
  token: string | null;
  isAuthenticated: boolean;
};

const initialState: AuthState = {
  token: tokenStorageHelpers.get(),
  isAuthenticated: tokenStorageHelpers.get() !== null,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    login(state, action: PayloadAction<string>) {
      state.token = action.payload;
      state.isAuthenticated = true;
      tokenStorageHelpers.set(action.payload);
    },
    logout(state) {
      state.token = null;
      state.isAuthenticated = false;
      tokenStorageHelpers.clear();
    },
  },
});

export const selectIsAuthenticated = (state: RootState) =>
  state.auth.isAuthenticated;
export const selectAuthToken = (state: RootState) => state.auth.token;

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;
