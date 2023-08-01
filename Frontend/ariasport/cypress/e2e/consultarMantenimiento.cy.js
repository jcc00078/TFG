Cypress.Commands.add("consultarMantenimiento", (username, password, shouldFail = false) => {
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

describe("Test consultar mantenimiento", () => {
    it("Seleccionar consultar mantenimiento", () => {
      cy.consultarMantenimiento("111111", "isa");
      // Navegar a la página de consultar mantenimiento
      cy.visit('http://localhost:8080/#/mantenimiento');  
      // Asegurarse de que se muestren las motos del usuario
      cy.get('img[id="imagenMoto"]').should('be.visible');
    });
  });
  