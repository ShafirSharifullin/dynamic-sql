package pro.siberian.dynamicsql.companies

import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepo: CompanyRepository,
    private val companyMapper: CompanyMapper,
) {

    fun findAll(hasFullTime: Boolean, sort: String) = companyMapper.findAll(hasFullTime, sort).toSet()

    fun findAll() = companyRepo.findAll().toList()

    fun findById(id: Long): Company? = companyRepo.findById(id).orElse(null)

    fun save(company: Company) = companyRepo.save(company)
}