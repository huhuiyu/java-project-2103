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
          {{ d.title }}
        </div>
      </ElCard>
    </div>
  </div>

  <PageComp :page="pageInfo"></PageComp>
</template>

<script lang="ts" setup>
import { ElCard } from 'element-plus'
import UserInfoComp from '../../components/UserInfoComp.vue'
import { ref } from 'vue'
import { ApiService } from '../../ts/ApiService'
import { Tools } from '../../ts/Tools'
import { PageInfo } from '../../ts/BaseResult'
import PageComp from '../../components/PageComp.vue'

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
    > div:first-of-type {
      margin: 0.5rem;
      border: 1px solid #aaa;
      padding: 0.5rem;
    }
  }
}
</style>
