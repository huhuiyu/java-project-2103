<template>
  <div>
    <ElPagination :total="page.total" 
    v-model:page-size="page.pageSize" 
    v-model:current-page="page.pageNumber" 
    @current-change="pageChange" @size-change="sizeChange" 
    :page-sizes="[3, 5, 10, 20]" 
    layout="prev,pager,next,total,sizes"></ElPagination>
  </div>
</template>
<script lang="ts" setup>
import { ElPagination } from 'element-plus'
import { PageInfo } from '../ts/BaseResult'

const emits = defineEmits(['page-change', 'number-change', 'size-change'])

const props = defineProps({
  page: {
    type: PageInfo,
    default: new PageInfo(),
  },
})

function pageChange() {
  emits('page-change', props.page)
  emits('number-change', props.page)
}

function sizeChange() {
  emits('page-change', props.page)
  emits('size-change', props.page)
}
</script>
<style></style>
