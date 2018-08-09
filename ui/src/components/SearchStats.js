import React, { Component } from 'react';
import { Card, CardText, CardBody,
  CardTitle, CardSubtitle, Button, FormGroup, Label, Input, Col, Row} from 'reactstrap';
import PopOver from './PopOver'

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
                    <span className="statName">timeTaken</span><span className="statValue">{stats.timeTaken}ms</span>
                    <span className="statName">resultsReturned</span><span className="statValue">{stats.resultsReturned} rows</span>
                    <span className="statName">rowsProcessed</span><span className="statValue">{stats.rowsProcessed} rows</span>
                    <span className="statName">limitedAt</span><span className="statValue">{stats.limitedAt} rows</span>
                    <span className="statName">indexUsed</span><span className="statValue">{stats.indexUsed}</span>
              </Col>

            </Row>
        );
    }
}
