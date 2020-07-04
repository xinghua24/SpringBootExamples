insert into user(`username`, `password`, `enabled`, `created`) values( 'user', 'pass', true, '2019-01-02');

insert into role(`name`) values('user');
insert into role(`name`) values('admin');

insert into user_role(`user_id`, `role_id`) values(1, 1);
insert into user_role(`user_id`, `role_id`) values(1, 2);
