import { useState } from 'react';
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
import * as React from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import axios from 'axios';

export const FormDialog = ({ handler }) => {
  const [open, setOpen] = useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };
  const [values, setValues] = useState({
    firstName: '',
    lastName: '',
    cell: '',
    sentence: 0,
    identification: 0,
    evaluation: '',
  });

  const handleChange = (event) => {
    setValues({
      ...values,
      [event.target.name]: event.target.value,
    });
  };

  const handleSubmit = async () => {
    const url = 'http://localhost:9000/api/v1/prisoners';
    const { access_token } = JSON.parse(localStorage.getItem('token'));
    const config = {
      headers: { Authorization: `Bearer ` + access_token },
    };
    await axios
      .post(
        url,
        {
          firstName: values.firstName,
          lastName: values.lastName,
          currentCell: values.cell,
          lengthOfSentence: values.sentence,
          identificationNumber: values.identification,
          sentenceEvaluation: values.evaluation,
          offense: 1,
        },
        config
      )
      .then((result) => console.log(result));
    handler();
    setOpen(false);
  };

  return (
    <div>
      <Button color="primary" variant="contained" onClick={handleClickOpen}>
        Add prisoner
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Add new prisoner</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Fill the fields below to add new prisoner to Penitentiary
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="firstName"
            label="firstName"
            name="firstName"
            type="text"
            fullWidth
            variant="standard"
            value={values.firstName}
            onChange={handleChange}
          />
          <TextField
            autoFocus
            margin="dense"
            id="lastName"
            label="lastName"
            type="text"
            name="lastName"
            fullWidth
            variant="standard"
            value={values.lastName}
            onChange={handleChange}
          />
          <TextField
            autoFocus
            margin="dense"
            id="cell"
            label="cell"
            name="cell"
            type="text"
            fullWidth
            variant="standard"
            value={values.cell}
            onChange={handleChange}
          />
          <TextField
            autoFocus
            margin="dense"
            id="sentence"
            label="sentence"
            type="number"
            name="sentence"
            fullWidth
            variant="standard"
            value={values.sentence}
            onChange={handleChange}
          />
          <TextField
            autoFocus
            margin="dense"
            id="identification"
            label="identification"
            name="identification"
            type="number"
            fullWidth
            variant="standard"
            value={values.identification}
            onChange={handleChange}
          />
          <TextField
            autoFocus
            margin="dense"
            id="evaluation"
            label="Evaluation"
            name="evaluation"
            type="text"
            fullWidth
            variant="standard"
            value={values.evaluation}
            onChange={handleChange}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleSubmit}>Add</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

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
