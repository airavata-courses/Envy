import React, { Component } from "react";
import "./signup.css";
import Cookies from "js-cookie";
import Card from "react-bootstrap/Card";
import { Button, Form, Alert, Spinner } from "react-bootstrap";

export default class Signup extends Component {
  constructor(props) {
    super(props);

    this.state = {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmPassword: "",
      confirmationCode: "",
      newUser: null,
      isLoading: false,
      error: false,
      message: ""
    };
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  validateForm() {
    return (
      this.state.email.length > 0 &&
      this.state.password.length > 0 &&
      this.state.firstName.length > 0 &&
      this.state.lastName.length > 0 &&
      this.state.firstName !== this.state.lastName
    );
  }

  componentWillMount() {
    if (Cookies.get("username") !== undefined) {
      Cookies.remove("username");
      Cookies.remove("search_id");
      Cookies.remove("user");
    }
  }

  handleSubmit = async event => {
    event.preventDefault();

    this.setState({ isLoading: true });
    const url = "http://149.165.171.47:30015/signup";
    let data = {
      username: this.state.email,
      password: this.state.password,
      firstName: this.state.firstName,
      lastName: this.state.lastName
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
          window.location.href = "/";
        } else {
          console.log("Username already taken");
          //alert("Please choose another email");
          this.setState({
            error: true,
            message: "Please choose another email id",
            isLoading: false
          });
        }
      })
      .catch(error => {
        console.error("Error:", error);
        this.setState({
          error: true,
          message: error,
          isLoading: false
        });
      });
    event.preventDefault();
  };

  handle = event => {
    this.setState({ error: false, message: "" });
  };

  render() {
    const { isLoading, error, message } = this.state;
    return (
      <div>
        <Card bg="light" className="container signup">
          <Card.Body>
            <Card.Title>Sign Up</Card.Title>
            <div className="signup-form">
              <Form onSubmit={this.handleSubmit}>
                <Form.Row>
                  <Form.Group
                    className="signup-form-group"
                    controlId="validationCustom01"
                  >
                    <Form.Label>First name</Form.Label>
                    <Form.Control
                      required
                      type="text"
                      placeholder="First Name"
                      id="firstName"
                      value={this.state.firstName}
                      onChange={this.handleChange}
                    />
                  </Form.Group>
                  <Form.Group
                    className="signup-form-group"
                    controlId="validationCustom02"
                  >
                    <Form.Label>Last name</Form.Label>
                    <Form.Control
                      required
                      type="text"
                      placeholder="Last Name"
                      id="lastName"
                      value={this.state.lastName}
                      onChange={this.handleChange}
                    />
                  </Form.Group>
                </Form.Row>
                <Form.Row>
                  <Form.Group
                    controlId="validationCustom03"
                    className="signup-form-group-email"
                  >
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                      required
                      id="email"
                      type="email"
                      value={this.state.email}
                      onChange={this.handleChange}
                      placeholder="Enter Email"
                    />
                  </Form.Group>

                  <Form.Group
                    controlId="validationCustom04"
                    className="signup-form-group"
                  >
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                      required
                      id="password"
                      type="password"
                      value={this.state.password}
                      onChange={this.handleChange}
                      placeholder="Enter Password"
                    />
                  </Form.Group>
                </Form.Row>
                <Button
                  variant="primary"
                  style={{ marginLeft: "42%" }}
                  disabled={isLoading || !this.validateForm()}
                  onClick={!isLoading ? this.handleSubmit : null}
                >
                  {isLoading ? (
                    <Spinner style={{ marginTop: "2px" }} animation="grow" />
                  ) : (
                    "  Sign up  "
                  )}
                </Button>
              </Form>
            </div>
          </Card.Body>
        </Card>
        {error ? (
          <Alert
            style={{ marginLeft: "80%" }}
            variant="danger"
            onClose={this.handle}
            dismissible
          >
            {message}
          </Alert>
        ) : null}
      </div>
    );
  }
}
