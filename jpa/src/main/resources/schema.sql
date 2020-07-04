CREATE TABLE `user` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`username` varchar(255) NOT NULL,
`password` varchar(255) NOT NULL,
`enabled` boolean,
`created` date,
PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `user_role` (
`user_id` bigint(11) NOT NULL,
`role_id` bigint(11) NOT NULL,
FOREIGN KEY (`user_id`) REFERENCES user(`id`),
FOREIGN KEY (`role_id`) REFERENCES role(`id`)
);