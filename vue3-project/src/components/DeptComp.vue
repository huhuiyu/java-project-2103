<template>
  <div>
    <ElForm :inline="true">
      <ElFormItem>
        <ElInput v-model="viewdata.queryInfo.deptName" placeholder="部门名称模糊查询"></ElInput>
      </ElFormItem>
      <ElFormItem>
        <ElButton @click="query" type="primary">查询</ElButton>
      </ElFormItem>
    </ElForm>
  </div>

  <div>
    <ElTable :data="viewdata.list" stripe border>
      <ElTableColumn prop="deptName" label="部门名称"></ElTableColumn>
      <ElTableColumn prop="deptInfo" label="部门描述"></ElTableColumn>
      <ElTableColumn label="信息最后修改时间">
        <template #default="scope">
          {{ Tools.formatDate(scope.row.lastupdate) }}
        </template>
      </ElTableColumn>
      <ElTableColumn label="操作">
        <template #default="scope">
          <ElButton @click="selected(scope.row)" type="primary">选中部门</ElButton>
        </template>
      </ElTableColumn>
    </ElTable>

    <PageComp :page="viewdata.page" @page-change="query"></PageComp>
  </div>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import ApiService from '../ts/ApiService'
import Tools from '../ts/Tools'
import { BaseListResult, PageInfo } from '../ts/BaseResult'
import { Dept } from '../ts/Manage'
import { ElButton, ElTable, ElTableColumn, ElForm, ElFormItem, ElInput } from 'element-plus'
import PageComp from './PageComp.vue'

const emits = defineEmits(['selected'])

const viewdata = reactive({
  page: new PageInfo(),
  queryInfo: {
    deptName: '',
  },
  list: new Array<Dept>(),
})

function selected(info: Dept) {
  emits('selected', Tools.concatJson(info))
}

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
