import PropTypes from 'prop-types';
import styled from '@emotion/styled';
import {
  AppBar,
  Avatar,
  Badge,
  Box,
  IconButton,
  Toolbar,
  Tooltip,
} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import { Bell as BellIcon } from '../icons/bell';
import { XCircle as Exit } from 'src/icons/x-circle';
import { UserCircle as UserCircleIcon } from '../icons/user-circle';
import { Users as UsersIcon } from '../icons/users';
import { useState, useEffect } from 'react';
import { useRouter } from 'next/router';

const DashboardNavbarRoot = styled(AppBar)(({ theme }) => ({
  backgroundColor: theme.palette.background.paper,
  boxShadow: theme.shadows[3],
}));

export const DashboardNavbar = (props) => {
  const { onSidebarOpen, ...other } = props;

  const [loggedRole, setRole] = useState('');

  const router = useRouter();

  //const navigate = useNavigate();

  useEffect(() => {
    if (typeof window != 'undefined') {
      const { role } = JSON.parse(localStorage.getItem('profileData'));
      setRole(role);
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('profileData');
    router.push('/login');
  };

  return (
    <>
      <DashboardNavbarRoot
        sx={{
          left: {
            lg: 280,
          },
          width: {
            lg: 'calc(100% - 280px)',
          },
        }}
        {...other}
      >
        <Toolbar
          disableGutters
          sx={{
            minHeight: 64,
            left: 0,
            px: 2,
          }}
        >
          <IconButton
            onClick={onSidebarOpen}
            sx={{
              display: {
                xs: 'inline-flex',
                lg: 'none',
              },
            }}
          >
            <MenuIcon fontSize="small" />
          </IconButton>

          <Box sx={{ flexGrow: 1 }} />
          {/*
          <Tooltip title="Contacts">
            <IconButton sx={{ ml: 1 }}>
              <UsersIcon fontSize="small" />
            </IconButton>
          </Tooltip>
          */}
          <Tooltip title="Logout" onClick={handleLogout}>
            <IconButton sx={{ ml: 1 }}>
              <Badge color="primary">
                <Exit fontSize="small" />
              </Badge>
            </IconButton>
          </Tooltip>
          <Avatar
            sx={{
              height: 40,
              width: 40,
              ml: 1,
            }}
            src={
              loggedRole == 'Guard'
                ? 'https://logomakercdn.truic.com/ux-flow/industry/security-guard-meta.png'
                : 'https://www.pngitem.com/pimgs/m/528-5287003_dragon-age-grey-warden-symbol-hd-png-download.png'
            }
          >
            <UserCircleIcon fontSize="small" />
          </Avatar>
        </Toolbar>
      </DashboardNavbarRoot>
    </>
  );
};

DashboardNavbar.propTypes = {
  onSidebarOpen: PropTypes.func,
};
