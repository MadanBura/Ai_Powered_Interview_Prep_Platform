import os

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/backend"
main_res = os.path.join(base_dir, "src/main/resources")
test_res = os.path.join(base_dir, "src/test/resources")

os.makedirs(main_res, exist_ok=True)
os.makedirs(test_res, exist_ok=True)

# 1. application.yml (Main)
application_yml = """spring:
  profiles:
    active: dev
"""
with open(os.path.join(main_res, "application.yml"), "w") as f:
    f.write(application_yml)

# 2. application-dev.yml (MySQL Dev)
application_dev_yml = """spring:
  datasource:
    url: jdbc:mysql://localhost:3306/interview_platform?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: root
    password: password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    database-platform: org.hibernate.dialect.MySQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
"""
with open(os.path.join(main_res, "application-dev.yml"), "w") as f:
    f.write(application_dev_yml)

# 3. application-test.yml (H2 Test)
application_test_yml = """spring:
  datasource:
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: 
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: create-drop
    show-sql: false
  h2:
    console:
      enabled: false
"""
with open(os.path.join(main_res, "application-test.yml"), "w") as f:
    f.write(application_test_yml)

# 4. test/resources/application.yml (Force test profile)
test_application_yml = """spring:
  profiles:
    active: test
"""
with open(os.path.join(test_res, "application.yml"), "w") as f:
    f.write(test_application_yml)

print("Spring Boot Database Configuration profiles created successfully.")
