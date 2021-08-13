import Vue from 'vue'
import Vuex from 'vuex'
import {
  getAuthFromCookie,
  getUserFromCookie,
  saveAuthToCookie,
  saveUserToCookie,
  // saveAuthToCookie,
  // saveUserToCookie,
} from '@/utils/cookies'
import { getUserUid } from '@/api/main'
Vue.use(Vuex)

import { loginUser, fetchUser } from '@/api/auth'

export default new Vuex.Store({
  state: {
    userID: getUserFromCookie() || '',
    token: getAuthFromCookie() || '',
    user: {},
    nowCommentList: [],
    client: null,
  },
  getters: {
    user(state) {
      console.log('asdas')
      console.log(state.user)
      return state.user
    },
    getCommentList(state) {
      return state.nowCommentList
    },
    userID(state) {
      return state.userID
    },
    client(state) {
      return state.client
    },
  },
  mutations: {
    setUserid(state, userID) {
      state.userID = userID
    },
    setToken(state, token) {
      state.token = token
    },
    clearUserid(state) {
      state.userID = ''
    },
    clearToken(state) {
      state.token = ''
    },
    setUser(state, payload) {
      console.log('여기는 setuser')
      console.log(payload)
      state.user = payload
    },
    CREATE_COMMENT(state, newComment) {
      state.nowCommentList.push(newComment)
    },
    // 2번
    // 연결된거 vuex에 저장
    setClient(state, payload) {
      state.client = payload
    },
  },
  actions: {
    async LOGIN({ commit }, userData) {
      const { data } = await loginUser(userData)
      if (data.message === 'fail') {
        return data
      }
      // state에 저장
      commit('setToken', data['access-token'])
      commit('setUserid', userData.userID)
      // 쿠키에 넣기
      saveAuthToCookie(data['access-token'])
      saveUserToCookie(userData.userID)
      return data
    },
    async getGameUid(context, userData) {
      const { data } = await getUserUid(userData)
      return data
    },
    // async getUser({ commit }, userID) {
    //   const { data } = await fetchUser(userID)

    //   commit('setUser', data.userInfo)
    // },
    async getUser(context, payload) {
      const { data } = await fetchUser(payload)
      context.commit('setUser', data.userInfo)
    },
    // 1번
    getClient(context, payload) {
      context.commit('setClient', payload)
    },
  },
})
