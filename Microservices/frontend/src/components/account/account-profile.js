import {
  Avatar,
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  Divider,
  Typography,
} from '@mui/material';

import { useState, useEffect } from 'react';

export const AccountProfile = (props) => {
  const [loggedRole, setRole] = useState('');
  const [firstName, setFirstName] = useState('');

  useEffect(() => {
    if (typeof window != 'undefined') {
      const { firstName, role } = JSON.parse(
        localStorage.getItem('profileData')
      );
      setRole(role);
      setFirstName(firstName);
    }
  }, []);
  return (
    <Card {...props}>
      <CardContent>
        <Box
          sx={{
            alignItems: 'center',
            display: 'flex',
            flexDirection: 'column',
          }}
        >
          <Avatar
            src={
              loggedRole == 'Guard'
                ? 'https://logomakercdn.truic.com/ux-flow/industry/security-guard-meta.png'
                : 'https://www.pngitem.com/pimgs/m/528-5287003_dragon-age-grey-warden-symbol-hd-png-download.png'
            }
            sx={{
              height: 256,
              mb: 2,
              width: 256,
            }}
          />
          <Typography color="textPrimary" gutterBottom variant="h5">
            {loggedRole} {firstName}
          </Typography>
        </Box>
      </CardContent>
      <Divider />
    </Card>
  );
};
