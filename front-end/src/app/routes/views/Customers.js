import React from 'react';
import {deleteById, getAll} from "../../../service/CustomersService";
import DataTable from "../../../common/DataTable";
import {Popconfirm, Table} from 'antd';
import Moment from 'react-moment';
import {CheckCircleOutlined, CloseCircleOutlined, DeleteOutlined} from "@ant-design/icons";

class Customers extends DataTable {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false
        }
    }

    processRequest = () => {
        return getAll();
    };

    deleteCustomer(id) {
        deleteById(id).then(result => this.load());
    }

    renderView() {
        const columns = [
            {
                title: 'ID',
                dataIndex: 'id',
                key: 'id'
            },
            {
                title: 'Name',
                dataIndex: 'name',
                key: 'name'
            },
            {
                title: 'Address',
                dataIndex: 'address',
                key: 'address'
            },
            {
                title: 'Email',
                dataIndex: 'email',
                key: 'email'
            },
            {
                title: 'Date',
                dataIndex: 'date',
                key: 'date',
                render: date => <Moment date={date} format="DD/MM/YYYY"/>
            },
            {
                title: 'Active',
                dataIndex: 'active',
                key: 'active',
                align: 'center',
                width: '4em',
                render: active => <div>
                    {
                        active ? <CheckCircleOutlined/> : <CloseCircleOutlined/>
                    }
                </div>
            },
            {
                key: 'operations',
                render: (customer) => <div>
                    {
                        <Popconfirm title="Do you want to delete this customer"
                                    onConfirm={() => {
                                        this.deleteCustomer(customer.id)
                                    }}>
                            <a href="#delete"><DeleteOutlined/></a>
                        </Popconfirm>
                    }
                </div>,
                width: '4em',
                align: 'center'
            }
        ];

        return <div>
            <Table
                dataSource={this.state.data}
                columns={columns} bordered loading={this.state.loading}
                size="small" rowKey="id"/>
        </div>
    }
}

export default Customers;