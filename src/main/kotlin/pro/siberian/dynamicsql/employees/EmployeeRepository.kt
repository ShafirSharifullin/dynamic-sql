package pro.siberian.dynamicsql.employees

import com.infobip.spring.data.jdbc.QuerydslJdbcFragment
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : CrudRepository<Employee, Long>, QuerydslPredicateExecutor<Employee>,
    QuerydslJdbcFragment<Employee>