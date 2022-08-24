package pro.siberian.dynamicsql.companies

import org.springframework.stereotype.Service

@Service
class CompanyService(private val companyRepo: CompanyRepository) {

    fun findAll(sort: String) = companyRepo.queryMany { q ->
        val query = q.select(companyRepo.entityProjection())
            .from(QCompany.company)

        if (sort == "date_create_new")
            query.orderBy(QCompany.company.dateCreate.desc())
        else
            query.orderBy(QCompany.company.dateCreate.asc())

        query
    }

    fun findAll() = companyRepo.findAll().toList()

    fun findById(id: Long): Company? = companyRepo.findById(id).orElse(null)

    fun save(company: Company) = companyRepo.save(company)
}