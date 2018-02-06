import React, { Component } from 'react'
import { Row, Col, Table, Button } from 'antd'
import intl from 'react-intl-universal'

import {BrowserRouter as Router, Route, Link} from 'react-router-dom'

const columns = [{
    title: "产品名称",
    dataIndex: "productName",
    key: "productName",
    render: text => <a href="#">{text}</a>
}, {
    title: "价格",
    dataIndex: "price",
    key: "price"
}]

const tableData = [{
    key: "1",
    productName: "这是T恤",
    price: "￥32.5"
}]

class ProductListing extends Component {
    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div>
                <Row type="flex" justify="end">
                    <Col>
                        <Link to="/product/new">
                            <Button type="primary">{intl.get("NEW_PRODUCT")}</Button>
                        </Link>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Table columns={columns} dataSource={tableData} />
                    </Col>
                </Row>
            </div>
        )
    }
}

export default ProductListing


