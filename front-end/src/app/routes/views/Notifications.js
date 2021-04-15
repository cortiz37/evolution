import React from 'react';
import {deleteById, getAll} from "../../../service/NotificationsService";
import DataTable from "../../../common/DataTable";
import {Popconfirm, Table} from 'antd';
import Moment from 'react-moment';
import {DeleteOutlined, MailOutlined, WarningOutlined} from "@ant-design/icons";

class Notifications extends DataTable {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false
        }
    }

    processRequest = () => {
        return getAll();
    };

    deleteNotification(id) {
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
                title: 'To',
                dataIndex: 'to',
                key: 'to'
            },
            {
                title: 'Subject',
                dataIndex: 'subject',
                key: 'subject'
            },
            {
                title: 'Body',
                dataIndex: 'body',
                key: 'body'
            },
            {
                title: 'Sent',
                dataIndex: 'sent',
                key: 'sent',
                align: 'center',
                width: '4em',
                render: sent => <div>
                    {
                        sent ? <MailOutlined/> : <WarningOutlined/>
                    }
                </div>
            },
            {
                key: 'operations',
                render: (notification) => <div>
                    {
                        <Popconfirm title="Do you want to delete this notification"
                                    onConfirm={() => {
                                        this.deleteNotification(notification.id)
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

export default Notifications;