delete from role;
ALTER SEQUENCE role_id_seq RESTART WITH 1;
insert into role(name) values ('programmer');
insert into role(name) values ('sysadmin');
insert into role(name) values ('user');
insert into role(name) values ('manager');

delete from rules;
ALTER SEQUENCE rules_id_seq RESTART WITH 1;
insert into rules(name) values ('rule.toProgrammer');
insert into rules(name) values ('rule.toSysadmin');
insert into rules(name) values ('rule.toManager');
insert into rules(name) values ('rule.backToUser');

delete from state;
ALTER SEQUENCE state_id_seq RESTART WITH 1;
insert into state(name) values ('state.executing');
insert into state(name) values ('state.onHold');
insert into state(name) values ('state.escalation');
insert into state(name) values ('state.done');
insert into state(name) values ('state.closed');

delete from category;
ALTER SEQUENCE category_id_seq RESTART WITH 1;
insert into category(name) values ('category.normal');
insert into category(name) values ('category.high');
insert into category(name) values ('category.critical');
insert into category(name) values ('category.VIP');

delete from users;
ALTER SEQUENCE users_id_seq RESTART WITH 1;
insert into users(name, role_id) VALUES ('Ivan', 3);
insert into users(name, role_id) VALUES ('Andrey', 1);
insert into users(name, role_id) VALUES ('Rail', 2);
insert into users(name, role_id) VALUES ('Petr', 4);

delete from item;
ALTER SEQUENCE item_id_seq RESTART WITH 1;
insert into item(name, users_id, category_id, state_id) VALUES ('Create database', 3, 2, 4);
insert into item(name, users_id, category_id, state_id) VALUES ('Create database script', 2, 1, 1);

delete from comments;
ALTER SEQUENCE comments_id_seq RESTART WITH 1;
insert into comments(name, item_id) VALUES ('Thanks!', 1);
insert into comments(name, item_id) VALUES ('Please hurry up.', 2);

delete from attachs;
ALTER SEQUENCE attachs_id_seq RESTART WITH 1;
insert into attachs(name, item_id) VALUES ('database must be accessible from outside', 1);
insert into attachs(name, item_id) VALUES ('script must be in SQL-format', 2);

delete from role_rules;
ALTER SEQUENCE role_rules_id_seq RESTART WITH 1;
insert into role_rules(role_id, rules_id) VALUES (1, 1);
insert into role_rules(role_id, rules_id) VALUES (2, 2);
insert into role_rules(role_id, rules_id) VALUES (3, 4);
insert into role_rules(role_id, rules_id) VALUES (4, 3);
