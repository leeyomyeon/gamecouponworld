import Vue from 'vue'
import App from './App.vue'
import router from '@/routes/index'
import store from '@/store/index'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// import firebase from 'firebase/app'
// import 'firebase/messaging'

Vue.use(BootstrapVue)

Vue.config.productionTip = false

// For Firebase JS SDK v7.20.0 and later, measurementId is optional
// const firebaseConfig = {
//   apiKey: 'AIzaSyDAPFf7oJHDvGkizxFNudtGYxvVxuNZvk4',
//   authDomain: 'snappy-storm-313017.firebaseapp.com',
//   projectId: 'snappy-storm-313017',
//   storageBucket: 'snappy-storm-313017.appspot.com',
//   messagingSenderId: '805865102836',
//   appId: '1:805865102836:web:5b8e21bd842c84598d2da1',
//   measurementId: 'G-E7JYBZLBVG',
// }
// firebase.initializeApp(firebaseConfig)

// const messaging = firebase.messaging()

// messaging.usePublicVapidKey(
//   'BNSRnytfWL9ldigoRXHRW7XMDXRXSomNnt5huvK6vnn3KyZi94oKqzO-xzF-vH7a04zgqSahrrvYmAZi-jdOdOc',
// )

// // 알림 수신을 위한 사용자 권한 요청
// Notification.requestPermission().then(permission => {
//   console.log('permission ', permission)
//   if (permission !== 'granted') {
//     alert('알림을 허용해주세요')
//   }
// })

// // TODO: Send token to server for send notification
// messaging.getToken().then(console.log)

// // Handle received push notification at foreground
// messaging.onMessage(payload => {
//   console.log(payload)
//   alert(payload.data.message)
// })

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
