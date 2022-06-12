import Head from "next/head";
import NextLink from "next/link";
import { useRouter } from "next/router";
import { useFormik } from "formik";
import { useState } from "react";
import * as Yup from "yup";
import axios from "axios";

import {
  Box,
  Button,
  Container,
  Grid,
  Link,
  TextField,
  Typography,
} from "@mui/material";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { Facebook as FacebookIcon } from "../icons/facebook";
import { Google as GoogleIcon } from "../icons/google";

let axiosConfig = {
  headers: {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  },
};

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const router = useRouter();
  /*
  const formik = useFormik({
    initialValues: {
      username: "username",
      password: "password",
    },
    validationSchema: Yup.object({
      email: Yup.string()
        .email("Must be a valid email")
        .max(255)
        .required("Email is required"),
      password: Yup.string().max(255).required("Password is required"),
    }),
    onSubmit: () => {
      router.push("/");
    },
  });
  */

  const loginHandler = async () => {
    const headers = {
      accept: "*/*",
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
      //"Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
      //"Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
    };
    await axios
      .post(
        // `http://localhost:4000/api/auth/login`,
        `http://localhost:9000/api/v1/auth/login`,
        {
          username: username,
          password: password,
        },
        {
          headers: headers,
        }
      )
      .then((res) => localStorage.setItem("token", JSON.stringify(res.data)));

    const { jwt } = JSON.parse(localStorage.getItem("token"));
    await axios
      .get(
        // `http://localhost:4000/api/auth/profile`,
        `http://localhost:9000/api/v1/auth/profile`,

        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      )
      .then((res) => {
        const profileData = res.data;
        localStorage.setItem("profileData", JSON.stringify(profileData));
      });
    router.push("/");
  };

  const handleUserNameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  return (
    <>
      <Head>
        <title>Login | Penitentiary</title>
      </Head>
      <Box
        component="main"
        sx={{
          alignItems: "center",
          display: "flex",
          flexGrow: 1,
          minHeight: "100%",
        }}
      >
        <Container maxWidth="sm">
          <Box sx={{ my: 3 }}>
            <Typography color="textPrimary" variant="h4">
              Sign in
            </Typography>
            <Typography color="textSecondary" gutterBottom variant="body2">
              Sign in on the internal platform
            </Typography>
          </Box>
          {/* <Grid container spacing={3}>
              <Grid item xs={12} md={6}>
                <Button
                  color="info"
                  fullWidth
                  startIcon={<FacebookIcon />}
                  onClick={formik.handleSubmit}
                  size="large"
                  variant="contained"
                >
                  Login with Facebook
                </Button>
              </Grid>
              <Grid item xs={12} md={6}>
                <Button
                  fullWidth
                  color="error"
                  startIcon={<GoogleIcon />}
                  onClick={formik.handleSubmit}
                  size="large"
                  variant="contained"
                >
                  Login with Google
                </Button>
              </Grid>
            </Grid> */}
          {/* <Box
              sx={{
                pb: 1,
                pt: 3,
              }}
            >
              <Typography align="center" color="textSecondary" variant="body1">
                or login with email address
              </Typography>
            </Box> */}
          <TextField
            id="username"
            //error={Boolean(formik.touched.email && formik.errors.email)}
            onChange={handleUserNameChange}
            fullWidth
            //helperText={formik.touched.email && formik.errors.email}
            label="Username"
            margin="normal"
            name="username"
            //onBlur={formik.handleBlur}
            type="text"
            //value={formik.values.email}
            variant="outlined"
            value={username}
          />
          <TextField
            id="password"
            //error={Boolean(formik.touched.password && formik.errors.password)}
            fullWidth
            //helperText={formik.touched.password && formik.errors.password}
            label="Password"
            margin="normal"
            name="password"
            onChange={handlePasswordChange}
            type="password"
            //value={formik.values.password}
            variant="outlined"
            value={password}
          />
          <Box sx={{ py: 2 }}>
            <Button
              onClick={loginHandler}
              color="primary"
              //disabled={formik.isSubmitting}
              fullWidth
              size="large"
              type="submit"
              variant="contained"
            >
              Sign In Now
            </Button>
          </Box>
          {/*
            <Typography color="textSecondary" variant="body2">
              Don&apos;t have an account?{" "}
              <NextLink href="/register">
                <Link
                  to="/register"
                  variant="subtitle2"
                  underline="hover"
                  sx={{
                    cursor: "pointer",
                  }}
                >
                  Sign Up
                </Link>
              </NextLink>
            </Typography>
            */}
        </Container>
      </Box>
    </>
  );
};

export default Login;
