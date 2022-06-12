import { formatDistanceToNow, subHours } from 'date-fns';
import { v4 as uuid } from 'uuid';
import {
  Box,
  Button,
  Card,
  CardHeader,
  Divider,
  IconButton,
  List,
  ListItem,
  ListItemAvatar,
  ListItemText,
} from '@mui/material';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { useEffect, useState } from 'react';
import axios from 'axios';

const products = [
  {
    id: uuid(),
    name: 'Adam Denisov',
    imageUrl: '/static/images/avatars/avatar_1.png',
    updatedAt: subHours(Date.now(), 2),
  },
  {
    id: uuid(),
    name: 'Ekaterina Tankova',
    imageUrl: '/static/images/avatars/avatar_2.png',
    updatedAt: subHours(Date.now(), 2),
  },
  {
    id: uuid(),
    name: 'Emilee Simchenko',
    imageUrl: '/static/images/avatars/avatar_3.png',
    updatedAt: subHours(Date.now(), 3),
  },
  {
    id: uuid(),
    name: 'Cao Yu',
    imageUrl: '/static/images/avatars/avatar_4.png',
    updatedAt: subHours(Date.now(), 5),
  },
  {
    id: uuid(),
    name: 'Anje Keizer',
    imageUrl: '/static/images/avatars/avatar_5.png',
    updatedAt: subHours(Date.now(), 9),
  },
];

export const LatestPrisoners = (props) => {
  const [prisoners, setPrisoners] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:4000/api/prisoner/latest`).then((res) => {
      const persons = res.data;
      setPrisoners(persons);
    });
  }, []);

  return (
    <Card {...props}>
      <CardHeader
        subtitle={`${prisoners.length} in total`}
        title="Latest Prisoners"
      />
      <Divider />
      <List>
        {prisoners.map((product, i) => {
          const date = new Date(product.createdAt);
          const arrivalDate =
            date.getDate() +
            '/' +
            (date.getMonth() + 1) +
            '/' +
            date.getFullYear();
          return (
            <ListItem divider={i < products.length - 1} key={product._id}>
              <ListItemAvatar>
                <img
                  alt={product.name}
                  src={
                    product.image ??
                    'https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Unknown_person.jpg/694px-Unknown_person.jpg'
                  }
                  style={{
                    height: 48,
                    width: 48,
                  }}
                />
              </ListItemAvatar>
              <ListItemText
                primary={product.name}
                secondary={'Arrived at ' + arrivalDate}
              />
            </ListItem>
          );
        })}
      </List>
      <Divider />
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'flex-end',
          p: 2,
        }}
      >
        <Button
          color="primary"
          endIcon={<ArrowRightIcon />}
          size="small"
          variant="text"
          href="/prisoners"
        >
          View all
        </Button>
      </Box>
    </Card>
  );
};
