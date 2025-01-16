INSERT INTO masters (description, status) VALUES ('Country', true);
INSERT INTO master_details (master_id, description, value) VALUES (1, 'PERU', '+51');--1
INSERT INTO master_details (master_id, description, value) VALUES (1, 'CHILE', '');--2
INSERT INTO master_details (master_id, description, value) VALUES (1, 'ARGENTINA', '');--3
INSERT INTO master_details (master_id, description, value) VALUES (1, 'COLOMBIA', '');--4

INSERT INTO masters (description, status) VALUES ('Currency', true);
INSERT INTO master_details (master_id, description, value) VALUES (2, 'SOLES', 'S/.');--5
INSERT INTO master_details (master_id, description, value) VALUES (2, 'DOLARES', '$');--6

INSERT INTO masters (description, status) VALUES ('Type Person', true);
INSERT INTO master_details (master_id, description, value) VALUES (3, 'NATURAL', '');--7
INSERT INTO master_details (master_id, description, value) VALUES (3, 'JURIDICA', '');--8

INSERT INTO masters (description, status) VALUES ('Gender', true);
INSERT INTO master_details (master_id, description, value) VALUES (4, 'MASCULINO', '');--9
INSERT INTO master_details (master_id, description, value) VALUES (4, 'FEMENINO', '');--10

INSERT INTO masters (description, status) VALUES ('Type Supplier', true);
INSERT INTO master_details (master_id, description, value) VALUES (5, 'PROVEEDOR GENERAL', '');--11

INSERT INTO masters (description, status) VALUES ('Unit of measurement', true);
INSERT INTO master_details (master_id, description, value) VALUES (6, 'UND', '');--12

INSERT INTO masters (description, status) VALUES ('Category', true);
INSERT INTO master_details (master_id, description, value) VALUES (7, 'CELULARES', '');--13
INSERT INTO master_details (master_id, description, value) VALUES (7, 'LAPTOPS', '');--14
INSERT INTO master_details (master_id, description, value) VALUES (7, 'TABLETS', '');--15
INSERT INTO master_details (master_id, description, value) VALUES (7, 'ACCESORIOS', '');--16

INSERT INTO masters (description, status) VALUES ('Model', true);
INSERT INTO master_details (master_id, description, value) VALUES (8, 'IPHONE', '');--17
INSERT INTO master_details (master_id, description, value) VALUES (8, 'GALAXY', '');--18
INSERT INTO master_details (master_id, description, value) VALUES (8, 'INSPIRON', '');--19
INSERT INTO master_details (master_id, description, value) VALUES (8, 'MX MASTER', '');--20

INSERT INTO masters (description, status) VALUES ('Brand', true);
INSERT INTO master_details (master_id, description, value) VALUES (9, 'APPLE', '');--21
INSERT INTO master_details (master_id, description, value) VALUES (9, 'SAMSUNG', '');--22
INSERT INTO master_details (master_id, description, value) VALUES (9, 'DELL', '');--23
INSERT INTO master_details (master_id, description, value) VALUES (9, 'LOGITECH', '');--24

INSERT INTO masters (description, status) VALUES ('Movement reason', true);
INSERT INTO master_details (master_id, description, value) VALUES (10, 'COMPRA DIRECTA', 'I');--25
INSERT INTO master_details (master_id, description, value) VALUES (10, 'VENTA DE PRODUCTOS', 'O');--26
INSERT INTO master_details (master_id, description, value) VALUES (10, 'TRASLADO ENTRE ALMACENES', 'O');--27
INSERT INTO master_details (master_id, description, value) VALUES (10, 'PRODUCTOS DEFECTUOSOS', 'O');--28
INSERT INTO master_details (master_id, description, value) VALUES (10, 'INVENTARIO INICIAL', 'I');--29
INSERT INTO master_details (master_id, description, value) VALUES (10, 'AJUSTE DE INVENTARIO', 'N');--30
INSERT INTO master_details (master_id, description, value) VALUES (10, 'ENTRADAS VARIAS', 'I');--31
INSERT INTO master_details (master_id, description, value) VALUES (10, 'DEVOLUCION A PROVEEDOR', 'O');--32
INSERT INTO master_details (master_id, description, value) VALUES (10, 'NOTA DE CREDITO', 'I');--33

