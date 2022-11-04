DROP TABLE IF EXISTS author CASCADE;
CREATE TABLE author
(
    AU_ID    bigint NOT NULL,
    address  varchar(255)   default NULL,
    AU_FNAME varchar(255)   default NULL,
    AU_LNAME varchar(255)   default NULL,
    city     varchar(255)   default NULL,
    contract decimal(19, 2) default NULL,
    phone    varchar(16)    default NULL,
    state    varchar(2)     default NULL,
    zip      varchar(16)    default NULL,
    PRIMARY KEY (AU_ID)
);

--
-- Dumping data for table author
--

ALTER TABLE author
    DISABLE TRIGGER ALL;
INSERT INTO author (AU_ID, address, AU_FNAME, AU_LNAME, city, contract, phone, state, zip)
VALUES (1, '10932 Bigge Rd.', 'Johnson', 'White', 'Menlo Park', '1.00', '408 496-7223', 'CA', '94025'),
       (2, '309 63rd St. #411', 'Marjorie', 'Green', 'Oakland', '1.00', '415 986-7020', 'CA', '94618'),
       (3, '589 Darwin Ln.', 'Cheryl', 'Carson', 'Berkeley', '1.00', '415 548-7723', 'CA', '94705'),
       (4, '22 Cleveland Av. #14', 'Michael', 'O''Leary', 'San Jose', '1.00', '408 286-2428', 'CA',
        '95128'),
       (5, '5420 College Av.', 'Dean', 'Straight', 'Oakland', '1.00', '415 834-2919', 'CA', '94609'),
       (6, '10 Mississippi Dr.', 'Meander', 'Smith', 'Lawrence', '0.00', '913 843-0462', 'KS', '66044'),
       (7, '6223 Bateman St.', 'Abraham', 'Bennet', 'Berkeley', '1.00', '415 658-9932', 'CA', '94705'),
       (8, '3410 Blonde St.', 'Ann', 'Dull', 'Palo Alto', '1.00', '415 836-7128', 'CA', '94301'),
       (9, 'PO Box 792', 'Burt', 'Gringlesby', 'Covelo', '1.00', '707 938-6445', 'CA', '95428'),
       (10, '18 Broadway Av.', 'Charlene', 'Locksley', 'San Francisco', '1.00', '415 585-4620', 'CA',
        '94130'),
       (11, '22 Graybar House Rd.', 'Morningstar', 'Greene', 'Nashville', '0.00', '615 297-2723', 'TN',
        '37215'),
       (12, '55 Hillsdale Bl.', 'Reginald', 'Blotchet-Halls', 'Corvallis', '1.00', '503 745-6402', 'OR',
        '97330'),
       (13, '3 Silver Ct.', 'Akiko', 'Yokomoto', 'Walnut Creek', '1.00', '415 935-4228', 'CA', '94595'),
       (14, '2286 Cram Pl. #86', 'Innes', 'del Castillo', 'Ann Arbor', '1.00', '615 996-8275', 'MI',
        '48105'),
       (15, '3 Balding Pl.', 'Michel', 'DeFrance', 'Gary', '1.00', '219 547-9982', 'IN', '46403'),
       (16, '5420 Telegraph Av.', 'Dirk', 'Stringer', 'Oakland', '0.00', '415 843-2991', 'CA', '94609'),
       (17, '44 Upland Hts.', 'Stearns', 'MacFeather', 'Oakland', '1.00', '415 354-7128', 'CA', '94612'),
       (18, '5720 McAuley St.', 'Livia', 'Karsen', 'Oakland', '1.00', '415 534-9219', 'CA', '94609'),
       (19, '1956 Arlington Pl.', 'Sylvia', 'Panteley', 'Rockville', '1.00', '301 946-8853', 'MD', '20853'),
       (20, '3410 Blonde St.', 'Sheryl', 'Hunter', 'Palo Alto', '1.00', '415 836-7128', 'CA', '94301'),
       (21, '301 Putnam', 'Heather', 'McBadden', 'Vacaville', '0.00', '707 448-4982', 'CA', '95688'),
       (22, '67 Seventh Av.', 'Anne', 'Ringer', 'Salt Lake City', '1.00', '801 826-0752', 'UT', '84152'),
       (23, '67 Seventh Av.', 'Albert', 'Ringer', 'Salt Lake City', '1.00', '801 826-0752', 'UT', '84152');

