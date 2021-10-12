/*Обновление данных таблиц и сброс счетчиков serial id.*/
delete from devices_people;
delete from people;
delete from devices;
ALTER SEQUENCE people_id_seq RESTART WITH 1;
ALTER SEQUENCE devices_id_seq RESTART WITH 1;
ALTER SEQUENCE devices_people_id_seq RESTART WITH 1;

/*Заполнить таблицы данными.*/
insert into people(name) values ('Аня'), ('Ваня'), ('Боря');
insert into devices(name, price) values ('dev0', 2000), ('dev1', 4000), ('dev3', 6000), ('dev4', 8000), ('dev5', 10000), ('dev6', 12000);
insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 2), (4, 2), (5, 3), (6, 3);

/*Используя агрегатные функции вывести среднюю цену устройств.*/
select avg(price) from devices;

/*Используя группировку вывести для каждого человека среднюю цену его устройств.*/
select p.name, avg(d.price) from devices_people as dp
join devices as d on dp.device_id=d.id
join people as p on dp.people_id=p.id
group by p.name;

/*Дополнить запрос п.4. условием, что средняя стоимость устройств должна быть больше 5000.*/
select avg(price) from devices
having avg(price) > 5000;