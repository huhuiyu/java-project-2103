<template>
  <div></div>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import ApiService from '../ts/ApiService'
import Tools from '../ts/Tools'
import { BaseResult, BaseListResult, PageInfo } from '../ts/BaseResult'
import { Dept } from '../ts/Manage'
import { ElDialog, ElButton, ElMessageBox, ElPagination, ElTable, ElTableColumn, ElForm, ElFormItem, ElInput } from 'element-plus'
import PageComp from './PageComp.vue'

const viewdata = reactive({
  page: new PageInfo(),
  queryInfo: {
    deptName: '',
  },
  list: new Array<Dept>(),
})

function query() {
  ApiService.post('/manage/dept/queryAll', Tools.concatJson(viewdata.page, viewdata.queryInfo), (data: BaseListResult<Dept>) => {
    if (data.success) {
      viewdata.page = data.page
      viewdata.list = data.list
      return
    }
  })
}

query()
</script>
<style></style>
