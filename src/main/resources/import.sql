INSERT INTO tb_schedule_type(name, worked_time, free_time, unity, monthly_hours, days_off) VALUES ('12x36', 12.0, 36.0, 5, 440, 1);
INSERT INTO tb_schedule_type(name, worked_time, free_time, unity, monthly_hours, days_off) VALUES ('6x1', 6.0, 1.0, 7, 440, 0);
INSERT INTO tb_schedule_type(name, worked_time, free_time, unity, monthly_hours, days_off) VALUES ('5x2', 5.0, 2.0, 7, 440, 0);

INSERT INTO tb_role(authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role(authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_team (name) VALUES  ('UTI 3');
INSERT INTO tb_team (name) VALUES  ('UTI 1');

INSERT INTO tb_turn (start_time, end_time, interval_duration) VALUES ('08:00', '18:00', '01:00');
INSERT INTO tb_turn (start_time, end_time, interval_duration) VALUES ('12:00', '00:00', '01:00');
INSERT INTO tb_turn (start_time, end_time, interval_duration) VALUES ('08:00', '20:00', '01:30');

INSERT INTO tb_user(username, full_name, email, password, img_url, team_id, turn_id, schedule_type_id) VALUES ('xflprflx', 'Filipe Rafael Pereira Machado', 'x.filipe.machado.x@gmail.com', '$2a$12$UQHHFTS7Z3UzWEb.fxe8M.Q7yrX/Jn/ZSL4Z5Xo9s1Ga0lE0hd/k.', 'https://media.licdn.com/dms/image/D4D35AQE_ULBBuNRWmQ/profile-framedphoto-shrink_200_200/0/1687561178954?e=1696464000&v=beta&t=blgVYs3Fgl7zsXqxZSEjndRhEPtCpdGsijyHmFYVGro', 1, 1, 2);
INSERT INTO tb_user(username, full_name, email, password, img_url, team_id, turn_id, schedule_type_id) VALUES ('mari', 'Mariana Bedin Santos', 'maribedin@gmail.com', '$2a$12$q1t3ayHzQ2BTJVzGFgnbzeWRfCyE5VS1drNP/aZqxdlBQE9Ew0KXm', 'https://media.licdn.com/dms/image/D4D03AQG8jT3QNbXq6g/profile-displayphoto-shrink_800_800/0/1690940089731?e=1701302400&v=beta&t=Lj4Rxlr86R0etWOgbJcuWUvv0igx50TpO41yYknfKoo', 1, 2, 3);

UPDATE tb_team SET manager_id = 1 WHERE id = 1;
UPDATE tb_team SET manager_id = 2 WHERE id = 2;

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);

INSERT INTO tb_team_role (team_id, role_id, quantity) VALUES (1, 1, 10);
INSERT INTO tb_team_role (team_id, role_id, quantity) VALUES (1, 2, 1);
INSERT INTO tb_team_role (team_id, role_id, quantity) VALUES (2, 1, 8);
INSERT INTO tb_team_role (team_id, role_id, quantity) VALUES (2, 2, 2);

INSERT INTO tb_work_schedule (initial_date, final_date, description, schedule_status) VALUES ('2023-01-01', '2023-01-30', 'Teste1', 'IN_PLANNING');

INSERT INTO tb_schedule_date (date, holiday) VALUES ('2023-01-04', false);
INSERT INTO tb_schedule_date (date, holiday) VALUES ('2023-01-02', false);
INSERT INTO tb_schedule_date (date, holiday) VALUES ('2023-01-03', false);
INSERT INTO tb_schedule_date (date, holiday) VALUES ('2023-01-01', false);
INSERT INTO tb_schedule_date (date, holiday) VALUES ('2023-01-05', false);

INSERT INTO tb_schedule_date_work_schedule (schedule_date_id, work_schedule_id) VALUES (1, 1);
INSERT INTO tb_schedule_date_work_schedule (schedule_date_id, work_schedule_id) VALUES (2, 1);
INSERT INTO tb_schedule_date_work_schedule (schedule_date_id, work_schedule_id) VALUES (3, 1);
INSERT INTO tb_schedule_date_work_schedule (schedule_date_id, work_schedule_id) VALUES (4, 1);
INSERT INTO tb_schedule_date_work_schedule (schedule_date_id, work_schedule_id) VALUES (5, 1);

INSERT INTO tb_user_schedule_date (schedule_date_id, user_id, turn_id) VALUES (1, 1, 1);
INSERT INTO tb_user_schedule_date (schedule_date_id, user_id, turn_id) VALUES (2, 2, 1);
