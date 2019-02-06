import React, { Component } from "react";
import { Timeline, TimelineItem } from "vertical-timeline-component-for-react";
import NotFound from "./NotFound";
import ResultCard from "./resultCard";

export default class DisplaySearchResults extends Component {
  constructor(props) {
    super(props);
    const v = props.location.authorize;
    this.state = {
      searchid: "",
      origin: "",
      destination: "",
      origin_cab_endpoint: "",
      destination_cab_startpoint: "",
      cheap_flight: "",
      cheap_flight_time: "",
      cheap_destination_cab: "",
      cheap_destination_cab_time: "",
      cheap_origin_cab: "",
      cheap_origin_cab_time: "",
      remaining_results: [0]
    };
  }
  componentWillMount() {
    const url = "http://localhost:3000/test";
    let data = {
      searchid: this.state.searchid
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
        const t = data.status;
        const tc = t.cheapest;
        if (data.status.type === "success") {
          this.setState({
            destination: t.destination,
            origin: t.origin,
            cheap_destination_cab: tc.cab_destination,
            cheap_origin_cab: tc.cab_origin,
            cheap_flight: tc.cheap_flight,
            cheap_flight_time: tc.flight_time,
            cheap_destination_cab_time: tc.cab_destination_time,
            cheap_origin_cab_time: tc.cab_origin_time,
            destination_cab_startpoint: tc.cab_destination_startpoint,
            origin_cab_endpoint: tc.cab_origin_endpoint,
            remaining_results: t.remaining_results
          });
        }
      })
      .catch(error => console.error("Error:", error));
  }
  render() {
    let renderComponent = <NotFound />;
    if (this.props.isAuthenticated === false) {
      const val = this.state;
      renderComponent = (
        <React.Fragment>
          <Timeline lineColor={"#ddd"}>
            <TimelineItem
              key="001"
              dateText={val.cheap_origin_cab_time}
              dateInnerStyle={{ background: "#61b8ff" }}
              bodyContainerStyle={{
                background: "#eeeeee",
                padding: "10px",
                borderRadius: "8px",
                boxShadow: "0.5rem 0.5rem 2rem 0 rgba(0, 0, 0, 0.2)"
              }}
            >
              <h5>{val.cheap_origin_cab}</h5>
              <p>
                Ride from {val.origin} -> {val.origin_cab_endpoint}
              </p>
              <p>Fare charge: 35$</p>
            </TimelineItem>
            <TimelineItem
              key="002"
              dateText={val.cheap_flight_time}
              dateInnerStyle={{ background: "#61b8ff" }}
              bodyContainerStyle={{
                background: "#eeeeee",
                padding: "10px",
                borderRadius: "8px",
                boxShadow: "0.5rem 0.5rem 2rem 0 rgba(0, 0, 0, 0.2)"
              }}
            >
              <h5>Flight - {val.cheap_flight}</h5>
              <p>
                {val.origin_cab_endpoint} -> {val.destination_cab_startpoint}
              </p>
              <p>Fare charge: 200$</p>
            </TimelineItem>
            <TimelineItem
              key="003"
              dateText={val.cheap_destination_cab_time}
              dateInnerStyle={{ background: "#61b8ff" }}
              bodyContainerStyle={{
                background: "#eeeeee",
                padding: "10px",
                borderRadius: "8px",
                boxShadow: "0.5rem 0.5rem 2rem 0 rgba(0, 0, 0, 0.2)"
              }}
            >
              <h5>{val.cheap_destination_cab}</h5>
              <p>
                Ride from {val.destination_cab_startpoint} -> {val.destination}
              </p>
              <p>Fare charge 50$</p>
            </TimelineItem>
          </Timeline>
          <hr />
          <div className="display-result">
            {this.state.remaining_results.slice(0).map(n => {
              return <ResultCard value={n} />;
            })}
          </div>
        </React.Fragment>
      );
    }
    return (
      this.state.remaining_results[0] && (
        <div>
          <div>{renderComponent}</div>
        </div>
      )
    );
  }
}
