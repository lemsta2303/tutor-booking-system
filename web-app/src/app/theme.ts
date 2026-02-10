import { createTheme } from "@mui/material/styles";

export const theme = createTheme({
  palette: {
    mode: "light",

    primary: {
      main: "#2563EB",
    },
    secondary: {
      main: "#7C3AED",
    },

    success: {
      main: "#16A34A",
    },
    warning: {
      main: "#F59E0B",
    },
    error: {
      main: "#DC2626",
    },
    info: {
      main: "#0EA5E9",
    },

    background: {
      default: "#F8FAFC",
      paper: "#FFFFFF",
    },

    text: {
      primary: "#0F172A",
      secondary: "#475569",
    },
  },

  shape: {
    borderRadius: 12,
  },

  typography: {
    fontFamily: "Inter, system-ui, sans-serif",
    button: {
      textTransform: "none",
      fontWeight: 600,
    },
  },
});
