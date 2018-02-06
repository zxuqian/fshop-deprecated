import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios'
import intl from 'react-intl-universal'

import {BrowserRouter as Router, Route, Link} from 'react-router-dom'

import { Layout, Menu, Icon, Row, Col, Table, Divider, Button } from 'antd';

import ProductListing from './views/product/ProductListing.js'
import NewProduct from './views/product/NewProduct';

const { Header, Content, Footer, Sider } = Layout

// Load locales
const locales = {
    "en-US": require("./locales/en-US.json"),
    "zh-CN": require("./locales/zh-CN.json")
}

class App extends Component {
    constructor(props) {
        super(props)
        this.state = {
            hello: "",
            initDone: false
        }
    }

    async componentDidMount() {
        //axios.get("http://localhost:8080").then(res => {this.setState({hello: res.data}) })
        await intl.init({
            currentLocale: 'zh-CN',
            locales
        })
        this.setState({initDone: true})
    }

    render() {
        return (this.state.initDone &&
            <Router>
            <Layout>
                <Sider className="Sidebar" breakpoint="lg"
                    collapsedWidth="0"
                    onCollapse={(collapsed, type) => { console.log(collapsed, type); }}>

                    <div className="logo">
                        <h1>F Shop</h1>
                    </div>
                    <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
                        <Menu.Item key="1">
                            <Link to="/">
                                <Icon type="user" />
                                <span className="nav-text">{intl.get('PRODUCT')}</span>
                            </Link>
                        </Menu.Item>
                        <Menu.Item key="2">
                            <Icon type="video-camera" />
                            <span className="nav-text">库存</span>
                        </Menu.Item>
                        <Menu.Item key="3">
                            <Icon type="upload" />
                            <span className="nav-text">订单</span>
                        </Menu.Item>
                        <Menu.Item key="4">
                            <Icon type="bar-chart" />
                            <span className="nav-text">优惠券</span>
                        </Menu.Item>
                        <Menu.Item key="5">
                            <Icon type="cloud-o" />
                            <span className="nav-text">用户</span>
                        </Menu.Item>
                        <Menu.Item key="6">
                            <Icon type="appstore-o" />
                            <span className="nav-text">分销</span>
                        </Menu.Item>
                        <Menu.Item key="7">
                            <Icon type="team" />
                            <span className="nav-text">代理</span>
                        </Menu.Item>
                        <Menu.Item key="8">
                            <Icon type="shop" />
                            <span className="nav-text">系统</span>
                        </Menu.Item>
                    </Menu>
                </Sider>
                <Layout>
                    <Header style={{ background: '#fff', padding: 0 }} />
                    <Content style={{ margin: '24px 16px 0', overflow: 'initial' }}>
                        <div style={{ padding: 24, background: '#fff', textAlign: 'center' }}>
                            <Route exact path="/" component = {ProductListing} />
                            <Route path="/product/new" component = {NewProduct} />
                        </div>
                    </Content>
                    <Footer style={{ textAlign: 'center' }}>
                        Ant Design ©2016 Created by Ant UED
                    </Footer>
                </Layout>
            </Layout>
            </Router>
        );
    }
}

export default App;
