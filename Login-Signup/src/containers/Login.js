import React, { Component } from "react";
import "./Login.css";

export default class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",
      error: "true"
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
    const url = "http://localhost:3000/login";
    let data = { username: this.state.email, password: this.state.password };
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
            pathname: "/",
            authorize: { authorize: this.state.error }
          });
        }
      })
      .catch(error => console.error("Error:", error));
    event.preventDefault();
  };

  render() {
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
