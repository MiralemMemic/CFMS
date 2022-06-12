import { useEffect, useState } from 'react';
import PerfectScrollbar from 'react-perfect-scrollbar';
import PropTypes from 'prop-types';
import { format } from 'date-fns';
import {
  Avatar,
  Box,
  Card,
  Checkbox,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TablePagination,
  TableRow,
  Typography,
} from '@mui/material';
import { getInitials } from '../../utils/get-initials';
import axios from 'axios';

export const CustomerListResults = ({ customers, ...rest }) => {
  const [selectedCustomerIds, setSelectedCustomerIds] = useState([]);
  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(0);

  const handleLimitChange = (event) => {
    setLimit(event.target.value);
  };

  const handlePageChange = (event, newPage) => {
    setPage(newPage);
  };

  return (
    <Card {...rest}>
      <PerfectScrollbar>
        <Box sx={{ minWidth: 1050 }}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Full Name</TableCell>
                <TableCell>Cell</TableCell>
                <TableCell>Sentence</TableCell>
                <TableCell>Identification</TableCell>
                <TableCell>Evaluation</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {customers.map((customer) => (
                <TableRow
                  hover
                  key={customer._id}
                  selected={selectedCustomerIds.indexOf(customer.id) !== -1}
                >
                  <TableCell>
                    <Box
                      sx={{
                        alignItems: 'center',
                        display: 'flex',
                      }}
                    >
                      <Avatar
                        src={
                          customer.image ??
                          'https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Unknown_person.jpg/694px-Unknown_person.jpg'
                        }
                        sx={{ mr: 2 }}
                      >
                        {getInitials(
                          customer.firstName + ' ' + customer.lastName
                        )}
                      </Avatar>
                      <Typography color="textPrimary" variant="body1">
                        {customer.firstName + ' ' + customer.lastName}
                      </Typography>
                    </Box>
                  </TableCell>
                  <TableCell>{customer.cell}</TableCell>
                  <TableCell>
                    {/* {`${customer.address.city}, ${customer.address.state}, ${customer.address.country}`} */}
                    {customer.sentence}
                  </TableCell>
                  <TableCell>{customer.identification}</TableCell>
                  <TableCell>{customer.evaluation ?? ''}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </Box>
      </PerfectScrollbar>
      <TablePagination
        component="div"
        count={customers.length}
        onPageChange={handlePageChange}
        onRowsPerPageChange={handleLimitChange}
        page={page}
        rowsPerPage={25}
        rowsPerPageOptions={[5, 10, 25]}
      />
    </Card>
  );
};

CustomerListResults.propTypes = {
  customers: PropTypes.array.isRequired,
};
