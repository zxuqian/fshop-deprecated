//  import { stringify } from 'qs';
import request from '../utils/request';
import config from '../common/config';

export async function addNewCategory(params) {
  return request(`${config.remote}/api/category`, {
    method: 'POST',
    body: params,
  });
}

export async function getAllCategories() {
  return request(`${config.remote}/api/category`, {
    method: 'GET',
  });
}

export async function updateCategory(params) {
  return request(`${config.remote}/api/category/${params.id}`, {
    method: 'PUT',
    body: {
      name: params.category,
    },
  });
}
