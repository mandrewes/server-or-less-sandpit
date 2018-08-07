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

export default class AddDocumentPage extends Component {
   constructor(props) {
        super(props);
        this.state = {
            loading: true
        };
    }


    render() {
        var self = this;
      return (
        <BaseLayout
            title="Add Document"
            body={
                <Col>
                    <p>Add Document</p>
                </Col>
            }
        />
      );
    }
}


