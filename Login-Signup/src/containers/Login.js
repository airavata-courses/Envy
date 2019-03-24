import React, { Component } from "react";
import { Alert } from "reactstrap";
import "./Login.css";

export default class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",
      error: "true",
      isLoading: 0
    };
  }

  validateForm() {
    return this.state.email.length > 0 && this.state.password.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  handleSubmit = event => {
    this.setState({ isLoading: 1 });
    const url = "http://149.165.170.100:3000/";
    let data = {
      key: "login",
      username: this.state.email,
      password: this.state.password
    };
    fetch(url, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(function(response) {
        return response.json();
      })
      .then(data => {
        this.setState({ error: data.status.error });
        if (data.status.error === "false") {
          console.log("user logged in");
          this.props.userHasAuthenticated(true);
          this.props.history.push({
            pathname: "/home",
            authorize: { authorize: this.state.error }
          });
          this.setState({ isLoading: 0 });
        } else {
          console.log("Invalid credentials");
          this.props.userHasAuthenticated(false);
          alert("Invalid Credentials");
          this.setState({ isLoading: 0 });
        }
      })
      .catch(error => console.error("Error:", error));
    event.preventDefault();
  };

  render() {
    let button = null;
    if (this.state.isLoading) {
      button = (
        <div className="above-submit">
          <div className="preloader-wrapper small active">
            <div className="spinner-layer spinner-green-only">
              <div className="circle-clipper left">
                <div className="circle" />
              </div>
              <div className="gap-patch">
                <div className="circle" />
              </div>
              <div className="circle-clipper right">
                <div className="circle" />
              </div>
            </div>
          </div>
        </div>
      );
    } else {
      button = null;
    }
    return (
      <div className="Login card grey lighten-3">
        <form onSubmit={this.handleSubmit}>
          <div className="row-login ">
            <div className="input-field col s6">
              <input
                id="email"
                type="text"
                value={this.state.email}
                onChange={this.handleChange}
              />
              <label className="deactive" htmlFor="email">
                Email
              </label>
            </div>
          </div>
          <div className="row-login">
            <div className="input-field col s6">
              <input
                id="password"
                type="password"
                value={this.state.password}
                onChange={this.handleChange}
              />
              <label className="deactive" htmlFor="password">
                Password
              </label>
            </div>
          </div>
          {button}
          <button
            className="btn btn-login waves-effect waves-light"
            type="submit"
            name="action"
            disabled={!this.validateForm()}
            block="true"
          >
            Submit
          </button>
        </form>
      </div>
    );
  }
}
