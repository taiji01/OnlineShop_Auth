import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../views/Layout.vue'
import Home from '../views/Home.vue'
import Cart from '../views/Cart.vue'
import Orders from '../views/Orders.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Layout,
      children: [
        {
          path: '',
          name: 'home',
          component: Home
        },
        {
          path: 'cart',
          name: 'cart',
          component: Cart
        },
        {
          path: 'orders',
          name: 'orders',
          component: Orders
        }
      ]
    }
  ]
})

export default router 