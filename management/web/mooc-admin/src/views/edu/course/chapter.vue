<template>
  <div class="app-container">
    <h2 style="text-align: center;">Publish New Course</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="Basic Info"/>
      <el-step title="Chapter Info"/>
      <el-step title="Final Publish"/>
    </el-steps>
    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">back</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">next</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      saveBtnDisabled: false
    }
  },
  created() {

  },
  methods: {
    previous() {
      this.$router.push('/course/info/' + this.$route.params.id)
    },
    next() {
      this.$router.push('/course/publish/1')
    }
  }
}
</script>

<style scoped>

</style>
