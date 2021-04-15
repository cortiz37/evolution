import React from 'react';
import {getAll} from "../../../service/InvoicesService";
import DataTable from "../../../common/DataTable";
import {Button, Table} from 'antd';
import Moment from 'react-moment';
import {BellOutlined, CheckCircleOutlined, PlusCircleOutlined} from "@ant-design/icons";

class Invoices extends DataTable {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false
        }
    }

    processRequest = () => {
        return getAll();
    };

    renderView() {
        const columns = [
            {
                title: 'Date',
                dataIndex: 'date',
                key: 'date',
                render: date => <Moment date={date} format="DD/MM/YYYY"/>
            },
            {
                title: 'Client',
                dataIndex: 'clientId',
                key: 'clientId'
            },
            {
                title: 'Merchant',
                dataIndex: 'merchant',
                key: 'merchant'
            },
            {
                title: 'Total',
                dataIndex: 'total',
                key: 'total'
            },
            {
                title: 'Paid',
                dataIndex: 'totalPaid',
                key: 'totalPaid'
            },
            {
                key: 'check',
                align: 'center',
                width: '4em',
                render: invoice => <div>
                    {
                        (invoice.total === invoice.totalPaid) ? <CheckCircleOutlined/> : <BellOutlined/>
                    }
                </div>
            }
        ];

        return <div>
            <div className="action-container">
                <div>
                <Button key="add"
                        type="primary"
                        onClick={(e) => {
                            alert('ok')
                        }}>
                    <PlusCircleOutlined />
                    Add
                </Button>
                </div>
            </div>
            <Table
                dataSource={this.state.data}
                columns={columns} bordered loading={this.state.loading}
                size="small" rowKey="id"/>
        </div>
    }
}

export default Invoices;