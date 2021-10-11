create table passport(
     id serial primary key,
     dateIssued date
 );

 create table car(
     id serial primary key,
	 model_id int references model(id),
     color varchar(100),
	 passport_id int references passport(id) unique
 );
