<template>
  <div>
    <UserInfoComp>
      <div>简易留言板</div>
    </UserInfoComp>
  </div>
  <hr />

  <div class="container">
    <div v-for="d in list">
      <ElCard>
        <template #header>
          <div class="header">
            <div>
              <ElAvatar :src="d.userInfo.img" fit="cover"></ElAvatar>
            </div>

            <div>
              <div>{{ d.user.username }}</div>
              <div>{{ Tools.formatDate(d.lastupdate) }}</div>
            </div>
          </div>
        </template>

        <div>
          <div>
            <a @click="showDetail(d.umid)" href="javascript:void(0)">
              <ElTag type="info" size="large"> {{ d.title }}</ElTag>
            </a>
          </div>
          <div class="info">
            <span>点赞数：</span><span>{{ d.praiseCount }}</span> <span>点击量：</span><span>{{ d.hits }}</span> <span>评论数：</span><span>{{ d.replyCount }}</span>
          </div>
        </div>
      </ElCard>
    </div>
  </div>

  <PageComp :page="pageInfo" @page-change="query"></PageComp>
</template>

<script lang="ts" setup>
import { ElCard, ElAvatar, ElTag } from 'element-plus'
import UserInfoComp from '../../components/UserInfoComp.vue'
import { ref } from 'vue'
import { ApiService } from '../../ts/ApiService'
import { Tools } from '../../ts/Tools'
import { PageInfo } from '../../ts/BaseResult'
import PageComp from '../../components/PageComp.vue'
import { router } from '../../router'

const list = ref([])
const pageInfo = ref(new PageInfo())
const queryInfo = ref({
  info: '',
  orderBy: 1,
})

function query() {
  ApiService.post('/message/queryAll', Tools.concatJson(pageInfo.value, queryInfo.value), (data: any) => {
    if (data.success) {
      pageInfo.value = data.page
      list.value = data.list
    }
  })
}

function showDetail(umid) {
  router.push(`/user/messagedetail/${umid}`)
}

query()
</script>

<style lang="scss" scoped>
.container {
  margin: 1rem auto;
  width: 60%;
  > div {
    margin: 1rem;
  }
  .header {
    display: flex;
    align-items: flex-start;
  }
  .info {
    text-align: right;
    padding: 0.5rem;
    font-size: 0.8rem;
    color: #999;
  }
}
</style>
