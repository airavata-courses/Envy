import React, { Component } from "react";
import "./image.css";

class Image extends Component {
  render() {
    return (
      <div className="containe carde">
        <img
          src="https://d2rbodpj0xodc.cloudfront.net/stories/918962597426889946/1c5f7561-938f-455a-b9f0-a322c12ea214.jpeg"
          alt="air balloon"
          width="350"
          height="550"
        />
      </div>
    );
  }
}

export default Image;
