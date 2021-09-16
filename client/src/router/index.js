import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Write from '../views/Write.vue'
import Login from '../views/Login.vue'
import View from '../views/View.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path:"/write",
    component:Write
  },
  {
    path:"/login",
    component:Login
  },
  {
    path:"/view/:id",
    component:View
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
