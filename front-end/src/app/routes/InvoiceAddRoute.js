import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import InvoiceAdd from "./views/InvoiceAdd";

class InvoiceAddRoute extends Component {

    render() {
        return <InvoiceAdd {...this.props}  />
    }
}

export default withRouter(InvoiceAddRoute);
