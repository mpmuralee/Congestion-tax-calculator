create table toll_free_vehicle (
    id bigint not null auto_increment,
    vehicle_type varchar(255),
    primary key (id)
);


insert into toll_free_vehicle (vehicle_type) values ('Emergency vehicles');
insert into toll_free_vehicle (vehicle_type) values ('Busses');
insert into toll_free_vehicle (vehicle_type) values ('Diplomat vehicles');
insert into toll_free_vehicle (vehicle_type) values ('Motorcycles');
insert into toll_free_vehicle (vehicle_type) values ('Military vehicles');
insert into toll_free_vehicle (vehicle_type) values ('Foreign vehicles');


-- Create the congestion_tax table
CREATE TABLE congestion_tax (
id BIGINT NOT NULL AUTO_INCREMENT,
from_time TIME NOT NULL,
to_time TIME NOT NULL,
amount INT,
city VARCHAR(255),
PRIMARY KEY (id)
);

-- Insert the data into the congestion_tax table
INSERT INTO congestion_tax (from_time, to_time, amount, city)
VALUES
('06:00:00', '06:29:00', 8, 'Gothenburg'),
('06:30:00', '06:59:00', 13, 'Gothenburg'),
('07:00:00', '07:59:00', 18, 'Gothenburg'),
('08:00:00', '08:29:00', 13, 'Gothenburg'),
('08:30:00', '14:59:00', 8, 'Gothenburg'),
('15:00:00', '15:29:00', 13, 'Gothenburg'),
('15:30:00', '16:59:00', 18, 'Gothenburg'),
('17:00:00', '17:59:00', 13, 'Gothenburg'),
('18:00:00', '18:29:00', 8, 'Gothenburg'),
('18:30:00', '05:59:00', 0, 'Gothenburg');

