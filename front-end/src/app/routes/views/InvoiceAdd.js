import React, {Component} from 'react';
import {Button, Card, Col, Form, Input, Row, Select} from 'antd';
import {Link} from "react-router-dom";
import {getAll} from "../../../service/CustomersService";
import {getAllActiveByClient} from "../../../service/RewardsService";
import {create} from "../../../service/InvoicesService";

class InvoiceAdd extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            clients: [],
            rewards: null
        };
    }

    handleClientSelect = value => {
        this.setState({rewards: null});
        if (value) {
            getAllActiveByClient(value).then(data => {
                if (data && data.length) {
                    const rewards = [];
                    data.forEach(r => {
                        rewards.push(<Select.Option key={r.id}
                                                    value={r.id}>{r.name + ' (' + r.discount + ')'}</Select.Option>);
                    });
                    this.setState({rewards: rewards});
                }
            });
        }
    };

    onFinish = (values) => {
        create(
            values.client,
            values.reward ? values.reward : '',
            {
                merchant: values.merchant,
                total: values.total
            }
        ).then(response => {
                if (response && response.status === 201) {
                    this.props.history.push('/invoices');
                }
            }
        );
    }

    loadClients() {
        getAll().then(data => {
            if (data) {
                const clients = [];
                data.forEach(c => {
                    clients.push(<Select.Option key={c.id} value={c.id}>{c.name}</Select.Option>);
                });
                this.setState({clients: clients});
            }
        });
    }

    componentDidMount() {
        this.loadClients();
    }

    render() {
        return <div>
            <div className="action-container"></div>
            <Card style={{padding: 30}}>
                <Form onFinish={this.onFinish} onFinishFailed={this.onFinishFailed}
                      layout="vertical">
                    <Row gutter={8}>
                        <Col span={12}>
                            <Form.Item label="Client" name="client"
                                       rules={[{required: true, message: 'This field is required!'}]}>
                                <Select onChange={this.handleClientSelect}>{this.state.clients}</Select>
                            </Form.Item>
                        </Col>
                        <Col span={12}>
                            {
                                this.state.rewards ?
                                    <Form.Item label="Reward" name="reward"
                                               rules={[{required: true, message: 'This field is required!'}]}>
                                        <Select>{this.state.rewards}</Select>
                                    </Form.Item> : null
                            }
                        </Col>
                    </Row>
                    <Row gutter={8}>
                        <Col span={12}>
                            <Form.Item label="Merchant" name="merchant"
                                       rules={[{required: true, message: 'This field is required!'}]}>
                                <Input/>
                            </Form.Item>
                        </Col>
                        <Col span={12}>
                            <Form.Item label="Total" name="total"
                                       rules={[{required: true, message: 'This field is required!'}]}>
                                <Input type={"number"}/>
                            </Form.Item>
                        </Col>
                    </Row>

                    <Form.Item>
                        <div className="to-right">
                            <Button type="primary" htmlType="submit"> Submit </Button>
                            <span> </span>
                            <Link to="/invoices"> <Button type="secondary"> Cancel </Button> </Link>
                        </div>
                    </Form.Item>
                </Form>
            </Card>
        </div>
    }
}

export default InvoiceAdd;