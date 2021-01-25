<template>
  <div class="app-container">
    <h2 style="text-align: center;">Publish</h2>
    <el-steps :active="3" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="Basic Info"/>
      <el-step title="Chapter Info"/>
      <el-step title="Final Publish"/>
    </el-steps>
    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">back</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="publish">publish</el-button>
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
      this.$router.push('/course/chapter/1')
    },
    publish() {
      this.$router.push('/course/list')
    }
  }
}
</script>

<style scoped>

</style>
