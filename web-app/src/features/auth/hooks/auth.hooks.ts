import { useMutation } from "@tanstack/react-query";
import { authApi } from "src/features/auth/api/auth.api.ts";
import type {
  LoginRequest,
  RegisterRequest,
} from "src/features/auth/types/auth.types.ts";
import { useDispatch } from "react-redux";
import { login } from "src/features/auth/slice/authSlice.ts";

export const useRegister = () =>
  useMutation({
    mutationFn: (dto: RegisterRequest) => authApi.register(dto),
    onError: (error: Error) => {
      console.error(error);
    },
  });

export const useLogin = () => {
  const dispatch = useDispatch();

  return useMutation({
    mutationFn: (dto: LoginRequest) => authApi.login(dto),
    onSuccess: (data) => {
      dispatch(login(data.token));
    },
    onError: (error: Error) => {
      console.error(error);
    },
  });
};
