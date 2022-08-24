package pro.siberian.dynamicsql.companies

import com.infobip.spring.data.jdbc.QuerydslJdbcFragment
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : CrudRepository<Company, Long>, QuerydslPredicateExecutor<Company>,
    QuerydslJdbcFragment<Company>