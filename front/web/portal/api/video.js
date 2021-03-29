import request from '@/utils/request'

const api_prefix = '/eduvod/video'

export default {
    getPlayAuth(videoId) {
        return request({
            url: `${api_prefix}/getAuth/${videoId}`,
            method: 'get'
        })
    }
}
