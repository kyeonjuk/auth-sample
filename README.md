# auth-sample
 [SpringBoot] 회원인증/로그인 sample


[직접 생성 SQL 문]
``` 
CREATE TABLE employee_role_mapping (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);

CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);



```