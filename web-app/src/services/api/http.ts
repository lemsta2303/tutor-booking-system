import axios from "axios";
import { tokenStorageHelpers } from "src/features/auth/helpers/tokenStorageHelpers.ts";

export const http = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL ?? "http://localhost:8080",
  withCredentials: false,
});

http.interceptors.request.use((config) => {
  const token = tokenStorageHelpers.get();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});
