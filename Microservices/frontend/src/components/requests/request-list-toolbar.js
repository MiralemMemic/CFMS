import {
  Box,
  Button,
  Card,
  CardContent,
  TextField,
  InputAdornment,
  SvgIcon,
  Typography,
} from '@mui/material';
import { Search as SearchIcon } from '../../icons/search';
import { Upload as UploadIcon } from '../../icons/upload';
import { Download as DownloadIcon } from '../../icons/download';
import * as React from 'react';
import { useState, useEffect } from 'react';

export const RequestListToolbar = (props) => (
  <Box {...props}>
    <Box
      sx={{
        alignItems: 'center',
        display: 'flex',
        justifyContent: 'space-between',
        flexWrap: 'wrap',
        m: -1,
      }}
    >
      <Typography sx={{ m: 1 }} variant="h4">
        Messages
      </Typography>
      <Box sx={{ m: 1, display: 'flex' }}>
        {/*
        <Button startIcon={<DownloadIcon fontSize="small" />} sx={{ mr: 1 }}>
          Export
        </Button>
         <Button color="primary" variant="contained">
          Add incident
        </Button> */}
        <FormDialog handler={props.handler} />
      </Box>
    </Box>
    {/* <Box sx={{ mt: 3 }}>
      <Card>
        <CardContent>
          <Box sx={{ maxWidth: 500 }}>
            <TextField
              fullWidth
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <SvgIcon color="action" fontSize="small">
                      <SearchIcon />
                    </SvgIcon>
                  </InputAdornment>
                ),
              }}
              placeholder="Search request"
              variant="outlined"
            />
          </Box>
        </CardContent>
      </Card>
    </Box>
            */}
  </Box>
);

import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

export const FormDialog = (handler) => {
  const [open, setOpen] = React.useState(false);

  const [description, setDescription] = useState('');
  const [receiver, setReceiver] = useState(9999);
  const [loggedUser, setLoggedUser] = useState(1000);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (e) => {
    setDescription(e.target.value);
  };

  const handleReceiver = (e) => {
    setReceiver(e.target.value);
  };

  const [users, setUsers] = useState([]);

  const [changeHappened, setChange] = useState(false);

  useEffect(() => {
    axios.get(`http://localhost:9000/api/v1/users`).then((res) => {
      const persons = res.data;
      setUsers(persons);
    });

    const { id } = JSON.parse(localStorage.getItem('profileData'));
    setLoggedUser(id);
  }, [changeHappened]);

  const handleAdd = () => {
    if (typeof window !== 'undefined') {
      const { access_token } = JSON.parse(localStorage.getItem('token'));
      axios
        .post(
          `http://localhost:9000/api/v1/messages`,
          {
            receiver: receiver,
            sender: loggedUser,
            content: description,
          },
          {
            headers: {
              Authorization: `Bearer ${access_token}`,
            },
          }
        )
        .then((res) => {
          setOpen(false);
        });
      handler.handler();
    }
  };

  return (
    <div>
      <Button color="primary" variant="contained" onClick={handleClickOpen}>
        Send Message
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Send New Message</DialogTitle>
        <DialogContent>
          <DialogContentText>
            To send a message please fill out the description below.
          </DialogContentText>
          <TextField
            multiline
            onChange={handleChange}
            autoFocus
            margin="dense"
            id="content"
            value={description}
            label="Content"
            type="text"
            fullWidth
            variant="standard"
          />

          <InputLabel id="select-label">Receiver</InputLabel>
          <FormControl fullWidth>
            <Select
              id="select-label"
              value={receiver}
              label="Receiver"
              onChange={handleReceiver}
            >
              {users.map((user) =>
                loggedUser != user.id ? (
                  <MenuItem value={user.id}>
                    {user.firstName + ' ' + user.lastName}
                  </MenuItem>
                ) : null
              )}
            </Select>
          </FormControl>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleAdd}>Create</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import axios from 'axios';
import { Form } from 'formik';

/*
export const SelectStatus = () => {
  const [age, setAge] = React.useState('');

  const handleChange = (event) => {
    setAge(event.target.value);
  };

  return (
    <Box sx={{ minWidth: 120, mt: 2 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Status</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={age}
          label="Status"
          onChange={handleChange}
        >
          <MenuItem value={10}>Low</MenuItem>
          <MenuItem value={20}>Warning</MenuItem>
          <MenuItem value={30}>Critical</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
};
*/
