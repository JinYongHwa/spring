import Vue from 'vue'

import App from './App.vue'
import router from './router'
import axios from "axios"
import vuetify from '@/plugins/vuetify'

axios.defaults.withCredentials  = true
Vue.prototype.$axios=axios
window.axios=axios

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
