Cypress.Commands.add('login', (username, password, shouldFail = false) => {
  cy.visit('http://localhost:8080/#/inicioSesion') // Visitar la página de inicio de sesión
  cy.get('#dni').type(username) // Ingresar el nombre de usuario
  cy.get('#password').type(password) // Ingresar la contraseña
  cy.get('#login-form').submit() // Enviar el formulario de inicio de sesión
  if (shouldFail) {
    cy.get('[id="errorMensaje"]').should('be.visible');
    cy.url().should('contain', '/inicioSesion');

  } else {
    cy.url().should('not.include', '/inicioSesion');
  }
})

describe('Test login', () => {
  it('Carga exitosa de credenciales', () => {
    cy.login("111111", "isa")
  });
  it('Muestra mensaje de error cuando se proporcionan credenciales incorrectas', () => {
    cy.login('111111', 'contraseña_incorrecta', true);
  });
})