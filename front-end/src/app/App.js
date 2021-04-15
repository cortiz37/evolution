import React, {Component} from 'react';
import '../css/App.css';
import 'antd/dist/antd.css'
import {Route, Switch, withRouter} from 'react-router-dom';
import NotFound from './NotFound';
import {Layout, Spin} from 'antd';
import MainRoute from "./routes/MainRoute";
import CustomerRoute from "./routes/CustomerRoute";
import InvoiceRoute from "./routes/InvoiceRoute";
import RewardRoute from "./routes/RewardRoute";
import NotificationRoute from "./routes/NotificationRoute";
import AppHeader from "./AppHeader";
import AppMenu from "./AppMenu";
import InvoiceAddRoute from "./routes/InvoiceAddRoute";

const {Content} = Layout;

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoading: false
        };
        window.app = this;
    }

    render() {
        if (this.state.isLoading) {
            return <div className="main-spin-container"><Spin size="large"/></div>
        }
        return (
            <Layout className="app-container">
                <AppHeader/>
                <Content className="app-content">
                    <div className="container container-main">
                        <AppMenu {...this.props} />
                        <Switch>
                            <Route exact path="/" render={(props) => <MainRoute {...this.state} />}/>
                            <Route path="/customers" render={(props) => <CustomerRoute {...this.state} />}/>
                            <Route path="/invoices" render={(props) => <InvoiceRoute {...this.state} />}/>
                            <Route path="/add/invoice" render={(props) => <InvoiceAddRoute {...this.state} />}/>
                            <Route path="/rewards" render={(props) => <RewardRoute {...this.state} />}/>
                            <Route path="/notifications" render={(props) => <NotificationRoute {...this.state} />}/>
                            <Route component={NotFound}></Route>
                        </Switch>
                    </div>
                </Content>
            </Layout>
        );
    }
}

export default withRouter(App);
