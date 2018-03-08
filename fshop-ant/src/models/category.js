//  import { routerRedux } from 'dva/router';
import { message } from 'antd';
import { addNewCategory } from '../services/category';

export default {
  namespace: 'category',

  // state: {
  //   step: {
  //     payAccount: 'ant-design@alipay.com',
  //     receiverAccount: 'test@example.com',
  //     receiverName: 'Alex',
  //     amount: '500',
  //   },
  // },

  effects: {
    *submitAddCategoryForm({ payload }, { call }) {
      yield call(addNewCategory, payload);
      message.success('提交成功');
    },
  },
};
