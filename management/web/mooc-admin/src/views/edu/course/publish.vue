<template>
  <div class="app-container">
    <h2 style="text-align: center;">Publish</h2>

    <el-steps :active="3" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="Basic Info"/>
      <el-step title="Chapter Info"/>
      <el-step title="Final Publish"/>
    </el-steps>

    <div class="ccInfo">
      <img :src="coursePublish.cover">
      <div class="main">
        <h2>{{ coursePublish.title }}</h2>
        <p class="gray"><span>{{ coursePublish.lessonNum }} course times</span></p>
        <p><span>subject：{{ coursePublish.subjectLevelOne }} — {{ coursePublish.subjectLevelTwo }}</span></p>
        <p>teacher：{{ coursePublish.teacherName }}</p>
        <h3 class="red">${{ coursePublish.price }}</h3>
      </div>
    </div>

    <div>
      <el-button @click="previous">back</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="publish">publish</el-button>
    </div>
  </div>
</template>

<script>
import courseApi from '@/api/edu/course'
export default {
  data() {
    return {
      saveBtnDisabled: false,
      coursePublish: {},
      courseId: ''
    }
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      this.courseId = this.$route.params.id
      this.getFinalPublishObject()
    }
  },
  methods: {
    getFinalPublishObject() {
      courseApi.getPublish(this.courseId)
        .then(response => {
          this.coursePublish = response.data.publish
        })
    },
    previous() {
      this.$router.push('/course/chapter/' + this.courseId)
    },
    publish() {
      courseApi.publishCourse(this.courseId)
        .then(response => {
          this.$message({
            type: 'success',
            message: 'course published'
          })
          this.$router.push('/course/list')
        })
    }
  }
}
</script>

<style scoped>
.ccInfo {
  background: #f5f5f5;
  padding: 20px;
  overflow: hidden;
  border: 1px dashed #DDD;
  margin-bottom: 40px;
  position: relative;
}
.ccInfo img {
  background: #d6d6d6;
  width: 500px;
  height: 278px;
  display: block;
  float: left;
  border: none;
}
.ccInfo .main {
  margin-left: 520px;
}
.ccInfo .main h2 {
  font-size: 28px;
  margin-bottom: 30px;
  line-height: 1;
  font-weight: normal;
}
.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}
.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}
.ccInfo .main h3 {
  left: 540px;
  bottom: 20px;
  line-height: 1;
  font-size: 28px;
  color: #d32f24;
  font-weight: normal;
  position: absolute;
}
</style>