INSERT INTO masters (description, status) VALUES ('Payment Condition', true);
INSERT INTO master_details (master_id, description, value) VALUES (11, 'AL CONTADO', '');--34
INSERT INTO master_details (master_id, description, value) VALUES (11, 'A CREDITO', '');--35

INSERT INTO masters (description, status) VALUES ('Delivery Status', true);
INSERT INTO master_details (master_id, description, value) VALUES (12, 'PENDIENTE', '');--36
INSERT INTO master_details (master_id, description, value) VALUES (12, 'ENTREGADO', '');--37
INSERT INTO master_details (master_id, description, value) VALUES (12, 'POR ENTREGAR', '');--38

INSERT INTO masters (description, status) VALUES ('Purchase Status', true);
INSERT INTO master_details (master_id, description, value) VALUES (13, 'PENDIENTE', '');--39
INSERT INTO master_details (master_id, description, value) VALUES (13, 'APROBADA', '');--40
INSERT INTO master_details (master_id, description, value) VALUES (13, 'RECHAZADA', '');--41
INSERT INTO master_details (master_id, description, value) VALUES (13, 'CERRADA', '');--42
INSERT INTO master_details (master_id, description, value) VALUES (13, 'ANULADA', '');--43

INSERT INTO masters (description, status) VALUES ('Type Operation', true);
INSERT INTO master_details (master_id, description, value) VALUES (14, 'INGRESOS POR VENTAS', 'I');--44
INSERT INTO master_details (master_id, description, value) VALUES (14, 'TRANSFERENCIA', 'E');--45
INSERT INTO master_details (master_id, description, value) VALUES (14, 'PAGO DE NOTA DE CREDITO', 'E');--46
INSERT INTO master_details (master_id, description, value) VALUES (14, 'PAGO A PROVEEDOR', 'E');--47
INSERT INTO master_details (master_id, description, value) VALUES (14, 'PAGO DE LETRA', 'I');--48
INSERT INTO master_details (master_id, description, value) VALUES (14, 'ARTICULOS DE OFICINA', 'E');--49
INSERT INTO master_details (master_id, description, value) VALUES (14, 'UTILES DE LIMPIEZA', 'E');--50
INSERT INTO master_details (master_id, description, value) VALUES (14, 'SUELDO', 'E');--51
INSERT INTO master_details (master_id, description, value) VALUES (14, 'EGRESOS VARIOS', 'E');--52
INSERT INTO master_details (master_id, description, value) VALUES (14, 'INGRESOS VARIOS', 'I');--53
INSERT INTO master_details (master_id, description, value) VALUES (14, 'MOVILIDAD', 'E');--54
INSERT INTO master_details (master_id, description, value) VALUES (14, 'MENU', 'E');--55

INSERT INTO masters (description, status) VALUES ('Method Payment', true);
INSERT INTO master_details (master_id, description, value) VALUES (15, 'EFECTIVO', '');--56
INSERT INTO master_details (master_id, description, value) VALUES (15, 'TARJETA', '');--57
INSERT INTO master_details (master_id, description, value) VALUES (15, 'NOTA DE CREDITO - VENTA', '');--58

INSERT INTO masters (description, status) VALUES ('Type Card', true);
INSERT INTO master_details (master_id, description, value) VALUES (16, 'TRANFERENCIA INTERBANK', '');--59
INSERT INTO master_details (master_id, description, value) VALUES (16, 'TRANFERENCIA BCP', '');--60
INSERT INTO master_details (master_id, description, value) VALUES (16, 'TRANSFERENCIA BBVA', '');--61
INSERT INTO master_details (master_id, description, value) VALUES (16, 'PLIN', '');--62
INSERT INTO master_details (master_id, description, value) VALUES (16, 'YAPE', '');--63
INSERT INTO master_details (master_id, description, value) VALUES (16, 'POS IZIPAY', '');--64
INSERT INTO master_details (master_id, description, value) VALUES (16, 'POS VENDE MAS', '');--65
INSERT INTO master_details (master_id, description, value) VALUES (16, 'MASTERCARD', '');--66