ALTER TABLE author
    ENABLE TRIGGER ALL;

--
-- Definition of table publisher
--

DROP TABLE IF EXISTS publisher CASCADE;
CREATE TABLE publisher
(
    PUB_ID   bigint NOT NULL,
    city     varchar(255) default NULL,
    country  varchar(255) default NULL,
    PUB_NAME varchar(255) default NULL,
    state    varchar(2)   default NULL,
    PRIMARY KEY (PUB_ID)
);

--
-- Dumping data for table publisher
--

ALTER TABLE publisher
    DISABLE TRIGGER ALL;
INSERT INTO publisher (PUB_ID, city, country, PUB_NAME, state)
VALUES (1, 'Boston', 'USA', 'New Moon Books', 'MA'),
       (2, 'Washington', 'USA', 'Binnet & Hardley', 'DC'),
       (3, 'Berkeley', 'USA', 'Algodata Infosystems', 'CA'),
       (4, 'Chicago', 'USA', 'Five Lakes Publishing', 'IL'),
       (5, 'Dallas', 'USA', 'Ramona Publishers', 'TX'),
       (6, 'M?nchen', 'Germany', 'GGG\\&G', NULL),
       (7, 'New York', 'USA', 'Scootney Books', 'NY'),
       (8, 'Paris', 'France', 'Lucerne Publishing', NULL);
ALTER TABLE publisher
    ENABLE TRIGGER ALL;


--
-- Definition of table title
--

DROP TABLE IF EXISTS title CASCADE;
CREATE TABLE title
(
    TITLE_ID bigint NOT NULL,
    advance  decimal(19, 2) default NULL,
    notes    varchar(255)   default NULL,
    price    decimal(19, 2) default NULL,
    pubdate  date           default NULL,
    royalty  decimal(19, 2) default NULL,
    title    varchar(255)   default NULL,
    type     varchar(12)    default NULL,
    YTD_SALE decimal(19, 2) default NULL,
    PUB_ID   bigint         default NULL REFERENCES publisher (PUB_ID),
    PRIMARY KEY (TITLE_ID)
);

--
-- Dumping data for table title
--

ALTER TABLE title
    DISABLE TRIGGER ALL;
