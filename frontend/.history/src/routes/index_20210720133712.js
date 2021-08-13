import Vue from 'vue'
import VueRouter from 'vue-router'
import List from '@/components/board/List'

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
      name: List,
      component: List,
    },
  ],
})

export default router
