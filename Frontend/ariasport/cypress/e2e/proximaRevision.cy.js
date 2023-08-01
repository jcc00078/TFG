describe("Test proxima revision", () => {
  it("Mostrar proxima revision", () => {
    //Iniciamos sesión con credenciales correctas
    cy.login("111111", "isa");
    // Navegar a la página de consultar mantenimiento
    cy.visit("http://localhost:8080/#/mantenimiento");
    // Asegurarnos de que se muestran las motos del usuario
    cy.get('img[id="imagenMoto"]').should("be.visible");
    //Elegimos una moto y nos redirige a su historial de mantenimiento
    cy.visit("http://localhost:8080/#/mantenimiento/historial/111");
    //Selecciona el botón "Mostrar próxima revisión" en la página web, verifica si está habilitado y, si es así, hace clic en él.
    //Muestra un adelanto de la próxima revisión.
    //Nos aseguramos de que se cargue este adelanto en un tiempo aceptable para el usuario de como máximo dos segundos
    cy.get("#proxR").should("be.enabled", { timeout: 2000 }).click();
    cy.get("#iconoTabla").click();
    //Nos aseguramos de que se muestre la tabla de la próxima revisión en detalle
    cy.get("#tablaMarca").should("be.visible", { timeout: 2000 });
});
});
