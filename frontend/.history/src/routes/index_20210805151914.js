import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store/index'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/main',
    },
    {
      path: '/account/signup',
      component: () => import('@/views/SignupPage.vue'),
    },
    {
      path: '/account/login',
      component: () => import('@/views/LoginPage.vue'),
    },
    {
      path: '/account/edit',
      meta: { auth: true },
      component: () => import('@/views/UserEdit.vue'),
    },
    {
      path: '/board',
      component: () => import('@/views/board/BoardPage.vue'),
    },
    {
      path: '/board/create',
      component: () => import('@/components/board/CreateForm.vue'),
    },
    {
      name: 'boardDetail',
      path: '/board/:id',
      props: true,
      component: () => import('@/views/board/DetailPage.vue'),
    },
    {
      path: '/board/edit/:id',
      meta: { auth: true },
      component: () => import('@/components/board/editForm.vue'),
    },
    {
      path: '/main',
      name: 'Main',
      component: () => import('@/views/MainPage.vue'),
      meta: { auth: true },
    },
    {
      path: '/user/profile/:userID',
      component: () => import('@/views/Profile.vue'),
    },
    {
      path: '/user/followList',
      component: () => import('@/components/user/followList.vue'),
    },
    {
      path: '/user/requestFollowList',
      component: () => import('@/components/user/requestFollowList.vue'),
    },
    {
      path: '/user/confirmEmail',
      component: () => import('@/components/user/confirmEmail.vue'),
    },
    {
      path: '/feed',
      component: () => import('@/views/feed/FeedPage.vue'),
    },
    {
      path: '/feed/create',
      meta: { auth: true },
      component: () => import('@/views/feed/CreatePage.vue'),
    },
    {
      path: '/feed/:id/update',
      component: () => import('@/views/feed/UpdatePage.vue'),
    },
    {
      path: '/feed/:id',
      component: () => import('@/views/feed/DetailPage.vue'),
    },
  ],
})

router.beforeEach((to, from, next) => {
  if (to.meta.auth && !store.getters.userID) {
    next('/account/login')
    return
  } else {
    next()
  }
})

export default router