INSERT INTO title (TITLE_ID, advance, notes, price, pubdate, royalty, title, type, YTD_SALE, PUB_ID)
VALUES (1, '5000.00',
        'An overview of available database systems with emphasis on common business applications. Illustrated.',
        '19.99', '2091-06-12', '10.00', 'The Busy Executive''s Database Guide', 'business', '4095.00', 3),
       (2, '5000.00', 'Helpful hints on how to use your electronic resources to the best advantage.', '11.95',
        '2091-06-09', '10.00', 'Cooking with Computers: Surreptitious Balance Sheets', 'business', '3876.00', 3),
       (3, '10125.00',
        'The latest medical and psychological techniques for living with the electronic office. Easy-to-understand explanations.',
        '2.99', '2091-06-30', '24.00', 'You Can Combat Computer Stress!', 'business', '18722.00', 1),
       (4, '5000.00',
        'Annotated analysis of what computers can do for you: a no-hype guide for the critical user.', '19.99',
        '2091-06-22', '10.00', 'Straight Talk About Computers', 'business', '4095.00', 3),
       (5, '0.00', 'Favorite recipes for quick, easy, and elegant meals.', '19.99', '2091-06-09', '12.00',
        'Silicon Valley Gastronomic Treats', 'mod_cook', '2032.00', 2),
       (6, '15000.00', 'Traditional French gourmet recipes adapted for modern microwave cooking.', '2.99',
        '2091-06-18', '24.00', 'The Gourmet Microwave', 'mod_cook', '22246.00', 2),
       (7, NULL, NULL, NULL, '2011-01-10', NULL, 'The Psychology of Computer Cooking', 'UNDECIDED', NULL,
        2),
       (8, '7000.00', 'A survey of software for the naive user, focusing on the ''friendliness'' of each.',
        '22.95', '2091-06-30', '16.00', 'But Is It User Friendly?', 'popular_comp', '8780.00', 3),
       (9, '8000.00',
        'Muckraking reporting on the world''s largest computer hardware and software manufacturers.', '20.00',
        '2094-06-12', '10.00', 'Secrets of Silicon Valley', 'popular_comp', '4095.00', 3),
       (10, NULL, 'A must-read for computer conferencing.', NULL, '2011-01-10', NULL, 'Net Etiquette',
        'popular_comp', NULL, 3),
       (11, '7000.00',
        'A must for the specialist, this book examines the difference between those who hate and fear computers and those who don''t.',
        '21.59', '2091-10-21', '10.00', 'Computer Phobic AND Non-Phobic Individuals: Behavior Variations', 'psychology',
        '375.00', 2),
       (12, '2275.00',
        'Carefully researched study of the effects of strong emotions on the body. Metabolic charts included.', '10.95',
        '2091-06-15', '12.00', 'Is Anger the Enemy?', 'psychology', '2045.00', 1),
       (13, '6000.00',
        'New exercise, meditation, and nutritional techniques that can reduce the shock of daily interactions. Popular audience. Sample menus included, exercise video available separately.',
        '7.00', '2091-10-05', '10.00', 'Life Without Fear', 'psychology', '111.00', 1),
       (14, '2000.00',
        'What happens when the data runs dry?  Searching evaluations of information-shortage effects.', '19.99',
        '2091-06-12', '10.00', 'Prolonged Data Deprivation: Four Case Studies', 'psychology', '4072.00', 1),
       (15, '4000.00',
        'Protecting yourself and your loved ones from undue emotional stress in the modern world. Use of computer and nutritional aids emphasized.',
        '7.99', '2091-06-12', '10.00', 'Emotional Security: A New Algorithm', 'psychology', '3336.00', 1),
       (16, '7000.00',
        'Profusely illustrated in color, this makes a wonderful gift book for a cuisine-oriented friend.', '20.95',
        '2091-10-21', '10.00', 'Onions, Leeks, and Garlic: Cooking Secrets of the Mediterranean', 'trad_cook', '375.00',
        2),
       (17, '4000.00',
        'More anecdotes from the Queen''s favorite cook describing life among English royalty. Recipes, techniques, tender vignettes.',
        '11.95', '2091-06-12', '14.00', 'Fifty Years in Buckingham Palace Kitchens', 'trad_cook', '15096.00', 2),
       (18, '8000.00', 'Detailed instructions on how to make authentic Japanese sushi in your spare time.',
        '14.99', '2091-06-12', '10.00', 'Sushi, Anyone?', 'trad_cook', '4095.00', 2);
ALTER TABLE title
    ENABLE TRIGGER ALL;


--
-- Definition of table titleauthor
--

DROP TABLE IF EXISTS titleauthor CASCADE;
CREATE TABLE titleauthor
(
    AU_ID      bigint NOT NULL REFERENCES author (AU_ID),
    TITLE_ID   bigint NOT NULL REFERENCES title (TITLE_ID),
    AU_ORD     decimal(19, 2) default NULL,
    royaltyper decimal(19, 2) default NULL,
    PRIMARY KEY (AU_ID, TITLE_ID)
);

--
-- Dumping data for table titleauthor
--

ALTER TABLE titleauthor
    DISABLE TRIGGER ALL;
INSERT INTO titleauthor (AU_ID, TITLE_ID, AU_ORD, royaltyper)
VALUES (1, 14, '1.00', '100.00'),
       (2, 1, '2.00', '40.00'),
       (2, 3, '1.00', '100.00'),
       (3, 8, '1.00', '100.00'),
       (4, 2, '2.00', '40.00'),
       (4, 18, '2.00', '30.00'),
       (5, 4, '1.00', '100.00'),
       (7, 1, '1.00', '60.00'),
       (8, 9, '1.00', '50.00'),
       (9, 18, '3.00', '30.00'),
       (10, 10, '1.00', '100.00'),
       (10, 15, '1.00', '100.00'),
       (12, 17, '1.00', '100.00'),
       (13, 18, '1.00', '40.00'),
       (14, 5, '1.00', '100.00'),
       (15, 6, '1.00', '75.00'),
       (17, 2, '1.00', '60.00'),
       (17, 11, '2.00', '25.00'),
       (18, 11, '1.00', '75.00'),
       (19, 16, '1.00', '100.00'),
       (20, 9, '2.00', '50.00'),
       (22, 6, '2.00', '25.00'),
       (22, 12, '2.00', '50.00'),
       (23, 12, '1.00', '50.00'),
       (23, 13, '1.00', '100.00');
