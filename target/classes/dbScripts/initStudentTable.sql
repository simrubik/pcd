CREATE TABLE student
(
student_id SERIAL PRIMARY KEY, 
name varchar(45), 
cnp varchar(45), 
placeOfBirth varchar(45)
);

--DROP TABLE student;
--
--CREATE TABLE student(student_id varchar(45), name varchar(45), cnp varchar(45), placeOfBirth varchar(45));
--
----INSERT INTO student(student_id, name , cnp, placeOfBirth) VALUES ('1', 'ana', '123', 'fcs');
--
--SELECT * FROM student;
--
--CREATE SEQUENCE STUDENT_SEQ
--   START WITH 1
--   MAXVALUE 9999999
--   MINVALUE 1;
--
--CREATE OR REPLACE FUNCTION GetNextStudent()
--   RETURNS "trigger" AS
--   $BODY$
--   BEGIN
--     New.student_id:=nextval('STUDENT_SEQ');
--     Return NEW;
--   END;
--   $BODY$
--   LANGUAGE 'plpgsql' VOLATILE;
-- 
--CREATE TRIGGER "STUDENT_SEQ_NEXT"  
--  BEFORE INSERT ON "student"              
--  FOR EACH ROW
--  EXECUTE PROCEDURE GetNextStudent();