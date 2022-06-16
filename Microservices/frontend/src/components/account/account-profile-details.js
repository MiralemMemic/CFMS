import { useState, useEffect } from 'react';
import {
  Box,
  Button,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Grid,
  TextField,
} from '@mui/material';
import axios from 'axios';

const states = [
  {
    value: 'alabama',
    label: 'Alabama',
  },
  {
    value: 'new-york',
    label: 'New York',
  },
  {
    value: 'pennsylvania',
    label: 'Pennsylvania',
  },
  {
    value: 'san-francisco',
    label: 'San Francisco',
  },
];

export const AccountProfileDetails = (props) => {
  const [values, setValues] = useState({
    firstName: '',
    lastName: '',
    email: '',
  });

  const handleChange = (event) => {
    setValues({
      ...values,
      [event.target.name]: event.target.value,
    });
  };

  useEffect(() => {
    if (typeof window != 'undefined') {
      const { firstName, lastName, email } = JSON.parse(
        localStorage.getItem('profileData')
      );
      setValues({
        firstName: firstName,
        lastName: lastName,
        email: email,
      });
    }
  }, []);

  // const submitChanges = () => {
  //   if (typeof window != 'undefined') {
  //     const { id } = JSON.parse(localStorage.getItem('profileData'));
  //     const { access_token } = JSON.parse(localStorage.getItem('token'));
  //     axios
  //       .put(
  //         `http://localhost:4000/api/user/${id}`,
  //         {
  //           firstName: values.firstName,
  //           lastName: values.lastName,
  //           email: values.email,
  //         },
  //         {
  //           headers: {
  //             Authorization: `Bearer ${access_token}`,
  //           },
  //         }
  //       )
  //       .then((res) => console.log(res))
  //       .catch((err) => console.log(err));
  //   }
  // };

  return (
    <form autoComplete="off" noValidate {...props}>
      <Card>
        <CardHeader subheader="The information can be edited" title="Profile" />
        <Divider />
        <CardContent>
          <Grid container spacing={3}>
            <Grid item md={6} xs={12}>
              <TextField
                disabled
                fullWidth
                helperText="Please specify the first name"
                label="First name"
                name="firstName"
                //onChange={handleChange}
                required
                value={values.firstName}
                variant="outlined"
              />
            </Grid>
            <Grid item md={6} xs={12}>
              <TextField
                disabled
                fullWidth
                label="Last name"
                name="lastName"
                //onChange={handleChange}
                required
                value={values.lastName}
                variant="outlined"
              />
            </Grid>
            <Grid item md={12} xs={12}>
              <TextField
                disabled
                fullWidth
                label="Email Address"
                name="email"
                //onChange={handleChange}
                required
                value={values.email}
                variant="outlined"
              />
            </Grid>
          </Grid>
        </CardContent>
        <Divider />
        <Box
          sx={{
            display: 'flex',
            justifyContent: 'flex-end',
            p: 2,
          }}
        >
          {/* <Button color="primary" variant="contained" onClick={submitChanges}>
            Save details
          </Button> */}
        </Box>
      </Card>
    </form>
  );
};
