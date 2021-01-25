<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="description">
        <el-tag type="info">Excel Template</el-tag>
        <el-tag>
          <i class="el-icon-download"/>
          <a :href="'/static/template.xlsx'">Click to download the template</a>
        </el-tag>
      </el-form-item>
      <el-form-item label="select Excel">
        <el-upload
          ref="upload"
          :auto-upload="false"
          :on-success="fileUploadSuccess"
          :on-error="fileUploadError"
          :disabled="importBtnDisabled"
          :limit="1"
          :action="BASE_API+'/eduservice/subject/import'"
          name="file"
          accept="application/vnd.ms-excel">
          <el-button slot="trigger" size="small" type="primary">select file</el-button>
          <el-button
            :loading="loading"
            style="margin-left: 10px;"
            size="small"
            type="success"
            @click="submitUpload">{{ fileUploadBtnText }}</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      BASE_API: process.env.VUE_APP_BASE_API,
      fileUploadBtnText: 'Upload to server',
      importBtnDisabled: false,
      loading: false
    }
  },
  created() {

  },
  methods: {
    // this is submit from form not from ajax
    submitUpload() {
      this.fileUploadBtnText = 'Uploading'
      this.importBtnDisabled = true
      this.loading = true
      this.$refs.upload.submit()
    },

    fileUploadSuccess(response) {
      if (response) {
        this.fileUploadBtnText = 'upload success'
        this.loading = false
        this.$message.success({
          type: 'success',
          message: response.message
        })
        this.$router.push('/subject/list')
      }
    },
    fileUploadError(response) {
      this.fileUploadBtnText = 'upload failure'
      this.loading = false
      this.$message.success({
        type: 'error',
        message: 'upload error'
      })
    }

  }
}
</script>

<style scoped>

</style>
