import React, { Component } from "react";
import { Timeline, TimelineItem } from "vertical-timeline-component-for-react";
import NotFound from "./NotFound";
import ResultCard from "./resultCard";

export default class DisplaySearchResults extends Component {
  constructor(props) {
    super(props);
    const v = props.location.authorize;
    this.state = {
      origin: v.origin,
      destination: v.destination,
      origin_cab_endpoint: v.cab_origin_endpoint,
      destination_cab_startpoint: v.cab_destination_startpoint,
      cheap_flight: v.cheapest.flight,
      cheap_flight_time: v.cheapest.flight_time,
      cheap_destination_cab: v.cheapest.cab_destination,
      cheap_destination_cab_time: v.cheapest.cab_destination_time,
      cheap_origin_cab: v.cheapest.cab_origin,
      cheap_origin_cab_time: v.cheapest.cab_time_origin,
      remaining_results: v.remaining_results
    };
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
            {this.state.remaining_results.map(n => {
              return <ResultCard value={n} />;
            })}
          </div>
        </React.Fragment>
      );
    }
    return (
      <div>
        <div>{renderComponent}</div>
      </div>
    );
  }
}
