import React from 'react';
import { Button, Popover, PopoverHeader, PopoverBody } from 'reactstrap';
import newId from './GenerateID';
var FontAwesome = require('react-fontawesome')

export default class PopOver extends React.Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this._id = newId();
    this.state = {
      popoverOpen: false
    };
  }

  toggle() {
    this.setState({
      popoverOpen: !this.state.popoverOpen
    });
  }

  render() {

    return (
      <div className="popOver">
        <Button id={this._id} color="link" onClick={this.toggle} size="sm" ><FontAwesome name="question" size="1x" /></Button>
        <Popover placement="bottom" isOpen={this.state.popoverOpen} target={this._id} toggle={this.toggle}>
          <PopoverHeader>{this.props.title}</PopoverHeader>
          <PopoverBody>{this.props.content}</PopoverBody>
        </Popover>
      </div>
    );
  }
}