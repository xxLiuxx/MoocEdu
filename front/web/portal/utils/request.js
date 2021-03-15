import axios from 'axios'
import cookie from 'js-cookie'

const service = axios.create({
    baseURL: 'http://localhost:9001',
    timeout: 20000
})

service.interceptors.request.use(
config => {
    //debugger
    if (cookie.get('mooc_token')) {
        config.headers['token'] = cookie.get('mooc_token');
    }
    return config
},
err => {
    return Promise.reject(err);
})

export default service
