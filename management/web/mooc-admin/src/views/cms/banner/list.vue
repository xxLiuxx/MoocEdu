<template>
  <div>
    <el-table v-loading="listLoading" :data="bannerList" element-loading-text="loading" border fit highlight-current-row row-class-name="myClassList">
      <el-table-column label="No." width="70" align="center">
        <template slot-scope="scope">
          {{ (pageNumber - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="Banner Info" width="600" align="center">
        <template slot-scope="scope">
          <div class="info">
            <div class="pic">
              <img :src="scope.row.imageUrl" width="150px">
            </div>
            <div class="title">
              <p>{{ scope.row.title }}</p>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="manage" width="600" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :current-page="pageNumber" :page-size="limit" :total="total" style="padding: 30px 0; text-align: center;" layout="total, prev, pager, next, jumper" @current-change="fetchData"/>
  </div>
</template>

<script>
import bannerApi from '@/api/crm/banner'
export default {
  data() {
    return {
      pageNumber: 1,
      limit: 3,
      total: 0,
      listLoading: false,
      bannerList: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData(page = 1) {
      this.pageNumber = page
      this.listLoading = true
      bannerApi.getBanner(this.pageNumber, this.limit)
        .then(response => {
          this.bannerList = response.data.bannerList
          this.total = response.data.total
          this.listLoading = false
        }).catch(() => {
          this.listLoading = false
        })
    },
    removeDataById(bannerId) {
      this.$confirm('The delete operation is irreversible!', 'Notice', {
        confirmButtonText: 'confirm',
        cancelButtonText: 'cancel',
        type: 'warning'
      }).then(() => {
        bannerApi.deleteBanner(bannerId)
          .then(response => {
            this.$message({
              type: 'success',
              message: 'banner is deleted!'
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
