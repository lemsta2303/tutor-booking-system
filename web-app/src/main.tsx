import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";
import { theme } from "src/app/themes/theme.ts";
import { ThemeProvider } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import { GlobalStyles } from "@mui/material";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <App />
    </ThemeProvider>
    <GlobalStyles
      styles={{
        html: {
          fontSize: "62.5%",
          [theme.breakpoints.down("md")]: {
            fontSize: "56.25%",
          },
          [theme.breakpoints.down("sm")]: {
            fontSize: "50%",
          },
        },
      }}
    />
  </StrictMode>,
);
