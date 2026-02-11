import { AppBar, Container, Toolbar, Typography } from "@mui/material";
import { Link } from "react-router-dom";
import UserMenu from "src/components/layout/Header/components/UserMenu";
import NavigationMenu from "src/components/layout/Header/components/NavigationMenu";

const Header = () => {
  return (
    <AppBar>
      <Container>
        <Toolbar
          disableGutters
          sx={{
            flexDirection: { xs: "column", lg: "row" },
            justifyContent: { xs: "flex-start", lg: "space-between" },
            alignItems: { xs: "flex-start", lg: "center" },
            py: { xs: 2, lg: 3 },
            gap: { xs: 3, lg: 2 },
          }}
        >
          <Typography
            variant="h4"
            component={Link}
            to="/"
            sx={{
              textDecoration: "none",
              color: "inherit",
              fontWeight: 800,
              fontSize: { xs: "3rem", lg: "3.2rem" },
            }}
          >
            TutorBookingSystem
          </Typography>
          <NavigationMenu className="lg:ml-auto  lg:mr-20" />
          <UserMenu />
        </Toolbar>
      </Container>
    </AppBar>
  );
};

export default Header;
