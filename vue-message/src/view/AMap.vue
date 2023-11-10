<template>
  <!-- 上中下布局，中间是地图 -->
  <div class="main-box">
    <div> 高德地图：<ElButton @click="openMap" type="primary">地图调起</ElButton> </div>

    <div>
      <div id="map-container"></div>
    </div>

    <div> 地图信息：{{ error_info }} </div>
  </div>

  <!-- 搜索的部分 -->
  <div class="search">
    <ElCard>
      <template #header>
        <div style="display: flex">
          <ElInput v-model="sinfo"></ElInput>
          <ElButton @click="search" type="primary">搜索</ElButton>
        </div>
      </template>
      <div id="divSearchInfo"> 请输入信息。。。 </div>
    </ElCard>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref, onUnmounted } from 'vue'
import AMapLoader from '@amap/amap-jsapi-loader'
import { ElButton, ElCard, ElInput } from 'element-plus'

// 记住地图对象
let map: any = null
// 动态加载的AMap对象
let amap: any = null
// 定位对象
let geo: any = null
// 地理编码
let getcoder: any = null
// 中间标记
let marker: any = null
// 初始的定位点
let start_position: any = null
let placeSearch: any = null
// 搜索的输入
let sinfo = ref('')
// 错误信息
let error_info = ref('')

const base_map_url = 'https://uri.amap.com/navigation'

// 地图调起
const openMap = () => {
  let url = `${base_map_url}?from=${start_position.lng},${start_position.lat}&to=${map.getCenter().lng},${map.getCenter().lat}&mode=walk`
  window.open(url)
}

// 搜索功能
const search = () => {
  if (sinfo.value.trim() == '') {
    return
  }
  placeSearch.search(sinfo.value)
}

// 发起定位
const startGeolocation = () => {
  geo.getCurrentPosition((s: any, r: any) => {
    console.log('定位结果', s, r)
    if ('complete' != s) {
      error_info.value = '定位失败'
      return
    }
    // 记住初始定位
    if (start_position == null) {
      start_position = r.position
    }
    // 定位地图中心点
    map.setCenter(r.position)
    // 标记中心点
    marker = new amap.Marker({
      position: r.position,
    })
    map.add(marker)
    // 监听移动地图
    map.on('moveend', (ev: any) => {
      console.log('移动地图结束', ev)
      // 移动标记到地图的中心
      marker.setPosition(map.getCenter())
    })
    map.on('mapmove', () => {
      // 移动标记到地图的中心
      marker.setPosition(map.getCenter())
    })
    // 地理编码
    getcoder = new amap.Geocoder({
      city: '010',
    })
    getcoder.getAddress(r.position, (cs: any, rs: any) => {
      console.log('地理编码结果', cs, rs)
      if (rs.info != 'OK' || cs != 'complete') {
        error_info.value = '地理编码失败'
        return
      }
      placeSearch = new amap.PlaceSearch({
        city: rs.regeocode.addressComponent.adcode,
        pageSize: 5,
        pageIndex: 1,
        citylimit: true,
        map: map,
        panel: 'divSearchInfo',
        autoFitView: true,
      })
    })
    // 定位作为组件添加到地图上
    map.addControl(geo)
  })
}

// 初始化地图
const initMap = () => {
  AMapLoader.load({
    key: '91b7beeea397d66f5cc8b54b4366a04c',
    version: '2.0',
    plugins: ['AMap.PlaceSearch', 'AMap.Scale', 'AMap.ToolBar', 'AMap.Geocoder', 'AMap.Marker', 'AMap.Geolocation'],
  })
    .then((AMap) => {
      // 记录AMap对象
      amap = AMap
      // 初始化地图对象
      map = new AMap.Map('map-container', {
        zoom: 16,
      })
      // 初始化定位对象
      geo = new AMap.Geolocation({
        enableHighAccuracy: true,
        timeout: 20000,
        offset: [10, 20],
        zoomToAccuracy: true,
        position: 'RB',
      })
      // 启动定位
      startGeolocation()
      // 工具栏
      map.addControl(
        new AMap.ToolBar({
          position: 'RT',
        }),
      )
      // 比例尺
      map.addControl(
        new AMap.Scale({
          position: 'LT',
        }),
      )
    })
    .catch((e) => {
      error_info.value = '地图api加载失败'
      console.error('地图api加载失败', e)
    })
}

onMounted(() => {
  initMap()
})

onUnmounted(() => {
  map?.destroy()
})
</script>

<style lang="scss" scoped>
#map-container {
  width: 100%;
  height: 100%;
}

.main-box {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  > div:nth-of-type(2) {
    flex: 10;
  }
  > div:nth-of-type(1),
  > div:nth-of-type(3) {
    padding: 1rem;
  }
}

.search {
  position: fixed;
  top: 1rem;
  right: 1rem;
}
</style>
