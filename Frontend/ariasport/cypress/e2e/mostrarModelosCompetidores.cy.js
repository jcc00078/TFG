Cypress.Commands.add('competidores', () => {
    cy.visit('http://localhost:8080/#/comparador'); // Visitar la página del comparador
    cy.get('select[id="selectComparador"]').eq(0).select(4);//Seleccionar la cuarta marca de la primera carta del comparador
    cy.get('select[id="selectComparador2"]').eq(0).select(1);//Seleccionar el primer modelo de la primera carta del comparador
    cy.get('select[id="selectComparador"]').eq(1).select(3);//Seleccionar la tercera marca de la segunda carta del comparador
    cy.get('select[id="selectComparador2"]').eq(1).select(1);//Seleccionar el primer modelo de la segunda carta del comparador
    cy.get('a[id="competidores"]').eq(0).click(); // Hace clic en el enlace que muestra los competidores de la primera moto
    //cy.get('a[id="competidores"]').eq(1).click(); // Hace clic en el enlace que muestra los competidores de la segunda moto
});
  
  describe('Test mostrar competidores motocicleta', () => {
    it('Mostrar competidores con éxito', () => {
      cy.competidores();
    });
  });