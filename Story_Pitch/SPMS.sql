create table Story_Pitch(
	Story_id serial primary key,
	Person_id int not null,
	tent_Title varchar(30) unique not null,
	tent_comp_date date not null,
	type_id int ,
	genre_id int ,
	tag_line varchar(30),
	description varchar(100) default null,
	status_id int default 1
);

insert into story_pitch values 
(default,2,'Something','03-10-2021',1,1,'horror,novel','dabgdasiuhgakhdasg',1),
(default,2,'Something1','03-16-2021',4,2,'comedy,article','dsgaasdgsagfdj',1),
(default,2,'Test1','03-10-2021',1,1,'horror,novel','dabgdasiuhgakhdasg',1),
(default,2,'Test2','03-16-2021',3,4,'asdf,asdf','dsgaasdgsagfdj',1),
(default,2,'Test3','03-20-2021',1,3,'asdf,adsf','dsgaasdgsagfdj',1),
(default,2,'Test4','03-11-2021',4,2,'adsf,afs','dsgaasdgsagfdj',1),
(default,2,'Test5','03-17-2021',2,4,'adf,adsf','dsgaasdgsagfdj',1),
(default,2,'Test6','03-19-2021',3,1,'adfs,adsf','dsgaasdgsagfdj',1);

create table Person(
	person_id SERIAL primary key,
	f_name varchar(30),
	l_name varchar(30),
	email varchar(30) unique,
	passwd varchar(30),
	role_id int default 4,
	weight int default null,
	genre_id int default null	
);

insert into Person values 
(default, 'Author1', 'AuthorL','author1@Author.com','pass',4,0,null),
(default, 'Author2', 'Author2L','author2@Author.com','pass',4,0,null),
(default, 'name1','last1','assistEditH@email.com','pass',1,null,1),
(default, 'name2','last2','generalEditH@email.com','pass',2,null,1),
(default, 'name3','last3','seniorEditH@email.com','pass',3,null,1),
(default, 'name4','last4','assistEditA@email.com','pass',1,null,4),
(default, 'name5','last5','generalEditA@email.com','pass',2,null,4),
(default, 'name6','last6','seniorEditA@email.com','pass',3,null,4);

create table Person_Role(
	role_id int primary key,
	role_name varchar(30)
);

insert into Person_Role values (1,'Assistant Editor'),(2,'General Editor'),(3,'Senior Editor'),(4,'Author');


create table Genre(
	genre_id serial primary key,
	genre_name varchar(30)
);

insert into genre values (default,'Horror'),(default,'Comedy'),(default,'Drama'),(default,'Action'),(default,'Other');

create table Story_Type(
	type_id int primary key,
	type_name varchar(30),
	weight int
);

insert into Story_Type values (1,'Novel',50),(2,'Novella',25),(3,'Short Story', 20),(4,'Article',10);

create table Status (
	status_id int primary key,
	status_name varchar(30)
);

insert into Status values (1, 'Pending'),(2,'On Hold'),(3,'Approved');

create table Story_History(
	history_id SERIAL primary key,
	story_id int, 
	assistant_id int, 
	senior_id int,
	general_id int, 
	info_request varchar(100) default null,
	constraint FK_story_id FOREIGN key (story_id) references Story_Pitch(story_id),
	constraint FK_assistant_id foreign key (assistant_id) references Person(person_id),
	constraint FK_senior_id foreign key (senior_id) references Person(person_id),
	constraint FK_general_id foreign key (general_id) references Person(person_id)
);




alter table Person add constraint FK_Person_Role
	foreign key (role_id) references Person_Role(role_id);

alter table Story_Pitch add constraint FK_Story_Type 
	foreign key (type_id) references Story_Type(type_id);

alter table story_pitch add constraint FK_genre
	foreign key (genre_id) references genre(genre_id);

alter table Person add constraint FK_genre
	foreign key (genre_id) references Genre(genre_id);

alter table Story_Pitch add constraint FK_Person
	foreign key (person_id) references Person(person_id);

alter table Story_Pitch add constraint FK_Status
	foreign key (status_id) references Status(status_id);


select * from story_pitch;
select * from status;
select * from story_type;
select * from Person;
select * from person_role;
select * from genre;
select * from Story_History;



















