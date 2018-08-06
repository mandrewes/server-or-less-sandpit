import React, { Component } from 'react';
import Config from '../config';
import Api from '../api/Api'
import Spinner from '../components/Spinner'

import {
    NavItem,
    NavLink,
    Col,
    Button
} from 'reactstrap';

import { Link } from 'react-router-dom'
import BaseLayout from '../layouts/Layout';

export default class SearchDocumentsPage extends Component {
   constructor(props) {
        super(props);
        this.state = {
            loading: true,
            search: null,
            results: []
        };
    }

    searchDocuments() {
        var self = this;
        var searchParams = {
            groupId :"NY1307090142SSCA1"
        };
        self.setState({
            loading: false,
            results: []
        });

        Api.post('/docs/search',searchParams, function(response) {
            self.setState({
                results: response.data,
                loading: false
            });
        });
    }

    render() {
        var self = this;
      return (
        <BaseLayout
            title="Search"
            body={
                <Col>
                     <p>Search</p>

                </Col>
                <Col>
                     <p>Results</p>
                     <Spinner loading={this.state.loading}/>
                     <AnswerQuestionCards questions={this.state.questions} cb={this.handleQuestionChange}/>
                     <DoneButton/>
                </Col>
            }
        />
      );
    }
}


