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
        <ElButton @click="avisible = true" type="warning">添加</ElButton>
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
          <ElButton @click="showModify(scope.row)" type="primary">修改</ElButton>
          <ElButton @click="del(scope.row)" type="danger">删除</ElButton>
        </template>
      </ElTableColumn>
    </ElTable>

    <ElPagination :total="page.total" v-model:page-size="page.pageSize" v-model:current-page="page.pageNumber" @current-change="query" @size-change="requery" :page-sizes="[3, 5, 10, 20]" layout="prev,pager,next,total,sizes"></ElPagination>
    <hr />

    <PageComp :page="page" @page-change="query" @size-change="sizeChange"></PageComp>
  </div>

  <!-- 添加的对话框 -->
  <ElDialog v-model="avisible" title="添加部门信息" width="30%" :close-on-click-modal="false" @closed="requery">
    <div>
      <ElForm v-loading="adding">
        <ElFormItem>
          <ElInput v-model="addInfo.deptName"></ElInput>
        </ElFormItem>

        <ElFormItem>
          <ElInput v-model="addInfo.deptInfo"></ElInput>
        </ElFormItem>
      </ElForm>
    </div>

    <template #footer>
      <div>
        <ElButton @click="add" v-loading="adding" type="primary">添加</ElButton>
        <ElButton @click="avisible = false" type="danger">关闭</ElButton>
      </div>
    </template>
  </ElDialog>

  <!-- 修改的对话框 -->
  <ElDialog v-model="mvisible" title="修改部门信息" width="30%" :close-on-click-modal="false" @closed="requery">
    <div>
      <ElForm v-loading="modifying">
        <ElFormItem>
          <ElInput v-model="modifyInfo.deptName"></ElInput>
        </ElFormItem>

        <ElFormItem>
          <ElInput v-model="modifyInfo.deptInfo"></ElInput>
        </ElFormItem>
      </ElForm>
    </div>

    <template #footer>
      <div>
        <ElButton @click="modify" v-loading="modifying" type="primary">保存</ElButton>
        <ElButton @click="mvisible = false" type="danger">关闭</ElButton>
      </div>
    </template>
  </ElDialog>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import ApiService from '../../ts/ApiService'
import Tools from '../../ts/Tools'
import { BaseResult, BaseListResult, PageInfo } from '../../ts/BaseResult'
import { Dept } from '../../ts/Manage'
import { ElDialog, ElButton, ElMessageBox, ElPagination, ElTable, ElTableColumn, ElForm, ElFormItem, ElInput } from 'element-plus'
import PageComp from '../../components/PageComp.vue'

const title = ref('部门管理')

//#region 查询的部分
const loading = ref(false)
const page = ref(new PageInfo())
const list = ref(new Array<Dept>())
const queryInfo = ref({
  deptName: '',
})

function sizeChange(page: PageInfo) {
  console.log('分页组件传递的信息：', page)
}

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
  })
}

query()
//#endregion

//#region 添加的部分
const addInfo = ref(new Dept())
const adding = ref(false)
const avisible = ref(false)

function add() {
  adding.value = true
  ApiService.post('/manage/dept/add', addInfo.value, (data: BaseResult) => {
    adding.value = false
    if (data.success) {
      ElMessageBox.alert(data.message, '部门添加')
    }
  })
}

//#endregion

//#region 删除的部分

function del(info: Dept) {
  ElMessageBox.confirm(`是否删除：${info.deptName}？`)
    .then(() => {
      ApiService.post(
        '/manage/dept/delete',
        {
          deptId: info.deptId,
        },
        (data: BaseResult) => {
          if (data.success) {
            ElMessageBox.alert(data.message)
            requery()
          }
        },
      )
    })
    .catch(() => {})
}

//#endregion

//#region 修改的部分
const modifyInfo = ref(new Dept())
const modifying = ref(false)
const mvisible = ref(false)

function showModify(info: Dept) {
  modifyInfo.value = Tools.concatJson(info)
  mvisible.value = true
}

function modify() {
  modifying.value = true
  ApiService.post('/manage/dept/update', modifyInfo.value, (data: BaseResult) => {
    modifying.value = false
    if (data.success) {
      ElMessageBox.alert(data.message)
    }
  })
}

//#endregion
</script>
<style></style>
