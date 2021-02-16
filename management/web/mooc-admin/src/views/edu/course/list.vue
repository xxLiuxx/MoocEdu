<template>
  <div class="app-container">
    <!--query form-->
    <el-form :inline="true" class="demo-form-inline">
      <!-- parent subject -->
      <el-form-item label="course subject">
        <el-select v-model="searchObj.subjectParentId" placeholder="parent subject" @change="showChildren">
          <el-option v-for="subject in subjectList" :key="subject.id" :label="subject.title" :value="subject.id"/>
        </el-select>
        <!-- sub subject -->
        <el-select v-model="searchObj.subjectId" placeholder="subject">
          <el-option v-for="subject in subSubjectList" :key="subject.id" :label="subject.title" :value="subject.id"/>
        </el-select>
      </el-form-item>
      <!-- title -->
      <el-form-item>
        <el-input v-model="searchObj.title" placeholder="course title"/>
      </el-form-item>
      <!-- teacher -->
      <el-form-item>
        <el-select v-model="searchObj.teacherId" placeholder="select teacher">
          <el-option v-for="teacher in teacherList" :key="teacher.id" :label="teacher.name" :value="teacher.id"/>
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">search</el-button>
      <el-button type="default" @click="resetData()">clear</el-button>
    </el-form>

    <!-- list -->
    <el-table v-loading="listLoading" :data="courseList" element-loading-text="loading" border fit highlight-current-row row-class-name="myClassList">
      <el-table-column label="No." width="70" align="center">
        <template slot-scope="scope">
          {{ (pageNumber - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="Course Info" width="470" align="center">
        <template slot-scope="scope">
          <div class="info">
            <div class="pic">
              <img :src="scope.row.cover" width="150px">
            </div>
            <div class="title">
              <p>{{ scope.row.title }}</p>
              <p>{{ scope.row.lessonNum }} Course Time</p>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="Create Time" align="center">
        <template slot-scope="scope">
          {{ scope.row.gmtCreate.substr(0, 10) }}
        </template>
      </el-table-column>
      <el-table-column label="Publish Time" align="center">
        <template slot-scope="scope">
          {{ scope.row.gmtModified.substr(0, 10) }}
        </template>
      </el-table-column>
      <el-table-column label="Price" width="100" align="center">
        <template slot-scope="scope">
          {{ Number(scope.row.price) === 0 ? 'Free' : '$' + scope.row.price.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column prop="buyCount" label="Students" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.buyCount }} people
        </template>
      </el-table-column>
      <el-table-column label="manage" width="150" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/info/'+scope.row.id">
            <el-button type="text" size="mini" icon="el-icon-edit">edit info</el-button>
          </router-link>
          <router-link :to="'/course/chapter/'+scope.row.id">
            <el-button type="text" size="mini" icon="el-icon-edit">edit chapter</el-button>
          </router-link>
          <el-button type="text" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- pagination -->
    <el-pagination :current-page="pageNumber" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="fetchData"/>
  </div>
</template>

<script>
import courseApi from '@/api/edu/course'
import subjectApi from '@/api/edu/subject'
import teacherApi from '@/api/edu/teacher'
export default {
  data() {
    return {
      pageNumber: 1,
      limit: 4,
      total: 0,
      listLoading: true,
      searchObj: {
        title: '',
        teacherId: '',
        subjectParentId: '',
        subjectId: ''
      },
      teacherList: [],
      subjectList: [],
      subSubjectList: [],
      courseList: []
    }
  },
  created() {
    this.getParentList()
    this.getTeacherList()
    this.fetchData()
  },
  methods: {
    getParentList() {
      subjectApi.findAll()
        .then(response => {
          this.subjectList = response.data.subjectTree
        })
    },
    showChildren(value) {
      for (let i = 0; i < this.subjectList.length; i++) {
        if (this.subjectList[i].id === value) {
          this.subSubjectList = this.subjectList[i].children
          this.searchObj.subjectId = ''
        }
      }
    },
    getTeacherList() {
      teacherApi.findAll()
        .then(response => {
          this.teacherList = response.data.list
        })
    },
    fetchData(page = 1) {
      this.pageNumber = page
      this.listLoading = true
      courseApi.queryCourse(this.pageNumber, this.limit, this.searchObj)
        .then(response => {
          this.courseList = response.data.courseList
          this.total = response.data.total
          this.listLoading = false
        })
    },
    resetData() {
      this.subjectList = []
      this.searchObj = {}
      this.fetchData()
    },
    removeDataById(courseId) {
      this.$confirm('The delete operation is irreversible!', 'Notice', {
        confirmButtonText: 'confirm',
        cancelButtonText: 'cancel',
        type: 'warning'
      }).then(() => {
        courseApi.deleteCourse(courseId)
          .then(response => {
            this.$message({
              type: 'success',
              message: 'course is deleted!'
            })
            this.fetchData()
          })
      })
    }
  }
}
</script>

<style scoped>
.myClassList .info {
  width: 450px;
  overflow: hidden;
}
.myClassList .info .pic {
  width: 150px;
  height: 90px;
  overflow: hidden;
  float: left;
}
.myClassList .info .pic a {
  display: block;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
.myClassList .info .pic img {
  display: block;
  width: 100%;
}
.myClassList td .info .title {
  width: 280px;
  float: right;
  height: 90px;
}
.myClassList td .info .title a {
  display: block;
  height: 48px;
  line-height: 24px;
  overflow: hidden;
  color: #00baf2;
  margin-bottom: 12px;
}
.myClassList td .info .title p {
  line-height: 20px;
  margin-top: 5px;
  color: #818181;
}
</style>
