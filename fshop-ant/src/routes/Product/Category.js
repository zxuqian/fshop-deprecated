import React, { PureComponent } from 'react';
import { connect } from 'dva';
import {
  Form, Input, Button, Card,
} from 'antd';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';
//  import from './Category.less';

const FormItem = Form.Item;

@connect(({ loading }) => ({
  submitting: loading.effects['category/submitAddCategoryForm'],
}))
@Form.create()
export default class Category extends PureComponent {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        this.props.dispatch({
          type: 'category/submitAddCategoryForm',
          payload: values,
        });
      }
    });
  }
  render() {
    const { submitting } = this.props;
    const { getFieldDecorator } = this.props.form;

    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 7 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 12 },
        md: { span: 10 },
      },
    };

    const submitFormLayout = {
      wrapperCol: {
        xs: { span: 24, offset: 0 },
        sm: { span: 10, offset: 7 },
      },
    };

    return (
      <PageHeaderLayout title="类别管理" content="管理平台所有产品类别">
        <Card bordered={false}>
          <Form
            onSubmit={this.handleSubmit}
            hideRequiredMark
            style={{ marginTop: 8 }}
          >
            <FormItem
              {...formItemLayout}
              label="类别名称"
            >
              {getFieldDecorator('title', {
                rules: [{
                  required: true, message: '请输入类别名称',
                }],
              })(
                <Input placeholder="类别名称" />
              )}
            </FormItem>
            <FormItem {...submitFormLayout} style={{ marginTop: 32 }}>
              <Button type="primary" htmlType="submit" loading={submitting}>
                提交
              </Button>
            </FormItem>
          </Form>
        </Card>
      </PageHeaderLayout>
    );
  }
}
