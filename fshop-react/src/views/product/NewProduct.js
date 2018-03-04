import React, { Component } from 'react'
import { Row, Col, Table, Button, Form, Input } from 'antd'
import intl from 'react-intl-universal'

class NewProduct extends Component {
    constructor(props) {
        super(props)
    }

    render() {
        const formItemLayout = {
            labelCol: {
                sm: { span: 8 }
            },
            wrapperCol: {
                sm: { span : 16 }
            }
        }
        return (
            <div>
                <h1>Add a Product</h1>
                <Form style={{maxWidth: `700px`}}>
                    <Form.Item {...formItemLayout} label="Product Name">
                        <Input />
                    </Form.Item>
                    <Form.Item {...formItemLayout} label="Product Price">
                        <Input />
                    </Form.Item>
                </Form>
            </div>
        )
    }
}

export default NewProduct


