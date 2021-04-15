import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import Notifications from "./views/Notifications";

class NotificationRoute extends Component {

    render() {
        return <Notifications {...this.props}  />
    }
}

export default withRouter(NotificationRoute);
