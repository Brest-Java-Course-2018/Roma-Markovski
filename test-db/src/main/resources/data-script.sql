INSERT INTO writer (writer_id, writer_name, writer_country) VALUES (1, 'Pushkin Alex', 'Russia');
INSERT INTO writer (writer_name, writer_country) VALUES ('Tolstoy Leo', 'Russia');
INSERT INTO writer (writer_name, writer_country) VALUES ('Kupala Yanka', 'Belarus');
INSERT INTO writer (writer_name, writer_country) VALUES ('Shakespeare William', 'United Kingdom');

INSERT INTO publication (publication_name, writer_id, publication_date, publication_num_of_pages, publication_description)
VALUES ('Evgeniy Onegin', 1, '2018-03-21', 235, 'Poem');