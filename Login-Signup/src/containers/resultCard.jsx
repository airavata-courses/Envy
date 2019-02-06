import React, { Component } from "react";
import "./resultCard.css";

export default class resultCard extends Component {
  constructor(props) {
    super(props);
    console.log("props", props);
    this.state = {
      val: this.props.value
    };
  }
  onComponentWillMount() {
    console.log("state", this.state);
  }
  render() {
    const disp = this.state.val;
    return (
      <div className="card-result grey lighten-3">
        <div>
          <ol>
            <li>
              <p className="diplome">{disp.cab_origin}</p>
              <span className="point" />
              <p className="description">
                Cab start time {disp.cab_time_origin} | Fare charge{" "}
                {disp.cab_fare_origin}
              </p>
            </li>
            <li>
              <p className="diplome">{disp.flight}</p>
              <span className="point" />
              <p className="description">
                Flight departure time {disp.flight_time}| Fare charge: 200$
              </p>
            </li>
            <li>
              <p className="diplome">{disp.cab_destination}</p>
              <span className="point" />
              <p className="description">
                Cab start time {disp.cab_destination_time} | Fare charge
                {disp.cab_destination_fare}
              </p>
            </li>
          </ol>
        </div>
      </div>
    );
  }
}
