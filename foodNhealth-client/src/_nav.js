export default {
  items: [
    // {
    //   name: 'Αρχική',
    //   url: '/dashboard',
    //   icon: 'icon-speedometer'
    // },
    {
      name: 'Άτομα',
      url: '/clients',
      icon: 'icon-puzzle'
    },
    {
      name: 'Συστατικά',
      url: '/ingredients',
      icon: 'icon-puzzle'
    },
    {
      name: 'Προϊόντα',
      url: '/products',
      icon: 'icon-puzzle'
    },
    {
      name: 'Συνταγές',
      url: '/recipes',
      icon: 'icon-puzzle'
    },
    {
      name: 'Συνταγές προς Ενδιαφερόμενο',
      url: '/selectedRecipes',
      icon: 'icon-puzzle'
    },
    {
      name: 'Διαχείριση',
      url: '/admin',
      icon: 'fa fa-wrench',
      children: [
        {
          name: 'Διατροφολόγοι',
          url: '/admin/nutritionists',
          icon: 'icon-puzzle'
        },
        {
          name: 'Πελάτες',
          url: '/admin/clients',
          icon: 'icon-puzzle'
        },
        {
          name: 'Αλλεργίες',
          url: '/admin/allergies',
          icon: 'icon-puzzle'
        },
        {
          name: 'Κύριες Κατηγορίες Συστατικών',
          url: '/admin/foodCategoryCoreTypes',
          icon: 'icon-puzzle'
        },
        {
          name: 'Υποκατηγορίες Συστατικών',
          url: '/admin/foodCategorySubTypes',
          icon: 'icon-puzzle'
        },
        {
          name: 'Supermarkets',
          url: '/admin/supermarkets',
          icon: 'icon-puzzle'
        },
        {
          name: 'Κουζίνες',
          url: '/admin/cuisines',
          icon: 'icon-puzzle'
        },
        {
          name: 'Κατηγορίες Συνταγών',
          url: '/admin/recipeCategories',
          icon: 'icon-puzzle'
        }
      ]
    }
    // ,
    // {
    //   title: true,
    //   name: 'UI elements',
    //   class: '',
    //   wrapper: {
    //     element: '',
    //     attributes: {}
    //   }
    // },
    // {
    //   name: 'Components',
    //   url: '/components',
    //   icon: 'icon-puzzle',
    //   children: [
    //     {
    //       name: 'Buttons',
    //       url: '/components/buttons',
    //       icon: 'icon-puzzle'
    //     },
    //     {
    //       name: 'Social Buttons',
    //       url: '/components/social-buttons',
    //       icon: 'icon-puzzle'
    //     },
    //     {
    //       name: 'Cards',
    //       url: '/components/cards',
    //       icon: 'icon-puzzle'
    //     },
    //     {
    //       name: 'Forms',
    //       url: '/components/forms',
    //       icon: 'icon-puzzle'
    //     },
    //     {
    //       name: 'Modals',
    //       url: '/components/modals',
    //       icon: 'icon-puzzle'
    //     },
    //     {
    //       name: 'Switches',
    //       url: '/components/switches',
    //       icon: 'icon-puzzle'
    //     },
    //     {
    //       name: 'Tables',
    //       url: '/components/tables',
    //       icon: 'icon-puzzle'
    //     }
    //   ]
    // },
    // {
    //   name: 'Icons',
    //   url: '/icons',
    //   icon: 'icon-star',
    //   children: [
    //     {
    //       name: 'Font Awesome',
    //       url: '/icons/font-awesome',
    //       icon: 'icon-star',
    //       badge: {
    //         variant: 'secondary',
    //         text: '4.7'
    //       }
    //     },
    //     {
    //       name: 'Simple Line Icons',
    //       url: '/icons/simple-line-icons',
    //       icon: 'icon-star'
    //     }
    //   ]
    // },
    // {
    //   name: 'Widgets',
    //   url: '/widgets',
    //   icon: 'icon-calculator',
    //   badge: {
    //     variant: 'primary',
    //     text: 'NEW'
    //   }
    // },
    // {
    //   name: 'Charts',
    //   url: '/charts',
    //   icon: 'icon-pie-chart'
    // },
    // {
    //   divider: true
    // },
    // {
    //   title: true,
    //   name: 'Extras'
    // },
    // {
    //   name: 'Pages',
    //   url: '/pages',
    //   icon: 'icon-star',
    //   children: [
    //     {
    //       name: 'Login',
    //       url: '/pages/login',
    //       icon: 'icon-star'
    //     },
    //     {
    //       name: 'Register',
    //       url: '/pages/register',
    //       icon: 'icon-star'
    //     },
    //     {
    //       name: 'Error 404',
    //       url: '/pages/404',
    //       icon: 'icon-star'
    //     },
    //     {
    //       name: 'Error 500',
    //       url: '/pages/500',
    //       icon: 'icon-star'
    //     }
    //   ]
    // }
  ]
}
