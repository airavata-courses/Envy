import React, { Component } from "react";
import "./resultCard.css";
import { Timeline } from "antd";
import "antd/dist/antd.css";
import "./displayTime.css";

export default class resultCard extends Component {
  constructor(props) {
    super(props);
    console.log("props", props);
    this.state = {
      val: this.props.value.data,
      origin: this.props.value.origin,
      destination: this.props.value.destination
    };
  }
  onComponentWillMount() {
    console.log("state", this.state);
  }
  render() {
    const disp = this.state.val;
    return (
      <div className="cardm contain-new grey lighten-3">
        <Timeline className="time">
          <div className="time-item">
            <Timeline.Item color="red">
              {this.state.origin}
              <p />
              <Timeline.Item color="blue">
                {disp.cab_origin}
                {" " + disp.cab_origin_fare+"$"}
              </Timeline.Item>
            </Timeline.Item>
          </div>
          <div className="time-item">
            <Timeline.Item className="time-item" color="green">
              {disp.cab_origin_endpoint}
              <p />
              <Timeline.Item color="blue">
                {disp.flight}{" "}
                {disp.total_price -
                  disp.cab_origin_fare -
                  disp.cab_destination_fare}
                $
              </Timeline.Item>
            </Timeline.Item>
          </div>
          <div className="time-item">
            <Timeline.Item className="time-item" color="green">
              {disp.cab_destination_startpoint}
              <p />
              <Timeline.Item color="blue">{disp.cab_destination}
                {" " + disp.cab_destination_fare+"$"}</Timeline.Item>
            </Timeline.Item>
          </div>
          <div className="time-item">
            <Timeline.Item className="time-item" color="red">
              {this.state.destination}
            </Timeline.Item>
          </div>
        </Timeline>
      </div>
    );
  }
}
