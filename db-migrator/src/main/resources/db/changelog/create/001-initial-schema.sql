create table invalidated_token
(
    ID             UUID PRIMARY KEY   DEFAULT gen_random_uuid(),
    TOKEN          TEXT      NOT NULL,
    INVALIDATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

create table company
(
    ID   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    NAME VARCHAR(255) NOT NULL UNIQUE
);

create table product_user
(
    ID         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    EMAIL      VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD   TEXT         NOT NULL,
    FIRST_NAME VARCHAR(255) NOT NULL,
    LAST_NAME  VARCHAR(255) NOT NULL,
    STATUS     VARCHAR(40)  NOT NULL
);

create table company_user_relation
(
    ID         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    USER_ID    UUID NOT NULL,
    COMPANY_ID UUID NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES PRODUCT_USER (ID),
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
    FOREIGN KEY (USER_ID) REFERENCES PRODUCT_USER (ID),
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
VALUES ('ADMIN')
    RETURNING ID
    ),

-- Step 2: Insert a new user into the product_user table
    inserted_user AS (
INSERT
INTO product_user (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, STATUS)
VALUES ('aleksandr.ts@gmail.com', '$2a$10$/9JQXWBEbHKU/ZzMFLQTbe6E4A6k6eqkHjNhfSVMSVc4Bp5NykYYS', 'Aleksandr', 'MegaPihar', 'ACTIVE')
    RETURNING ID
    )

-- Step 3: Insert a record into the user_role_relation table using the retrieved UUIDs
INSERT
INTO user_role_relation (USER_ID, ROLE_ID)
SELECT user_id, role_id
FROM (SELECT ID AS user_id FROM inserted_user) u,
     (SELECT ID AS role_id FROM inserted_role) r;

-- Step 4: Add permissions
INSERT INTO user_permission(name)
VALUES ('admin:create'),
       ('admin:read'),
       ('admin:update'),
       ('admin:delete');

-- Step 5 Bind user permission relation
WITH permission_ids AS (SELECT ID
                        FROM user_permission
                        WHERE NAME IN ('admin:create', 'admin:read', 'admin:update', 'admin:delete')),
     role_id AS (SELECT ID
                 FROM user_role
                 WHERE NAME = 'ADMIN')

INSERT
INTO role_permission_relation (ROLE_ID, PERMISSION_ID)
SELECT r.ID, p.ID
FROM role_id r,
     permission_ids p;


-- Default for all
INSERT
INTO user_role (NAME)
VALUES ('USER')