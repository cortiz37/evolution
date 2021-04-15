import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import Rewards from "./views/Rewards";

class RewardRoute extends Component {

    render() {
        return <Rewards {...this.props}  />
    }
}

export default withRouter(RewardRoute);
