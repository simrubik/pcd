CREATE TABLE project
(
project_id SERIAL PRIMARY KEY, 
title varchar(45), 
description varchar(45), 
year varchar(4)
);

CREATE TABLE student
(
student_id SERIAL PRIMARY KEY, 
name varchar(45), 
cnp varchar(45), 
placeOfBirth varchar(45)
);

CREATE TABLE project_student
(
project_id integer, 
student_id integer
);

insert into student values(1, 'ana', '123456', 'focsani');
insert into student values(1, 'adi', '223456', 'suceava');
insert into student values(1, 'irina', '123456', 'bucuresti');
insert into student values(1, 'ionel', '123456', 'focsani');

insert into project values(1, 'proj1', 'proj1', '1');

insert into project_student values ();
SELECT s FROM Project p INNER JOIN Student s ON (s.id = p.students.id) WHERE p.id = :project_id