import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import Invoices from "./views/Invoices";

class InvoiceRoute extends Component {

    render() {
        return <Invoices {...this.props}  />
    }
}

export default withRouter(InvoiceRoute);
