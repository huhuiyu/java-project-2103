<template>
  <div>
    <ElButton type="primary" @click="showAdd">
      <span>发布新闻</span>
      <svg t="1699241635129" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4171" width="32" height="32">
        <path
          d="M940.809299 333.581448c-23.391756-57.684794-57.752332-109.431342-102.126982-153.805992-44.37465-44.37465-96.124268-78.736249-153.808038-102.128005C629.167536 55.056943 570.307987 43.603077 509.933945 43.603077c-60.375066 0-119.232568 11.453866-174.939311 34.044374-57.68377 23.391756-109.431342 57.753355-153.805992 102.128005-44.37465 44.37465-78.734202 96.122221-102.125959 153.805992C56.473199 389.288191 45.019333 448.145693 45.019333 508.519736c0 60.375066 11.453866 119.231545 34.044374 174.939311 23.391756 57.68377 57.751309 109.431342 102.125959 153.805992 44.37465 44.37465 96.122221 78.734202 153.805992 102.126982 55.706743 22.589484 114.565269 34.044374 174.939311 34.044374 60.374042 0 119.232568-11.453866 174.939311-34.044374 57.684794-23.391756 109.433388-57.752332 153.810085-102.126982 44.373627-44.373627 78.733179-96.122221 102.125959-153.805992 22.589484-55.706743 34.044374-114.564245 34.044374-174.939311C974.85265 448.145693 963.398784 389.288191 940.809299 333.581448zM905.988236 669.338445c-21.491477 52.999075-53.075823 100.559277-93.874017 141.357472-40.800241 40.799218-88.361467 72.383564-141.360542 93.875041-51.201126 20.761859-105.308441 31.289634-160.819732 31.289634-55.512315 0-109.61963-10.527774-160.818709-31.289634-52.999075-21.491477-100.558254-53.075823-141.357472-93.874017-40.799218-40.799218-72.382541-88.35942-93.875041-141.358495-20.762883-51.200102-31.289634-105.307418-31.289634-160.818709 0-55.511291 10.527774-109.618607 31.289634-160.818709 21.491477-52.999075 53.075823-100.558254 93.875041-141.357472 40.799218-40.800241 88.358397-72.384587 141.357472-93.877087 51.200102-20.762883 105.307418-31.290657 160.818709-31.290657 55.511291 0 109.618607 10.527774 160.819732 31.290657 52.999075 21.491477 100.5603 53.076846 141.359518 93.877087 40.799218 40.799218 72.383564 88.358397 93.875041 141.357472 20.761859 51.200102 31.289634 105.307418 31.289634 160.818709C937.278893 564.031027 926.750095 618.138342 905.988236 669.338445z"
          fill="#ffffff"
          p-id="4172"
        ></path>
        <path
          d="M810.369481 489.052359c-0.014326 0-0.029676 0-0.042979 0l-281.56577 0.635473 0.590448-282.148032c0.021489-10.376325-8.372691-18.804275-18.747993-18.826787-0.014326 0-0.025583 0-0.039909 0-10.356882 0-18.764366 8.385994-18.785855 18.747993l-0.591471 282.311761-281.725406 0.636496c-10.376325 0.023536-18.767436 8.453533-18.744923 18.829857 0.023536 10.361999 8.429996 18.744923 18.785855 18.744923 0.014326 0 0.029676 0 0.042979 0l281.561677-0.635473-0.590448 282.152125c-0.021489 10.375302 8.372691 18.804275 18.747993 18.825764 0.014326 0 0.025583 0 0.039909 0 10.355859 0 18.764366-8.385994 18.785855-18.747993l0.590448-282.314831 281.728476-0.636496c10.376325-0.023536 18.768459-8.453533 18.744923-18.829857C829.131801 497.435283 820.726364 489.052359 810.369481 489.052359z"
          fill="#ffffff"
          p-id="4173"
        ></path>
      </svg>
    </ElButton>
  </div>
  <!-- 新闻显示的部分 -->
  <div class="news">
    <div v-for="d in list">
      <el-card>
        <template #header>
          <div>
            <div>{{ d.source }}</div>
          </div>
        </template>
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

  <!-- 分页信息 -->
  <div style="display: flex; justify-content: center; padding: 1rem">
    <PageComp :page="page" @page-change="query"></PageComp>
  </div>

  <!-- 详情查看的对话框 -->
  <ElDialog title="新闻详情" v-model="news_info_visible">
    <div>
      <div class="detail" v-html="news_info.info"></div>
    </div>
  </ElDialog>

  <!-- 添加的对话框 -->
  <ElDialog title="添加新闻" v-model="add_visible" @closed="query">
    <div>
      <ElForm>
        <ElFormItem>
          <ElInput v-model="addInfo.title" placeholder="请输入标题"></ElInput>
        </ElFormItem>

        <ElFormItem>
          <ElInput v-model="addInfo.source" placeholder="请输入来源"></ElInput>
        </ElFormItem>

        <ElFormItem>
          <WangEditorComp @value-change="chnage_edit_value" :init-value="addInfo.info"></WangEditorComp>
        </ElFormItem>

        <ElFormItem>
          <ElButton type="primary" @click="add">发布</ElButton>
        </ElFormItem>
      </ElForm>
    </div>
  </ElDialog>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { ApiService } from '../api/ApiService'
import { tools } from '../api/Tools'
import { ElForm, ElFormItem, ElInput, ElButton, ElMessageBox, ElCard, ElDialog, ElIcon } from 'element-plus'
import PageComp from '../components/PageComp.vue'
import WangEditorComp from '../components/WangEditorComp.vue'

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

//#region 添加新闻的部分
const chnage_edit_value = (value: any) => {
  addInfo.value.info = value
}
const add_visible = ref(false)

const addInfo = ref({
  title: '',
  info: '请输入新闻内容...',
  source: '',
})

const showAdd = () => {
  addInfo.value = {
    title: '',
    info: '请输入新闻内容...',
    source: '',
  }
  add_visible.value = true
}

const add = () => {
  ApiService.post('/crud/news', addInfo.value, (data: any) => {
    ElMessageBox.alert(data.message, '黑暗骑士的网站')
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

svg.icon {
  width: 1rem;
  height: auto;
  display: inline-block;
  margin: 0px 0.2rem;
}
</style>
