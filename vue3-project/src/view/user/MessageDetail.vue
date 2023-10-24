<template>
  <div class="info">
    <ElCard>
      <template #header>
        <div>
          <div>
            <ElAvatar v-if="info.userInfo && info.userInfo.img" :src="info.userInfo.img"></ElAvatar>
            {{ info.user?.username }}
            -
            {{ Tools.formatDate(info.lastupdate) }}
          </div>
          <div>{{ info.title }}</div>
        </div>
      </template>

      <div>
        <pre v-html="info.info"></pre>
      </div>
    </ElCard>
  </div>
</template>

<script lang="ts" setup>
import { ElCard, ElAvatar } from 'element-plus'
import { ApiService } from '../../ts/ApiService'
import { Tools } from '../../ts/Tools'
import { PageInfo } from '../../ts/BaseResult'
import { ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const umid = route.params.id
const info = ref({})
const list = ref([])
const page = ref(new PageInfo())

function query() {
  ApiService.post(
    '/message/queryDetail',
    {
      umid: umid,
    },
    (data: any) => {
      if (data.success) {
        info.value = data.info
        page.value = data.page
        list.value = data.list
      }
    },
  )
}

query()
</script>

<style lang="scss" scoped>
.info {
  width: 90vw;
  margin: 1rem auto;
}
</style>
