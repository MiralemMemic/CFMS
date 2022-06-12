import {
  Box,
  Button,
  Card,
  CardContent,
  TextField,
  InputAdornment,
  SvgIcon,
  Typography,
} from "@mui/material";
import { Search as SearchIcon } from "../../icons/search";
import { Upload as UploadIcon } from "../../icons/upload";
import { Download as DownloadIcon } from "../../icons/download";
import * as React from "react";
import { useState } from "react";

export const RequestListToolbar = (props) => (
  <Box {...props}>
    <Box
      sx={{
        alignItems: "center",
        display: "flex",
        justifyContent: "space-between",
        flexWrap: "wrap",
        m: -1,
      }}
    >
      <Typography sx={{ m: 1 }} variant="h4">
        Messages
      </Typography>
      <Box sx={{ m: 1, display: "flex" }}>
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

import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";

export const FormDialog = (handler) => {
  const [open, setOpen] = React.useState(false);

  const [description, setDescription] = useState("");

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (e) => {
    setDescription(e.target.value);
  };

  const handleAdd = () => {
    if (typeof window !== "undefined") {
      const { access_token } = JSON.parse(localStorage.getItem("token"));
      axios
        .post(
          `http://localhost:4000/api/request`,
          {
            text: description,
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
        Create Message
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Create New Message</DialogTitle>
        <DialogContent>
          <DialogContentText>
            To create a message please fill out the description below.
          </DialogContentText>
          <TextField
            onChange={handleChange}
            autoFocus
            margin="dense"
            id="description"
            value={description}
            label="Description"
            type="text"
            fullWidth
            variant="standard"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleAdd}>Create</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import axios from "axios";

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
