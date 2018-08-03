import React, { Component } from 'react';
import BaseLayout from '../layouts/Layout';

import {
    Col
} from 'reactstrap';

export default class NotFoundPage extends Component {
    render() {
      return (
        <BaseLayout
            body={
                <Col>
                    <h1>Page Not Found</h1>
                </Col>
            }
        />
      );
    }
}
