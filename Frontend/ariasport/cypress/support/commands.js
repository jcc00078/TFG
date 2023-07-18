// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

Cypress.Commands.add("login", (username, password, shouldFail = false) => {
  cy.visit("http://localhost:8080/#/inicioSesion"); // Visitar la página de inicio de sesión
  cy.get("#dni").type(username); // Ingresar el nombre de usuario
  cy.get("#password").type(password); // Ingresar la contraseña
  cy.get("#login-form").submit(); // Enviar el formulario de inicio de sesión
  if (shouldFail) {
    cy.get('[id="errorMensaje"]').should("be.visible");
    cy.url().should("contain", "/inicioSesion");
  } else {
    cy.url().should("not.include", "/inicioSesion");
  }
});
