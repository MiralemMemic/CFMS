import Head from 'next/head';
import { Box, Container } from '@mui/material';
import { IncidentListResults } from '../components/incidents/incident-list-results';
import { IncidentListToolbar } from '../components/incidents/incident-list-toolbar';
import { DashboardLayout } from '../components/dashboard-layout';
import { customers } from '../__mocks__/customers';
import { useEffect, useState } from 'react';
import axios from 'axios';

const Incidents = () => {
  const [incidents, setIncidents] = useState([]);

  useEffect(() => {
    if (typeof window !== 'undefined') {
      const { access_token } = JSON.parse(localStorage.getItem('token'));
      axios
        .get(`http://localhost:4000/api/incident`, {
          headers: {
            Authorization: `Bearer ${access_token}`,
          },
        })
        .then((res) => {
          const requests = res.data;
          setIncidents(requests);
        });
    }
  }, []);

  const changeHandler = () => {
    if (typeof window !== 'undefined') {
      const { access_token } = JSON.parse(localStorage.getItem('token'));
      axios
        .get(`http://localhost:4000/api/incident`, {
          headers: {
            Authorization: `Bearer ${access_token}`,
          },
        })
        .then((res) => {
          const incidents = res.data;
          setIncidents(incidents);
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
          <IncidentListToolbar handler={changeHandler} />
          <Box sx={{ mt: 3 }}>
            <IncidentListResults
              customers={incidents}
              handler={changeHandler}
            />
          </Box>
        </Container>
      </Box>
    </>
  );
};
Incidents.getLayout = (page) => <DashboardLayout>{page}</DashboardLayout>;

export default Incidents;
