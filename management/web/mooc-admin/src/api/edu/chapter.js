import request from '@/utils/request'

const api_prefix = '/eduservice/chapter'

export default {
  getChapterVideo(courseId) {
    return request({
      url: `${api_prefix}/getChapterVideo/` + courseId,
      method: 'get'
    })
  },
  addChapter(chapter) {
    return request({
      url: `${api_prefix}/addChapter`,
      method: 'post',
      data: chapter
    })
  },
  getChapter(chapterId) {
    return request({
      url: `${api_prefix}/` + chapterId,
      method: 'get'
    })
  },
  updateChapter(chapter) {
    return request({
      url: `${api_prefix}/updateChapter`,
      method: 'put',
      data: chapter
    })
  },
  deleteChapter(chapterId) {
    return request({
      url: `${api_prefix}/` + chapterId,
      method: 'delete'
    })
  }
}
