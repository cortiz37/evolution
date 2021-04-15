import React from 'react';
import {deleteById, getAll} from "../../../service/RewardsService";
import DataTable from "../../../common/DataTable";
import {Popconfirm, Table} from 'antd';
import Moment from 'react-moment';
import {CheckCircleOutlined, CloseCircleOutlined, DeleteOutlined} from "@ant-design/icons";

class Rewards extends DataTable {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false
        }
    }

    processRequest = () => {
        return getAll();
    };

    deleteReward(id) {
        deleteById(id).then(result => this.load());
    }

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
                title: 'Name',
                dataIndex: 'name',
                key: 'name'
            },
            {
                title: 'Discount',
                dataIndex: 'discount',
                key: 'discount'
            },
            {
                title: 'Available',
                dataIndex: 'active',
                key: 'active',
                align: 'center',
                width: '6em',
                render: active => <div>
                    {
                        active ? <CheckCircleOutlined/> : <CloseCircleOutlined/>
                    }
                </div>
            },
            {
                key: 'operations',
                render: (reward) => <div>
                    {
                        <Popconfirm title="Do you want to delete this reward"
                                    onConfirm={() => {
                                        this.deleteReward(reward.id)
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

export default Rewards;