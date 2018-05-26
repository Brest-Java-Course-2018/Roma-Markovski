INSERT INTO writer (writer_id, writer_name, writer_country) VALUES (1, 'Pushkin Alex', 'Russia');
INSERT INTO writer (writer_name, writer_country) VALUES ('Tolstoy Leo', 'Russia');
INSERT INTO writer (writer_name, writer_country) VALUES ('Kolas Yakub', 'Belarus');
INSERT INTO writer (writer_name, writer_country) VALUES ('Shakespeare William', 'United Kingdom');

INSERT INTO publication (publication_name, writer_id, publication_date, publication_num_of_pages, publication_description)
VALUES ('Evgeniy Onegin', 1, '2018-03-21', 384, 'Poem');
INSERT INTO publication (publication_name, writer_id, publication_date, publication_num_of_pages, publication_description)
VALUES ('War and Peace', 2, '2018-01-21', 1274, 'Novel');
INSERT INTO publication (publication_name, writer_id, publication_date, publication_num_of_pages, publication_description)
VALUES ('Anna Karenina', 2, '2018-03-01', 864, 'Novel');
INSERT INTO publication (publication_name, writer_id, publication_date, publication_num_of_pages, publication_description)
VALUES ('A New Land', 3, '2018-03-21', 352, 'Poem');
INSERT INTO publication (publication_name, writer_id, publication_date, publication_num_of_pages, publication_description)
VALUES ('Romeo and Juliet', 4, '2017-05-19', 243, 'Tragedy');