import React, { Component } from "react";
import { Timeline, TimelineItem } from "vertical-timeline-component-for-react";
import NotFound from "./NotFound";
import ResultCard from "./resultCard";

export default class DisplaySearchResults extends Component {
  constructor(props) {
    super(props);
    const v = props.location.authorize;
    this.state = {
      searchid: v.searchid,
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
    const url =
      "http://127.0.0.1:8000/getiternary/?search_id=" + this.state.searchid;
    let data = {
      search_id: this.state.searchid
    };
    fetch(url, {
      method: "get",
      data: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(function(response) {
        console.log(JSON.stringify(response, null, 4), response.length);
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
            cheap_destination_cab_fare: tc.cab_destination_fare,
            cheap_origin_cab: tc.cab_origin,
            cheap_flight: tc.flight,
            cheap_total_fare: tc.total_price,
            cheap_flight_time: tc.flight_time,
            cheap_destination_cab_time: tc.cab_destination_time,
            cheap_origin_cab_time: tc.cab_origin_time,
            cheap_origin_cab_fare: tc.cab_origin_fare,
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
    if (this.props.isAuthenticated !== false) {
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
              <p>Fare charge: {val.cheap_origin_cab_fare}$</p>
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
              <p>
                Fare charge:{" "}
                {val.cheap_total_fare -
                  val.cheap_origin_cab_fare -
                  val.cheap_destination_cab_fare}
                $
              </p>
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
              <p>Fare charge {val.cheap_destination_cab_fare}$</p>
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
