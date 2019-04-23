import React, { Component } from "react";
import { Button, Modal } from "react-bootstrap";
import "./displayResults.css";

export default class displayResults extends Component {
  constructor(props) {
    super(props);
    console.log("props", props);
    this.state = {
      show: false
    };
  }

  handleOpen = event => {
    this.setState({ show: true });
  };

  handleClose = event => {
    this.props.close();
  };

  render() {
    return (
      <Modal show={this.props.show} onHide={this.props.close}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>Woohoo, you're reading this text in a modal!</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={this.handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={this.handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    );
  }
}
