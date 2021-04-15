import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import Customers from "./views/Customers";

class CustomerRoute extends Component {

    render() {
        return <Customers {...this.props}  />
    }
}

export default withRouter(CustomerRoute);