ALTER TABLE titleauthor
    ENABLE TRIGGER ALL;



--
-- Definition of table store
--

DROP TABLE IF EXISTS store CASCADE;
CREATE TABLE store
(
    STOR_ID      bigint NOT NULL,
    city         varchar(255) default NULL,
    state        varchar(2)   default NULL,
    STOR_ADDRESS varchar(255) default NULL,
    STOR_NAME    varchar(255) default NULL,
    zip          varchar(5)   default NULL,
    PRIMARY KEY (STOR_ID)
);

--
-- Dumping data for table store
--

ALTER TABLE store
    DISABLE TRIGGER ALL;
INSERT INTO store (STOR_ID, city, state, STOR_ADDRESS, STOR_NAME, zip)
VALUES (9, 'Seattle', 'WA', '788 Catamaugus Ave.', 'Eric the Read Books', '98056'),
       (10, 'Tustin', 'CA', '567 Pasadena Ave.', 'Barnum''s', '92789'),
       (11, 'Los Gatos', 'CA', '577 First St.', 'News & Brews', '96745'),
       (12, 'Remulade', 'WA', '24-A Avogadro Way', 'Doc-U-Mat: Quality Laundry and Books', '98014'),
       (13, 'Fremont', 'CA', '89 Madison St.', 'Fricative Bookshop', '90019'),
       (14, 'Portland', 'OR', '679 Carson St.', 'Bookbeat', '89076');
ALTER TABLE store
    ENABLE TRIGGER ALL;



--
-- Definition of table discount
--

DROP TABLE IF EXISTS discount CASCADE;
CREATE TABLE discount
(
    DISCOUNT_ID  bigint NOT NULL,
    discount     decimal(19, 2) default NULL,
    discounttype varchar(40)    default NULL,
    highqty      decimal(19, 2) default NULL,
    lowqty       decimal(19, 2) default NULL,
    STOR_ID      bigint         default NULL REFERENCES store (STOR_ID),
    PRIMARY KEY (DISCOUNT_ID)
);

--
-- Dumping data for table discount
--

ALTER TABLE discount
    DISABLE TRIGGER ALL;
INSERT INTO discount (DISCOUNT_ID, discount, discounttype, highqty, lowqty, STOR_ID)
VALUES (1, '10.50', 'Initial Customer', NULL, NULL, NULL),
       (2, '6.70', 'Volume Discount', '1000.00', '100.00', NULL),
       (3, '5.00', 'Customer Discount', NULL, NULL, 14);
ALTER TABLE discount
    ENABLE TRIGGER ALL;

--
-- Definition of table job
--

DROP TABLE IF EXISTS job CASCADE;
CREATE TABLE job
(
    JOB_ID   bigint NOT NULL,
    JOB_DESC varchar(255)   default NULL,
    MAX_LVL  decimal(19, 2) default NULL,
    MIN_LVL  decimal(19, 2) default NULL,
    PRIMARY KEY (JOB_ID)
);

--
-- Dumping data for table job
--

ALTER TABLE job
    DISABLE TRIGGER ALL;
INSERT INTO job (JOB_ID, JOB_DESC, MAX_LVL, MIN_LVL)
VALUES (1, 'New Hire - Job not specified', '10.00', '10.00'),
       (2, 'Chief Executive Officer', '250.00', '200.00'),
       (3, 'Business Operations Manager', '225.00', '175.00'),
       (4, 'Chief Financial Officier', '250.00', '175.00'),
       (5, 'Publisher', '250.00', '150.00'),
       (6, 'Managing Editor', '225.00', '140.00'),
       (7, 'Marketing Manager', '200.00', '120.00'),
       (8, 'Public Relations Manager', '175.00', '100.00'),
       (9, 'Acquisitions Manager', '175.00', '75.00'),
       (10, 'Productions Manager', '165.00', '75.00'),
       (11, 'Operations Manager', '150.00', '75.00'),
       (12, 'Editor', '100.00', '25.00'),
       (13, 'Sales Representative', '100.00', '25.00'),
       (14, 'Designer', '100.00', '25.00');
