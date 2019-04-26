import React, { Component } from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Nav from "./containers/nav.js";
import "./App.css";
import Login from "./containers/login.js";
import Signup from "./containers/signup.js";
import Home from "./containers/home.js";
//test jenkins
class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">
          <Nav />
          <Route exact path="/" component={Login} />
          <Route exact path="/register" component={Signup} />
          <Route exact path="/home" component={Home} />
        </div>
      </Router>
    );
  }
}

export default App;
