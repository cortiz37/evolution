import React, {Component} from 'react';
import NotFound from '../app/NotFound';
import {Spin} from 'antd';

class DataTable extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            total: 0
        };
    }

    load() {
        this.setState({
            isLoading: true
        });

        this.processRequest().then(response => {
            if (response) {
                this.setState({
                    data: response,
                    isLoading: false
                });
            }
        });
    }

    componentDidMount() {
        this.setState({
            isLoading: true
        });
        this.load();
    }

    render() {
        if (this.state.isLoading) {
            return <Spin size="large"/>;
        }

        if (this.state.notFound) {
            return <NotFound/>;
        }

        return <div>
            {
                this.state.data ? (this.renderView()) : <p>No results...</p>
            }
        </div>
    }
}

export default DataTable;