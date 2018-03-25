DROP TABLE IF EXISTS department;
CREATE TABLE department (
  departmentId   INT          NOT NULL AUTO_INCREMENT,
  departmentName VARCHAR(255) NOT NULL,
  description    VARCHAR(255) NULL,
  PRIMARY KEY (departmentId)
);

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  employeeId    INT          NOT NULL AUTO_INCREMENT,
  employeeName  VARCHAR(255) NOT NULL,
  employeeEmail  VARCHAR(255) NOT NULL,
  salary        INT          NOT NULL,
  departmentId  INT          NOT NULL,
  PRIMARY KEY (employeeId),
  CONSTRAINT FK_EmployeeId_DepartmentId
  FOREIGN KEY (departmentId) REFERENCES department(departmentId)
  ON UPDATE CASCADE
  ON DELETE CASCADE
);