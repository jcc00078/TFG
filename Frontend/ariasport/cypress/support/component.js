// ***********************************************************
// This example support/component.js is processed and
// loaded automatically before your test files.
//
// This is a great place to put global configuration and
// behavior that modifies Cypress.
//
// You can change the location of this file or turn off
// automatically serving support files with the
// 'supportFile' configuration option.
//
// You can read more here:
// https://on.cypress.io/configuration
// ***********************************************************

// Import commands.js using ES2015 syntax:
import './commands'

// Alternatively you can use CommonJS syntax:
// require('./commands')
import { Cypress } from 'cypress';
import { createApp } from 'vue';
import App from '@/App.vue';
import { mount } from 'cypress/vue'
import { createPinia } from 'pinia'
 

Cypress.Commands.add('mount', mount)







  
