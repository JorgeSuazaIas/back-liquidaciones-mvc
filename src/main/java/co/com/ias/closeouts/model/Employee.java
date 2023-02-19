package co.com.ias.closeouts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "employee_seq", allocationSize = 1, name = "CUST_SEQ")
    private int employeeId;

    @Column(name = "EmployeeName")
    private String employeeName;

    @Column(name = "EmployeeDocNumber")
    private int employeeDocNumber;

    @Column(name = "EmployeeStartDate")
    private Date employeeStartDate;

    @Column(name = "EmployeeSalary")
    private int employeeSalary;

    @Column(name = "EmployeeProfession")
    private String employeeProfession;

}
