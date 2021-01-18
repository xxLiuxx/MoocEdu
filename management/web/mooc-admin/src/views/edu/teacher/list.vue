<template>
  <div class="app-container">
    <!--search form-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="name"/>
      </el-form-item>
      <el-form-item>
        <el-select v-model="teacherQuery.level" clearable placeholder="level">
          <el-option :value="1" label="junior"/>
          <el-option :value="2" label="senior"/>
        </el-select>
      </el-form-item>
      <el-form-item label="create time">
        <el-date-picker
          v-model="teacherQuery.begin"
          type="datetime"
          placeholder="select the start time"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherQuery.end"
          type="datetime"
          placeholder="select the end time"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()">search</el-button>
      <el-button type="default" @click="resetData()">clear</el-button>
    </el-form>

    <!-- table -->
    <el-table
      :data="list"
      element-loading-text="loading data"
      border
      fit
      highlight-current-row
    >

      <el-table-column
        label="No."
        width="70"
        align="center"
      >
        <template slot-scope="scope">
          {{ (pageNumber - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="name" width="80"/>

      <el-table-column label="level" width="80">
        <template slot-scope="scope">
          {{ scope.row.level === 1 ? 'junior' : 'senior' }}
        </template>
      </el-table-column>

      <el-table-column prop="intro" label="introduction"/>
      <el-table-column prop="gmtCreate" label="create time" width="160"/>
      <el-table-column prop="sort" label="sort" width="60"/>

      <el-table-column label="operations" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/edu/teacher/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">edit</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">delete
          </el-button>
        </template>
      </el-table-column>

    </el-table>

    <!-- pagination -->
    <el-pagination
      :current-page="pageNumber"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
  </div>
</template>

<script>
import teacher from '@/api/edu/teacher'

export default {
  // define the data and the initial values
  data() {
    return {
      pageNumber: 1,
      limit: 4,
      teacherQuery: {},
      list: null,
      total: 0
    }
  },

  // execute before the page is loaded, to load some data
  created() {
    this.getList()
  },

  // define and import methods
  methods: {
    getList(page = 1) {
      this.pageNumber = page
      teacher.getTeacherList(this.pageNumber, this.limit, this.teacherQuery)
        .then(response => {
          this.list = response.data.pageResult.items
          this.total = response.data.pageResult.totalRows
          console.log(this.list)
          console.log(this.total)
        })
    },
    removeDataById(id) {
      // ask user to confirm the delete operation first
      this.$confirm('The delete operation is irreversible!', 'Notice', {
        confirmButtonText: 'confirm',
        cancelButtonText: 'cancel',
        type: 'warning'
      }).then(() => {
        teacher.deleteTeacher(id)
          .then(response => {
            this.$message({
              type: 'success',
              message: 'record is deleted!'
            })
          })
        this.getList()
      })
    },
    resetData() {
      this.teacherQuery = {}
      this.getList()
    }
  }
}
</script>

<style scoped>

</style>
