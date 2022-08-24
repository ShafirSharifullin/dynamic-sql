package pro.siberian.dynamicsql.companies

import com.querydsl.core.annotations.QueryEntity
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@QueryEntity
@Table("companies")
data class Company(

    @Id
    val id: Long,

    val name: String,

    @Column("date_create")
    val dateCreate: LocalDateTime,

//    Отношение один-ко-многим не настраивается
//    @MappedCollection(idColumn = "company_id", keyColumn = "id")
//    val employees: List<Employee> = listOf(),
)