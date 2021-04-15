import {API_BASE_URL} from '../constants/index';
import {request} from "../util/APIUtils";

export function getAll() {
    return request({
        url: API_BASE_URL + '/customers/invoices',
        method: 'GET'
    });
}