// noprotect
import React, { Component } from "react";
import "antd/dist/antd.css";
import Cookies from "js-cookie";
import Card from "react-bootstrap/Card";
import { Button, Form, Row, Col, Alert, Spinner } from "react-bootstrap";
import moment from "moment";
import "./home.css";
import DisplayCard from "./displyCard.js";
import Script from "react-load-script";

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      search_id: "",
      origin: "",
      destination: "",
      date: "",
      error: false,
      isLoading: false,
      isDataRendered: false,
      data: [],
      message: "",
      show: false,
      session: moment()
    };
  }

  componentDidMount() {
    console.log("username", Cookies.get("username"));
    if (Cookies.get("username") === undefined) {
      window.location.href = "/";
    }
  }

  componentWillMount() {
    console.log("username", Cookies.get("username"));
    if (Cookies.get("username") === undefined) {
      window.location.href = "/";
    }
  }

  handleScriptLoad = event => {
    // Declare Options For Autocomplete
    var options = { types: ["address"] };

    // Initialize Google Autocomplete
    /*global google*/
    this.autocomplete_origin = new google.maps.places.Autocomplete(
      document.getElementById("origin"),
      options
    );
    this.autocomplete_destination = new google.maps.places.Autocomplete(
      document.getElementById("destination"),
      options
    );
    // Fire Event when a suggested name is selected
    this.autocomplete_origin.addListener(
      "place_changed",
      this.handlePlaceSelect
    );
    this.autocomplete_destination.addListener(
      "place_changed",
      this.handlePlaceSelect_destination
    );
  };

  handlePlaceSelect_destination = event => {
    // Extract City From Address Object
    let addressObject = this.autocomplete_destination.getPlace();
    let address = addressObject.address_components;

    // Check if address is valid
    if (address) {
      // Set State
      this.setState({
        destination: addressObject.formatted_address
      });
      console.log(addressObject.formatted_address);
    }
  };

  handlePlaceSelect = event => {
    // Extract City From Address Object
    let addressObject = this.autocomplete_origin.getPlace();
    let address = addressObject.address_components;

    // Check if address is valid
    if (address) {
      // Set State
      this.setState({
        origin: addressObject.formatted_address
      });
      console.log(addressObject.formatted_address);
    }
  };

  getSearchData = event => {
    console.log("getSearchData");
    this.setState({ isLoading: true });
    const url =
      " http://149.165.171.47:30035/getiternary/?search_id=" +
      this.state.search_id;
    console.log("url", url);
    fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(function(response) {
        console.log(response);
        return response.json();
      })
      .then(data => {
        console.log(data);
        if (data.status.type === "success") {
          this.setState({
            isLoading: false,
            isDataRendered: true,
            data: data.status.remaining_results
          });
          this.forceUpdate();
        } else {
          this.setState({
            isLoading: false,
            error: true
          });
        }
      })
      .catch(error => console.error("Error:", error));
  };

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  validateForm() {
    return (
      this.state.origin.length > 0 &&
      this.state.destination.length > 0 &&
      this.state.origin !== this.state.destination
    );
  }

  handleSubmit = event => {
    this.setState({ isLoading: true });
    var date = moment(this.state.date);
    if (date.isBefore(moment())) {
      this.setState({
        error: true,
        message: "Please select a date in the future.",
        isLoading: false
      });
    } else {
      const url =
        "http://149.165.171.47:30025/getAirport?origin=" +
        this.state.origin +
        "&destination=" +
        this.state.destination +
        "&date=" +
        this.state.date +
        "&search_id=" +
        moment();
      console.log("url", url);
      fetch(url, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*"
        }
      })
        .then(function(response) {
          return response.json();
        })
        .then(data => {
          console.log(data);
          if (data.success === true) {
            this.setState({
              isLoading: false,
              search_id: data.searchId,
              isDataRendered: false
            });
            this.forceUpdate();
            this.getSearchData();
            console.log("working");
          } else {
            if (data.message === "") {
              this.setState({
                error: true,
                isLoading: false,
                message: "Invalid address. Please try again"
              });
            } else {
              this.setState({
                error: true,
                isLoading: false,
                message: data.message
              });
            }

            this.forceUpdate();
            //alert(data.message);
          }
        })
        .catch(error => console.error("Error:", error));
      event.preventDefault();
    }
  };

  handle = event => {
    this.setState({ error: false });
  };

  render() {
    const { isLoading, error, isDataRendered, message } = this.state;
    const URL =
      "https://maps.googleapis.com/maps/api/js?key=AIzaSyBtwctlbfq65Nd6n-6zAugl7JEi7cxRGQw&libraries=places&sessiontoken=" +
      this.state.session;
    return (
      <div>
        <Script url={URL} onLoad={this.handleScriptLoad} />
        <Card bg="light" className="container">
          <Card.Body>
            <Card.Title>Search </Card.Title>
            <Card.Subtitle className="mb-3 text-muted">
              Search for the cheapest itinerary
            </Card.Subtitle>
            <div className="home-form">
              <Form>
                <Form.Group>
                  <Form.Label>Origin Address</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder=" <Street> <City> <Zip>"
                    id="origin"
                    value={this.state.origin}
                    onChange={this.handleChange}
                  />
                </Form.Group>
                <Form.Group>
                  <Form.Label>Destination Address </Form.Label>
                  <Form.Control
                    type="text"
                    placeholder=" <Street> <City> <Zip>"
                    id="destination"
                    value={this.state.destination}
                    onChange={this.handleChange}
                  />
                </Form.Group>
                <Row>
                  <Col style={{ display: "flex" }}>
                    <Form.Check
                      type="radio"
                      label="Uber"
                      name="uber"
                      id="uber"
                      checked="True"
                      style={{ marginLeft: "35%", fontSize: "17px" }}
                    />
                  </Col>
                  <Col>
                    <input
                      id="date"
                      type="date"
                      value={this.state.date}
                      onChange={this.handleChange}
                    />
                  </Col>
                </Row>
                <br />
                <Button
                  variant="primary"
                  style={{ marginLeft: "46%" }}
                  disabled={isLoading || !this.validateForm()}
                  onClick={!isLoading ? this.handleSubmit : null}
                >
                  {isLoading ? (
                    <Spinner style={{ marginTop: "2px" }} animation="grow" />
                  ) : (
                    "Search"
                  )}
                </Button>
              </Form>
            </div>
          </Card.Body>
        </Card>
        {isDataRendered ? (
          <div>
            <div style={{ margin: "5%", textAlign: "left" }}>
              <h2>Search Results</h2>
              <hr
                style={{
                  height: "1px",
                  background: "#000"
                }}
              />
            </div>
            <div
              style={{
                marginLeft: "10%",
                marginRight: "10%",
                marginBottom: "5px"
              }}
            >
              {this.state.data.map((n, k) => {
                let data = {
                  data: n,
                  origin: this.state.origin,
                  destination: this.state.destination
                };
                return <DisplayCard data={data} key={n[k]} />;
              })}
            </div>
          </div>
        ) : null}
        <div>
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
      </div>
    );
  }
}
