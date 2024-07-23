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
    LAST_NAME  VARCHAR(255) NOT NULL
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
    NAME VARCHAR(20) NOT NULL UNIQUE
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