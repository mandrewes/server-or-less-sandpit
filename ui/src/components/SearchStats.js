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
                <Col xs="1">
                     <PopOver
                                                 title="Search Results"
                                                 content={
                                                 <div>
                                                 <p>These are the results of the search, statistics here mean. </p>
                                                 <ul>
                                                 <li><b>timeTaken</b> - how long the server took to search the database</li>
                                                 <li><b>resultsReturned</b> - how many results the server returned to the UI</li>
                                                 <li><b>rowsProcessed</b> - how many database records the server loaded from DynamoDB at to generate the results</li>
                                                 <li><b>limitedAt</b> - the limit at which the server decided to stop searching and send data to the client</li>
                                                 <li><b>indexUsed</b> - the index used for the search</li>
                                                 </ul>
                                                 </div>
                                                 }
                                                 />
                </Col>
                <Col xs="11">
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
