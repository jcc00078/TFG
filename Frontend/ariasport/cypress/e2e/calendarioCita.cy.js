
  Cypress.Commands.add("citas", (username, password, shouldFail = false) => {
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

describe("Test citas", () => {
  it("Carga exitosa de datos", () => {
    cy.citas("111111", "isa");
    cy.visit("http://localhost:8080/#/citas"); // Visitar el apartado de citas
    cy.url().should("include", "/citas");
    //cy.visit('http://localhost:8080/#/citas/diasDeshabilitados') // Visita la página que muestra las fechas deshabilitadas
    cy.request('GET', 'http://localhost:8080/citas/diasDeshabilitados'); // Hacer una petición GET a la API que devuelve los días deshabilitados
      


  });
});
