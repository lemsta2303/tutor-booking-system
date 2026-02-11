import { createTheme } from "@mui/material/styles";

export const theme = createTheme({
  breakpoints: {
    values: {
      xs: 0,
      sm: 640,
      md: 768,
      lg: 1024,
      xl: 1280,
    },
  },
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
    htmlFontSize: 10,
    fontFamily: "Inter, system-ui, sans-serif",
    button: {
      textTransform: "none",
      fontWeight: 600,
    },
  },
});
