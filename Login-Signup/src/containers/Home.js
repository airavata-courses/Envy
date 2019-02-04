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
    this.props.history.push("/display-results");
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
