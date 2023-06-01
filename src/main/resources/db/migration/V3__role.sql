CREATE TABLE roles(
    `id` INTEGER,   
    `name` VARCHAR(32) NOT NULL, 
    PRIMARY KEY(`id`)
);


CREATE TABLE user_role(
    `user_id` VARCHAR(32) NOT NULL,    
    `role_id` INTEGER,  
    PRIMARY KEY (`user_id`, `role_id`)
);

INSERT INTO roles(`id`, `name`) VALUES(1, 'ROLE_GENERAL');
INSERT INTO roles(`id`, `name`) VALUES(2, 'ROLE_ADMIN');

INSERT INTO user_role(`user_id`, `role_id`) VALUES('admin', 2);
INSERT INTO user_role(`user_id`, `role_id`) VALUES('general', 1);
