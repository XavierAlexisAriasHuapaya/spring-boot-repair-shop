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
INSERT INTO master_details (master_id, description, value) VALUES (7, 'Laptops', '');
INSERT INTO master_details (master_id, description, value) VALUES (7, 'Tablets', '');
INSERT INTO master_details (master_id, description, value) VALUES (7, 'Accesorios', '');

INSERT INTO masters (description, status) VALUES ('Model', true);
INSERT INTO master_details (master_id, description, value) VALUES (8, 'iPhone', '');
INSERT INTO master_details (master_id, description, value) VALUES (8, 'Galaxy', '');
INSERT INTO master_details (master_id, description, value) VALUES (8, 'Inspiron', '');
INSERT INTO master_details (master_id, description, value) VALUES (8, 'MX Master', '');

INSERT INTO masters (description, status) VALUES ('Brand', true);
INSERT INTO master_details (master_id, description, value) VALUES (9, 'Apple', '');
INSERT INTO master_details (master_id, description, value) VALUES (9, 'Samsung', '');
INSERT INTO master_details (master_id, description, value) VALUES (9, 'Dell', '');
INSERT INTO master_details (master_id, description, value) VALUES (9, 'Logitech', '');

INSERT INTO masters (description, status) VALUES ('Movement reason', true);
INSERT INTO master_details (master_id, description, value) VALUES (10, 'COMPRA DIRECTA', 'I');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'VENTA DE PRODUCTOS', 'O');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'TRASLADO ENTRE ALMACENES', 'O');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'PRODUCTOS DEFECTUOSOS', 'O');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'INVENTARIO INICIAL', 'I');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'AJUSTE DE INVENTARIO', 'N');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'ENTRADAS VARIAS', 'I');
INSERT INTO master_details (master_id, description, value) VALUES (10, 'DEVOLUCION A PROVEEDOR', 'O');

INSERT INTO masters (description, status) VALUES ('Payment Condition', true);
INSERT INTO master_details (master_id, description, value) VALUES (11, 'AL CONTADO', '');
INSERT INTO master_details (master_id, description, value) VALUES (11, 'A CREDITO', '');

INSERT INTO masters (description, status) VALUES ('Delivery Status', true);
INSERT INTO master_details (master_id, description, value) VALUES (12, 'PENDIENTE', '');
INSERT INTO master_details (master_id, description, value) VALUES (12, 'ENTREGADO', '');
INSERT INTO master_details (master_id, description, value) VALUES (12, 'POR ENTREGAR', '');

INSERT INTO masters (description, status) VALUES ('Purchase Status', true);
INSERT INTO master_details (master_id, description, value) VALUES (13, 'PENDIENTE', '');
INSERT INTO master_details (master_id, description, value) VALUES (13, 'APROBADA', '');
INSERT INTO master_details (master_id, description, value) VALUES (13, 'RECHAZADA', '');
INSERT INTO master_details (master_id, description, value) VALUES (13, 'CERRADA', '');
INSERT INTO master_details (master_id, description, value) VALUES (13, 'ANULADA', '');

INSERT INTO business (country_id, company_name, phone, address, created_at, updated_at, status) VALUES (1, 'Digital Perú A&H', '989854252', 'Jr. Bolognesi', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO roles (description, created_at, updated_at, status) VALUES ('Administrator', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO roles (description, created_at, updated_at, status) VALUES ('Technical', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO stores (currency_id, name, address, phone, logo, created_at, updated_at, status) VALUES (5, 'Lima One', 'Jr. Lima 154', '989748154', null, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO stores (currency_id, name, address, phone, logo, created_at, updated_at, status) VALUES (6, 'Ayacucho Two', 'Av. Ayacucho 1874', '947991007', null, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO users (username, password, email, rol_id, created_at, updated_at, status) VALUES ('Xavier', 'clave123', 'alexis@digital.com', 1, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO employees (user_id, identity_number, first_name, last_name, address, phone, created_at, updated_at, status) VALUES (1, '73070360', 'Xavier Alexis', 'Arias Huapaya', 'Cañete', '916496724', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO documents (name, abbreviation, sale, bill, created_at, updated_at, status) VALUES ('NOTA DE VENTA', 'NT', true, false, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO document_stores (document_id, store_id, serie, number, created_at, updated_at, status) VALUES (1, 1, 'NT01', 0, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO document_stores (document_id, store_id, serie, number, created_at, updated_at, status) VALUES (1, 2, 'NT02', 0, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO clients (type_person_id, gender_id, identity_number, first_name, last_name, brith_or_anniversary, address, phone, email, occupation, observation, created_at, updated_at, status) VALUES (7, 9, '15415874', 'Manuel Alberto', 'Zevallos Casas', '1984-01-15', null, null, null, null, null, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO clients (type_person_id, gender_id, identity_number, first_name, last_name, brith_or_anniversary, address, phone, email, occupation, observation, created_at, updated_at, status) VALUES (8, 9, '20606825570', 'S & S CONSULTORES Y SERVICIOS GENERALES SOCIEDAD ANONIMA CERRADA', '', '2020-05-20', 'CAL.BENITO BONIFAZ NRO. 161 INT. 2 URB. FERROVIARIOS AREQUIPA - AREQUIPA - AREQUIPA', null, null, null, null, '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

INSERT INTO suppliers (type_supplier_id, company_name, identity_number, address, phone, web, created_at, updated_at, status) VALUES (11, 'A&F CONSULTORES Y SERVICIOS AMBIENTALES S.A.C.', '20456234918', 'AV. AREQUIPA NRO. 906 URB. SEMI RURAL PACHACUTEC AREQUIPA - AREQUIPA - CERRO COLORADO', '900145748', 'https://www.ayfsac.com/', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);

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