INSERT INTO masters (description, status) VALUES ('Type Credit Note', true);
INSERT INTO master_details (master_id, description, value) VALUES (17, 'AJUSTES - MONTOS Y/O FECHAS DE PAGO', '');--67
INSERT INTO master_details (master_id, description, value) VALUES (17, 'AJUSTES AFECTOS AL IVAP', '');--68
INSERT INTO master_details (master_id, description, value) VALUES (17, 'AJUSTES DE OPERACIONES DE EXPORTACION', '');--69
INSERT INTO master_details (master_id, description, value) VALUES (17, 'ANULACION DE LA OPERACION', '');--70
INSERT INTO master_details (master_id, description, value) VALUES (17, 'ANULACION POR ERROR DE RUC', '');--71
INSERT INTO master_details (master_id, description, value) VALUES (17, 'BONIFICACION', '');--72
INSERT INTO master_details (master_id, description, value) VALUES (17, 'CORRECCION POR ERROR EN LA DESCRIPCION', '');--73
INSERT INTO master_details (master_id, description, value) VALUES (17, 'DESCUENTO GLOBAL', '');--74
INSERT INTO master_details (master_id, description, value) VALUES (17, 'DESCUENTO POR ITEM', '');--75
INSERT INTO master_details (master_id, description, value) VALUES (17, 'DEVOLUCION POR ITEM', '');--76
INSERT INTO master_details (master_id, description, value) VALUES (17, 'DEVOLUCION TOTAL', '');--77
INSERT INTO master_details (master_id, description, value) VALUES (17, 'DESMINUCION EN EL VALOR', '');--78
INSERT INTO master_details (master_id, description, value) VALUES (17, 'OTROS CONCEPTOS', '');--79