ALTER TABLE job
    ENABLE TRIGGER ALL;

--
-- Definition of table publisher
--

DROP TABLE IF EXISTS publisher CASCADE;
CREATE TABLE publisher
(
    PUB_ID   bigint NOT NULL,
    city     varchar(255) default NULL,
    country  varchar(255) default NULL,
    PUB_NAME varchar(255) default NULL,
    state    varchar(2)   default NULL,
    PRIMARY KEY (PUB_ID)
);

--
-- Dumping data for table publisher
--

ALTER TABLE publisher
    DISABLE TRIGGER ALL;
INSERT INTO publisher (PUB_ID, city, country, PUB_NAME, state)
VALUES (1, 'Boston', 'USA', 'New Moon Books', 'MA'),
       (2, 'Washington', 'USA', 'Binnet & Hardley', 'DC'),
       (3, 'Berkeley', 'USA', 'Algodata Infosystems', 'CA'),
       (4, 'Chicago', 'USA', 'Five Lakes Publishing', 'IL'),
       (5, 'Dallas', 'USA', 'Ramona Publishers', 'TX'),
       (6, 'M?nchen', 'Germany', 'GGG\\&G', NULL),
       (7, 'New York', 'USA', 'Scootney Books', 'NY'),
       (8, 'Paris', 'France', 'Lucerne Publishing', NULL);
ALTER TABLE publisher
    ENABLE TRIGGER ALL;



--
-- Definition of table employee
--

DROP TABLE IF EXISTS employee CASCADE;
CREATE TABLE employee
(
    EMP_ID    bigint NOT NULL,
    fname     varchar(255)   default NULL,
    HIRE_DATE date           default NULL,
    JOB_LVL   decimal(19, 2) default NULL,
    lname     varchar(255)   default NULL,
    minit     varchar(1)     default NULL,
    JOB_ID    bigint         default NULL REFERENCES job (JOB_ID),
    PUB_ID    bigint         default NULL REFERENCES publisher (PUB_ID),
    PRIMARY KEY (EMP_ID)
);

--
-- Dumping data for table employee
--

ALTER TABLE employee
    DISABLE TRIGGER ALL;
