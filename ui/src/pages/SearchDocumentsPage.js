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

    getInformationRequest() {
        var self = this;
        Api.get('/v1/informationrequest',function(response) {
            self.setState({
                informationRequest: response.data,
                questions: response.data.questions,
                loading: false
            });

            Logger.debug("Information Request" + JSON.stringify(response.data,0,4));
        });
    }


    handleQuestionChange = (onOff,question) => {
        var eq = this.state.answeredQuestions;
        eq = eq.filter(q => q.code != question.code);

        if ( onOff === true ) {
            eq.push(question);
            Logger.debug("id " + question.code  + " is " + JSON.stringify(question))
        } else {
            Logger.debug("id " + question.code + " removed");
        }
        this.setState({
           answeredQuestions: eq
        });
    }


    render() {
        var self = this;

        function DoneButton() {
            if ( self.state.answeredQuestions.length > 0 ) {
                return (<Button color="primary" onClick={self.onDoneButtonClick} >Done</Button>);
            }
            return (<Button disabled outline color="primary" onClick={self.onDoneButtonClick} >Done</Button>);
        }

      return (
        <BaseLayout
            title="Collect Information"
            body={
                <Col>
                     <p> Collect Information </p>
                     <Spinner loading={this.state.loading}/>
                     <AnswerQuestionCards questions={this.state.questions} cb={this.handleQuestionChange}/>
                     <DoneButton/>
                </Col>
            }
        />
      );
    }
}


