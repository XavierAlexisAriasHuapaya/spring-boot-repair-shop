INSERT INTO masters (description, status) VALUES ('Country', true);
INSERT INTO master_details (master_id, description) VALUES (1, 'Peru');
INSERT INTO master_details (master_id, description) VALUES (1, 'Chile');
INSERT INTO master_details (master_id, description) VALUES (1, 'Argentina');
INSERT INTO master_details (master_id, description) VALUES (1, 'Colombia');

INSERT INTO masters (description, status) VALUES ('Currency', true);
INSERT INTO master_details (master_id, description) VALUES (2, 'Soles');
INSERT INTO master_details (master_id, description) VALUES (2, 'Dolares');

INSERT INTO roles (description, created_at, updated_at, status) VALUES ('Administrator', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);
INSERT INTO roles (description, created_at, updated_at, status) VALUES ('Technical', '2024-12-05 16:52:45.436', '2024-12-05 16:52:45.436', true);