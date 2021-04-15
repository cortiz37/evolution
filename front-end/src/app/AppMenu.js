import React, {Component} from 'react';
import {Menu} from "antd";
import {GifOutlined, GroupOutlined, HomeOutlined, MoneyCollectOutlined, NotificationOutlined} from "@ant-design/icons";
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
                <Menu.Item key="/customers" icon={<GroupOutlined/>}>
                    <Link to="/customers">
                        Customers
                    </Link>
                </Menu.Item>
                <Menu.Item key="/invoices" icon={<MoneyCollectOutlined/>}>
                    <Link to="/invoices">
                        Invoices
                    </Link>
                </Menu.Item>
                <Menu.Item key="/rewards" icon={<GifOutlined/>}>
                    <Link to="/rewards">
                        Rewards
                    </Link>
                </Menu.Item>
                <Menu.Item key="/notifications" icon={<NotificationOutlined/>}>
                    <Link to="/notifications">
                        Notifications
                    </Link>
                </Menu.Item>
            </Menu>
        </div>
    }
}

export default AppMenu;