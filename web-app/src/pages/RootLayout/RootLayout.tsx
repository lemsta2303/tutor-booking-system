import { Outlet } from "react-router";
import { Box, Container } from "@mui/material";
import Header from "src/components/layout/Header";

const RootLayout = () => {
  return (
    <Box sx={{ minHeight: "100vh", bgColor: "palette.background.default" }}>
      <Header />
      <Container sx={{ py: 3 }}>
        <Outlet />
      </Container>
    </Box>
  );
};

export default RootLayout;
