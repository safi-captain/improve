import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'hello',
      component: () => import('components/Hello')
    },
    {
      path: '/index',
      name: 'welcomeIndex',
      component: () => import('components/welcome/WelcomeIndex')
    },
    {
      path: '/user/:userId',
      name: 'userIndex',
      component: () => import('components/welcome/UserIndex'),
      children: [{
        path: 'info',
        component: () => import('components/welcome/UserInfo')
      }]
    },
    {
      path: '/index-member',
      component: () => import('components/welcome/MemberIndex')
    },
    {
      path: '/index-member-detail',
      components: {
        default: () => import('components/welcome/MemberInfo'),
        memberInfo: () => import('components/welcome/UserInfo')
      }
    }
  ]
})
