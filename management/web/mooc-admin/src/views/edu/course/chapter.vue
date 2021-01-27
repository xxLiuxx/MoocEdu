<template>
  <div class="app-container">
    <h2 style="text-align: center;">Publish New Course</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="Basic Info"/>
      <el-step title="Chapter Info"/>
      <el-step title="Final Publish"/>
    </el-steps>
    <ul class="chanpterList">
      <li
        v-for="chapter in chapterVideoList"
        :key="chapter.id"
      >
        <p>
          {{ chapter.title }}
          <span class="acts">
            <el-button type="text">add chapter</el-button>
            <el-button style="" type="text">edit</el-button>
            <el-button type="text">delete</el-button>
          </span>
        </p>
        <ul class="chanpterList videoList">
          <li
            v-for="video in chapter.videoList"
            :key="video.id"
          >
            <p>{{ video.title }}
              <span class="acts">
                <el-button type="text">edit</el-button>
                <el-button type="text">delete</el-button>
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
  </div>
</template>

<script>
import chapterApi from '@/api/edu/chapter'

export default {
  data() {
    return {
      saveBtnDisabled: false,
      courseId: '',
      chapterVideoList: []
    }
  },
  created() {
    if (this.$route.params.id) {
      this.courseId = this.$route.params.id
    }
    this.getChapterVideo()
  },
  methods: {
    getChapterVideo() {
      chapterApi.getChapterVideo(this.courseId)
        .then(response => {
          this.chapterVideoList = response.data.chapterList
        })
    },
    previous() {
      this.$router.push('/course/info/' + this.courseId)
    },
    next() {
      this.$router.push('/course/publish/1')
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
