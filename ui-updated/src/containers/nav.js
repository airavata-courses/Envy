import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import Cookies from "js-cookie";
import { Button, Form, Navbar, Nav } from "react-bootstrap";
import "./nav.css";

class nav extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: Cookies.get("username")
    };
  }

  handleSubmit = event => {
    Cookies.remove("username");
    Cookies.remove("search_id");
    Cookies.remove("user");
    window.location.href = "/";
  };

  render() {
    console.log("username", Cookies.get("username"));
    const { username } = this.state;
    return (
      <div>
        <Navbar className="navbar-default" expand="lg">
          <Navbar.Brand
            href="#home"
            style={{
              fontSize: "2em",
              color: "azure"
            }}
          >
            Travel - Guide Blue
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto" />
            <Form inline>
              {username === undefined ? (
                <div>
                  <Button variant="light mr-sm-2">
                    <Link className="nav-link" to="/register">
                      Sign Up
                    </Link>
                  </Button>
                  <Button variant="light sm">
                    <Link className="nav-link" to="/">
                      Login
                    </Link>
                  </Button>
                </div>
              ) : (
                <Button variant="light sm" onClick={this.handleSubmit}>
                  <Link className="nav-link">Logout</Link>
                </Button>
              )}
            </Form>
          </Navbar.Collapse>
        </Navbar>
      </div>
    );
  }
}

Navbar.propTypes = {
  classes: PropTypes.object.isRequired
};
export default nav;
