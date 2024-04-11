create database hosiptal;


create table patient( patient_id int PRIMARY KEY NOT NULL,
                       name varchar(100),
                       age int,
                       gender varchar(10),
                       contact_information int(10),
                       join_date date,
					   disease varchar(100),
                       medical_history varchar(100)
                       );

create table appointment(appointment_id int PRIMARY KEY NOT NULL,
                      patient_id int ,
                      staff_id int,
                      date date,
                      status varchar(50)
                      );
					
create table staff ( staff_id int PRIMARY KEY NOT NULL,
                     name varchar(100),
                     role varchar(100),
                     department_id int);
                     
                     
create table department ( department_id int,
                          department_name varchar(100),
                          description varchar(400));


select * from appontment;


select * from department;



select * from patient;



select * from staff;

                      
