import axios from 'axios'
import cookie from 'js-cookie'

const service = axios.create({
    baseURL: 'http://localhost:9001',
    timeout: 40000
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

// service.interceptors.response.use(
//     response => {
//         //debugger
//         if (response.data.code == 28004) {
//             console.log("response.data.resultCode是28004")
//             // 返回 错误代码-1 清除ticket信息并跳转到登录页面
//             //debugger
//             window.location.href = "/login"
//             return
//         } else {
//             if (response.data.code !== 200) {
//                 //25000：订单支付中，不做任何提示
//                 if (response.data.code != 25000) {
//                     Message({
//                         message: response.data.message || 'error',
//                         type: 'error',
//                         duration: 5 * 100
//                     })
//                 }
//             } else {
//                 return response;
//             }
//         }
//     },
//     error => {
//         return Promise.reject(error.response)
//     })

export default service
