<template>
  <div class="app-container">
    <h2 style="text-align: center;">Publish New Course</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="Basic Info"/>
      <el-step title="Chapter Info"/>
      <el-step title="Final Publish"/>
    </el-steps>

    <el-button @click="dialogChapterFormVisible=true">Add Chapter</el-button>

    <ul class="chanpterList">
      <li v-for="chapter in chapterVideoList" :key="chapter.id">
        <p>
          {{ chapter.title }}
          <span class="acts">
            <el-button type="text" @click="openVideoAdd(chapter.id)">add section</el-button>
            <el-button type="text" @click="openChapterEdit(chapter.id)">edit</el-button>
            <el-button type="text" @click="deleteChapter(chapter.id)">delete</el-button>
          </span>
        </p>
        <ul class="chanpterList videoList">
          <li v-for="video in chapter.videoList" :key="video.id">
            <p>{{ video.title }}
              <span class="acts">
                <el-button type="text" @click="openVideoEdit(video.id)">edit</el-button>
                <el-button type="text" @click="deleteVideo(video.id)">delete</el-button>
              </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>
    <div>
      <el-button @click="previous">back</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="next">next</el-button>
    </div>

    <!--  chapter  -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="add chapter">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="chapter title">
          <el-input v-model="chapter.title"/>
        </el-form-item>
        <el-form-item label="chapter sort">
          <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">cancel</el-button>
        <el-button type="primary" @click="saveOrUpdate">finish</el-button>
      </div>
    </el-dialog>

    <!--  video  -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="add video">
      <el-form :model="video" label-width="120px">
        <el-form-item label="video ">
          <el-input v-model="video.title"/>
        </el-form-item>
        <el-form-item label="video sort">
          <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="video price">
          <el-radio-group v-model="video.isFree">
            <el-radio :label="true">free</el-radio>
            <el-radio :label="false">default</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="upload video">
          <el-upload :on-success="handleVodUploadSuccess" :on-remove="handleVodRemove" :before-remove="beforeVodRemove" :on-exceed="handleUploadExceed" :file-list="fileList" :action="BASE_API+'/eduvod/video/uploadVideo'" :limit="1" class="upload-demo">
            <el-button size="small" type="primary">Upload Video</el-button>
            <el-tooltip placement="right-end">
              <div slot="content">Max size 1G，<br>
                Format: 3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                SWF、TS、VOB、WMV、WEBM</div>
              <i class="el-icon-question"/>
            </el-tooltip>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">cancel</el-button>
        <el-button type="primary" @click="saveOrUpdateVideo">finish</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import chapterApi from '@/api/edu/chapter'
import videoApi from '@/api/edu/video'

