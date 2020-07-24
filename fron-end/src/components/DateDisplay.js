import React from 'react';
import PropTypes from 'prop-types';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Paper from '@material-ui/core/Paper';
import { parse } from 'event-stream';


const DateDisplay = ((props) => (
  
  <Paper className="container">
      <List>
          <ListItem>
          <ListItemText>Day: {props.apiResponse.split("-")[0]}</ListItemText>
      </ListItem>
      <ListItem>
          <ListItemText>Month: {props.apiResponse.split("-")[1]}</ListItemText>
      </ListItem>
      <ListItem>
          <ListItemText>Year: {props.apiResponse.split("-")[2]}</ListItemText>
      </ListItem>
      </List>
  </Paper>
));



DateDisplay.propTypes = {  
  apiResponse: PropTypes.string  
}
export default DateDisplay;