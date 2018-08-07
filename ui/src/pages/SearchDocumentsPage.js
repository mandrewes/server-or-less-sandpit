import React, { Component } from 'react';
import Config from '../config';
import Api from '../api/Api'
import Spinner from '../components/Spinner'
import DocResult from '../components/DocResult'

import {
    NavItem,
    NavLink,
    Col,
    Row,
    Button,
    Form,
    FormGroup,
    Label,
    Input
} from 'reactstrap';

import { Link } from 'react-router-dom'
import BaseLayout from '../layouts/Layout';

export default class SearchDocumentsPage extends Component {
   constructor(props) {
        super(props);
        this.state = {
            loading: false,
            search: null,
            groupId: "NY1307090142SSCA1",
            folder: "",
            customerId: "",
            accountId: "TX1210150419AJWA100-36-56",
            freeFormNameA: "",
            freeFormValueA: "",
            freeFormNameB: "",
            freeFormValueB: "",
            results: [],
            resultsInfo: null
        };

        this.onSearchButtonClick = this.onSearchButtonClick.bind(this);
        this.updateInputValue = this.updateInputValue.bind(this);
        this.updateInputValue = this.updateInputValue.bind(this);

    }

    componentDidMount() {
        //this.onSearchButtonClick();
    }

    updateInputValue(evt){
        console.log("input field updated with "+evt.target.value);
        this.setState({
             [evt.target.name]: evt.target.value
           });
    }

    onSubmit(evt){
        evt.preventDefault();
    }

    addCriteria(o,name) {
        var v = this.state[name];
        if ( v && v.length > 0) {
            o[name] = v;
        }
    }

    buildSearchCriteria() {
        var ret = {
        };

        this.addCriteria(ret,"groupId");
        this.addCriteria(ret,"customerId");
        this.addCriteria(ret,"accountId");
        this.addCriteria(ret,"folder");

        ret.freeForm = {};
        var n = this.state.freeFormNameA;
        var v = this.state.freeFormValueA;
        if ( n && n.length > 0) {
            ret.freeForm[n] = v;
        }

        n = this.state.freeFormNameB;
        v = this.state.freeFormValueB;
        if ( n && n.length > 0) {
            ret.freeForm[n] = v;
        }

        console.log("query " + JSON.stringify(ret,0,4))
        return ret;
    }

    onSearchButtonClick() {
        var self = this;
        var searchParams = this.buildSearchCriteria();

        self.setState({
            loading: true,
            resultsInfo: null,
            results: []
        });

        Api.post('/docs/search',searchParams, function(response) {
            self.setState({
                results: response.data.results,
                resultsInfo: response.data,
                loading: false
            });
            window.scrollTo(0, 0)
        });
    }

    render() {
      var self = this;
      function ResultsList(props) {
            var results = props.results;
            const items = results.map((item) =>
                <DocResult meta={item}/>
            );
            return (
                <Row>
                  {items}
                </Row>
            );
        }
        function ResultsHeader(props) {
            var resultsInfo = props.resultsInfo;
            if ( ! resultsInfo ) {
                return null;
            }

            return (
                <Row>
                  <Col xs="12">
                    timeTaken: {resultsInfo.timeTaken}ms <br/>
                    resultsReturned: {resultsInfo.resultsReturned} rows <br/>
                    limitedAt: {resultsInfo.limitedAt} rows <br/>
                    indexUsed : {resultsInfo.indexUsed}.

                  </Col>
                </Row>
            );
        }
      return (
        <BaseLayout
            title="Search"
            body={
            <Row>
                <Col xs="3" className={["panel","search"]}>
                      <h2 class="h3">Search</h2>
                      <Form onSubmit={this.handleSubmit}>
                        <FormGroup row>
                          <Label for="accountId" sm={3}>Account ID</Label>
                          <Col sm={8}>
                            <Input name="accountId" id="accountId" bsSize="sm" value={this.state.accountId} onChange={this.updateInputValue}/>
                          </Col>
                        </FormGroup>
                        <FormGroup row>
                          <Label for="groupId" sm={3}>Group ID</Label>
                          <Col sm={8}>
                            <Input name="groupId" id="groupId" bsSize="sm" value={this.state.groupId} onChange={this.updateInputValue}/>
                          </Col>
                        </FormGroup>
                        <FormGroup row>
                          <Label for="customerId" sm={3}>Customer ID</Label>
                          <Col sm={8}>
                            <Input name="customerId" id="customerId" bsSize="sm" value={this.state.customerId} onChange={this.updateInputValue}/>
                          </Col>
                        </FormGroup>
                        <FormGroup row>
                          <Label for="folder" sm={3}>Folder</Label>
                          <Col sm={8}>
                            <Input name="folder" id="folder" bsSize="sm" value={this.state.folder} onChange={this.updateInputValue}/>
                          </Col>
                        </FormGroup>
                        <hr/>
                        <Row>
                            <Col>
                                <h3 class="h3">Free Form Fields</h3>
                                <p>Free form fields. There can be as many as needed</p>
                            </Col>
                        </Row>
                        <FormGroup row>
                          <Label for="freeFormName" sm={3}>Name</Label>
                          <Col sm={8}>
                            <Input name="freeFormNameA" id="freeFormNameA" bsSize="sm" value={this.state.freeFormNameA} onChange={this.updateInputValue}/>
                          </Col>
                        </FormGroup>
                        <FormGroup row>
                          <Label for="freeFormValue" sm={3}>Value</Label>
                          <Col sm={8}>
                            <Input name="freeFormValueA" id="freeFormValueA" bsSize="sm" value={this.state.freeFormValueA} onChange={this.updateInputValue}/>
                          </Col>
                        </FormGroup>
                        <FormGroup row>
                          <Label for="freeFormNameB" sm={3}>Name</Label>
                          <Col sm={8}>
                            <Input name="freeFormNameB" id="freeFormNameB" bsSize="sm" value={this.state.freeFormNameB} onChange={this.updateInputValue}/>
                          </Col>
                        </FormGroup>
                        <FormGroup row>
                          <Label for="freeFormValueB" sm={3}>Value</Label>
                          <Col sm={8}>
                            <Input name="freeFormValueB" id="freeFormValueB" bsSize="sm" value={this.state.freeFormValueB} onChange={this.updateInputValue}/>
                          </Col>
                        </FormGroup>
                      </Form>
                    <Button color="primary" onClick={self.onSearchButtonClick} >Search</Button>
                </Col>
                <Col xs="8" className={["panel","results"]}>
                     <h2 class="h3">Results</h2>
                     <Spinner loading={this.state.loading}/>
                     <ResultsHeader resultsInfo={this.state.resultsInfo}/>
                     <ResultsList results={this.state.results}/>
                </Col>
                </Row>
            }
        />
      );
    }
}


