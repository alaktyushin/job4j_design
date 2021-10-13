create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);


delete from departments;
delete from employees;
ALTER SEQUENCE departments_id_seq RESTART WITH 1;
ALTER SEQUENCE employees_id_seq RESTART WITH 1;

insert into departments(name) values ('dep1'), ('dep2'), ('dep3'), ('dep4'), ('dep5');
insert into employees(name, departments_id) values ('name1', 1), ('name2', 2), ('name3', 1), ('name4', 5), ('name5', 1), ('name6', 2);


/*Выполнить запросы с left, rigth, full, cross соединениями*/
select * from employees e left join departments d on e.departments_id = d.id;
select * from departments d left join employees e on d.id = e.departments_id;
select * from employees e right join departments d on e.departments_id = d.id;
select * from departments d right join employees e on d.id = e.departments_id;
select * from employees e full join departments d on e.departments_id = d.id;
select * from departments d full join employees e on d.id = e.departments_id;
select * from employees cross join departments;
select * from departments cross join employees;

/*Используя left join найти департаменты, у которых нет работников*/
select * from departments d left join employees e on d.id = e.departments_id
where e.departments_id is null;

/*Используя left и right join написать запросы, которые давали бы одинаковый результат*/
select e.name, d.name, e.departments_id from departments d left join employees e on d.id = e.departments_id
group by e.name, d.name,  e.departments_id
order by e.name asc;
select e.name, d.name, e.departments_id from employees e right join departments d on e.departments_id = d.id
group by e.name, d.name,  e.departments_id
order by e.name asc;
