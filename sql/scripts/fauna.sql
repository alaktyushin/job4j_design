delete from fauna;

insert into fauna (name, avg_age, discovery_date) VALUES ('dog Bobby', 15, '20180101');
insert into fauna (name, avg_age, discovery_date) VALUES ('dog', 15000, '10000101');
insert into fauna (name, avg_age, discovery_date) VALUES ('Garfield cat', 15, '20180101');
insert into fauna (name, avg_age, discovery_date) VALUES ('cat', 15000, '10000101');
insert into fauna (name, avg_age, discovery_date) VALUES ('fish Vanda', 5, '20180101');
insert into fauna (name, avg_age, discovery_date) VALUES ('fish Tuna', 15, '20180101');
insert into fauna (name, avg_age, discovery_date) VALUES ('fish', 15000, '10000101');
insert into fauna (name, avg_age, discovery_date) VALUES ('bird Twitty', 15, '20180101');
insert into fauna (name, avg_age) VALUES ('dino', 15);

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 10000 and avg_age < 21000; 

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '19500101'; 
