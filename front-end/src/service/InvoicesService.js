import {API_BASE_URL} from '../constants/index';
import {request} from "../util/APIUtils";

export function getAll() {
    return request({
        url: API_BASE_URL + '/customers/invoices',
        method: 'GET'
    });
}

export function create(client, reward, invoice) {
    return request({
        url: API_BASE_URL + '/customers/' + client + '/invoices?reward=' + reward,
        method: 'POST',
        body: JSON.stringify(invoice)
    });
}