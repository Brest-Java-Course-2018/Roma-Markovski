DROP TABLE IF EXISTS writer;
CREATE TABLE writer (
  writer_id      INT          NOT NULL AUTO_INCREMENT,
  writer_name    VARCHAR(255) NOT NULL,
  writer_country VARCHAR(63)  NOT NULL,
  PRIMARY KEY (writer_id)
);

DROP TABLE IF EXISTS publication;
CREATE TABLE publication (
  publication_id           INT          NOT NULL AUTO_INCREMENT,
  publication_name         VARCHAR(255) NOT NULL,
  writer_id                INT          NOT NULL,
  publication_date         DATE         NOT NULL,
  publication_num_of_pages INT          NULL,
  publication_description  VARCHAR(255) NULL,
  PRIMARY KEY (publication_id),
  CONSTRAINT FK_publication_id_writer_id
  FOREIGN KEY (writer_id) REFERENCES writer(writer_id)
  ON UPDATE CASCADE
  ON DELETE NO ACTION
);