create type gender_type as enum ('male', 'female');
create table teens(
    id serial primary key,
    name varchar(255),
	gender gender_type
);

/*delete from teens;
ALTER SEQUENCE teens_id_seq RESTART WITH 1;
*/

insert into teens(name, gender) values ('boy1', 'male'), ('boy2', 'male'), ('boy3', 'male'), ('boy4', 'male'), ('boy5', 'male');
insert into teens(name, gender) values ('girl1', 'female'), ('girl2', 'female'), ('girl3', 'female'), ('girl4', 'female'), ('girl5', 'female');

select * from teens t cross join teens
where t.gender != teens.gender;