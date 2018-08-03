import React, { Component } from 'react';
var FontAwesome = require('react-fontawesome')

export default class Spinner extends Component {
    render() {
      var msg = "Loading | ";

      if ( this.props.message ) {
        msg = this.props.message + " | ";
      }

      if ( this.props.loading === true ) {
        return (
            <p>{msg}
            <FontAwesome
                    name="refresh"
                    size="2x"
                    spin
                  />
            </p>
        );
      } else {
        return null;
      }
    }
}
