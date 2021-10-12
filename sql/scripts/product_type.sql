/*create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
	expired_date date,
	price float
);
*/

delete from product;
delete from type;
ALTER SEQUENCE type_id_seq RESTART WITH 1;
ALTER SEQUENCE product_id_seq RESTART WITH 1;
insert into type(name) values ('СЫР'), ('МОЛОКО'), ('МОРОЖЕНОЕ'), ('КОЛБАСА'), ('ХЛЕБ');
insert into product(name, type_id, expired_date, price) values ('Сыр плавленый', 1, '20211201', 100);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарела', 1, '20211001', 200);
insert into product(name, type_id, expired_date, price) values ('Сыр бри', 1, '20210901', 250);
insert into product(name, type_id, expired_date, price) values ('Молоко 3,5%', 2, '20211101', 55);
insert into product(name, type_id, expired_date, price) values ('Молоко 2,5%', 2, '20211201', 50);
insert into product(name, type_id, expired_date, price) values ('Молоко отборное', 2, '20210901', 60);
insert into product(name, type_id, expired_date, price) values ('Мороженое пломбир', 3, '20210501', 100);
insert into product(name, type_id, expired_date, price) values ('Мороженое сливочное', 3, '20210601', 80);
insert into product(name, type_id, expired_date, price) values ('Мороженое в стаканчике', 3, '20210701', 50);
insert into product(name, type_id, expired_date, price) values ('Колбаса вареная', 4, '20211201', 300);
insert into product(name, type_id, expired_date, price) values ('Колбаса копченая', 4, '20211201', 600);
insert into product(name, type_id, expired_date, price) values ('Сосиски', 4, '20211201', 200);
insert into product(name, type_id, expired_date, price) values ('Хлеб ржаной', 5, '20211201', 30);
insert into product(name, type_id, expired_date, price) values ('Хлеб Дарницкий', 5, '20211201', 350);
insert into product(name, type_id, expired_date, price) values ('Хлеб Бородинский', 5, '20211201', 40);
insert into product(name, type_id, expired_date, price) values ('Батон Нарезной', 5, '20211201', 30);
insert into product(name, type_id, expired_date, price) values ('Багет Французский', 5, '20211201', 60);
insert into product(name, type_id, expired_date, price) values ('Хлеб ржаной в нарезке', 5, '20211201', 30);
insert into product(name, type_id, expired_date, price) values ('Хлеб Дарницкий в нарезке', 5, '20211201', 350);
insert into product(name, type_id, expired_date, price) values ('Хлеб Бородинский в нарезке', 5, '20211201', 40);
insert into product(name, type_id, expired_date, price) values ('Батон Нарезной (половина)', 5, '20211201', 30);
insert into product(name, type_id, expired_date, price) values ('Багет Французский (половина)', 5, '20211201', 60);
insert into product(name, type_id, expired_date, price) values ('Булка ржаная', 5, '20211201', 30);
insert into product(name, type_id, expired_date, price) values ('Булка', 5, '20211201', 350);

/*Написать запрос получение всех продуктов с типом "СЫР"*/
select name from product
where type_id=1;

/*Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"*/
select name from product
where name like '%Мороженое%' or name like '%мороженое%';

/*Написать запрос, который выводит все продукты, срок годности которых уже истек*/
select name,  expired_date from product
where expired_date < current_date;

/*Написать запрос, который выводит самый дорогой продукт.*/
select name, price from product
where price = (select max(price) from product);

/*Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество.*/
select t.name, count(p.type_id) from product as p
join type as t on p.type_id=t.id
group by t.name, p.type_id;

/*Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".*/
select p.name, t.name from product as p
join type as t on p.type_id=t.id
where t.name like 'СЫР' or t.name like 'МОЛОКО'
group by p.name, t.name
order by t.name desc;

/*Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.*/
select t.name, count(p.type_id) from product as p
join type as t on p.type_id=t.id
group by t.name, p.type_id
having count(p.type_id) < 10
order by t.name asc;


/*Вывести все продукты и их тип.*/
select p.name, t.name as type from product as p
join type as t on p.type_id=t.id
group by p.name, t.name
order by p.name asc;
