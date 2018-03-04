import React, { Component } from 'react'
import { Row, Col, Table, Button } from 'antd'
import intl from 'react-intl-universal'

import { BrowserRouter as Router, Route, Link } from 'react-router-dom'

import { Transition, TransitionGroup, CSSTransition } from "react-transition-group"

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

// const transitionStyles = {
//     entering: { transform: `translateY(0)`, opacity: 1},
//     entered: { transform: `translateY(-100%)`, opacity: 0}
// }

class ProductListing extends Component {
    constructor(props) {
        super(props)
        this.state = { in: false }
        this.toggleEnterState = this.toggleEnterState.bind(this)
    }

    toggleEnterState() {
        this.setState({ in: !this.state.in })
    }

    render() {
        return (
            <div>
                {/* <Transition in={this.state.in} timeout={300}>
                            {(state) => (
                                <div style={{transition: `transform 300ms ease-in-out, opacity 300ms ease-in-out`, ...transitionStyles[state]}}> */}
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
                {/* </div>
                )}
                </Transition> */}
            </div>
        )
    }
}

export default ProductListing


