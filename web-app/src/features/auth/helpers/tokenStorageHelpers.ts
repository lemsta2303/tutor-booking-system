export const tokenStorageHelpers = {
  get: () => localStorage.get("accessToken"),
  set: (token: string) => localStorage.set("accessToken", token),
  clear: () => localStorage.removeItem("accessToken"),
};
