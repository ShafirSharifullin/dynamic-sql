package pro.siberian.dynamicsql.companies

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CompanyService(
    private val companyRepo: CompanyRepository,
    private val companyMapper: CompanyMapper,
) {

    fun findAll(
        name: String?,
        birthYears: List<Int>?,
        hasFullTime: Boolean,
        hasFreelance: Boolean,
        dateFrom: LocalDateTime?,
        dateTill: LocalDateTime?,
        salaryFrom: Int?,
        salaryTill: Int?,
        sort: String,
    ) = companyMapper.findAll(
        if (name != null) "%$name%" else name,
        birthYears,
        hasFullTime,
        hasFreelance,
        dateFrom,
        dateTill,
        salaryFrom,
        salaryTill,
        sort
    ).toSet()

    fun findAll() = companyRepo.findAll().toList()

    fun findById(id: Long): Company? = companyRepo.findById(id).orElse(null)

    fun save(company: Company) = companyRepo.save(company)
}