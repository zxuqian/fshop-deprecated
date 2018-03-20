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
    *submitAddCategoryForm({ payload }, { call, put }) {
      const hide = message.loading('添加中...');
      const response = yield call(categoryService.addNewCategory, payload);
      if (response.success) {
        yield put({
          type: 'add',
          payload: response.obj,
        });
        hide();
        message.success('提交成功');
      }
    },
    *updateCategory({ payload }, { call }) {
      const hide = message.loading('修改中...');
      const response = yield call(categoryService.updateCategory, payload);
      if (response.success) {
        hide();
        message.success('修改成功');
      }
    },
    *deleteCategory({ payload }, { call, put }) {
      const hide = message.loading('删除中...');
      const response = yield call(categoryService.deleteCategory, payload);
      if (response.success) {
        hide();
        message.success('删除成功');
        yield put({
          type: 'delete',
          payload: payload.id,
        });
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
    add(state, action) {
      if (action.payload) {
        state.categories.push(action.payload);
      }
      return {
        ...state,
      };
    },
    delete(state, action) {
      return {
        ...state,
        categories: state.categories.filter(value => value.id !== action.payload),
      };
    },
  },
};
