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
import { useState } from 'react';
import axios from 'axios';
import { FormDialog } from '../prisoners/add-prisoner';

export const CustomerListToolbar = (props) => {
  return (
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
          Prisoners
        </Typography>
        <Box sx={{ m: 1 }}>
          {/*
        <Button startIcon={<UploadIcon fontSize="small" />} sx={{ mr: 1 }}>
          Import
        </Button>
        <Button startIcon={<DownloadIcon fontSize="small" />} sx={{ mr: 1 }}>
          Export
        </Button>
    */}
          <FormDialog handler={props.handler} />
        </Box>
      </Box>
      {/*showForm ? (<div>
      <form onSubmit={handleSubmit}>
        <label>
          First Name :
        <input type="text" value={firstName} onChange={handleFirstNameChange}></input>
        </label>
        <br></br>
        <label>
          Last Name :
        <input type="text" value={lastName} onChange={handleLastNameChange}></input>
        </label>
        <br></br>
        <label>
          Cell :
        <input type="number" value={currentCell} onChange={handleCell}></input>
        </label>
        <br></br>
        <label>
          Sentence :
        <input type="number" value={lenghtOfSentence} onChange={handleLength}></input>
        </label>
        <br></br>
        <label>
          Identification :
        <input type="number" value={identificationNumber} onChange={handleIdentification}></input>
        </label>
        <br></br>
        <label>
          Evaluation :
        <input type="text" value={sentenceEvaluation} onChange={handleEvaluation}></input>
        </label>
        <br></br>
        <label>
          Offense :
        <input type="number" value={offense} onChange={handleOffense}></input>
        </label>
        <button type="Submit">Submit</button>
      </form>
    </div>) : null
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
              placeholder="Search prisoner"
              variant="outlined"
            />
          </Box>
        </CardContent>
      </Card>
    </Box>
    */}
    </Box>
  );
};
