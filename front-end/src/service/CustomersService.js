import {API_BASE_URL} from '../constants/index';
import {request} from "../util/APIUtils";

export function getAll() {
    return request({
        url: API_BASE_URL + '/customers',
        method: 'GET'
    });
}

export function deleteById(id) {
    return request({
        url: API_BASE_URL + '/customers/' + id,
        method: 'DELETE'
    });
}