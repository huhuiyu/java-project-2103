<template>
  <!-- 新闻显示的部分 -->
  <div class="news">
    <div v-for="d in list">
      <el-card>
        <template #header>{{ d.source }}</template>
        <div>
          <div>
            <span class="title" @click="showNews(d.nid)"> {{ d.title }}</span>
          </div>
          <div class="footer">
            {{ tools.formatDate(d.lastupdate) }}
          </div>
        </div>
      </el-card>
    </div>
  </div>

  <div>{{ page }}</div>
  <!-- 详情查看的对话框 -->
  <ElDialog title="新闻详情" v-model="news_info_visible">
    <div>
      <div class="detail" v-html="news_info.info"></div>
    </div>
  </ElDialog>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { ApiService } from '../api/ApiService'
import { tools } from '../api/Tools'
import { ElMessageBox, ElCard, ElDialog } from 'element-plus'

//#region 查询的部分

const page = ref({
  pageNumber: 1,
  pageSize: 10,
})

const queryInfo = ref({
  title: '',
  info: '',
})

const list = ref([])

const query = () => {
  ApiService.get('/crud/news', tools.concatJson(page.value, queryInfo.value), (data: any) => {
    if (data.success) {
      page.value = data.pageBean
      list.value = data.data
    } else {
      ElMessageBox.alert(data.message, '黑暗骑士的网站')
    }
  })
}

query()

//#endregion

//#region 显示新闻详情的部分

const news_info = ref({})
const news_info_visible = ref(false)

const showNews = (id: any) => {
  ApiService.get(`/crud/news/${id}`, {}, (data: any) => {
    if (data.success) {
      news_info.value = data.data
      news_info_visible.value = true
    } else {
      ElMessageBox.alert(data.message, '黑暗骑士的网站')
    }
  })
}

//#endregion
</script>

<style lang="scss" scoped>
.news {
  display: flex;
  flex-wrap: wrap;
  margin: 1rem;
  > div {
    margin: 1rem;
    width: 30rem;
  }
  .footer {
    padding: 1rem;
    text-align: end;
  }
  .title {
    display: inline-block;
    padding: 1rem 0px;
    cursor: pointer;
    &:hover {
      color: #1890ff;
    }
  }
}
.detail {
  max-height: 60vh;
  width: 100%;
  overflow-y: auto;
}
</style>
