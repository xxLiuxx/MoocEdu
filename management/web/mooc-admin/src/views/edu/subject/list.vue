<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />
    <el-tree
      ref="subjectTree"
      :data="subjectTree"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />
  </div>
</template>

<script>
import subjectApi from '@/api/edu/subject'

export default {
  data() {
    return {
      filterText: '',
      subjectTree: [],
      defaultProps: {
        children: 'children',
        label: 'title'
      }
    }
  },
  created() {
    this.getSubjectTree()
  },
  watch: {
    filterText(val) {
      this.$refs.subjectTree.filter(val)
    }
  },
  methods: {
    getSubjectTree() {
      subjectApi.findAll()
        .then(response => {
          this.subjectTree = response.data.subjectTree
        })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1
    }
  }
}
</script>

<style scoped>

</style>
