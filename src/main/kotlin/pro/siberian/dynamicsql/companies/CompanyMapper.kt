package pro.siberian.dynamicsql.companies

import org.apache.ibatis.annotations.*

@Mapper
interface CompanyMapper {

    @Results(value = [
        Result(column = "id", property = "id"),
        Result(column = "date_create", property = "dateCreate"),
        Result(column = "id",
            property = "employees",
            many = Many(select = "pro.siberian.dynamicsql.employees.EmployeeMapper.findByCompanyId")),
    ])
    @Select(
        """
            <script>
              SELECT *
              FROM companies c left join employees e on c.id = e.company_id
              <if test="hasFullTime == true">
                WHERE e.status = 'full_time_emp'
              </if>
              ORDER BY c.date_create
              <choose>
                <when test="sort == 'date_create_new'">
                  desc
                </when>
                <when test="sort == 'date_create_old'">
                  asc
                </when>
              </choose>
            </script>
        """
    )
    fun findAll(
        @Param("hasFullTime") hasFullTime: Boolean,
        @Param("sort") sort: String,
    ): List<Company>
}