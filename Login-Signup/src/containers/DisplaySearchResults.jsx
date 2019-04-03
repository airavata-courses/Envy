import React, { Component } from "react";
import { Timeline, TimelineItem } from "vertical-timeline-component-for-react";
import ResultCard from "./resultCard";

export default class DisplaySearchResults extends Component {
  constructor(props) {
    super(props);
    const v = props.location.authorize;
    this.state = {
      searchid: v.searchid,
      remaining_results: [0],
      isLoading: false,
      data: ""
    };
  }
  setValue() {
    console.log(this.state.data);
  }
  componentWillMount() {
    console.log("Dsiplay results");
    let data = {
      key: "display",
      search_id: this.state.searchid
    };
    const url = "http://149.165.170.230:30035/getiternary/?search_id="+this.state.searchid;
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
          this.setState({ isLoading: true, data: data });
        }
      })
      .catch(error => console.error("Error:", error));
  }
  render() {
    return (
      this.state.isLoading && (
        <React.Fragment>
          <Timeline lineColor={"#ddd"}>
            <TimelineItem
              key="001"
              dateText={this.state.data.status.cheapest.cab_origin_time}
              dateInnerStyle={{ background: "#61b8ff" }}
              bodyContainerStyle={{
                background: "#eeeeee",
                padding: "10px",
                borderRadius: "8px",
                boxShadow: "0.5rem 0.5rem 2rem 0 rgba(0, 0, 0, 0.2)"
              }}
            >
              <h5>{this.state.data.status.cheapest.cab_origin}</h5>
              <p>
                Ride from {this.state.data.status.origin} ->{" "}
                {this.state.data.status.cheapest.cab_origin_endpoint}
              </p>
              <p>
                Fare charge: {this.state.data.status.cheapest.cab_origin_fare}$
              </p>
            </TimelineItem>
            <TimelineItem
              key="002"
              dateText={this.state.data.status.cheapest.flight_time}
              dateInnerStyle={{ background: "#61b8ff" }}
              bodyContainerStyle={{
                background: "#eeeeee",
                padding: "10px",
                borderRadius: "8px",
                boxShadow: "0.5rem 0.5rem 2rem 0 rgba(0, 0, 0, 0.2)"
              }}
            >
              <h5>Flight - {this.state.data.status.cheapest.flight}</h5>
              <p>
                {this.state.data.status.cheapest.cab_origin_endpoint} ->{" "}
                {this.state.data.status.cheapest.cab_destination_startpoint}
              </p>
              <p>
                Fare charge:{" "}
                {this.state.data.status.cheapest.total_price -
                  this.state.data.status.cheapest.cab_origin_fare -
                  this.state.data.status.cheapest.cab_destination_fare}
                $
              </p>
            </TimelineItem>
            <TimelineItem
              key="003"
              dateText={this.state.data.status.cheapest.cab_destination_time}
              dateInnerStyle={{ background: "#61b8ff" }}
              bodyContainerStyle={{
                background: "#eeeeee",
                padding: "10px",
                borderRadius: "8px",
                boxShadow: "0.5rem 0.5rem 2rem 0 rgba(0, 0, 0, 0.2)"
              }}
            >
              <h5>{this.state.data.status.cheapest.cab_destination}</h5>
              <p>
                Ride from{" "}
                {this.state.data.status.cheapest.cab_destination_startpoint} ->{" "}
                {this.state.data.status.destination}
              </p>
              <p>
                Fare charge{" "}
                {this.state.data.status.cheapest.cab_destination_fare}$
              </p>
            </TimelineItem>
          </Timeline>
          <hr />
          <div className="display-result">
            {this.state.data.status.remaining_results.map(n => {
              return <ResultCard value={n} />;
            })}
          </div>
        </React.Fragment>
      )
      // this.state.remaining_results[0] && (
      //   <div>
      //     <div>{renderComponent}</div>
      //   </div>
      // )
    );
  }
}
