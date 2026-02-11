import type {
  LoginRequest,
  LoginResponse,
  RegisterRequest,
} from "src/features/auth/types/auth.types.ts";
import { http } from "src/services/api/http.ts";

export const authApi = {
  register: async (dto: RegisterRequest) => {
    const response = await http.post("/api/auth/register", dto);
    return response.data;
  },

  login: async (dto: LoginRequest) => {
    const response = await http.post<LoginResponse>("/api/auth/login", dto);
    return response.data;
  },
};
