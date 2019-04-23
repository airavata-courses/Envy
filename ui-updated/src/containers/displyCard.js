import React, { Component } from "react";
import "antd/dist/antd.css";
import { Card } from "react-bootstrap";
import "./displayCard.css";
import { Timeline } from "antd";

export default class resultCard extends Component {
  constructor(props) {
    super(props);
    console.log(props);
    this.state = {
      val: "",
      showPopUp: false
    };
  }

  render() {
    const { data } = this.props.data;
    return (
      <Card className="display">
        <Timeline
          style={{ marginTop: "10%", marginLeft: "10%", textAlign: "left" }}
        >
          <Timeline.Item color="red">
            {this.props.data.origin}
            <p />
            <Timeline.Item color="blue">
              {data.cab_origin}
              {" " + data.cab_origin_fare + "$"}
            </Timeline.Item>
          </Timeline.Item>
          <Timeline.Item color="green">
            {data.cab_origin_endpoint.includes("Airport") ||
            data.cab_origin_endpoint.includes("airport")
              ? data.cab_origin_endpoint
              : data.cab_origin_endpoint + " Airport"}
            <p />
            <Timeline.Item color="blue">
              {data.flight}{" "}
              {" " +
                data.total_price -
                data.cab_origin_fare -
                data.cab_destination_fare}
              $
            </Timeline.Item>
          </Timeline.Item>
          <Timeline.Item color="green">
            {data.cab_destination_startpoint}
            <p />
            <Timeline.Item color="blue">
              {data.cab_destination}
              {data.cab_destination_fare + "$"}
            </Timeline.Item>
          </Timeline.Item>
          <Timeline.Item className="time-item" color="red">
            {this.props.data.destination}
          </Timeline.Item>
        </Timeline>
      </Card>
    );
  }
}