export default {
  data() {
    return {
      saveBtnDisabled: false,
      courseId: '',
      chapterVideoList: [],
      chapter: {
        title: '',
        sort: 0
      },
      video: {
        isFree: false,
        title: '',
        sort: 0,
        videoSourceId: '',
        videoOriginalName: ''
      },
      dialogChapterFormVisible: false,
      dialogVideoFormVisible: false,
      fileList: [],
      BASE_API: process.env.VUE_APP_BASE_API
    }
  },
  created() {
    if (this.$route.params.id) {
      this.courseId = this.$route.params.id
    }
    this.getChapterVideo()
  },
  methods: {
    // chapter
    getChapterVideo() {
      chapterApi.getChapterVideo(this.courseId)
        .then(response => {
          this.chapterVideoList = response.data.chapterList
        })
    },
    saveOrUpdate() {
      if (this.chapter.id) {
        this.update()
      } else {
        this.save()
      }
    },
    update() {
      chapterApi.updateChapter(this.chapter)
        .then(response => {
          // close the window
          this.dialogChapterFormVisible = false
          // success messgae
          this.$message({
            type: 'success',
            message: 'chapter updated'
          })
          // refresh
          this.getChapterVideo()
        })
    },
    save() {
      this.chapter.courseId = this.courseId
      chapterApi.addChapter(this.chapter)
        .then(response => {
          // close the window
          this.dialogChapterFormVisible = false
          // success messgae
          this.$message({
            type: 'success',
            message: 'chapter added'
          })
          // refresh
          this.chapter.title = ''
          this.getChapterVideo()
        })
    },
    deleteChapter(chapterId) {
      this.$confirm('Are you sure to delete the chapter?', 'Notice', {
        confirmButtonText: 'continue',
        cancelButtonText: 'cancel',
        type: 'warning'
      }).then(() => {
        chapterApi.deleteChapter(chapterId)
          .then(response => {
            this.$message({
              type: 'success',
              message: 'chapter deleted'
            })
            this.getChapterVideo()
          })
      })
    },
    openChapterEdit(chapterId) {
      this.dialogChapterFormVisible = true
      chapterApi.getChapter(chapterId)
        .then(response => {
          this.chapter = response.data.chapter
        })
    },

    // video
    openVideoAdd(chapterId) {
      this.dialogVideoFormVisible = true
      this.chapterId = chapterId
      if (this.video.id) {
        this.video.title = ''
        this.video.free = false
        this.video.videoSourceId = ''
        this.video.sort = 0
      }
    },
    openVideoEdit(videoId) {
      this.dialogVideoFormVisible = true
      videoApi.getVideo(videoId)
        .then(response => {
          this.video = response.data.video
        })
    },
    saveOrUpdateVideo() {
      if (this.video.id) {
        this.updateVideo()
      } else {
        this.saveVideo()
      }
    },
    saveVideo() {
      this.video.courseId = this.courseId
      this.video.chapterId = this.chapterId
      videoApi.addVideo(this.video)
        .then(response => {
          this.dialogVideoFormVisible = false
          this.$message({
            type: 'success',
            message: 'video added'
          })
          this.helpSaveVideo()
        })
    },
    updateVideo() {
      videoApi.updateVideo(this.video)
        .then(response => {
          this.dialogVideoFormVisible = false
          this.$message({
            type: 'success',
            message: 'video updated'
          })
          this.helpSaveVideo()
        })
    },
    deleteVideo(videoId) {
      this.$confirm('Are you sure to delete the video?', 'Notice', {
        confirmButtonText: 'continue',
        cancelButtonText: 'cancel',
        type: 'warning'
      }).then(() => {
        videoApi.deleteVideo(videoId)
          .then(response => {
            this.$message({
              type: 'success',
              message: 'video deleted'
            })
            this.fileList = []
            this.getChapterVideo()
          })
      })
    },
    helpSaveVideo() {
      this.dialogVideoFormVisible = false
      this.getChapterVideo()
      this.video.title = ''
      this.video.sort = 0
      this.video.videoSourceId = ''
      this.fileList = []
    },

    handleVodUploadSuccess(response, file, fileList) {
      this.video.videoSourceId = response.data.videoId
      this.video.videoOriginalName = file.name
    },
    handleVodRemove() {
      videoApi.deleteAliyunVod(this.video.videoSourceId)
        .then(response => {
          this.$message({
            type: 'success',
            message: 'video deleted'
          })
          this.fileList = []
          // delete the videoId and videoName in the database
          this.video.videoSourceId = ''
          this.video.videoOriginalName = ''
        })
    },
    beforeVodRemove(file, fileList) {
      return this.$confirm(`Are you sure to delete ${file.name} ?`)
    },
    handleUploadExceed() {
      this.$message.warning('If you want to upload the video again, please delete the original one.')
    },
    previous() {
      this.$router.push('/course/info/' + this.courseId)
    },
    next() {
      this.$router.push('/course/publish/' + this.courseId)
    }
  }
}
</script>

<style scoped>
.chanpterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}

.chanpterList li {
  position: relative;
}

.chanpterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}

.chanpterList .acts {
  float: right;
  font-size: 14px;
}

.videoList {
  padding-left: 50px;
}

.videoList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}
</style>
