package pro.siberian.dynamicsql.employees

import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepo: EmployeeRepository) {

    fun findAll(hasFullTimeEmp: Boolean, hasFreelanceEmp: Boolean): List<Employee> = employeeRepo.queryMany { q ->
        val query = q.select(employeeRepo.entityProjection())
            .from(QEmployee.employee)

        if (hasFreelanceEmp && hasFullTimeEmp)
            query.where(QEmployee.employee.status.eq("freelance_emp")
                .or(QEmployee.employee.status.eq("full_time_emp")))
        else if (hasFreelanceEmp)
            query.where(QEmployee.employee.status.eq("freelance_emp"))
        else if (hasFullTimeEmp)
            query.where(QEmployee.employee.status.eq("full_time_emp"))

        query
    }

    fun findAll() = employeeRepo.findAll().toList()

    fun save(employee: Employee) = employeeRepo.save(employee)
}