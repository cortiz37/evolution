import React, {Component} from 'react';
import {Card} from "antd";

class MainRoute extends Component {

    render() {
        return <div>
            <Card size="big" title="Home - Customer App" style={{ width: '100%' }}>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.</p>
                <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                <p> Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
            </Card>
        </div>
    }
}

export default MainRoute;