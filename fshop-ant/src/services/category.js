//  import { stringify } from 'qs';
import request from '../utils/request';

export async function addNewCategory(params) {
  return request('https://localhost:8443/api/category', {
    method: 'POST',
    body: params,
  });
}

