import React, { Component } from "react";
import "./Signup.css";

export default class Signup extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
      confirmPassword: "",
      confirmationCode: "",
      newUser: null
    };
  }

  validateForm() {
    return (
      this.state.email.length > 0 &&
      this.state.password.length > 0 &&
      this.state.password === this.state.confirmPassword
    );
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  handleSubmit = async event => {
    event.preventDefault();

    this.setState({ isLoading: true });
    const url = "/foo/signup";
    let data = {
      key: "signup",
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
          console.log("user has signed in");
          this.props.userHasAuthenticated(true);
          this.props.history.push({
            pathname: "/home",
            authorize: { authorize: this.state.error }
          });
        } else {
          console.log("Username already taken");
          alert("Please choose another email");
        }
      })
      .catch(error => console.error("Error:", error));
    event.preventDefault();
  };

  render() {
    return (
      <div className="Signup cardsignup grey lighten-3">
        <form onSubmit={this.handleSubmit}>
          <div className="row-signup">
            <div className="input-field col s6">
              <input
                id="email"
                type="email"
                value={this.state.email}
                onChange={this.handleChange}
              />
              <label className="deactive" htmlFor="email">
                Email
              </label>
            </div>
          </div>
          <div className="row-signup-password">
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
          <div className="row-signup-password">
            <div className="input-field col s6">
              <input
                id="confirmPassword"
                type="password"
                value={this.state.confirmPassword}
                onChange={this.handleChange}
              />
              <label className="deactive" htmlFor="confirmPassword">
                Confirm Password
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
            Signup
          </button>
        </form>
      </div>
    );
  }
}
