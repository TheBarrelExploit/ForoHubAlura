CREATE TABLE IF NOT EXISTS Answer(
    id BIGINT AUTO_INCREMENT,
    answer VARCHAR(400) NOT NULL,
    creationdate DATE NOT NULL,
    idautor BIGINT NOT NULL,
    idtopico BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(idautor) REFERENCES User(id),
    FOREIGN KEY(idtopico) REFERENCES Topic(id)
);