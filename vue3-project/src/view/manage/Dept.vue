<template>
  <div>
    {{ title }}
  </div>

  <div>
    <ElForm :inline="true">
      <ElFormItem>
        <ElInput v-model="queryInfo.deptName" placeholder="部门名称模糊查询"></ElInput>
      </ElFormItem>
      <ElFormItem>
        <ElButton v-loading="loading" @click="requery" type="primary">查询</ElButton>
        <ElButton v-loading="loading" @click="reset" type="danger">重置</ElButton>
      </ElFormItem>
    </ElForm>
  </div>

  <div v-loading="loading">
    <ElTable :data="list" stripe border>
      <ElTableColumn prop="deptName" label="部门名称"></ElTableColumn>
      <ElTableColumn prop="deptInfo" label="部门描述"></ElTableColumn>
      <ElTableColumn label="信息最后修改时间">
        <template #default="scope">
          {{ Tools.formatDate(scope.row.lastupdate) }}
        </template>
      </ElTableColumn>
      <ElTableColumn label="操作">
        <template #default="scope">
          <ElButton type="primary">修改</ElButton>
          <ElButton type="danger">删除</ElButton>
        </template>
      </ElTableColumn>
    </ElTable>

    <ElPagination :total="page.total" v-model:page-size="page.pageSize" v-model:current-page="page.pageNumber" @current-change="query" @size-change="requery" :page-sizes="[3, 5, 10, 20]" layout="prev,pager,next,total,sizes"></ElPagination>
  </div>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import ApiService from '../../ts/ApiService'
import Tools from '../../ts/Tools'
import { BaseListResult, PageInfo } from '../../ts/BaseResult'
import { Dept } from '../../ts/Manage'
import { ElButton, ElMessageBox, ElPagination, ElTable, ElTableColumn } from 'element-plus'

const title = ref('部门管理')

//#region 查询的部分
const loading = ref(false)
const page = ref(new PageInfo())
const list = ref(new Array<Dept>())
const queryInfo = ref({
  deptName: '',
})

function reset() {
  queryInfo.value = {
    deptName: '',
  }
  // queryInfo.value.deptName = ''
  requery()
}

function requery() {
  page.value.pageNumber = 1
  query()
}

function query() {
  loading.value = true
  ApiService.post('/manage/dept/queryAll', Tools.concatJson(page.value, queryInfo.value), (data: BaseListResult<Dept>) => {
    loading.value = false
    if (data.success) {
      page.value = data.page
      list.value = data.list
      return
    }
    ElMessageBox.alert(data.message, '部门管理')
  })
}

query()
//#endregion
</script>
<style></style>
