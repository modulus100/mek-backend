create table invalidated_token
(
    ID             UUID PRIMARY KEY   DEFAULT gen_random_uuid(),
    TOKEN          TEXT      NOT NULL,
    INVALIDATED_AT TIMESTAMP NOT NULL
);

create table company
(
    ID   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    NAME VARCHAR(255) NOT NULL UNIQUE
);

create table user_account
(
    ID                UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    EMAIL             VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD          TEXT         NOT NULL,
    FIRST_NAME        VARCHAR(255) NOT NULL,
    LAST_NAME         VARCHAR(255) NOT NULL,
    STATUS            VARCHAR(40)  NOT NULL,
    REGISTRATION_DATE TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    BIRTH_DATE        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    IS_ACTIVE         BOOLEAN      NOT NULL,
    JOB_TITLE         VARCHAR(30),
    PHONE_NUMBER      VARCHAR(30),
    PERSONAL_ID_CODE  VARCHAR(20)
);

create table company_user_relation
(
    ID         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    USER_ID    UUID NOT NULL,
    COMPANY_ID UUID NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES user_account (ID),
    FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY (ID)
);

create table user_permission
(
    ID   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    NAME VARCHAR(100) NOT NULL UNIQUE
);

create table user_role
(
    ID   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    NAME VARCHAR(100) NOT NULL UNIQUE
);

create table user_role_relation
(
    ID      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    USER_ID UUID NOT NULL,
    ROLE_ID UUID NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES user_account (ID),
    FOREIGN KEY (ROLE_ID) REFERENCES USER_ROLE (ID)
);

create table role_permission_relation
(
    ID            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ROLE_ID       UUID NOT NULL,
    PERMISSION_ID UUID NOT NULL,
    FOREIGN KEY (ROLE_ID) REFERENCES USER_ROLE (ID),
    FOREIGN KEY (PERMISSION_ID) REFERENCES USER_PERMISSION (ID)
);

-- Step 1: Insert a new role into the user_role table
WITH inserted_role AS (
    INSERT
        INTO user_role (NAME)
            VALUES ('DEVELOPER')
            RETURNING ID),

-- Step 2: Insert a new user into the user_account table
     inserted_user AS (
         INSERT
             INTO user_account (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, STATUS, REGISTRATION_DATE, BIRTH_DATE, IS_ACTIVE, JOB_TITLE,
                                PHONE_NUMBER, PERSONAL_ID_CODE)
                 VALUES ('aleksandr.ts@gmail.com',
                         '$2a$10$/9JQXWBEbHKU/ZzMFLQTbe6E4A6k6eqkHjNhfSVMSVc4Bp5NykYYS',
                         'Aleksandr',
                         'MegaPihar',
                         'ACTIVE',
                         now(),
                         now(),
                         TRUE,
                         'Developer',
                         '+37253126780',
                         '39408056822')
                 RETURNING ID)

-- Step 3: Insert a record into the user_role_relation table using the retrieved UUIDs
INSERT
INTO user_role_relation (USER_ID, ROLE_ID)
SELECT user_id, role_id
FROM (SELECT ID AS user_id FROM inserted_user) u,
     (SELECT ID AS role_id FROM inserted_role) r;


-- Step 4: Add permissions
INSERT INTO user_permission(name)
VALUES ('developer:create'),
       ('developer:read'),
       ('developer:update'),
       ('developer:delete');

-- Step 5 Bind user permission relation
WITH permission_ids AS (SELECT ID
                        FROM user_permission
                        WHERE NAME IN ('developer:create', 'developer:read', 'developer:update', 'developer:delete')),
     role_id AS (SELECT ID
                 FROM user_role
                 WHERE NAME = 'DEVELOPER')

INSERT
INTO role_permission_relation (ROLE_ID, PERMISSION_ID)
SELECT r.ID, p.ID
FROM role_id r,
     permission_ids p;


-- Insert roles and permissions and bind them
-- Insert ADMIN role and permissions, then bind them
WITH inserted_admin_role AS (
    INSERT INTO user_role (NAME)
        VALUES ('ADMIN')
        RETURNING ID, NAME),
     permission_ids_admin AS (
         INSERT INTO user_permission (name)
             VALUES ('admin:create'), ('admin:read'), ('admin:update'), ('admin:delete')
             RETURNING ID, NAME)
-- Bind user permission relation for ADMIN
INSERT
INTO role_permission_relation (ROLE_ID, PERMISSION_ID)
SELECT r.ID, p.ID
FROM inserted_admin_role r
         JOIN permission_ids_admin p ON p.NAME IN ('admin:create', 'admin:read', 'admin:update', 'admin:delete');

-- Insert USER role and permissions, then bind them
WITH inserted_user_role AS (
    INSERT INTO user_role (NAME)
        VALUES ('USER')
        RETURNING ID, NAME),
     permission_ids_user AS (
         INSERT INTO user_permission (name)
             VALUES ('user:create'), ('user:read'), ('user:update'), ('user:delete')
             RETURNING ID, NAME)
-- Bind user permission relation for USER
INSERT
INTO role_permission_relation (ROLE_ID, PERMISSION_ID)
SELECT r.ID, p.ID
FROM inserted_user_role r
         JOIN permission_ids_user p ON p.NAME IN ('user:create', 'user:read', 'user:update', 'user:delete');

