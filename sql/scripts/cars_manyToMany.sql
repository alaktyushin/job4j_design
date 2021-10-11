 create table engine(
     id serial primary key,
     name varchar(100)
 );

 create table model(
     id serial primary key,
	 maker_id int references maker(id),
     name varchar(100)
 );

 create table engines(
     id serial primary key,
     model_id int references model(id),
	 engine_id int references engine(id)
 );
