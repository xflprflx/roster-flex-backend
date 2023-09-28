INSERT INTO tb_schedule_type(name, worked_time, free_time, unity, monthly_hours, days_off) VALUES ('12x36', 12.0, 36.0, 5, 440, 1);
INSERT INTO tb_schedule_type(name, worked_time, free_time, unity, monthly_hours, days_off) VALUES ('6x1', 6.0, 1.0, 7, 440, 0);
INSERT INTO tb_schedule_type(name, worked_time, free_time, unity, monthly_hours, days_off) VALUES ('5x2', 5.0, 2.0, 7, 440, 0);

INSERT INTO tb_role(authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role(authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user(username, full_name, email, password, img_url) VALUES ('xflprflx', 'Filipe Rafael Pereira Machado', 'x.filipe.machado.x@gmail.com', '1234', 'https://media.licdn.com/dms/image/D4D35AQE_ULBBuNRWmQ/profile-framedphoto-shrink_200_200/0/1687561178954?e=1696464000&v=beta&t=blgVYs3Fgl7zsXqxZSEjndRhEPtCpdGsijyHmFYVGro');
INSERT INTO tb_user(username, full_name, email, password, img_url) VALUES ('mari', 'Mariana Bedin Santos', 'maribedin@gmail.com', '1234', 'https://media.licdn.com/dms/image/D4D03AQG8jT3QNbXq6g/profile-displayphoto-shrink_800_800/0/1690940089731?e=1701302400&v=beta&t=Lj4Rxlr86R0etWOgbJcuWUvv0igx50TpO41yYknfKoo');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
