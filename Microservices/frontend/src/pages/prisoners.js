import Head from 'next/head';
import { Box, Container } from '@mui/material';
import { CustomerListResults } from '../components/customer/customer-list-results';
import { CustomerListToolbar } from '../components/customer/customer-list-toolbar';
import { DashboardLayout } from '../components/dashboard-layout';
import { customers } from '../__mocks__/customers';
import { useEffect, useState } from 'react';
import axios from 'axios';

const Customers = () => {
  const [prisoners, setPrisoners] = useState([]);

  const [changeHappened, setChange] = useState(false);

  useEffect(() => {
    axios.get(`http://localhost:9000/api/v1/prisoners`).then((res) => {
      const persons = res.data;
      setPrisoners(persons);
    });
  }, [changeHappened]);

  const changeHandler = () => {
    setChange(!changeHappened);
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
          <CustomerListToolbar handler={changeHandler} />
          <Box sx={{ mt: 3 }}>
            <CustomerListResults customers={prisoners} />
          </Box>
        </Container>
      </Box>
    </>
  );
};
Customers.getLayout = (page) => <DashboardLayout>{page}</DashboardLayout>;

export default Customers;
