import request from '@/utils/request'

const api_prefix = '/eduservice/chapter'

export default {
  getChapterVideo(courseId) {
    return request({
      url: `${api_prefix}/getChapterVideo/` + courseId,
      method: 'get'
    })
  }
}
