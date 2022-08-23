package pro.siberian.dynamicsql.employees

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.springframework.data.repository.query.Param

@Mapper
interface EmployeeMapper {

    @Results(value = [
        Result(column = "id", property = "id"),
        Result(column = "year_birth", property = "yearBirth"),
        Result(column = "company_id", property = "companyId")
    ])
    @Select(
        """
            SELECT * FROM employees e WHERE e.company_id = #{companyId}
        """
    )
    fun findByCompanyId(@Param("companyId") companyId: Long): List<Employee>
}