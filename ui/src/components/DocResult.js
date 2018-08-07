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
                <Col xs="10">
                <ul>
                    <li><span className="fieldName">Folder</span>{this.state.folder}</li>
                    <li><span className="fieldName">Group</span>{this.state.groupId}</li>
                    <li><span className="fieldName">Account</span>{this.state.accountId}</li>
                    <li><span className="fieldName">File</span>{this.state.fileName}</li>
                </ul>
              </Col>
              <Col xs="2">
                <DocModal meta={this.state}/>
              </Col>
            </Row>
        );
    }
}
