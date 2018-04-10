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
import FoodCategoryCoreTypes from '@/foodNhealth/FoodCategoryCoreTypes'
import FoodCategorySubTypes from '@/foodNhealth/FoodCategorySubTypes'
import Supermarkets from '@/foodNhealth/Supermarkets'
import Ingredients from '@/foodNhealth/Ingredients'
import Products from '@/foodNhealth/Products'
import Recipes from '@/foodNhealth/Recipes'
import Cuisines from '@/foodNhealth/Cuisines'
import RecipeCategories from '@/foodNhealth/RecipeCategories'
import SelectedRecipes from '@/foodNhealth/SelectedRecipes'
import Clients from '@/foodNhealth/Clients'

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
          path: 'clients',
          name: 'Clients',
          component: Clients
        },
        {
          path: 'ingredients',
          name: 'Ingredients',
          component: Ingredients
        },
        {
          path: 'products',
          name: 'Products',
          component: Products
        },
        {
          path: 'recipes',
          name: 'Recipes',
          component: Recipes
        },
        {
          path: 'selectedRecipes',
          name: 'SelectedRecipes',
          component: SelectedRecipes
        },
        {
          path: 'admin',
          redirect: '/admin/persons',
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
              path: 'supermarkets',
              name: 'Supermarkets',
              component: Supermarkets
            },
            {
              path: 'cuisines',
              name: 'Cuisines',
              component: Cuisines
            },
            {
              path: 'recipeCategories',
              name: 'RecipeCategories',
              component: RecipeCategories
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
