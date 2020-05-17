INSERT INTO company
            (NAME,
             address,
             created_by,
             created_at,
             updated_by,
             updated_at)
VALUES      ( '統一企業股份有限公司',
              '110台北市信義區松高路9號',
              'admin',
              Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'));  
INSERT INTO company
            (NAME,
             address,
             created_by,
             created_at,
             updated_by,
             updated_at)
VALUES      ( '全家便利商店股份有限公司',
              '台北市中山區中山北路二段61號7樓',
              'admin',
              Parsedatetime('2020-05-08 23:26:37', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-08 23:26:37', 'yyyy-MM-dd HH:mm:ss'));                
INSERT INTO company
            (NAME,
             address,
             created_by,
             created_at,
             updated_by,
             updated_at)
VALUES      ( '萊爾富國際股份有限公司',
              '台北市內湖區瑞光路502號3樓 ',
              'admin',
              Parsedatetime('2020-05-08 23:27:42', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-08 23:27:42', 'yyyy-MM-dd HH:mm:ss'));                
                                       
              
INSERT INTO client
            (company_id,
             NAME,
             email,
             phone,
             created_by,
             created_at,
             updated_by,
             updated_at)              
VALUES      ( 1,
              'Adam',
              'tsungming@test.com',
              '0999889988',
              'admin',
              Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-10 13:30:00', 'yyyy-MM-dd HH:mm:ss'));      
              
INSERT INTO client
            (company_id,
             NAME,
             email,
             phone,
             created_by,
             created_at,
             updated_by,
             updated_at)
VALUES      ( 1,
              'Jerry',
              'daxiong@test.com',
              '07-343-3433',
              'admin',
              Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-10 13:30:00', 'yyyy-MM-dd HH:mm:ss'));           

INSERT INTO client
            (company_id,
             NAME,
             email,
             phone,
             created_by,
             created_at,
             updated_by,
             updated_at)              
VALUES      ( 1,
              'Ann',
              'jian@test.com',
              '04-223-4334#123',
              'admin',
              Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-10 13:30:00', 'yyyy-MM-dd HH:mm:ss'));           

INSERT INTO client
            (company_id,
             NAME,
             email,
             phone,
             created_by,
             created_at,
             updated_by,
             updated_at)
VALUES      ( 2,
              '王曉明',
              'xiaoming@test.com',
              '02-2722-3743',
              'admin',
              Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-10 13:30:00', 'yyyy-MM-dd HH:mm:ss'));

INSERT INTO client
            (company_id,
             NAME,
             email,
             phone,
             created_by,
             created_at,
             updated_by,
             updated_at)
VALUES      ( 2,
              '陳小美',
              'xiaomei@test.com',
              '091234567',
              'admin',
              Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-10 13:30:00', 'yyyy-MM-dd HH:mm:ss'));

              
INSERT INTO client
            (company_id,
             NAME,
             email,
             phone,
             created_by,
             created_at,
             updated_by,
             updated_at)
VALUES      ( 3,
              '阿笠博士',
              'ali@test.com',
              '0966663333',
              'admin',
              Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-10 13:30:00', 'yyyy-MM-dd HH:mm:ss'));    
              
              
INSERT INTO client
            (company_id,
             NAME,
             email,
             phone,
             created_by,
             created_at,
             updated_by,
             updated_at)
VALUES      ( 3,
              '工藤新一',
              'xinyi@test.com',
              '0954328765',
              'admin',
              Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-10 13:30:00', 'yyyy-MM-dd HH:mm:ss'));

INSERT INTO client
            (company_id,
             NAME,
             email,
             phone,
             created_by,
             created_at,
             updated_by,
             updated_at)
VALUES      ( 3,
              '服部平次',
              'pingci@test.com',
              '02-2822-2282',
              'admin',
              Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
              'admin',
              Parsedatetime('2020-05-10 13:30:00', 'yyyy-MM-dd HH:mm:ss'));                      

INSERT INTO ROLE(NAME_ID,NAME,PERMISSION_ID,PERMISSION_NAME) values (1,'Adam',1,'Super User');
INSERT INTO ROLE(NAME_ID,NAME,PERMISSION_ID,PERMISSION_NAME) values (2,'Jerry',2,'Manager');
INSERT INTO ROLE(NAME_ID,NAME,PERMISSION_ID,PERMISSION_NAME) values (3,'Ann',3,'Operator');
INSERT INTO ROLE(NAME_ID,NAME,PERMISSION_ID,PERMISSION_NAME) values (4,'王曉明',1,'Super User');
INSERT INTO ROLE(NAME_ID,NAME,PERMISSION_ID,PERMISSION_NAME) values (5,'陳小美',3,'Operator');
INSERT INTO ROLE(NAME_ID,NAME,PERMISSION_ID,PERMISSION_NAME) values (6,'阿笠博士',1,'Super User');
INSERT INTO ROLE(NAME_ID,NAME,PERMISSION_ID,PERMISSION_NAME) values (7,'工藤新一',2,'Manager');     
INSERT INTO ROLE(NAME_ID,NAME,PERMISSION_ID,PERMISSION_NAME) values (8,'服部平次',3,'Operator');

INSERT INTO Password
            (name_id,
             api_key,
             created_by,
             created_at,
             updated_by,
             updated_at,
             pwd_comment)
VALUES      (1,
			 HASH('SHA256', STRINGTOUTF8('12345'), 1000),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             '12345');

INSERT INTO Password
            (name_id,
             api_key,
             created_by,
             created_at,
             updated_by,
             updated_at,
             pwd_comment)
VALUES      (2,
			 HASH('SHA256', STRINGTOUTF8('abced'), 1000),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'abced');
             
INSERT INTO Password
            (name_id,
             api_key,            
             created_by,
             created_at,
             updated_by,
             updated_at,
             pwd_comment)
VALUES      (3,
			 HASH('SHA256', STRINGTOUTF8('abc123'), 1000),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'abc123');
             
INSERT INTO Password
            (name_id,
             api_key,            
             created_by,
             created_at,
             updated_by,
             updated_at,
             pwd_comment)
VALUES      (4,
			 HASH('SHA256', STRINGTOUTF8('987654'), 1000),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             '987654');
             
INSERT INTO Password
            (name_id,
             api_key,            
             created_by,
             created_at,
             updated_by,
             updated_at,
             pwd_comment)
VALUES      (5,
			 HASH('SHA256', STRINGTOUTF8('12345'), 1000),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             '12345');
             
INSERT INTO Password
            (name_id,
             api_key,            
             created_by,
             created_at,
             updated_by,
             updated_at,
             pwd_comment)
VALUES      (6,
			 HASH('SHA256', STRINGTOUTF8('78910'), 1000),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             '78910');
             
INSERT INTO Password
            (name_id,
             api_key,            
             created_by,
             created_at,
             updated_by,
             updated_at,
             pwd_comment)
VALUES      (7,
			 HASH('SHA256', STRINGTOUTF8('qwert'), 1000),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'qwert');
             
INSERT INTO Password
            (name_id,
             api_key,            
             created_by,
             created_at,
             updated_by,
             updated_at,
             pwd_comment)
VALUES      (8,
			 HASH('SHA256', STRINGTOUTF8('3254'), 1000),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             'admin',
             Parsedatetime('2020-05-08 23:24:20', 'yyyy-MM-dd HH:mm:ss'),
             '3254');             
