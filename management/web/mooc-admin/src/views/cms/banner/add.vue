<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="Banner Title">
        <el-input v-model="banner.title" placeholder="e.g: Java"/>
      </el-form-item>
      <el-form-item label="Banner Link">
        <el-input v-model="banner.linkUrl" placeholder="/course"/>
      </el-form-item>
      <el-form-item label="Banner">
        <el-upload
          :show-file-list="false"
          :on-success="handleCoverSuccess"
          :before-upload="beforeCoverUpload"
          :action="BASE_API+'/eduoss/file'"
          class="avatar-uploader"
        >
          <img :src="banner.imageUrl">
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="save">save</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import bannerApi from '@/api/crm/banner'
export default {
  data() {
    return {
      banner: {
        imageUrl: '@/static/01.jpg',
        linkUrl: '/course',
        title: ''
      },
      BASE_API: process.env.VUE_APP_BASE_API,
      saveBtnDisabled: false
    }
  },
  created() {

  },
  methods: {
    save() {
      bannerApi.addBanner(this.banner)
        .then(response => {
          this.$message({
            type: 'success',
            message: 'banner added'
          })
          this.$router.push('/banner/list')
        })
    },
    handleCoverSuccess(res, file) {
      this.banner.imageUrl = res.data.url
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
    }
  }
}
</script>

<style scoped>

</style>
