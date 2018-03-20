import React, { PureComponent } from 'react';
import { connect } from 'dva';
import {
  Form, Input, Button, Card, Tree, Row, Col,
} from 'antd';
import PageHeaderLayout from '../../layouts/PageHeaderLayout';
// import styles from './Category.less';

const FormItem = Form.Item;
const { TreeNode } = Tree;

@Form.create()
@connect(({ category, loading }) => ({
  category,
  loading: loading.models.category,
  submitting: loading.effects['category/submitAddCategoryForm'],
}))
export default class Category extends PureComponent {
  componentDidMount() {
    this.props.dispatch({
      type: 'category/fetch',
    });
  }

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

  updateCategory = (id, e) => {
    e.preventDefault();
    const { value } = e.target;

    this.props.dispatch({
      type: 'category/updateCategory',
      payload: {
        id,
        category: value,
      },
    });
  }

  deleteCategory = (id, e) => {
    e.preventDefault();
    this.props.dispatch({
      type: 'category/deleteCategory',
      payload: {
        id,
      },
    });
  }

  renderTreeNodes = (categories) => {
    return categories.map((category) => {
      return (
        <TreeNode
          title={
            <React.Fragment>
              <Input defaultValue={category.name} style={{ border: 'none', width: 'auto' }} size="small" onBlur={this.updateCategory.bind(this, category.id)} />
              <a onClick={this.deleteCategory.bind(this, category.id)}>删除</a>
            </React.Fragment>}
          key={category.id}
        />
      );
    });
  }

  render() {
    const { submitting } = this.props;
    const { getFieldDecorator } = this.props.form;
    const { category: { categories } } = this.props;

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
        <Row gutter={16}>
          <Col md={12}>
            <Card title="新增类别" bordered={false}>
              <Form
                onSubmit={this.handleSubmit}
                hideRequiredMark
                style={{ marginTop: 8 }}
              >
                <FormItem
                  {...formItemLayout}
                  label="类别名称"
                >
                  {getFieldDecorator('name', {
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
          </Col>
          <Col md={12}>
            <Card title="已有类别" bordered={false}>
              <Tree checkable>
                {this.renderTreeNodes(categories)}
              </Tree>
            </Card>
          </Col>
        </Row>
      </PageHeaderLayout>
    );
  }
}
