//  import { routerRedux } from 'dva/router';
import { message } from 'antd';
import * as categoryService from '../services/category';

export default {
  namespace: 'category',

  state: {
    categories: [],
  },

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
      yield call(categoryService.addNewCategory, payload);
      message.success('提交成功');
    },
    *updateCategory({ payload }, { call }) {
      const hide = message.loading('修改中...');
      const response = yield call(categoryService.updateCategory, payload);
      if (response.success) {
        hide();
        message.success('修改成功');
      }
    },
    *fetch(_, { call, put }) {
      const response = yield call(categoryService.getAllCategories);
      yield put({
        type: 'save',
        payload: Array.isArray(response) ? response : [],
      });
    },
  },

  reducers: {
    save(state, action) {
      return {
        ...state,
        categories: action.payload,
      };
    },
  },
};
