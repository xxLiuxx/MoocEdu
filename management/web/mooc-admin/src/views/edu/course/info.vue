<template>
  <div class="app-container">
    <h2 style="text-align: center;">Publish New Course</h2>
    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="Basic Info"/>
      <el-step title="Chapter Info"/>
      <el-step title="Final Publish"/>
    </el-steps>

    <el-form label-width="120px">
      <el-form-item label="Course Title">
        <el-input v-model="courseInfo.title" placeholder="e.g: Springboot Bootcamp: Build the management system in 10 days!"/>
      </el-form-item>

      <el-form-item label="Course Subject">
        <el-select v-model="courseInfo.subjectParentId" placeholder="subject one" @change="showChildren">
          <el-option v-for="subject in subjectList" :key="subject.id" :label="subject.title" :value="subject.id"/>
        </el-select>
        <el-select v-model="courseInfo.subjectId" placeholder="subject two">
          <el-option v-for="subject in childrenSubjectList" :key="subject.id" :label="subject.title" :value="subject.id"/>
        </el-select>
      </el-form-item>

      <el-form-item label="teacher">
        <el-select v-model="courseInfo.teacherId" placeholder="select">
          <el-option v-for="teacher in teacherList" :key="teacher.id" :label="teacher.name" :value="teacher.id"/>
        </el-select>
      </el-form-item>

      <el-form-item label="Total Course Time">
        <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="Enter the total course time"/>
      </el-form-item>

      <el-form-item label="Course Description">
        <tinymce :height="300" v-model="courseInfo.description"/>
      </el-form-item>

      <el-form-item label="Course cover">
        <el-upload
          :show-file-list="false"
          :on-success="handleCoverSuccess"
          :before-upload="beforeCoverUpload"
          :action="BASE_API+'/eduoss/file'"
          class="avatar-uploader"
        >
          <img :src="courseInfo.cover">
        </el-upload>
      </el-form-item>

      <el-form-item label="Course Price">
        $
        <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="free course: 0"/>
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">next</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import courseApi from '@/api/edu/course.js'
import teacherApi from '@/api/edu/teacher.js'
import subjectApi from '@/api/edu/subject.js'
import Tinymce from '@/components/Tinymce'

export default {
  components: {
    Tinymce
  },
  data() {
    return {
      saveBtnDisabled: false,
      courseInfo: {
        title: '',
        subjectId: '',
        subjectParentId: '',
        teacherId: '',
        lessonNum: 0,
        description: '',
        cover: '@/static/01.jpg',
        price: 0
      },
      teacherList: [],
      subjectList: [],
      childrenSubjectList: [],
      BASE_API: process.env.VUE_APP_BASE_API,
      courseId: ''
    }
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      this.courseId = this.$route.params.id
      this.getCourse()
    }
    this.getTeacherList()
    this.getParentList()
  },
  methods: {
    getTeacherList() {
      teacherApi.findAll()
        .then(response => {
          this.teacherList = response.data.list
        })
    },
    getParentList() {
      subjectApi.findAll()
        .then(response => {
          this.subjectList = response.data.subjectTree
        })
    },
    showChildren(value) {
      for (let i = 0; i < this.subjectList.length; i++) {
        if (this.subjectList[i].id === value) {
          this.childrenSubjectList = this.subjectList[i].children
          this.courseInfo.subjectId = ''
        }
      }
    },
    handleCoverSuccess(res, file) {
      this.courseInfo.cover = res.data.url
    },
    beforeCoverUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('The image can only be jpg')
      }
      if (!isLt2M) {
        this.$message.error('The image can not exceed 2MB')
      }
      return isJPG && isLt2M
    },
    saveOrUpdate() {
      if (this.$route.params.id) {
        this.update()
      } else {
        this.save()
      }
    },
    getCourse() {
      courseApi.getCourse(this.courseId)
        .then(response => {
          this.courseInfo = response.data.courseInfo

          // init children list
          subjectApi.findAll()
            .then(response => {
              this.subjectList = response.data.subjectTree
              for (let i = 0; i < this.subjectList.length; i++) {
                if (this.subjectList[i].id === this.courseInfo.subjectParentId) {
                  this.childrenSubjectList = this.subjectList[i].children
                }
              }
            })
        })
    },
    update() {
      courseApi.updateCourse(this.courseInfo)
        .then(response => {
          this.$message({
            type: 'success',
            message: 'basic info updated'
          })
          this.$router.push('/course/chapter/' + this.courseId)
        })
    },
    save() {
      courseApi.addCourse(this.courseInfo)
        .then(response => {
          this.$message({
            type: 'success',
            message: 'basic info added'
          })
          this.$router.push('/course/chapter/' + response.data.courseId)
        })
    }
  }
}
</script>

<style scoped>
.tinymce-container {
  line-height: 29px;
}
</style>
