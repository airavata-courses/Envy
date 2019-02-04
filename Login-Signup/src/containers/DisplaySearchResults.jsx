import React, { Component } from "react";
import { Timeline, TimelineItem } from "vertical-timeline-component-for-react";
import NotFound from "./NotFound";

export default class DisplaySearchResults extends Component {
  constructor(props) {
    super(props);
    this.state = {
      results: ""
    };
  }

  render() {
    let renderComponent = <NotFound />;
    if (this.props.isAuthenticated !== false) {
      renderComponent = (
        <Timeline lineColor={"#ddd"}>
          <TimelineItem
            key="001"
            dateText="11/03/2019 – 2:30 Hrs"
            dateInnerStyle={{ background: "#61b8ff" }}
            bodyContainerStyle={{
              background: "#eeeeee",
              padding: "10px",
              borderRadius: "8px",
              boxShadow: "0.5rem 0.5rem 2rem 0 rgba(0, 0, 0, 0.2)"
            }}
          >
            <h5>Uber</h5>
            <p>
              Ride from 2665 E, 7th St, Bloomington -> (IND) Indianapolis
              Airport
            </p>
            <p>Fare charge: 35$</p>
          </TimelineItem>
          <TimelineItem
            key="002"
            dateText="11/03/2019 – 4:30 Hrs"
            dateInnerStyle={{ background: "#61b8ff" }}
            bodyContainerStyle={{
              background: "#eeeeee",
              padding: "10px",
              borderRadius: "8px",
              boxShadow: "0.5rem 0.5rem 2rem 0 rgba(0, 0, 0, 0.2)"
            }}
          >
            <h5>Flight - Southwest Airlines</h5>
            <p>(IND) Indianapolis -> (JFK) NewYork</p>
            <p>Fare charge: 200$</p>
          </TimelineItem>
          <TimelineItem
            key="003"
            dateText="11/03/2019 – 5:45 Hrs"
            dateInnerStyle={{ background: "#61b8ff" }}
            bodyContainerStyle={{
              background: "#eeeeee",
              padding: "10px",
              borderRadius: "8px",
              boxShadow: "0.5rem 0.5rem 2rem 0 rgba(0, 0, 0, 0.2)"
            }}
          >
            <h5>Lyft</h5>
            <p>
              Ride from (JFK) John F. Kennedy Airport -> Stony Brook University
            </p>
            <p>Fare charge 50$</p>
          </TimelineItem>
        </Timeline>
      );
    }
    return <div>{renderComponent}</div>;
  }
}
