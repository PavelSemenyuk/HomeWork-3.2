SELECT * from student;
ALTER TABLE student
ADD CONSTRAINT age_constraint CHECK (age >= 16);
ALTER TABLE student
ALTER COLUMN name SET NOT NULL;
ALTER TABLE student
ADD CONSTRAINT name_unique UNIQUE (name);
ALTER TABLE student
ALTER COLUMN age SET DEFAULT 20;




