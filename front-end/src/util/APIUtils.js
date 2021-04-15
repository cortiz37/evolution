import {Modal} from "antd/lib/index";

export const request = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json'
    });

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
        .then(response => {
            if (response.status === 204 || response.status === 201) {
                return response;
            }
            return response.json()
                .then(json => {
                    return json;
                })
        }).catch(error => {
            console.log(error)
            return showError('Something went wrong.')
        });
};

export function showError(content, title = 'Error') {
    Modal.error({
        title: title,
        content: content
    });
}

export function showInfo(content, title = 'Info') {
    Modal.info({
        title: title,
        content: content
    });
}

export function isArrayEmpty(arr) {
    return arr === undefined || arr.length === 0;
}