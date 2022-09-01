package pro.siberian.dynamicsql.companies

import org.apache.ibatis.annotations.*
import java.time.LocalDateTime

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
              <where>
                <if test="name != null">
                    c.name ILIKE #{name}
                </if>
                <if test="hasFullTime == true">
                    AND e.status = 'full_time_emp'
                </if>
                <if test="hasFreelance == true">
                    AND e.status = 'freelance_emp'
                </if>
                <if test="birthYears != null">
                    AND e.year_birth IN
                    <foreach item="yearBirth" index="index" collection="birthYears"
                        open="(" separator="," close=")">
                        #{yearBirth}
                    </foreach>
                </if>
                <if test="salaryFrom != null and salaryTill != null">
                    AND (e.salary BETWEEN #{salaryFrom} AND #{salaryTill})
                </if>
                <if test="dateFrom != null and dateTill != null">
                    AND (c.date_create BETWEEN #{dateFrom} AND #{dateTill})
                </if>
              </where>
              ORDER BY 
              <choose>
                <when test="sort == 'date_create_new'">
                  c.date_create desc
                </when>
                <when test="sort == 'date_create_old'">
                  c.date_create asc
                </when>
                <when test="sort == 'id_new'">
                  c.id desc
                </when>
                <otherwise>
                  c.id asc
                </otherwise>
              </choose>
            </script>
        """
    )
    fun findAll(
        @Param("name") name: String?,
        @Param("birthYears") birthYears: List<Int>?,
        @Param("hasFullTime") hasFullTime: Boolean,
        @Param("hasFreelance") hasFreelance: Boolean,
        @Param("dateFrom") dateFrom: LocalDateTime?,
        @Param("dateTill") dateTill: LocalDateTime?,
        @Param("salaryFrom") salaryFrom: Int?,
        @Param("salaryTill") salaryTill: Int?,
        @Param("sort") sort: String,
    ): List<Company>
}