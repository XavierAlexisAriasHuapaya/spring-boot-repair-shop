INSERT INTO masters (description, status) VALUES ('Country', true);
INSERT INTO master_details (master_id, description, value) VALUES (1, 'Peru', '');
INSERT INTO master_details (master_id, description, value) VALUES (1, 'Chile', '');
INSERT INTO master_details (master_id, description, value) VALUES (1, 'Argentina', '');
INSERT INTO master_details (master_id, description, value) VALUES (1, 'Colombia', '');

INSERT INTO masters (description, status) VALUES ('Currency', true);
INSERT INTO master_details (master_id, description, value) VALUES (2, 'Soles', '');
INSERT INTO master_details (master_id, description, value) VALUES (2, 'Dolares', '');

INSERT INTO masters (description, status) VALUES ('Type Person', true);
INSERT INTO master_details (master_id, description, value) VALUES (3, 'Natural', '');
INSERT INTO master_details (master_id, description, value) VALUES (3, 'Juridica', '');

INSERT INTO masters (description, status) VALUES ('Gender', true);
INSERT INTO master_details (master_id, description, value) VALUES (4, 'Masculino', '');
INSERT INTO master_details (master_id, description, value) VALUES (4, 'Femenino', '');

INSERT INTO masters (description, status) VALUES ('Type Supplier', true);
INSERT INTO master_details (master_id, description, value) VALUES (5, 'Proveedor General', '');

INSERT INTO masters (description, status) VALUES ('Unit of measurement', true);
INSERT INTO master_details (master_id, description, value) VALUES (6, 'UND', '');

INSERT INTO masters (description, status) VALUES ('Category', true);
INSERT INTO master_details (master_id, description, value) VALUES (7, 'Celulares', '');

INSERT INTO masters (description, status) VALUES ('Model', true);
INSERT INTO master_details (master_id, description, value) VALUES (8, 'iPhone', '');

INSERT INTO masters (description, status) VALUES ('Brand', true);
INSERT INTO master_details (master_id, description, value) VALUES (9, 'Apple', '');

INSERT INTO masters (description, status) VALUES ('Movement reason', true);
INSERT INTO master_details (master_id, description, value) VALUES (10, 'COMPRA DIRECTA', 'I');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'VENTA DE PRODUCTOS', 'O');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'TRASLADO ENTRE ALMACENES', 'N');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'PRODUCTOS DEFECTUOSOS', 'O');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'INVENTARIO INICIAL', 'I');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'AJUSTE DE INVENTARIO', 'N');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'ENTRADAS VARIAS', 'I');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'DEVOLUCION A PROVEEDOR', 'O');

INSERT INTO roles (description, created_at, updated_at, status) VALUES ('Administrator', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO roles (description, created_at, updated_at, status) VALUES ('Technical', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);