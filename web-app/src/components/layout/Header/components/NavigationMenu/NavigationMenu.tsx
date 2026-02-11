import { Box, Link as MuiLink } from "@mui/material";
import { Link } from "react-router-dom";

type NavigationMenuProps = {
  className?: string;
};

const NavigationMenu = ({ className }: NavigationMenuProps) => {
  return (
    <Box component="nav" className={className}>
      <Box
        component="ul"
        sx={{
          display: "flex",
          gap: { xs: 1, lg: 2 },
          listStyle: "none",
          m: 0,
          p: 0,
          flexDirection: { xs: "column", lg: "row" },
        }}
      >
        <Box component="li">
          <MuiLink
            component={Link}
            to="/"
            underline="none"
            color="common.white"
            sx={{
              fontWeight: 500,
              "&:hover": { color: "text.primary" },
            }}
          >
            Home
          </MuiLink>
        </Box>

        <Box component="li">
          <MuiLink
            component={Link}
            to="/"
            underline="none"
            color="common.white"
            sx={{
              fontWeight: 500,
              "&:hover": { color: "text.primary" },
            }}
          >
            Reservations
          </MuiLink>
        </Box>

        <Box component="li">
          <MuiLink
            component={Link}
            to="/"
            underline="none"
            color="common.white"
            sx={{
              fontWeight: 500,
              "&:hover": { color: "text.primary" },
            }}
          >
            Tutors
          </MuiLink>
        </Box>
      </Box>
    </Box>
  );
};

export default NavigationMenu;
