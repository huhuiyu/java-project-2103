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
          <div class="header">
            <div>{{ d.source }}</div>
            <div>
              <svg @click="showModify(d)" t="1699252555088" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4035" width="32" height="32">
                <path
                  d="M571.52 136.832a32 32 0 0 1 44.8 0.64l174.4 175.904a32 32 0 0 1-1.632 46.624L368.864 728.064a32 32 0 0 1-21.76 7.936l-155.776-3.328a32 32 0 0 1-31.328-32v-158.208a32 32 0 0 1 9.92-23.168z m42.464 514.496l239.84 4.672a32 32 0 1 1-1.248 64l-239.84-4.672a32 32 0 1 1 1.28-64zM592.96 204.8L224 556.16v113.184l112 2.4 385.312-337.472L592.96 204.8z m259.296 606.528a32 32 0 0 1 0.48 64l-628.48 4.672a32 32 0 0 1-0.48-64l628.48-4.672z"
                  fill="#111111"
                  p-id="4036"
                ></path>
              </svg>
              <svg @click="del(d)" t="1699252624472" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5050" width="32" height="32">
                <path d="M607.897867 768.043004c-17.717453 0-31.994625-14.277171-31.994625-31.994625L575.903242 383.935495c0-17.717453 14.277171-31.994625 31.994625-31.994625s31.994625 14.277171 31.994625 31.994625l0 351.94087C639.892491 753.593818 625.61532 768.043004 607.897867 768.043004z" fill="#575B66" p-id="5051"></path>
                <path d="M415.930119 768.043004c-17.717453 0-31.994625-14.277171-31.994625-31.994625L383.935495 383.935495c0-17.717453 14.277171-31.994625 31.994625-31.994625 17.717453 0 31.994625 14.277171 31.994625 31.994625l0 351.94087C447.924744 753.593818 433.647573 768.043004 415.930119 768.043004z" fill="#575B66" p-id="5052"></path>
                <path
                  d="M928.016126 223.962372l-159.973123 0L768.043004 159.973123c0-52.980346-42.659499-95.983874-95.295817-95.983874L351.94087 63.989249c-52.980346 0-95.983874 43.003528-95.983874 95.983874l0 63.989249-159.973123 0c-17.717453 0-31.994625 14.277171-31.994625 31.994625s14.277171 31.994625 31.994625 31.994625l832.032253 0c17.717453 0 31.994625-14.277171 31.994625-31.994625S945.73358 223.962372 928.016126 223.962372zM319.946246 159.973123c0-17.545439 14.449185-31.994625 31.994625-31.994625l320.806316 0c17.545439 0 31.306568 14.105157 31.306568 31.994625l0 63.989249L319.946246 223.962372 319.946246 159.973123 319.946246 159.973123z"
                  fill="#575B66"
                  p-id="5053"
                ></path>
                <path
                  d="M736.048379 960.010751 288.123635 960.010751c-52.980346 0-95.983874-43.003528-95.983874-95.983874L192.139761 383.591466c0-17.717453 14.277171-31.994625 31.994625-31.994625s31.994625 14.277171 31.994625 31.994625l0 480.435411c0 17.717453 14.449185 31.994625 31.994625 31.994625l448.096758 0c17.717453 0 31.994625-14.277171 31.994625-31.994625L768.215018 384.795565c0-17.717453 14.277171-31.994625 31.994625-31.994625s31.994625 14.277171 31.994625 31.994625l0 479.231312C832.032253 916.835209 789.028725 960.010751 736.048379 960.010751z"
                  fill="#575B66"
                  p-id="5054"
                ></path>
              </svg>
            </div>
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

  <!-- 编辑的对话框 -->
  <ElDialog title="编辑新闻" v-model="mvisible" @closed="query">
    <div>
      <ElForm>
        <ElFormItem>
          <ElInput v-model="modifyInfo.title" placeholder="请输入标题"></ElInput>
        </ElFormItem>

        <ElFormItem>
          <ElInput v-model="modifyInfo.source" placeholder="请输入来源"></ElInput>
        </ElFormItem>

        <ElFormItem>
          <WangEditorComp @value-change="chnage_modify_value" :init-value="modifyInfo.info"></WangEditorComp>
        </ElFormItem>

        <ElFormItem>
          <ElButton type="primary" @click="modify">保存</ElButton>
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

//#region 修改新闻的部分
const chnage_modify_value = (value: any) => {
  modifyInfo.value.info = value
}

const mvisible = ref(false)
const modifyInfo = ref({
  nid: -1,
  title: '',
  info: '',
  source: '',
})

const showModify = (info: any) => {
  ApiService.get(`/crud/news/${info.nid}`, {}, (data: any) => {
    modifyInfo.value = data.data
    mvisible.value = true
  })
}

const modify = () => {
  ApiService.put(`/crud/news/${modifyInfo.value.nid}`, modifyInfo.value, (data: any) => {
    ElMessageBox.alert(data.message, '黑暗骑士的网站')
  })
}

//#endregion

//#region 删除新闻的部分
const del = (info: any) => {
  ElMessageBox.confirm(`是否删除来自${info.source}的新闻:${info.title}`, '删除确认')
    .then(() => {
      ApiService.delete(`/crud/news/${info.nid}`, {}, (data: any) => {
        ElMessageBox.alert(data.message, '黑暗骑士的网站').then(query).catch(query)
      })
    })
    .catch(() => {})
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

  .header {
    display: flex;
    justify-content: space-between;
    svg {
      margin: 0.5rem;
      &:hover {
        cursor: pointer;
        transform: scale(1.4);
      }
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
