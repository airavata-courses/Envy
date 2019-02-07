import React, { Component } from "react";
import "./Home.css";
import NotFound from "./NotFound";
import moment from "moment";
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
    return this.state.origin.length > 0 && this.state.destination.length > 0;
  }
  handleSubmit = event => {
    var date = moment(this.state.date);
    if (!date.isValid()) {
      alert("Date is not valid");
    } else {
      const url = "http://localhost:9200/getAirport";
      let data = {
        origin: this.state.origin,
        destination: this.state.destination,
        date: this.state.date,
        search_di: moment()
      };
      fetch(url, {
        method: "get",
        data: JSON.stringify(data),
        headers: {
          "Content-Type": "application/json"
        }
      })
        .then(function(response) {
          return response.json();
        })
        .then(data => {
          console.log(data);
          if (data.success === true) {
            this.props.history.push({
              pathname: "/display-results",
              authorize: { searchid: data.status.searchid }
            });
            //   console.log("user has signed in");
          }
        })
        .catch(error => console.error("Error:", error));
      event.preventDefault();
    }
  };

  render() {
    let renderComponent = <NotFound />;
    if (this.props.isAuthenticated === false) {
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
                        YYYY-MM-DD
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
