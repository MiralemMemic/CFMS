import Head from 'next/head';
import { Box, Container } from '@mui/material';
import { RequestListResults } from '../components/requests/request-list-results';
import { RequestListToolbar } from '../components/requests/request-list-toolbar';
import { DashboardLayout } from '../components/dashboard-layout';
import { customers } from '../__mocks__/customers';
import { useEffect, useState } from 'react';
import axios from 'axios';

const Requests = () => {
  const [requests, setRequests] = useState([]);

  const [changes, setChanges] = useState(false);

  const [idUlogovanog, setUlogovani] = useState(0);

  useEffect(() => {
    if (typeof window !== 'undefined') {
      const { access_token } = JSON.parse(localStorage.getItem('token'));
      const { id } = JSON.parse(localStorage.getItem('profileData'));
      setUlogovani(id);
      axios
        .get(`http://localhost:9000/api/v1/messages/received/${id}`, {
          headers: {
            Authorization: `Bearer ${access_token}`,
          },
        })
        .then((res) => {
          const requests = res.data;
          setRequests(requests);
        });
    }
  }, [changes]);

  const changeHandler = () => {
    if (typeof window !== 'undefined') {
      const { access_token } = JSON.parse(localStorage.getItem('token'));
      axios
        .get(`http://localhost:4000/api/request`, {
          headers: {
            Authorization: `Bearer ${access_token}`,
          },
        })
        .then((res) => {
          const requests = res.data;
          setRequests(requests);
        });
    }
  };

  return (
    <>
      <Head>
        <title>Prisoners | Penitentiary</title>
      </Head>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          py: 8,
        }}
      >
        <Container maxWidth={false}>
          <RequestListToolbar handler={changeHandler} />
          <Box sx={{ mt: 3 }}>
            <RequestListResults
              logged={idUlogovanog}
              customers={requests}
              handler={changeHandler}
            />
          </Box>
        </Container>
      </Box>
    </>
  );
};
Requests.getLayout = (page) => <DashboardLayout>{page}</DashboardLayout>;

export default Requests;
