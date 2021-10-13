create table body(
    id serial primary key,
    type varchar(255)
);

create table engine(
    id serial primary key,
    type varchar(255)
);

create table transmission(
    id serial primary key,
    type varchar(255)
);

create table car(
    id serial primary key,
    name varchar(255),
    body_id int references body(id),
	engine_id int references body(id),
	transmission_id int references body(id)
);

/*delete from car;
delete from body;
ALTER SEQUENCE body_id_seq RESTART WITH 1;
delete from engine;
ALTER SEQUENCE engine_id_seq RESTART WITH 1;
delete from transmission;
ALTER SEQUENCE transmission_id_seq RESTART WITH 1;
ALTER SEQUENCE car_id_seq RESTART WITH 1;
*/

insert into body(type) values ('limo'), ('pickup'), ('hatch'), ('truck');
insert into engine(type) values ('petrol'), ('diesel'), ('electro'), ('gazoil');
insert into transmission(type) values ('auto'), ('manual'), ('ECVT');
insert into car(name, body_id, engine_id, transmission_id) values ('car-1', 1, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('car-2', 1, 2, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('car-3', 1, 1, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('car-4', 1, 2, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('car-5', 3, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('car-6', 3, 2, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('car-7', 3, 1, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('car-8', 3, 2, 2);

/*Вывести список всех машин и все привязанные к ним детали.*/
select c.name name, b.type body, e.type engine, t.type transmission from car c
join body b on c.body_id = b.id
join engine e on c.engine_id = e.id
join transmission t on c.transmission_id = t.id
group by c.name, b.type, e.type, t.type
order by c.name asc;

/*Вывести отдельно детали (1 деталь - 1 запрос),
которые не используются НИ в одной машине, кузова, двигатели, коробки передач.*/
select * from body b left join car c on b.id = c.body_id
where c.body_id is null;
select * from engine e left join car c on e.id = c.engine_id
where c.engine_id is null;
select * from transmission t left join car c on t.id = c.transmission_id
where c.transmission_id is null;
