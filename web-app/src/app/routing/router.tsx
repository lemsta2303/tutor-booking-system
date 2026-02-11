import { createBrowserRouter } from "react-router";
import RootLayout from "src/pages/RootLayout";
import HomePage from "src/pages/HomePage";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    children: [{ index: true, element: <HomePage /> }],
  },
]);
