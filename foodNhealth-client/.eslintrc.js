// http://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parser: 'babel-eslint',
  parserOptions: {
    sourceType: 'module'
  },
  env: {
    browser: true,
  },
  // https://github.com/feross/standard/blob/master/RULES.md#javascript-standard-style
  extends: 'standard',
  // required to lint *.vue files
  plugins: [
    'html'
  ],
  // add your custom rules here
  'rules': {
    // allow paren-less arrow functions
    'arrow-parens': 0,
    // allow async-await
    'generator-star-spacing': 0,
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0,
    // force semicolon usage (https://eslint.org/docs/2.0.0/rules/semi)
    // 'semi':[2, 'always'],
    //  Disallow Unreachable Code (https://eslint.org/docs/2.0.0/rules/no-unreachable)
    'no-unreachable': 2
  },
  globals: {
    '$': true,
    'jQuery': true
  }
}
