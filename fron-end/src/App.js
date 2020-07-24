import React, { Component } from 'react';
import API from './services/dateAPI';
import DateButton from './components/DateButton';
import DateDisplay from './components/DateDisplay';

class App extends Component {
  constructor() {
    super();
    this.state= {
      'apiResponse': ''
    };    
  }

  handleButtonClick = () => {   
    API.getAPIResponse().then((res) => {      
      console.log("Console Response is -->"+JSON. stringify(res));      
      var data = JSON.parse(JSON. stringify(res))
      this.setState({        
        apiResponse: data.date
      })
    });
  }

  render() {
    return (
      <div>
        <center><h1>Date API</h1></center>
        <center><DateButton onClickButton={this.handleButtonClick}></DateButton></center>
        <DateDisplay apiResponse={this.state.apiResponse}>DateDisplay Test</DateDisplay>
      </div>
    );
  }
}

export default App;
