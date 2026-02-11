import { RouterProvider } from "react-router";
import { router } from "src/app/routing/router.tsx";

function App() {
  return <RouterProvider router={router} />;
}

export default App;
