CREATE TABLE Company(
id int(5) auto_increment not null primary key,
name char(20) not null,
address char(80) not null,
created_by char(20) not null,
created_at timestamp not null,
updated_by char(20) not null,
updated_at timestamp not null
);

CREATE TABLE Client(
id int(5) auto_increment not null primary key,
company_id char(20) not null,
name char(20) not null,
email char(80),
phone chaR(20),
created_by char(20) not null,
created_at timestamp not null,
updated_by char(20) not null,
updated_at timestamp not null
);

CREATE TABLE Role(
name_id int(5) not null,
name char(20) not null,
permission_id int(5) not null,
permission_name char(20) not null
);

CREATE TABLE Password(
name_id int(5) not null,
api_key char(80) not null,
created_by char(20) not null,
created_at timestamp not null,
updated_by char(20) not null,
updated_at timestamp not null,
pwd_comment char(80)
);