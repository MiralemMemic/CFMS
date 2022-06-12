import { format } from "date-fns";
import { v4 as uuid } from "uuid";
import PerfectScrollbar from "react-perfect-scrollbar";
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
} from "@mui/material";
import ArrowRightIcon from "@mui/icons-material/ArrowRight";
import { SeverityPill } from "../severity-pill";
import axios from "axios";
import { useState, useEffect } from "react";

export const LatestAnnouncmenets = (props) => {
  const [incidents, setIncidents] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:4000/api/incident/latest`).then((res) => {
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
                <TableCell>Description</TableCell>
                <TableCell>Prisoner</TableCell>
                <TableCell sortDirection="desc">
                  <Tooltip enterDelay={300} title="Sort">
                    <TableSortLabel active direction="desc">
                      Date
                    </TableSortLabel>
                  </Tooltip>
                </TableCell>
                <TableCell>Status</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {incidents.map((incident) => {
                const date = new Date(incident.createdAt);
                const arrivalDate =
                  date.getDate() +
                  "/" +
                  (date.getMonth() + 1) +
                  "/" +
                  date.getFullYear();
                return (
                  <TableRow hover key={incident._id}>
                    <TableCell>{incident.text}</TableCell>
                    <TableCell>{incident.prisonerId}</TableCell>
                    <TableCell>{arrivalDate}</TableCell>
                    <TableCell>
                      <SeverityPill
                        color={
                          incident.status == "Warning"
                            ? "warning"
                            : incident.status == "Closed"
                            ? "primary"
                            : "error"
                        }
                      >
                        {incident.status}
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
          display: "flex",
          justifyContent: "flex-end",
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
