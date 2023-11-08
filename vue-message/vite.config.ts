import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import basicSsl from '@vitejs/plugin-basic-ssl'

// https://vitejs.deqv/config/
export default defineConfig({
  server: {
    // https: true,
  },
  //basicSsl()
  plugins: [vue()],
})
