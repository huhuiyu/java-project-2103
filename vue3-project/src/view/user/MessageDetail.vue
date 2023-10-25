<template>
  <!-- 帖子详情显示 -->
  <div class="info">
    <ElCard>
      <template #header>
        <div>
          <div style="display: flex">
            <ElAvatar v-if="info.userInfo && info.userInfo.img" :src="info.userInfo.img"></ElAvatar>
            <div>
              {{ info.user?.username }}
              <br />
              {{ Tools.formatDate(info.lastupdate) }}
            </div>
          </div>
          <div style="text-align: center">{{ info.title }}</div>
        </div>
      </template>

      <div>
        <pre v-html="info.info"></pre>
      </div>
    </ElCard>
  </div>

  <!-- 回复帖子功能 -->
  <div style="display: flex; margin: 0px 10vw">
    <ElInput v-model="reply_info.info" placeholder="我要回帖"></ElInput>
    <ElButton type="primary" @click="reply">回复</ElButton>
  </div>

  <!-- 回复的信息列表 -->
  <div style="margin: 2rem 10vw">
    <div v-for="d in list">
      <ElCard>
        <template #header>
          <div>{{ d.user?.username }}</div>
        </template>
        <div>
          <div> {{ d.info }}</div>
          <div> {{ Tools.formatDate(d.lastupdate) }}</div>
        </div>
      </ElCard>
    </div>
  </div>
  <!-- 分页 -->
  <PageComp :page="page" @page-change="query"></PageComp>
</template>

<script lang="ts" setup>
import { ElCard, ElAvatar, ElInput, ElMessageBox } from 'element-plus'
import { ApiService } from '../../ts/ApiService'
import { Tools } from '../../ts/Tools'
import { PageInfo } from '../../ts/BaseResult'
import { ref } from 'vue'
import { useRoute } from 'vue-router'
import PageComp from '../../components/PageComp.vue'

const route = useRoute()
const umid = route.params.id
const info = ref({})
const list = ref([])
const page = ref(new PageInfo())
page.value.pageSize = 5
const reply_info = ref({
  umid: umid,
  info: '',
})

function query() {
  let params = { orderBy: 2, umid: umid }
  ApiService.post('/message/queryDetail',
    Tools.concatJson(params, page.value), 
    (data: any) => {
    if (data.success) {
      info.value = data.info
      page.value = data.page
      list.value = data.list
    } 
  })
}

function reply() {
  ApiService.post('/message/addReply', reply_info.value, (data: any) => {
    ElMessageBox.alert(data.message).then(query)
  })
}

query()
</script>

<style lang="scss" scoped>
.info {
  width: 90vw;
  margin: 1rem auto;
}
</style>