INSERT INTO employee (EMP_ID, fname, HIRE_DATE, JOB_LVL, lname, minit, JOB_ID, PUB_ID)
VALUES (1, 'Aria', '2091-10-26', '87.00', 'Cruz', NULL, 10, 3),
       (2, 'Annette', '2090-02-21', '152.00', 'Roulet', NULL, 6, 8),
       (3, 'Ann', '2091-07-16', '200.00', 'Devon', 'M', 3, 7),
       (4, 'Anabela', '2093-01-27', '100.00', 'Domingues', 'R', 8, 2),
       (5, 'Carlos', '2089-04-21', '211.00', 'Hernadez', 'F', 5, 8),
       (6, 'Carine', '2092-07-07', '64.00', 'Schmitt', 'G', 13, 3),
       (7, 'Daniel', '2090-01-01', '75.00', 'Tonini', 'B', 11, 2),
       (8, 'Diego', '2091-12-16', '192.00', 'Roel', 'W', 6, 3),
       (9, 'Elizabeth', '2090-07-24', '35.00', 'Lincoln', 'N', 14, 2),
       (10, 'Francisco', '2090-11-03', '227.00', 'Chang', NULL, 4, 7),
       (11, 'Gary', '2088-08-09', '170.00', 'Thomas', 'H', 9, 1),
       (12, 'Helen', '2089-09-21', '35.00', 'Bennett', NULL, 12, 2),
       (13, 'Helvetius', '2093-03-19', '120.00', 'Nagy', 'A', 7, 8),
       (14, 'Howard', '2088-11-19', '100.00', 'Snyder', 'A', 12, 1),
       (15, 'Janine', '2091-05-26', '172.00', 'Labrune', 'Y', 5, 6),
       (16, 'Karin', '2092-10-17', '100.00', 'Josephs', 'F', 14, 1),
       (17, 'Karla', '2094-03-11', '170.00', 'Jablonski', 'J', 9, 8),
       (18, 'Lesley', '2091-02-13', '120.00', 'Brown', NULL, 7, 2),
       (19, 'Laurence', '2090-06-03', '175.00', 'Lebihan', 'A', 5, 1),
       (20, 'Maria', '2092-03-27', '135.00', 'Larsson', NULL, 7, 3),
       (21, 'Manuel', '2089-01-09', '101.00', 'Pereira', NULL, 8, 8),
       (22, 'Martine', '2092-02-05', '75.00', 'Rance', NULL, 9, 2),
       (23, 'Miguel', '2092-12-07', '112.00', 'Paolino', 'A', 11, 3),
       (24, 'Margaret', '2088-09-29', '78.00', 'Smith', 'A', 9, 3),
       (25, 'Martin', '2090-04-13', '165.00', 'Sommer', 'F', 10, 1),
       (26, 'Matti', '2094-05-01', '220.00', 'Karttunen', 'G', 6, 1),
       (27, 'Maria', '2089-03-01', '246.00', 'Pontes', 'J', 5, 5),
       (28, 'Mary', '2093-06-29', '175.00', 'Saveley', 'M', 8, 1),
       (29, 'Patricia', '2089-08-01', '150.00', 'McKenna', 'C', 11, 8),
       (30, 'Palle', '2093-05-09', '195.00', 'Ibsen', 'D', 7, 1),
       (31, 'Peter', '2092-05-17', '75.00', 'Franken', 'H', 10, 2),
       (32, 'Paolo', '2092-08-27', '35.00', 'Accorti', 'M', 13, 2),
       (33, 'Pirkko', '2093-11-29', '80.00', 'Koskitalo', 'O', 10, 8),
       (34, 'Pedro', '2090-12-24', '89.00', 'Afonso', 'S', 14, 3),
       (35, 'Paula', '2094-01-19', '125.00', 'Parente', 'S', 8, 3),
       (36, 'Philip', '2089-11-11', '215.00', 'Cramer', 'T', 2, 7),
       (37, 'Paul', '2093-08-19', '159.00', 'Henriot', 'X', 5, 2),
       (38, 'Roland', '2091-09-05', '150.00', 'Mendel', NULL, 11, 1),
       (39, 'Rita', '2093-10-09', '198.00', 'Muller', 'B', 5, 4),
       (40, 'Sven', '2091-04-05', '150.00', 'Ottlieb', 'K', 5, 3),
       (41, 'Timothy', '2088-06-19', '100.00', 'O''Rourke', 'P', 13, 1),
       (42, 'Victoria', '2090-09-13', '140.00', 'Ashworth', 'P', 6, 2),
       (43, 'Yoshi', '2089-06-11', '32.00', 'Latimer', NULL, 12, 3);
ALTER TABLE employee
    ENABLE TRIGGER ALL;



--
-- Definition of table pub_info
--

DROP TABLE IF EXISTS pub_info CASCADE;
CREATE TABLE pub_info
(
    PUB_ID  bigint NOT NULL,
    logo    bytea,
    PR_INFO text,
    PRIMARY KEY (PUB_ID)
);

--
-- Dumping data for table pub_info
--

ALTER TABLE pub_info
    DISABLE TRIGGER ALL;
INSERT INTO pub_info (PUB_ID, logo, PR_INFO)
VALUES (1, NULL, 'None yet'),
       (2, NULL, 'None yet'),
       (3, NULL, 'None yet'),
       (4, NULL, 'None yet'),
       (5, NULL, 'None yet'),
       (6, NULL, 'None yet'),
       (7, NULL, 'None yet'),
       (8, NULL, 'None yet');
ALTER TABLE pub_info
    ENABLE TRIGGER ALL;



--
-- Definition of table roysched
--

DROP TABLE IF EXISTS roysched CASCADE;
CREATE TABLE roysched
(
    ROY_ID   bigint NOT NULL,
    hirange  decimal(19, 2) default NULL,
    lorange  decimal(19, 2) default NULL,
    royalty  decimal(19, 2) default NULL,
    TITLE_ID bigint         default NULL REFERENCES title (TITLE_ID),
    PRIMARY KEY (ROY_ID)

);

--
-- Dumping data for table roysched
--

ALTER TABLE roysched
    DISABLE TRIGGER ALL;
