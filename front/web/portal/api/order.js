import request from '@/utils/request'

const api_prefix = '/orderservice/order'

export default {
    createOrder(courseId) {
        return request({
            url: `${api_prefix}/createOrder/${courseId}`,
            method: 'post'
        })
    },
    getOrderInfo(orderNo) {
        return request({
            url: `${api_prefix}/getOrder/${orderNo}`,
            method: 'get'
        })
    }
}
