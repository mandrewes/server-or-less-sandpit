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
            <Row className="resultRow">
                <Col xs="3">
                {this.state.folder}
                </Col>
                <Col xs="2">
                {this.state.groupId}
                </Col>
                <Col xs="2">
                {this.state.accountId}
                </Col>
                <Col xs="3">
                {this.state.fileName}
                </Col>
                <Col xs="2" className="text-right">
                 <DocModal meta={this.state}/>
                </Col>
            </Row>
        );
    }
}
