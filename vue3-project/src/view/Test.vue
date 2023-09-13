<template>
  <div>
    {{ viewdata.title }}
  </div>

  <div>
    {{ viewdata.page }}
    <input type="text" v-model="viewdata.page.pageNumber" />
    <input type="text" v-model="viewdata.page.pageSize" />
  </div>

  <div>
    {{ viewdata.result }}
  </div>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import { PageInfo, BaseResult } from '../ts/BaseResult'
import ApiService from '../ts/ApiService'

const viewdata = reactive({
  title: '测试页面',
  page: new PageInfo(),
  result: new BaseResult(),
})

viewdata.result.message = '服务器信息'

ApiService.get('/', { echo: '黑暗骑士' }, (data: BaseResult) => {
  alert(data.message)
})
</script>
<style></style>
