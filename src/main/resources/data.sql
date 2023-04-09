CREATE TABLE users(
id int NOT NULL AUTO_INCREMENT,
first_name varchar NOT NULL,
last_name varchar NOT NULL,
email varchar NOT NULL,
phone varchar,
address varchar,
is_connected boolean NOT NULL default false,
PRIMARY KEY (id)
);

CREATE TABLE items(
id int NOT NULL AUTO_INCREMENT,
title varchar NOT NULL,
picture varchar NOT NULL,
usd_price double NOT NULL,
amount int NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO users (first_name, last_name, email, phone, address)
VALUES('a', 'aa', 'a@gmail.com', '0524436671', 'aen eliezer 45, netanya'),
('b', 'bb', 'b@gmail.com', '0504380333', 'ben eliezer 45, netanya'),
('c', 'cc', 'c@gmail.com', '0524125553', 'cen eliezer 45, netanya'),
('d', 'dd', 'd@gmail.com', '0523715940', 'den eliezer 45, netanya'),
('e', 'ee', 'e@gmail.com', '0548976525', 'een eliezer 45, netanya'),
('f', 'ff', 'f@gmail.com', '0527468720', 'fen eliezer 45, netanya'),
('g', 'gg', 'g@gmail.com', '0554809620', 'gen eliezer 45, netanya'),
('h', 'hh', 'h@gmail.com', '0508887777', 'hen eliezer 45, netanya'),
('i', 'ii', 'i@gmail.com', '0556632241', 'ien eliezer 45, netanya'),
('j', 'jj', 'j@gmail.com', '0536645528', 'jen eliezer 45, netanya');

INSERT INTO items (title, picture, usd_price, amount)
VALUES('helmet', 'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/613loUzm9WL._SL1500_.jpg', 15, 50),
('clock', 'https://www.ikea.com/us/en/images/products/pluttis-wall-clock-black__1038559_pe839733_s5.jpg', 34, 1000),
('TV', 'https://media.istockphoto.com/id/638043774/photo/modern-curved-4k-ultrahd-tv.jpg?s=612x612&w=0&k=20&c=ZJBK7-64tG3uPBtXuUnWt-lAPSqz_nBlYXNYsVtmRtc=', 595, 30),
('flowerpot', 'https://upload.wikimedia.org/wikipedia/commons/9/9a/%22Meillandine%22_Rose_in_clay_pot.jpg', 20, 100),
('oven', 'https://universal.bertazzoni.com/media/immagini/13061_z_F6011PRO-TX.jpg', 750, 200),
('scooter', 'https://www.mi-il.co.il/images/site/products/9bfeeaf9-2992-4d65-930c-11da2e87fb0f.jpg', 920, 60),
('book', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbXCpiYKfm11YUjU715AE4xto0XO6fzBiL8Q&usqp=CAU', 25, 2000),
('air-conditioner', 'https://zabilo.com/24070-large_default/electra-top-air-conditioner-2hp-19551-btu-cooling-output-wifi-exclusive-23.jpg', 550, 80),
('briefcase', 'https://www.caseluggage.com/media/catalog/product/cache/c7f0fef5d6f0c21800fabe206ec678b5/c/a/carl_cfc8802_cognac_m_1.jpg', 150, 20),
('chair', 'https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/71mKwaKglhL._SL1500_.jpg', 230, 10),
('picture', 'https://target.scene7.com/is/image/Target/WallArt_QUIVER-210303-1614805155207', 45, 40);

CREATE TABLE orders(
id int NOT NULL AUTO_INCREMENT,
user_id int NOT NULL,
order_date date DEFAULT current_date,
shipping_address varchar NOT NULL,
total_price double NOT NULL default 0,
status varchar NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE item_to_user(
item_id int NOT NULL,
user_id int NOT NULL,
FOREIGN KEY (item_id) REFERENCES items (id),
FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE item_to_order(
item_id int NOT NULL,
order_id int NOT NULL,
FOREIGN KEY (item_id) REFERENCES items (id),
FOREIGN KEY (order_id) REFERENCES orders (id)
);

