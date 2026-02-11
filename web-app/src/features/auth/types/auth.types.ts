import type { Role } from "src/features/auth/types/role.type.ts";

export type RegisterRequest = {
  username: string;
  password: string;
  role: Role;
};

export type LoginRequest = {
  username: string;
  password: string;
};

export type LoginResponse = {
  token: string;
};
