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
import { SeverityPill } from '../severity-pill';
import { useState, useEffect } from 'react';
import axios from 'axios';

export const RequestListResults = ({ customers, handler, ...rest }) => {
  const [selectedCustomerIds, setSelectedCustomerIds] = useState([]);
  const [limit, setLimit] = useState(25);
  const [page, setPage] = useState(0);

  const [loggedRole, setRole] = useState('');

  React.useEffect(() => {
    if (typeof window != 'undefined') {
      const { role } = JSON.parse(localStorage.getItem('profileData'));
      setRole(role);
    }
  }, []);

  /*
  const handleSelectOne = (event, id) => {
    const selectedIndex = selectedCustomerIds.indexOf(id);
    let newSelectedCustomerIds = [];

    if (selectedIndex === -1) {
      newSelectedCustomerIds = newSelectedCustomerIds.concat(
        selectedCustomerIds,
        id
      );
    } else if (selectedIndex === 0) {
      newSelectedCustomerIds = newSelectedCustomerIds.concat(
        selectedCustomerIds.slice(1)
      );
    } else if (selectedIndex === selectedCustomerIds.length - 1) {
      newSelectedCustomerIds = newSelectedCustomerIds.concat(
        selectedCustomerIds.slice(0, -1)
      );
    } else if (selectedIndex > 0) {
      newSelectedCustomerIds = newSelectedCustomerIds.concat(
        selectedCustomerIds.slice(0, selectedIndex),
        selectedCustomerIds.slice(selectedIndex + 1)
      );
    }

    setSelectedCustomerIds(newSelectedCustomerIds);
  };
  */

  const handleLimitChange = (event) => {
    setLimit(event.target.value);
  };

  const handlePageChange = (event, newPage) => {
    setPage(newPage);
  };

  return (
    <Card {...rest}>
      <div>
        <PerfectScrollbar>
          <Box sx={{ minWidth: 1050 }}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Description</TableCell>
                  <TableCell>Sender</TableCell>
                  <TableCell>Status</TableCell>
                  <TableCell>Options</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {customers.slice(0, limit).map((request, index) => (
                  <TableRow
                    hover
                    key={index}
                    //selected={selectedCustomerIds.indexOf(customer.id) !== -1}
                  >
                    <TableCell>
                      <Box
                        sx={{
                          alignItems: 'center',
                          display: 'flex',
                        }}
                      >
                        {/* <Avatar src={customer.image} sx={{ mr: 2 }}>
                        {getInitials(customer.name)}
                      </Avatar> */}
                        <Typography
                          key={index}
                          color="textPrimary"
                          variant="body1"
                        >
                          {request.text}
                        </Typography>
                      </Box>
                    </TableCell>
                    <TableCell key={index}>{request.user}</TableCell>
                    <TableCell>
                      <SeverityPill
                        key={index}
                        color={
                          request.status == 'Awaiting approval'
                            ? 'warning'
                            : request.status == 'Completed'
                            ? 'primary'
                            : 'error'
                        }
                      >
                        {request.status}
                      </SeverityPill>
                    </TableCell>
                    <TableCell>
                      {request.status != 'Completed' &&
                        loggedRole == 'Warden' && (
                          <LongMenu
                            requestId={request._id}
                            changeHandler={handler}
                          />
                        )}
                    </TableCell>
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
          rowsPerPage={limit}
          rowsPerPageOptions={[5, 10, 25]}
        />
      </div>
    </Card>
  );
};

RequestListResults.propTypes = {
  customers: PropTypes.array.isRequired,
};

import * as React from 'react';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import MoreVertIcon from '@mui/icons-material/MoreVert';

const options = ['Complete', 'Deny'];

const ITEM_HEIGHT = 48;

export default function LongMenu(request) {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = async (e) => {
    let status = e.target.innerHTML;
    var realStatus =
      status == 'Complete'
        ? 'Completed'
        : status == 'Deny'
        ? 'Denied'
        : 'Warning';

    if (typeof window != 'undefined') {
      await axios
        .put(`http://localhost:4000/api/request/update/${request.requestId}`, {
          status: realStatus,
        })
        .then((res) => console.log(res))
        .catch((err) => console.log(err));
    }
    setAnchorEl(null);
    request.changeHandler();
  };

  return (
    <div>
      <IconButton
        aria-label="more"
        id="long-button"
        aria-controls={open ? 'long-menu' : undefined}
        aria-expanded={open ? 'true' : undefined}
        aria-haspopup="true"
        onClick={handleClick}
      >
        <MoreVertIcon />
      </IconButton>
      <Menu
        id="long-menu"
        MenuListProps={{
          'aria-labelledby': 'long-button',
        }}
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        PaperProps={{
          style: {
            maxHeight: ITEM_HEIGHT * 4.5,
            width: '20ch',
          },
        }}
      >
        {options.map((option) => (
          <MenuItem
            key={option}
            selected={option === 'Pyxis'}
            onClick={handleClose}
          >
            {option}
          </MenuItem>
        ))}
      </Menu>
    </div>
  );
}
