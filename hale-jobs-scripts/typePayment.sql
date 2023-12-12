CREATE TABLE type_of_payment(
   id    INTEGER  NOT NULL PRIMARY KEY 
  ,clave INTEGER  NOT NULL
  ,valor VARCHAR(12) NOT NULL
);

INSERT INTO type_of_payment(id,clave,valor) VALUES (1,1,'Nómina');
INSERT INTO type_of_payment(id,clave,valor) VALUES (2,2,'Honorarios');
INSERT INTO type_of_payment(id,clave,valor) VALUES (3,3,'Por proyecto');
