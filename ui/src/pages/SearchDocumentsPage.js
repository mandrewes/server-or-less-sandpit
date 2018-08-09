import React, { Component } from 'react';
import Config from '../config';
import Api from '../api/Api'
import Spinner from '../components/Spinner'
import DocResult from '../components/DocResult'
import SearchStats from '../components/SearchStats'
import TableStats from '../components/TableStats'
import IntroductionPanel from '../components/IntroductionPanel'
import PopOver from '../components/PopOver'
import Examples from '../components/Examples'

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
            groupId: "",
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
        this.acceptExampleModel = this.acceptExampleModel.bind(this);

    }

    componentDidMount() {
        this.onSearchButtonClick();
    }

    acceptExampleModel(m){
        this.setState(m, this.onSearchButtonClick);
    }

    updateInputValue(evt){
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
                <Col xs="12">
                  {items}
                </Col>
            );
        }

      return (
        <BaseLayout
            title="Search"
            body={
            <div>
            <IntroductionPanel/>
            <Examples cb={this.acceptExampleModel}/>
            <Row>
                <Col xs="3" className={["panel","search"]}>
                      <h2 className="h3">Indexed Fields</h2>
                      <PopOver
                        title="Indexed Fields"
                        content={
                        "These fields are common across all documents and searches. At least one of these needs to be used to search a document (quickly)"
                        }
                       />
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
                                <h3 class="h3">Non Indexed Fields</h3>
                            </Col>
                        <PopOver
                                 title="Non Indexed Fields"
                                 content={
                                 <p>These fields are dynamic and can store any data the frontend needs
                                 <br/><br/>
                                 These field scan also be searched quickly, in combination with at least one indexed field
                                 </p>
                                 }
                        />
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
                    <hr/>
                    <TableStats/>
                </Col>
                <Col xs="8" className={["panel","results"]}>
                    <Row className="resultsHdr">
                        <Col xs="4">
                             <h2 class="h3">Results</h2>
                        </Col>
                        <Col xs="8" className="text-right">
                            <Spinner loading={this.state.loading}/>
                            <SearchStats resultsInfo={this.state.resultsInfo}/>
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
                    </Row>

                     <ResultsList results={this.state.results}/>
                </Col>
                </Row>
                </div>
            }
        />
      );
    }
}


