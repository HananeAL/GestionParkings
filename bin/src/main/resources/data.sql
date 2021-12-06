INSERT INTO tb_client (first_Name, last_Name, cpf, address, cell_Phone) VALUES ('Maria', 'Silva', '12485775505', 'Rua Antonio Prado, n° 97', '(11) 97845-2301');
INSERT INTO tb_client (first_Name, last_Name, cpf, address, cell_Phone) VALUES ('João', 'Pereira', '06585775502', 'Avenida General Castro e Silva, n° 1102', '(19) 99589-4574');

INSERT INTO tb_vehicle (model, color, license_Plate) VALUES ('Toyota Corolla', 'Preto', 'FZY3R17');
INSERT INTO tb_vehicle (model, color, license_Plate) VALUES ('Renaut Duster', 'Prata', 'TJP0Z25');
INSERT INTO tb_vehicle (model, color, license_Plate) VALUES ('Hyundai Tucson', 'Preto', 'XYZ1J80');

INSERT INTO tb_stay (entry_Date, departure_Date, entry_Time, exit_Time, hourly_Valor, vehicle_id) VALUES ('2021-06-12', '2021-06-12', '09:10:52', '13:32:10', 8.00, 2);
INSERT INTO tb_stay (entry_Date, departure_Date, entry_Time, exit_Time, hourly_Valor, vehicle_id) VALUES ('2021-07-20', '2021-07-21', '08:00:42', '18:42:34', 8.00, 1);
INSERT INTO tb_stay (entry_Date, departure_Date, entry_Time, exit_Time, hourly_Valor, vehicle_id) VALUES ('2021-08-15', '2021-08-15', '07:05:00', '12:30:00', 10.00, 3);

INSERT INTO tb_vehicle_client (vehicle_id, client_id) VALUES (1, 1);
INSERT INTO tb_vehicle_client (vehicle_id, client_id) VALUES (3, 1);
INSERT INTO tb_vehicle_client (vehicle_id, client_id) VALUES (2, 2);