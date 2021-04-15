import {API_BASE_URL} from '../constants/index';
import {request} from "../util/APIUtils";

export function getAll() {
    return request({
        url: API_BASE_URL + '/customers/rewards',
        method: 'GET'
    });
}

export function getAllActiveByClient(id) {
    return request({
        url: API_BASE_URL + '/customers/' + id + '/rewards',
        method: 'GET'
    });
}

export function deleteById(id) {
    return request({
        url: API_BASE_URL + '/customers/rewards/' + id,
        method: 'DELETE'
    });
}