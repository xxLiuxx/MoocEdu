<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="name">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="sort">
        <el-input-number v-model="teacher.sort" controls-position="right" :min="0"/>
      </el-form-item>
      <el-form-item label="level">
        <el-select v-model="teacher.level" clearable placeholder="select">
          <el-option :value="1" label="junior"/>
          <el-option :value="2" label="senior"/>
        </el-select>
      </el-form-item>
      <el-form-item label="career">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="introduction">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate()">save</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacherApi from '@/api/edu/teacher'

export default {
  data() {
    return {
      teacher: {},
      saveBtnDisabled: false
    }
  },

  created() {
    if (this.$route.params && this.$route.params.id) {
      const id = this.$route.params.id
      this.loadTeacher(id)
    }
  },

  methods: {
    saveOrUpdate() {
      if (this.teacher.id) {
        const id = this.teacher.id
        this.update(id)
      } else {
        this.save()
      }
    },
    save() {
      teacherApi.addTeacher(this.teacher)
        .then(response => {
          this.$message({
            type: 'success',
            message: 'add success'
          })
          this.$router.push({ path: '/teacher/table' })
        })
    },
    loadTeacher(id) {
      teacherApi.getTeacher(id)
        .then(response => {
          console.log(response)
          this.teacher = response.data.teacher
        })
    },
    update(id) {
      teacherApi.updateTeacher(id, this.teacher)
        .then(response => {
          this.$message({
            type: 'success',
            message: 'update success'
          })
          this.$router.push({ path: '/teacher/table' })
        })
    }
  }
}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

