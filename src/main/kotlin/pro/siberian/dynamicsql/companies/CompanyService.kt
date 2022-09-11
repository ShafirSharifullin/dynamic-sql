package pro.siberian.dynamicsql.companies

import org.springframework.stereotype.Service
import pro.siberian.dynamicsql.employees.QEmployee
import java.time.LocalDateTime

@Service
class CompanyService(private val companyRepo: CompanyRepository) {

    fun findAll(
        name: String?,
        birthYears: List<Int>?,
        hasFullTime: Boolean,
        hasFreelance: Boolean,
        dateFrom: LocalDateTime?,
        dateTill: LocalDateTime?,
        salaryFrom: Long?,
        salaryTill: Long?,
        sort: String,
    ): Set<Company> = companyRepo.queryMany { q ->
        val employees = QEmployee.employee
        val companies = QCompany.company
        val query = q.select(companies)
            .from(companies)
            .leftJoin(employees)
            .on(companies.id.eq(employees.companyId))

        if (name != null)
            query.where(companies.name.containsIgnoreCase(name))
        if (hasFreelance)
            query.where(employees.status.eq("freelance_emp"))
        if (hasFullTime)
            query.where(employees.status.eq("full_time_emp"))
        if (!birthYears.isNullOrEmpty())
            query.where(employees.yearBirth.`in`(birthYears))
        if (salaryFrom != null && salaryTill != null)
            query.where(employees.salary.between(salaryFrom, salaryTill))
        if (dateFrom != null && dateTill != null)
            query.where(companies.dateCreate.between(dateFrom, dateTill))

        when (sort) {
            "date_create_new" -> query.orderBy(companies.dateCreate.desc())
            "date_create_old" -> query.orderBy(companies.dateCreate.asc())
            "id_new" -> query.orderBy(companies.id.desc())
            else -> query.orderBy(companies.id.asc())
        }

        query
    }.toSet()

    fun findAll() = companyRepo.findAll().toList()

    fun findById(id: Long): Company? = companyRepo.findById(id).orElse(null)

    fun save(company: Company) = companyRepo.save(company)
}