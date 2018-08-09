import React, { Component } from 'react';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    Container,
    Row,
    Jumbotron
} from 'reactstrap';

export default class BaseLayout extends Component {
    constructor(props) {
        super(props);
        this.toggle = this.toggle.bind(this);

        this.state = {
            isOpen: false,
            authenticated: false
        };
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
      return (
              <div>
                <ToastContainer/>
                <div className="pageHeading">
                    <h1>SEARCH POC</h1>
                </div>
                <Container>
                    {this.props.body}
                </Container>
            </div>
      );
    }
}
