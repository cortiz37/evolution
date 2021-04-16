import {API_NOTIFICATION_URL} from '../constants/index';
import {request} from "../util/APIUtils";

export function getAll() {
    return request({
        url: API_NOTIFICATION_URL,
        method: 'GET'
    }, true);
}

export function deleteById(id) {
    return request({
        url: API_NOTIFICATION_URL + '/' + id,
        method: 'DELETE'
    }, true);
}