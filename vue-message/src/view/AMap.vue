<template>
  <!-- 上中下布局，中间是地图 -->
  <div class="main-box">
    <div> 高德地图 </div>

    <div>
      <div id="map-container"></div>
    </div>

    <div> 地图信息：{{ error_info }} </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref, onUnmounted } from 'vue'
import AMapLoader from '@amap/amap-jsapi-loader'

// 记住地图对象
let map: any = null
// 动态加载的AMap对象
let amap: any = null
// 定位对象
let geo: any = null
// 中间标记
let marker: any = null

// 错误信息
let error_info = ref('')

// 发起定位
const startGeolocation = () => {
  geo.getCurrentPosition((s: any, r: any) => {
    console.log('定位结果', s, r)
    if ('complete' != s) {
      error_info.value = '定位失败'
      return
    }
    // 定位地图中心点
    map.setCenter(r.position)
    // 标记中心点
    marker = new amap.Marker({
      position: r.position,
    })
    map.add(marker)
  })
}

// 初始化地图
const initMap = () => {
  AMapLoader.load({
    key: '91b7beeea397d66f5cc8b54b4366a04c',
    version: '2.0',
    plugins: ['AMap.Marker', 'AMap.Geolocation'],
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
        timeout: 10000,
        offset: [10, 20],
        zoomToAccuracy: true,
        position: 'RB',
      })
      // 启动定位
      startGeolocation()
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
</style>
