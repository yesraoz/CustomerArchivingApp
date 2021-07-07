insert into user(name)
VALUES('admin');
insert into user(name)
VALUES('test');


insert into customer(id,name,surname,email)
VALUES(1,'Esra','Öz','esraoz@gmail.com');
insert into customer(id,name,surname,email)
VALUES(2,'Sena','Sali','senasali@gmail.com');
insert into customer(id,name,surname,email)
VALUES(3,'Mami','Şan','mamisan@gmail.com');
insert into customer(id,name,surname,email)
VALUES(4,'Batu','Yonter','batuyonter@gmail.com');


insert into folder(id, path, customer_id)
VALUES (1, 'path1', 1);
insert into folder(id, path, customer_id)
VALUES (2, 'path2', 1);
insert into folder(id, path, customer_id)
VALUES (3, 'path3', 1);
insert into folder(id, path, customer_id)
VALUES (4, 'path4', 2);
insert into folder(id, path, customer_id)
VALUES (5, 'path5', 2);
insert into folder(id, path, customer_id)
VALUES (6, 'path6', 3);
insert into folder(id, path, customer_id)
VALUES (7, 'path7', 4);