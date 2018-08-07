import React, { Component } from 'react';
import { Card, CardText, CardBody,
  CardTitle, CardSubtitle, Button, FormGroup, Label, Input, Col, Row} from 'reactstrap';


export default class SearchStats extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        if (this.props.resultsInfo == null) {
            return null;
        }
        var stats = this.props.resultsInfo;

        return (
            <Row>
                <Col xs="12">
                <ul>
                    <li><span className="stats">timeTaken</span>{stats.timeTaken}ms</li>
                    <li><span className="stats">resultsReturned</span>{stats.resultsReturned} rows</li>
                    <li><span className="stats">rowsProcessed</span>{stats.rowsProcessed} rows</li>
                    <li><span className="stats">limitedAt</span>{stats.limitedAt} rows</li>
                    <li><span className="stats">indexUsed</span>{stats.indexUsed}</li>
                </ul>
              </Col>
            </Row>
        );
    }
}
