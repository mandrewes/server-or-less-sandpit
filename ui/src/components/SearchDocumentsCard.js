import React, { Component } from 'react';
import { Card, CardText, CardBody,
  CardTitle, CardSubtitle, Button, FormGroup, Label, Input} from 'reactstrap';



export default class SearchDocumentsCard extends Component {
    constructor(props) {
        super(props);
        this.callBack = props.cb;

        this.state = {
            buttonState: false,
            buttonLabel: "Select",
            buttonOutline: "outline"
        };
        this.onButtonClick = this.onButtonClick.bind(this);
    }

    onCustomMessageClick() {
        Logger.info("state onCustomMessageClick")
    }

    onButtonClick() {
        var prevState = this.state.buttonState;
        var newState = !prevState;
        this.setState({
            buttonState: newState
        });
        this.onButtonStateChange(newState);
    }

    onButtonStateChange(st) {
        Logger.info("state onButtonStateChange " + st)
        // the below code will only allow 1 "other" question - use ID's if more are needed
        this.callBack(st,this.props.question);
    }

    render() {
        var question = this.props.question;
        var self = this;

        function OnOffButton() {
            if ( self.state.buttonState === true ) {
                return (<Button outline color="primary" onClick={self.onButtonClick} >Selected</Button>);
            }
            return (<Button outline color="secondary" onClick={self.onButtonClick} >&nbsp;&nbsp;Select&nbsp;&nbsp;</Button>);
        }

        return (
            <div>
              <Card>
                <CardBody>
                  <CardTitle><Bundle k={question.code}/></CardTitle>
                  <CardSubtitle><Bundle k={question.code + "_DESC"}/></CardSubtitle>
                  <CardText><Bundle k={question.responseType}/></CardText>
                  <OnOffButton/>
                </CardBody>
              </Card>
            </div>
        );
    }
}