INSERT INTO roysched (ROY_ID, hirange, lorange, royalty, TITLE_ID)
VALUES (1, '5000.00', '0.00', '10.00', 1),
       (2, '50000.00', '5001.00', '12.00', 1),
       (3, '2000.00', '0.00', '10.00', 8),
       (4, '3000.00', '2001.00', '12.00', 8),
       (5, '4000.00', '3001.00', '14.00', 8),
       (6, '10000.00', '4001.00', '16.00', 8),
       (7, '50000.00', '10001.00', '18.00', 8),
       (8, '1000.00', '0.00', '10.00', 3),
       (9, '3000.00', '1001.00', '12.00', 3),
       (10, '5000.00', '3001.00', '14.00', 3),
       (11, '7000.00', '5001.00', '16.00', 3),
       (12, '10000.00', '7001.00', '18.00', 3),
       (13, '12000.00', '10001.00', '20.00', 3),
       (14, '14000.00', '12001.00', '22.00', 3),
       (15, '50000.00', '14001.00', '24.00', 3),
       (16, '1000.00', '0.00', '10.00', 12),
       (17, '5000.00', '1001.00', '12.00', 12),
       (18, '10000.00', '5001.00', '14.00', 12),
       (19, '50000.00', '10001.00', '16.00', 12),
       (20, '2000.00', '0.00', '10.00', 13),
       (21, '5000.00', '2001.00', '12.00', 13),
       (22, '10000.00', '5001.00', '14.00', 13),
       (23, '50000.00', '10001.00', '16.00', 13),
       (24, '1000.00', '0.00', '10.00', 6),
       (25, '2000.00', '1001.00', '12.00', 6),
       (26, '4000.00', '2001.00', '14.00', 6),
       (27, '6000.00', '4001.00', '16.00', 6),
       (28, '8000.00', '6001.00', '18.00', 6),
       (29, '10000.00', '8001.00', '20.00', 6),
       (30, '12000.00', '10001.00', '22.00', 6),
       (31, '50000.00', '12001.00', '24.00', 6),
       (32, '2000.00', '0.00', '10.00', 16),
       (33, '4000.00', '2001.00', '12.00', 16),
       (34, '6000.00', '4001.00', '14.00', 16),
       (35, '8000.00', '6001.00', '16.00', 16),
       (36, '10000.00', '8001.00', '18.00', 16),
       (37, '12000.00', '10001.00', '20.00', 16),
       (38, '14000.00', '12001.00', '22.00', 16),
       (39, '50000.00', '14001.00', '24.00', 16),
       (40, '5000.00', '0.00', '10.00', 9),
       (41, '10000.00', '5001.00', '12.00', 9),
       (42, '15000.00', '10001.00', '14.00', 9),
       (43, '50000.00', '15001.00', '16.00', 9),
       (44, '5000.00', '0.00', '10.00', 15),
       (45, '50000.00', '5001.00', '12.00', 15),
       (46, '5000.00', '0.00', '10.00', 14),
       (47, '10000.00', '5001.00', '12.00', 14),
       (48, '15000.00', '10001.00', '14.00', 14),
       (49, '50000.00', '15001.00', '16.00', 14),
       (50, '4000.00', '0.00', '10.00', 2),
       (51, '8000.00', '4001.00', '12.00', 2),
       (52, '10000.00', '8001.00', '14.00', 2),
       (53, '16000.00', '12001.00', '16.00', 2),
       (54, '20000.00', '16001.00', '18.00', 2),
       (55, '24000.00', '20001.00', '20.00', 2),
       (56, '28000.00', '24001.00', '22.00', 2),
       (57, '50000.00', '28001.00', '24.00', 2),
       (58, '2000.00', '0.00', '10.00', 5),
       (59, '4000.00', '2001.00', '12.00', 5),
       (60, '8000.00', '4001.00', '14.00', 5),
       (61, '12000.00', '8001.00', '16.00', 5),
       (62, '20000.00', '12001.00', '18.00', 5),
       (63, '50000.00', '20001.00', '20.00', 5),
       (64, '5000.00', '0.00', '10.00', 18),
       (65, '15000.00', '5001.00', '12.00', 18),
       (66, '50000.00', '15001.00', '14.00', 18),
       (67, '2000.00', '0.00', '10.00', 17),
       (68, '8000.00', '2001.00', '12.00', 17),
       (69, '16000.00', '8001.00', '14.00', 17),
       (70, '24000.00', '16001.00', '16.00', 17),
       (71, '32000.00', '24001.00', '18.00', 17),
       (72, '40000.00', '32001.00', '20.00', 17),
       (73, '50000.00', '40001.00', '22.00', 17),
       (74, '5000.00', '0.00', '10.00', 4),
       (75, '10000.00', '5001.00', '12.00', 4),
       (76, '15000.00', '10001.00', '14.00', 4),
       (77, '20000.00', '15001.00', '16.00', 4),
       (78, '25000.00', '20001.00', '18.00', 4),
       (79, '30000.00', '25001.00', '20.00', 4),
       (80, '35000.00', '30001.00', '22.00', 4),
       (81, '50000.00', '35001.00', '24.00', 4),
       (82, '10000.00', '0.00', '10.00', 11),
       (83, '20000.00', '10001.00', '12.00', 11),
       (84, '30000.00', '20001.00', '14.00', 11),
       (85, '40000.00', '30001.00', '16.00', 11),
       (86, '50000.00', '40001.00', '18.00', 11);
