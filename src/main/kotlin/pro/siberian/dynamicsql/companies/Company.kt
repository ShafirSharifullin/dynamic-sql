package pro.siberian.dynamicsql.companies

import com.querydsl.core.annotations.QueryEntity
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import pro.siberian.dynamicsql.employees.Employee
import java.time.LocalDateTime

@QueryEntity
@Table("companies")
data class Company(

    @Id
    val id: Long = 0,

    val name: String = "",

    @Column("date_create")
    val dateCreate: LocalDateTime = LocalDateTime.now(),

    @MappedCollection(idColumn = "company_id")
    val employees: Set<Employee> = setOf(),
)