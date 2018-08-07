import React, { Component } from 'react';
import { Card, CardText, CardBody,
  CardTitle, CardSubtitle, Button, FormGroup, Label, Input, Col, Row} from 'reactstrap';
import DocModal from './DocModal'


export default class DocResult extends Component {
    constructor(props) {
        super(props);
        this.state = props.meta;
    }

    render() {
        return (
            <Row>
              <Col xs="2">
                ID: {this.state.id}
              </Col>
              <Col xs="2">
                File: {this.state.fileName}
              </Col>
              <Col xs="2">
                Group: {this.state.groupId}
              </Col>
              <Col xs="2">
                Folder: {this.state.folder}
              </Col>
               <Col xs="2">
                <DocModal meta={this.state}/>
              </Col>
            </Row>
        );
    }
}
