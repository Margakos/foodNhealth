import Vue from 'vue'
import Router from 'vue-router'

// Containers
import Full from '@/containers/Full'

// Views
import Dashboard from '@/views/Dashboard'
import Charts from '@/views/Charts'
import Widgets from '@/views/Widgets'

// Views - Components
import Buttons from '@/views/components/Buttons'
import SocialButtons from '@/views/components/SocialButtons'
import Cards from '@/views/components/Cards'
import Forms from '@/views/components/Forms'
import Modals from '@/views/components/Modals'
import Switches from '@/views/components/Switches'
import Tables from '@/views/components/Tables'

// Views - Icons
import FontAwesome from '@/views/icons/FontAwesome'
import SimpleLineIcons from '@/views/icons/SimpleLineIcons'

// Views - Pages
import Page404 from '@/views/pages/Page404'
import Page500 from '@/views/pages/Page500'
import Register from '@/views/pages/Register'

// Views - FoodNHealth
import Persons from '@/foodNhealth/Persons'
import Login from '@/foodNhealth/Login'
import Ingredients from '@/foodNhealth/Ingredients'
import FoodCategoryCoreTypes from '@/foodNhealth/FoodCategoryCoreTypes'
import FoodCategorySubTypes from '@/foodNhealth/FoodCategorySubTypes'
import MeatCategoryTypes from '@/foodNhealth/MeatCategoryTypes'
import ProximateTypes from '@/foodNhealth/ProximateTypes'
import MineralTypes from '@/foodNhealth/MineralTypes'
import VitaminTypes from '@/foodNhealth/VitaminTypes'
import LipidTypes from '@/foodNhealth/LipidTypes'
import OtherNutrientTypes from '@/foodNhealth/OtherNutrientTypes'

Vue.use(Router)

export default new Router({
  mode: 'hash', // Demo is living in GitHub.io, so required!
  linkActiveClass: 'open active',
  scrollBehavior: () => ({ y: 0 }),
  routes: [
    {
      path: '/',
      redirect: '/pages/login',
      name: 'Food-N-Health',
      component: Full,
      children: [
        {
          path: 'dashboard',
          name: 'Dashboard',
          component: Dashboard
        },
        {
          path: 'charts',
          name: 'Charts',
          component: Charts
        },
        {
          path: 'widgets',
          name: 'Widgets',
          component: Widgets
        },
        {
          path: 'ingredients',
          name: 'Ingredients',
          component: Ingredients
        },
        {
          path: 'admin',
          redirect: '/admin/foodCategoryCoreTypes',
          name: 'Διαχείριση',
          component: {
            render (c) {
              return c('router-view')
            }
            // render (c) { return c('keep-alive', [c('router-view')]) }
          },
          children: [
            {
              path: 'persons',
              name: 'Persons',
              component: Persons
            },
            {
              path: 'foodCategoryCoreTypes',
              name: 'FoodCategoryCoreTypes',
              component: FoodCategoryCoreTypes
            },
            {
              path: 'foodCategorySubTypes',
              name: 'FoodCategorySubTypes',
              component: FoodCategorySubTypes
            },
            {
              path: 'meatCategoryTypes',
              name: 'MeatCategoryTypes',
              component: MeatCategoryTypes
            },
            {
              path: 'proximateTypes',
              name: 'ProximateTypes',
              component: ProximateTypes
            },
            {
              path: 'mineralTypes',
              name: 'MineralTypes',
              component: MineralTypes
            },
            {
              path: 'vitaminTypes',
              name: 'VitaminTypes',
              component: VitaminTypes
            },
            {
              path: 'lipidTypes',
              name: 'LipidTypes',
              component: LipidTypes
            },
            {
              path: 'otherNutrientTypes',
              name: 'OtherNutrientTypes',
              component: OtherNutrientTypes
            }
          ]
        },
        {
          path: 'components',
          redirect: '/components/buttons',
          name: 'Components',
          component: {
            render (c) { return c('router-view') }
          },
          children: [
            {
              path: 'buttons',
              name: 'Buttons',
              component: Buttons
            },
            {
              path: 'social-buttons',
              name: 'Social Buttons',
              component: SocialButtons
            },
            {
              path: 'cards',
              name: 'Cards',
              component: Cards
            },
            {
              path: 'forms',
              name: 'Forms',
              component: Forms
            },
            {
              path: 'modals',
              name: 'Modals',
              component: Modals
            },
            {
              path: 'switches',
              name: 'Switches',
              component: Switches
            },
            {
              path: 'tables',
              name: 'Tables',
              component: Tables
            }
          ]
        },
        {
          path: 'icons',
          redirect: '/icons/font-awesome',
          name: 'Icons',
          component: {
            render (c) { return c('router-view') }
          },
          children: [
            {
              path: 'font-awesome',
              name: 'Font Awesome',
              component: FontAwesome
            },
            {
              path: 'simple-line-icons',
              name: 'Simple Line Icons',
              component: SimpleLineIcons
            }
          ]
        }
      ]
    },
    {
      path: '/pages',
      redirect: '/pages/p404',
      name: 'Pages',
      component: {
        render (c) { return c('router-view') }
      },
      children: [
        {
          path: '404',
          name: 'Page404',
          component: Page404
        },
        {
          path: '500',
          name: 'Page500',
          component: Page500
        },
        {
          path: 'login',
          name: 'Login',
          component: Login
        },
        {
          path: 'register',
          name: 'Register',
          component: Register
        },
        {
          path: 'error/:code',
          name: 'Error',
          component: Error
        }
      ]
    }
  ]
})
