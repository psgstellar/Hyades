DROP TABLE member_in_project, project, tech_stack, member;

CREATE OR REPLACE TABLE member (
                                   member_id BIGINT NOT NULL AUTO_INCREMENT,
                                   email VARCHAR(30) NOT NULL UNIQUE,
                                   nickname VARCHAR(30) NOT NULL,
                                   profile_img VARCHAR(50),
                                   PRIMARY KEY(member_id)
);

CREATE OR REPLACE TABLE tech_stack (
                                       tech_stack_id BIGINT NOT NULL AUTO_INCREMENT,
                                       tech_name VARCHAR(30) NOT NULL,
                                       PRIMARY KEY(tech_stack_id)
);
ALTER TABLE tech_stack ADD UNIQUE INDEX (tech_name);

CREATE OR REPLACE TABLE member_tech_stack (
                                              member_id BIGINT NOT NULL,
                                              tech_stack_id BIGINT NOT NULL,
                                              PRIMARY KEY(member_id, tech_stack_id),
                                              FOREIGN KEY(member_id) REFERENCES member (member_id),
                                              FOREIGN KEY(tech_stack_id) REFERENCES tech_stack (tech_stack_id)
);

CREATE OR REPLACE TABLE project (
                                    project_id BIGINT NOT NULL AUTO_INCREMENT,
                                    title VARCHAR(100) NOT NULL,
                                    PRIMARY KEY(project_id)
);

CREATE OR REPLACE TABLE member_in_project (
                                              member_id BIGINT NOT NULL,
                                              project_id BIGINT NOT NULL,
                                              member_position VARCHAR(20),
                                              PRIMARY KEY (member_id, project_id),
                                              FOREIGN KEY(member_id) REFERENCES member (member_id),
                                              FOREIGN KEY(project_id) REFERENCES project (project_id)
);