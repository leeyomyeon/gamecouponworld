import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {
      path: '/testone',
      component: () => import('@/views/TestOne.vue'),
    },
    {
      path: '/testtwo',
      component: () => import('@/views/TestTwo.vue'),
    },
    {
      path: '/board/list',
      component: () => import('@/components/board/List.vue')
    },
  ],
})

export default router
