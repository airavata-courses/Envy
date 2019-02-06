import React, { Component } from "react";
import "./Home.css";
import NotFound from "./NotFound";

export default class Home extends Component {
  constructor(props) {
    super(props);
    console.log("golbal", this.props.isAuthenticated);
    this.state = {
      origin: "",
      destination: "",
      date: "",
      cheapest: { cab_origin: "", cab_destination: "", flight: "" },
      remaining_results: [
        { cab_origin: "", cab_destination: "", flight: "" },
        { cab_origin: "", cab_destination: "", flight: "" },
        { cab_origin: "", cab_destination: "", flight: "" },
        { cab_origin: "", cab_destination: "", flight: "" }
      ],
      rental: ""
    };
  }
  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };
  validateForm() {
    return (
      this.state.origin.length > 0 &&
      this.state.destination.length > 0 &&
      this.state.date.length === 5 &&
      this.state.date.includes("-") &&
      this.state.date[0] + this.state.date[1] > 6 &&
      this.state.date[3] + this.state.date[4] >= 2
    );
  }
  handleSubmit = event => {
    const searchResealts = {
      origin: "2665 E, Bloomington",
      destination: "Stonu Brook, New York",
      cab_origin_endpoint: "Chicago O'Hare Airport",
      cab_destination_startpoint: "JFK Airport",
      cheapest: {
        cab_origin: "Lyft",
        cab_fare_origin: "45$",
        cab_time_origin: "2:30hrs",
        cab_destination: "Uber",
        cab_destination_fare: "30$",
        cab_destination_time: "5:45hrs",
        flight: "American Airlines",
        flight_time: "4:00hrs"
      },
      remaining_results: [
        {
          cab_origin: "Ola",
          cab_fare_origin: "45$",
          cab_time_origin: "2:30hrs",
          cab_destination: "Taxi fare",
          cab_destination_fare: "30$",
          cab_destination_time: "5:45hrs",
          flight: "Indigo",
          flight_time: "4:00hrs"
        },
        {
          cab_origin: "Didi Chuxing",
          cab_fare_origin: "45$",
          cab_time_origin: "2:30hrs",
          cab_destination: "Uber",
          cab_destination_fare: "30$",
          cab_destination_time: "5:45hrs",
          flight: "Kingfisher"
        },
        {
          cab_origin: "Didi Chuxing",
          cab_time_origin: "2:30hrs",
          cab_destination: "Uber",
          cab_destination_fare: "30$",
          cab_destination_time: "5:45hrs",
          flight: "Vistara",
          flight_time: "4:00hrs"
        },
        {
          cab_origin: "Uber",
          cab_fare_origin: "45$",
          cab_time_origin: "2:30hrs",
          cab_destination: "Yellow Cabs",
          cab_destination_fare: "30$",
          cab_destination_time: "5:45hrs",
          flight: "Indigo",
          flight_time: "4:00hrs"
        }
      ]
    };
    this.props.history.push({
      pathname: "/display-results",
      authorize: searchResealts
    });
  };

  render() {
    let renderComponent = <NotFound />;
    if (this.props.isAuthenticated !== false) {
      renderComponent = (
        <div className="classGridHome">
          <div className="cardl contain grey lighten-3">
            <div className="search-form">
              <form onSubmit={this.handleSubmit}>
                <div className="row">
                  <div className="input-field col s6">
                    <input
                      id="origin"
                      type="text"
                      value={this.state.origin}
                      onChange={this.handleChange}
                    />
                    <label className="deactive" htmlFor="origin">
                      Origin Address
                    </label>
                  </div>
                </div>
                <div className="row">
                  <div className="input-field col s6">
                    <input
                      id="destination"
                      type="text"
                      value={this.state.destination}
                      onChange={this.handleChange}
                    />
                    <label className="deactive" htmlFor="destination">
                      Destiation Address
                    </label>
                  </div>
                </div>
                <div className="div-radio-date">
                  <p className="radio">
                    <label>
                      <input
                        className="with-gap"
                        name="group3"
                        type="radio"
                        checked={true}
                        value={this.state.rental}
                        onChange={this.handleChange}
                      />
                      <span>Cabs</span>
                    </label>
                    <label>
                      <input
                        className="with-gap"
                        name="group3"
                        type="radio"
                        value={this.state.rental}
                        onChange={this.handleChange}
                      />
                      <span>Rental Car</span>
                    </label>
                  </p>
                  <div className="row-date">
                    <div className="input-field col s6">
                      <input
                        id="date"
                        type="text"
                        value={this.state.date}
                        onChange={this.handleChange}
                      />
                      <label className="deactive" htmlFor="date">
                        DD-MM
                      </label>
                    </div>
                  </div>
                </div>
                <button
                  className="btn waves-effect waves-light"
                  type="submit"
                  name="action"
                  disabled={!this.validateForm()}
                >
                  Submit
                </button>
              </form>
            </div>
          </div>
        </div>
      );
    }
    return <div className="Home">{renderComponent}</div>;
  }
}
