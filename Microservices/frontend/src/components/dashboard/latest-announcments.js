import { format } from 'date-fns';
import { v4 as uuid } from 'uuid';
import PerfectScrollbar from 'react-perfect-scrollbar';
import {
  Box,
  Button,
  Card,
  CardHeader,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  TableSortLabel,
  Tooltip,
} from '@mui/material';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import { SeverityPill } from '../severity-pill';
import axios from 'axios';
import { useState, useEffect } from 'react';

export const LatestAnnouncmenets = (props) => {
  const [incidents, setIncidents] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:9000/api/v1/notifications`).then((res) => {
      const incidents = res.data;
      setIncidents(incidents);
    });
  }, []);
  return (
    <Card {...props}>
      <CardHeader title="Latest Notifications " />
      <PerfectScrollbar>
        <Box sx={{ minWidth: 800 }}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Information</TableCell>
                <TableCell>Status</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {incidents.slice(0, 5).map((incident) => {
                return (
                  <TableRow hover key={incident._id}>
                    <TableCell>{incident.text}</TableCell>
                    <TableCell>
                      <SeverityPill
                        color={
                          incident.status == 'Warning'
                            ? 'warning'
                            : incident.status == 'Closed'
                            ? 'primary'
                            : 'error'
                        }
                      >
                        Alarm
                      </SeverityPill>
                    </TableCell>
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        </Box>
      </PerfectScrollbar>
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'flex-end',
          p: 2,
        }}
      >
        <Button
          color="primary"
          endIcon={<ArrowRightIcon fontSize="small" />}
          size="small"
          variant="text"
          href="/incidents"
        >
          View all
        </Button>
      </Box>
    </Card>
  );
};
