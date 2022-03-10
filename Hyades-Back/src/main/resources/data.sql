-- member
INSERT INTO member(email, nickname, profile_img) VALUES ('member1@email.com', 'member1_nickname', 'member1_profile_img');
INSERT INTO member(email, nickname, profile_img) VALUES ('member2@email.com', 'member2_nickname', 'member2_profile_img');
INSERT INTO member(email, nickname, profile_img) VALUES ('member3@email.com', 'member3_nickname', 'member3_profile_img');
INSERT INTO member(email, nickname, profile_img) VALUES ('member4@email.com', 'member4_nickname', 'member4_profile_img');
INSERT INTO member(email, nickname, profile_img) VALUES ('member5@email.com', 'member5_nickname', 'member5_profile_img');

-- tech_stack
INSERT INTO tech_stack(member_id, tech_name) VALUES (1, 'java'), (1, 'spring'), (1, 'C'), (1, 'C++');
INSERT INTO tech_stack(member_id, tech_name) VALUES (2, 'java'), (2, 'spring'), (2, 'python');
INSERT INTO tech_stack(member_id, tech_name) VALUES (3, 'java'), (3, 'spring'), (3, 'C#'), (3, 'mybatis'), (3, "jpa");
-- INSERT INTO tech_stack(member_id, tech_name) VALUES (1, 'java'); -- unique error

-- project
INSERT INTO project(title) VALUES ('hyades');
INSERT INTO project(title) VALUES ('pet book');

-- member_in_project
INSERT INTO member_in_project(member_id, project_id, member_position) VALUES (1, 1, 'back-end');
INSERT INTO member_in_project(member_id, project_id, member_position) VALUES (1, 2, 'front-end');
INSERT INTO member_in_project(member_id, project_id, member_position) VALUES (2, 1, 'full-stack');
INSERT INTO member_in_project(member_id, project_id, member_position) VALUES (2, 2, 'back-end');
INSERT INTO member_in_project(member_id, project_id, member_position) VALUES (3, 2, 'front-end');
INSERT INTO member_in_project(member_id, project_id, member_position) VALUES (3, 1, null);