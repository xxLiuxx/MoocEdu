import request from '@/utils/request'

const api_prefix = '/eduservice/subject'

export default {
  findAll() {
    return request({
      url: `${api_prefix}/findAll`,
      method: 'get'
    })
  }
}
