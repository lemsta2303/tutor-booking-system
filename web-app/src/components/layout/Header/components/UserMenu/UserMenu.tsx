import { Box, Link as MuiLink } from "@mui/material";
import { Link } from "react-router-dom";

type UserMenuProps = {
  className?: string;
};

const UserMenu = ({ className }: UserMenuProps) => {
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
            Login
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
            Register
          </MuiLink>
        </Box>
      </Box>
    </Box>
  );
};

export default UserMenu;
