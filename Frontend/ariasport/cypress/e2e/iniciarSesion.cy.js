describe("Test login", () => {
  it("Carga exitosa de credenciales", () => {
    cy.login("111111", "isa");
  });
  it("Muestra mensaje de error cuando se proporcionan credenciales incorrectas", () => {
    cy.login("111111", "contraseña_incorrecta", true);
  });
});
