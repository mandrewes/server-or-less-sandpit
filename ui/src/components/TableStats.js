import React, { Component } from 'react';
import { Card, CardText, CardBody,
  CardTitle, CardSubtitle, Button, FormGroup, Label, Input, Col, Row} from 'reactstrap';
import Spinner from "./Spinner"
import Config from '../config';
import Api from '../api/Api'

export default class TableStats extends Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: true
        }
    }

    componentDidMount() {
        this.fetchTableStats();
    }

    fetchTableStats() {
        var self = this;
        Api.get('/docs/stats', function(response) {
                self.setState({
                    stats: response.data,
                    loading: false
                });
                window.scrollTo(0, 0)
            });
    }

    render() {
        if ( this.state.loading === true ) {
         return (
            <div>
                <h3>Table Stats</h3>
                <Spinner loading={this.state.loading}/>
            </div>
            );
        }
        var table = this.state.stats.table;
        function round(num) {
            return +(Math.round(num + "e+2")  + "e-2");
        }

        function Indexes() {
            var indexes = table.globalSecondaryIndexes;
            const items = indexes.map((index) =>
            <div>
                <ul>
                    <li><span className="stats">name</span>{index.indexName}</li>
                    <li><span className="stats">provisioned reads</span>{index.provisionedThroughput.readCapacityUnits}</li>
                    <li><span className="stats">provisioned writes</span>{index.provisionedThroughput.writeCapacityUnits}</li>
                    <li><span className="stats">size</span>{round(index.indexSizeBytes/1024/1024/1024)}GB</li>
               </ul>
                <hr/>
            </div>
            );
            return (
                <div>
                  {items}
                </div>
            );
        }
        return (
            <div>
                <h3>Table Stats</h3>
                <ul>
                    <li><span className="stats">tableSize</span>{round(table.tableSizeBytes/1024/1024/1024)}GB</li>
                    <li><span className="stats">itemCount</span>{table.itemCount.toLocaleString()}</li>
                    <li><span className="stats">provisioned reads</span>{table.provisionedThroughput.readCapacityUnits}</li>
                    <li><span className="stats">provisioned writes</span>{table.provisionedThroughput.writeCapacityUnits}</li>
                </ul>
                <h3>Index Stats</h3>
                <Indexes/>
            </div>
        );
    }
}
