Cypress.Commands.add('registro', (username, nombre, apellidos, password, shouldFail = false) => {
  cy.visit('http://localhost:8080/#/registrarme') // Visitar la página de registro de usuario
  cy.get('#dni').type(username) // Ingresar el dni del usuario
  cy.get('#nombre').type(nombre) // Ingresar el nombre del usuario
  cy.get('#Apellidos').type(apellidos) // Ingresar los apellidos del usuario
  cy.get('#password').type(password) // Ingresar la contraseña del usuario
  cy.get('#registro-form').submit() // Enviar el formulario de registro de usuario
  if (shouldFail) {
    cy.get('[id="errorMensaje"]').should('be.visible');
    cy.url().should('contain', '/registrarme');

  } else {
    cy.url().should('not.include', '/registrarme');
  }
})
describe('Test registro', () => {
    it('Registro exitoso de usuario', () => {
      cy.registro("1111115", "Isabel", "Rodriguez", "isa");
    });
    it('Muestra mensaje de error cuando se proporciona datos de usuario ya registrado', () => {
      cy.registro('111111',  "Isabel","A R", "isa", true);
    });
  })