import React, { Component } from 'react';
import Config from '../config';

import { Card, CardText, CardBody,
  CardTitle, CardSubtitle, Button, FormGroup, Label, Input, Col, Row} from 'reactstrap';


export default class IntroductionPanel extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <Row>
                <Col xs="12">
                <p>
                This POC seeks to show that across a significant collection of data,
                 queries that combine both indexed and non indexed criteria can perform with sub second response times. While using dynamodb at low cost
                 </p>
                <br/>
                <p><font color="red">If you get 403 errors! </font> please visit these URLs and click continue in zScaler. (it happens quite often)
                <ul>
                    <li><a href={Config.api + "/docs/search"}>{Config.api + "/docs/search"}</a></li>
                    <li><a href={Config.api + "/docs/stats"}>{Config.api + "/docs/stats"}</a></li>
                </ul>
                </p>
                </Col>
            </Row>
        );
    }
}
