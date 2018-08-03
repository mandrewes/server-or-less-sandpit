import React, { Component } from 'react';


export default class DocumentDescriptor extends Component {
    render() {
      return (
        <p>Id: {this.props.id}, created: {this.props.created}<br/></p>
      );
    }
}
