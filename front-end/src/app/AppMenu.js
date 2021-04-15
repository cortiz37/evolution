import React, {Component} from 'react';
import {Menu} from "antd";
import {DollarCircleOutlined, GiftOutlined, HomeOutlined, MailOutlined, TeamOutlined} from "@ant-design/icons";
import {Link} from "react-router-dom";

class AppMenu extends Component {

    render() {
        return <div style={{marginBottom: '10px'}}>
            <Menu mode="horizontal" selectedKeys={[this.props.location.pathname]}>
                <Menu.Item key="/" icon={<HomeOutlined/>}>
                    <Link to="/">
                        Home
                    </Link>
                </Menu.Item>
                <Menu.Item key="/customers" icon={<TeamOutlined/>}>
                    <Link to="/customers">
                        Customers
                    </Link>
                </Menu.Item>
                <Menu.Item key="/invoices" icon={<DollarCircleOutlined/>}>
                    <Link to="/invoices">
                        Invoices
                    </Link>
                </Menu.Item>
                <Menu.Item key="/rewards" icon={<GiftOutlined/>}>
                    <Link to="/rewards">
                        Rewards
                    </Link>
                </Menu.Item>
                <Menu.Item key="/notifications" icon={<MailOutlined/>}>
                    <Link to="/notifications">
                        Notifications
                    </Link>
                </Menu.Item>
            </Menu>
        </div>
    }
}

export default AppMenu;