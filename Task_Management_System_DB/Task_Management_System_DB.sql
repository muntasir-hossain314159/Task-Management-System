DROP DATABASE task_management_system_db;
CREATE DATABASE IF NOT EXISTS task_management_system_db ;
SET SQL_SAFE_UPDATES = 0;
USE task_management_system_db;
DROP TABLE IF EXISTS admin_account, new_user_account, approved_user_account, task; 

CREATE TABLE admin_account (
							Admin_ID		INT				NOT NULL,
                            Admin_username	VARCHAR(50)		NOT NULL	UNIQUE,
                            Admin_password	VARCHAR(50)		NOT NULL,
                            
                            PRIMARY KEY(Admin_ID));
                            
CREATE TABLE new_user_account (
								User_ID				INT				NOT NULL	AUTO_INCREMENT,
								Username			VARCHAR(50)		NOT NULL	UNIQUE,
								User_password		VARCHAR(50)		NOT NULL,
                                Admin_ID			INT				NOT NULL,
								
								PRIMARY KEY(User_ID),
                                FOREIGN KEY (Admin_ID) REFERENCES admin_account(Admin_ID)
									ON UPDATE CASCADE
									ON DELETE CASCADE);
                                
CREATE TABLE approved_user_account (
									Approved_user_ID		INT				NOT NULL,
									Approved_username		VARCHAR(50)		NOT NULL 		UNIQUE,
									Approved_user_password	VARCHAR(50)		NOT NULL,
									
									PRIMARY KEY(Approved_user_ID));
                                        
CREATE TABLE task (
					Task_ID					INT 			NOT NULL	 AUTO_INCREMENT,
                    User_ID					INT 			NOT NULL,
                    Start_date_time			TIMESTAMP		NOT NULL,		
                    End_date_time			TIMESTAMP		NOT NULL,
                    Duration				INT				NOT NULL,
                    Title					VARCHAR(50),
                    Description_of_task		VARCHAR(100),
                    
                    PRIMARY KEY (Task_ID),
                    FOREIGN KEY (User_ID) REFERENCES approved_user_account(Approved_user_ID)
							ON UPDATE CASCADE
							ON DELETE CASCADE);
-- --------------------------------------------------------------------------------------------------
INSERT into admin_account VALUES (1, 'Anta', 'JAVA');
INSERT into admin_account VALUES (2, 'Emily', 'JAVA');
INSERT into admin_account VALUES (3, 'Jake', 'JAVA');
INSERT into admin_account VALUES (4, 'Nate', 'JAVA');
INSERT into admin_account VALUES (5, 'Muntasir', 'JAVA');
-- --------------------------------------------------------------------------------------------------
SELECT * FROM admin_account;
SELECT * FROM new_user_account;
SELECT * FROM approved_user_account;
SELECT * FROM task;
                         
                            
                            
                    
                    
                    
					
                                        
                                       
									
								


