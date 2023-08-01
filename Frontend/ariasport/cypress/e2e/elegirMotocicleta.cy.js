Cypress.Commands.add('elegirMoto', () => {
    cy.visit('http://localhost:8080/#/comparador'); // Visitar la página del comparador
    cy.get('select[id="selectComparador"]').eq(0).select(4);//Seleccionar la cuarta marca de la primera carta del comparador
    cy.get('select[id="selectComparador2"]').eq(0).select(1);//Seleccionar el primer modelo de la primera carta del comparador
});
  
  describe('Test elegir motocicleta', () => {
    it('Elección de motocicleta exitosa', () => {
      cy.elegirMoto();
    });
  });
  