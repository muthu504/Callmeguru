create user guru identified by guru;
grant create session to guru;
grant dba to guru;
alter user guru quota unlimited on system;
connect guru/guru;

drop table admin;
create table admin(
username varchar2(20) ,
password varchar2(20) 
);


drop table customer;
create table customer(
cid number(10) primary key,
username varchar2(20),
password varchar2(20) ,
email varchar2(30) unique,
phone varchar2(15),
gender varchar2(10),
address varchar2(50)
);

drop table providers;
create table providers(
pid number(4),
username varchar2(40),
password varchar2(40),
typeofservice varchar2(30),
phone varchar2(40),
email varchar2(40),
address varchar2(40),
status varchar2(40) default 'inactive',
reason varchar2(80) default 'no'
);

drop table problems;
create table problems(
probid number(4),
provid number(4),
cid number(4),
typeofservice varchar2(40),
description varchar2(40),
phone varchar2(40),
cost number(5),
location varchar2(40),
request_time varchar(30),
acceptime varchar2(40),
reachtime varchar2(40),
status varchar2(20) default 'pending'
);


drop table emergency;
create table emergency(
req_id number(4),
pname varchar2(40),
phone varchar2(40),
description varchar2(40),
location varchar2(40),
request_time varchar(30),
acceptime varchar2(40),
status varchar2(10) default 'pending'
);

drop table providermessages;
create table providermessages(
email varchar2(40) unique,
phone varchar2(20),
subject varchar2(40),
description varchar2(100)
);

drop table customermessages;
create table customermessages(
email varchar2(40) unique,
phone varchar2(20),
subject varchar2(40),
description varchar2(100)
);


insert into admin(username,password) values('admin','admin');
insert into customer(cid,username,password,email,phone,gender,address) values(1,'somu','somu','somunadhm26@gmail.com','7989349738','Male','seethammadara');
insert into providers(pid,username,password,typeofservice,phone,email,address) values(1,'vasanth','vasanth','Mechanic','828585585','saivasant1999@gmail.com','M.v.p');
insert into emergency(req_id,pname,phone,description,location) values(1,'rajesh','8297887558','accedent','m.v.p double road,near kankatala'); 
insert into problems(probid,cid,typeofservice,description,location,request_time) values(1,1,'Mechanic','bike repair','gajuwaka','8:30:AM');

insert into providermessages(email,phone,subject,description) values('yaswanthrock566@gmail.com','8297887558','Activation','Plese activate my Account');
insert into customermessages(email,phone,subject,description) values('saivasant1999@gmail.com','8790261497','compliant','i have a problem regarding this provider');

commit;

