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
import axios from "axios";

export const IncidentListToolbar = (props) => (
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
        Notifications
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
    {/*
    <Box sx={{ mt: 3 }}>
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
              placeholder="Search incident"
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

  const [values, setValues] = useState({
    description: "",
    sectorId: "",
    prisonerId: "",
    risk: "",
  });

  const handleChange = (event) => {
    setValues({
      ...values,
      [event.target.name]: event.target.value,
    });
  };

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const submitChanges = async () => {
    if (typeof window != "undefined") {
      const { id } = JSON.parse(localStorage.getItem("profileData"));
      const { access_token } = JSON.parse(localStorage.getItem("token"));
      await axios
        .post(
          `http://localhost:4000/api/incident`,
          {
            text: values.description,
            sector: values.sectorId,
            prisonerId: values.prisonerId,
            risk: values.risk,
          },
          {
            headers: {
              Authorization: `Bearer ${access_token}`,
            },
          }
        )
        .then((res) => console.log(res))
        .catch((err) => console.log(err));
    }
    setOpen(false);
    handler.handler();
  };

  return (
    <div>
      <Button color="primary" variant="contained" onClick={handleClickOpen}>
        Add notification
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Add New Notification</DialogTitle>
        <DialogContent>
          <DialogContentText>
            To add a notification please fill out all the fields below.
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="description"
            label="Description"
            value={values.description}
            onChange={handleChange}
            type="text"
            fullWidth
            name="description"
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="sectorId"
            label="Sector ID"
            value={values.sectorId}
            onChange={handleChange}
            type="text"
            name="sectorId"
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="prisonerId"
            label="Prisoner ID"
            value={values.prisonerId}
            onChange={handleChange}
            type="text"
            fullWidth
            name="prisonerId"
            variant="standard"
          />
          <SelectStatus handler={handleChange} rizik={values.risk} />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={submitChanges}>Create</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";

export const SelectStatus = ({ handler, rizik }) => {
  return (
    <Box sx={{ minWidth: 120, mt: 2 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Risk</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={rizik}
          label="Risk"
          name="risk"
          onChange={handler}
        >
          <MenuItem value={"Low"}>Low</MenuItem>
          <MenuItem value={"Warning"}>Warning</MenuItem>
          <MenuItem value={"Critical"}>Critical</MenuItem>
        </Select>
      </FormControl>
    </Box>
  );
};