INSERT INTO business (country_id, company_name, phone, address, created_at, updated_at, status) VALUES (1, 'Digital Perú A&H', '989854252', 'Jr. Bolognesi', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO roles (description, created_at, updated_at, status) VALUES ('ADMINISTRATOR', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO roles (description, created_at, updated_at, status) VALUES ('TECHNICAL', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO stores (currency_id, name, address, phone, logo, created_at, updated_at, status) VALUES (5, 'Lima One', 'Jr. Lima 154', '989748154', null, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO stores (currency_id, name, address, phone, logo, created_at, updated_at, status) VALUES (6, 'Ayacucho Two', 'Av. Ayacucho 1874', '947991007', null, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO users (username, password, email, rol_id, created_at, updated_at, status) VALUES ('Xavier', 'clave123', 'alexis@digital.com', 1, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO employees (user_id, identity_number, first_name, last_name, address, phone, created_at, updated_at, status) VALUES (1, '73070360', 'Xavier Alexis', 'Arias Huapaya', 'Cañete', '916496724', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO documents (name, abbreviation, sale, bill, created_at, updated_at, status) VALUES ('NOTA DE VENTA', 'NT', true, false, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO document_stores (document_id, store_id, serie, number, created_at, updated_at, status) VALUES (1, 1, 'NT01', 0, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO document_stores (document_id, store_id, serie, number, created_at, updated_at, status) VALUES (1, 2, 'NT02', 0, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO documents (name, abbreviation, sale, bill, created_at, updated_at, status) VALUES ('FACTURA ELECTRONICA COMPRA', 'FE', false, false, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO documents (name, abbreviation, sale, bill, created_at, updated_at, status) VALUES ('BOLETA ELECTRONICA COMPRA', 'FE', false, false, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO documents (name, abbreviation, sale, bill, created_at, updated_at, status) VALUES ('FACTURA ELECTRONICA VENTA', 'FV', false, true, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO document_stores (document_id, store_id, serie, number, created_at, updated_at, status) VALUES (4, 1, 'F001', 0, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO document_stores (document_id, store_id, serie, number, created_at, updated_at, status) VALUES (4, 2, 'F002', 0, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO documents (name, abbreviation, sale, bill, created_at, updated_at, status) VALUES ('NOTE DE CREDITO', 'NC', true, true, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO document_stores (document_id, store_id, serie, number, created_at, updated_at, status) VALUES (5, 1, 'FC01', 0, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO document_stores (document_id, store_id, serie, number, created_at, updated_at, status) VALUES (5, 2, 'FC02', 0, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);


INSERT INTO clients (type_person_id, gender_id, identity_number, first_name, last_name, brith_or_anniversary, address, phone, email, occupation, observation, created_at, updated_at, status) VALUES (7, 9, '15415874', 'Manuel Alberto', 'Zevallos Casas', '1984-01-15', null, null, null, null, null, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO clients (type_person_id, gender_id, identity_number, first_name, last_name, brith_or_anniversary, address, phone, email, occupation, observation, created_at, updated_at, status) VALUES (8, 9, '20606825570', 'S & S CONSULTORES Y SERVICIOS GENERALES SOCIEDAD ANONIMA CERRADA', '', '2020-05-20', 'CAL.BENITO BONIFAZ NRO. 161 INT. 2 URB. FERROVIARIOS AREQUIPA - AREQUIPA - AREQUIPA', null, null, null, null, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO suppliers (type_supplier_id, country_id, company_name, identity_number, address, phone, web, created_at, updated_at, status) VALUES (11, 1, 'A&F CONSULTORES Y SERVICIOS AMBIENTALES S.A.C.', '20456234918', 'AV. AREQUIPA NRO. 906 URB. SEMI RURAL PACHACUTEC AREQUIPA - AREQUIPA - CERRO COLORADO', '900145748', 'https://www.ayfsac.com/', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO bank_boxes (store_id, name, created_at, updated_at, status) VALUES (1, 'Bank Lima One', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO petty_cash (bank_box_id, opening_observation, closing_observation, opening_date, closing_date, opening_amount, cash_closing, other_closing, open_petty_cash, exchange_rate, created_at, updated_at, status) VALUES (1, 'Opening', null, '2025-01-04', null, 1500, null, null, true, 3.78, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO products (unit_of_measure_id, category_id, model_id, brand_id, name, unit_price, purchase_price, minimum_stock, image, created_at, updated_at, status) VALUES (12, 13, 17, 21, 'iPhone 15 Pro Max (256 GB)', 4298, 4000, 5, 'https://pe.tiendasishop.com/cdn/shop/files/IMG-10942125_9b1937bf-2e78-4f4d-bd4d-015536bb74be.jpg?v=1722625475&width=823', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO products (unit_of_measure_id, category_id, model_id, brand_id, name, unit_price, purchase_price, minimum_stock, image, created_at, updated_at, status) VALUES (12, 13, 18, 22, 'Galaxy S23 Ultra (512 GB)', 3200, 2900, 3, 'https://images.samsung.com/is/image/samsung/p6pim/pe/2302/gallery/pe-galaxy-s23-s918-sm-s918bzkvltp-534855455?$684_547_PNG$', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO products (unit_of_measure_id, category_id, model_id, brand_id, name, unit_price, purchase_price, minimum_stock, image, created_at, updated_at, status) VALUES (12, 14, 19, 23, 'Dell Inspiron 15', 1500, 1200, 2, 'https://i.dell.com/is/image/DellContent/content/dam/ss2/product-images/dell-client-products/notebooks/inspiron-notebooks/15-3530-intel/media-gallery/silver-metal/notebook-inspiron-15-3530-nt-metal-silver-gallery-12.psd?fmt=png-alpha&pscan=auto&scl=1&hei=402&wid=685&qlt=100,1&resMode=sharp2&size=685,402&chrss=full', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO products (unit_of_measure_id, category_id, model_id, brand_id, name, unit_price, purchase_price, minimum_stock, image, created_at, updated_at, status) VALUES (12, 16, 20, 24, 'Logitech MX Master 3S', 120, 100, 10, 'https://resource.logitech.com/w_692,c_lpad,ar_4:3,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/logitech/en/products/mice/mx-master-3s/gallery/mx-master-3s-mouse-top-view-graphite.png?v=1', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO movements (reason_id, origin_store_id, destination_store_id, reference_movement_id, sale_id, operation_date, observation, sub_total, tax_amount, movement_total, created_at, updated_at, status) VALUES (25, 1, null, null, null, '2025-01-04', 'Inbound', 18211.86, 3.78, 21490, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO inbounds (product_id, quantity, sale_price, created_at, updated_at, status) VALUES (1, 50, 4298, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO inbounds (product_id, quantity, sale_price, created_at, updated_at, status) VALUES (2, 100, 3200, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO product_stores (product_id, store_id, stock, sale_price, purchase_price, created_at, updated_at, status) VALUES (1, 1, 50, null, null, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO product_stores (product_id, store_id, stock, sale_price, purchase_price, created_at, updated_at, status) VALUES (2, 1, 100, null, null, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);