ALTER TABLE roysched
    ENABLE TRIGGER ALL;


--
-- Definition of table sale
--

DROP TABLE IF EXISTS sale CASCADE;
CREATE TABLE sale
(
    ORD_NUM  bigint NOT NULL,
    STOR_ID  bigint NOT NULL REFERENCES store (STOR_ID),
    TITLE_ID bigint NOT NULL REFERENCES title (TITLE_ID),
    ORD_DATE date           default NULL,
    payterms varchar(255)   default NULL,
    qty      decimal(19, 2) default NULL,
    PRIMARY KEY (ORD_NUM, STOR_ID, TITLE_ID)
);

--
-- Dumping data for table sale
--

ALTER TABLE sale
    DISABLE TRIGGER ALL;
INSERT INTO sale (ORD_NUM, STOR_ID, TITLE_ID, ORD_DATE, payterms, qty)
VALUES (33, 14, 6, '2094-09-14', 'ON invoice', '15.00'),
       (34, 14, 1, '2094-09-14', 'ON invoice', '10.00'),
       (15, 9, 1, '2094-09-14', 'Net 60', '5.00'),
       (36, 9, 12, '2094-09-13', 'Net 60', '3.00'),
       (37, 10, 9, '2093-05-24', 'Net 30', '50.00'),
       (38, 11, 12, '2094-09-14', 'Net 60', '10.00'),
       (19, 12, 12, '2094-09-14', 'Net 30', '20.00'),
       (20, 12, 6, '2094-09-14', 'Net 30', '25.00'),
       (41, 11, 16, '2092-06-15', 'Net 30', '40.00'),
       (41, 11, 17, '2092-06-15', 'Net 30', '20.00'),
       (41, 11, 18, '2092-06-15', 'Net 30', '20.00'),
       (42, 12, 11, '2093-05-29', 'Net 60', '20.00'),
       (42, 12, 13, '2093-05-29', 'Net 60', '25.00'),
       (42, 12, 14, '2093-05-29', 'Net 60', '15.00'),
       (42, 12, 15, '2093-05-29', 'Net 60', '25.00'),
       (43, 14, 2, '2093-03-11', 'Net 30', '25.00'),
       (44, 10, 12, '2094-09-13', 'ON invoice', '75.00'),
       (45, 14, 8, '2093-05-22', 'Net 30', '30.00'),
       (21, 13, 4, '2093-10-28', 'Net 60', '15.00'),
       (47, 13, 5, '2093-12-12', 'Net 60', '10.00'),
       (48, 13, 3, '2093-02-21', 'ON invoice', '35.00');
ALTER TABLE sale
    ENABLE TRIGGER ALL;

-- The fk to pub_id from title is ignored, so recreate it!
ALTER TABLE title
    ADD CONSTRAINT title_pub_id_fkey FOREIGN KEY (pub_id) REFERENCES publisher;
-- Idem for pub_info fk
ALTER TABLE pub_info
    ADD CONSTRAINT pub_info_pub_id_fkey FOREIGN KEY (pub_id) REFERENCES publisher;

DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE SEQUENCE hibernate_sequence;

ALTER SEQUENCE hibernate_sequence OWNER TO cursist;
