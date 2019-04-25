import React, { Component } from "react";
import "./login.css";
import { Link } from "react-router-dom";
import Cookies from "js-cookie";
import Card from "react-bootstrap/Card";
import { Button, Form, Alert, Spinner } from "react-bootstrap";
import { GoogleLogin } from "react-google-login";

export default class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",
      error: false,
      isLoading: false,
      message: ""
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

  responseGoogleSucc = response => {
    console.log("Logged in");
    Cookies.set("username", response.profileObj.name);
    window.location.href = "/home";
  };

  responseGoogle = response => {
    this.setState({
      error: true,
      isLoading: false,
      message: response
    });
  };

  componentWillMount() {
    if (Cookies.get("username") !== undefined) {
      Cookies.remove("username");
      Cookies.remove("search_id");
      Cookies.remove("user");
    }
  }

  handleSubmit = event => {
    this.setState({ isLoading: true });
    const url = "http://149.165.171.47/login";
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
        if (data.status.error === "false") {
          console.log("Logged in");
          Cookies.set("username", data.status.firstName);
          window.location.href = "/home";
        } else {
          console.log("Invalid credentials");
          this.setState({
            error: true,
            isLoading: false,
            message: "Invalid Credentials"
          });
          //window.location.href = "/register";
        }
      })
      .catch(error => console.error("Error:", error));
    event.preventDefault();
  };

  render() {
    const { isLoading, error } = this.state;
    const handleHide = () => this.setState({ error: false });
    return (
      <div>
        <Card bg="light" className="container shadow-lg">
          <Card.Body>
            <Card.Title>Log In </Card.Title>
            <Card.Subtitle className="mb-2 text-muted">
              Sign into your account
            </Card.Subtitle>
            <div className="login-form">
              <Form>
                <Form.Group>
                  <Form.Label>Email address</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Enter email"
                    id="email"
                    value={this.state.email}
                    onChange={this.handleChange}
                  />
                </Form.Group>
                <Form.Group>
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Password"
                    id="password"
                    value={this.state.password}
                    onChange={this.handleChange}
                  />
                </Form.Group>
                <br />
                <Button
                  variant="primary"
                  style={{ marginLeft: "45%" }}
                  disabled={isLoading || !this.validateForm()}
                  onClick={!isLoading ? this.handleSubmit : null}
                >
                  {isLoading ? (
                    <Spinner style={{ marginTop: "2px" }} animation="grow" />
                  ) : (
                    "Login"
                  )}
                </Button>
              </Form>
            </div>
            <Card.Text>
              New User?
              <Button variant="link sm" className="btn-link">
                <Link className="nav-link" to="/register">
                  Sign Up
                </Link>
              </Button>
            </Card.Text>
            <hr
              style={{
                marginLeft: "15%",
                marginRight: "15%",
                marginTop: "3%",
                marginBottom: "5%",
                background: "#835b4b"
              }}
            />
            <GoogleLogin
              clientId="422192420540-ihkf9e02egv5tve8ce0v8nc5an8qlsjk.apps.googleusercontent.com"
              buttonText="Login"
              onSuccess={this.responseGoogleSucc}
              onFailure={this.responseGoogle}
              cookiePolicy={"single_host_origin"}
              className="googleButton"
            />
          </Card.Body>
        </Card>
        <div style={{ marginLeft: "80%", marginTop: "2%" }}>
          <Alert show={error} variant="danger" style={{ display: "flex" }}>
            <Alert.Heading style={{ padding: "2%" }}>
              {this.state.message}
            </Alert.Heading>
            {"  "}
            <span style={{ marginLeft: "15%", marginTop: "1%" }}>
              <Button onClick={handleHide} variant="danger">
                X
              </Button>
            </span>
          </Alert>
        </div>
      </div>
    );
  }
}
