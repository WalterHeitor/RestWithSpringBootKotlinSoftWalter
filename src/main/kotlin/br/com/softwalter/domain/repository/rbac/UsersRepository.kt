package br.com.softwalter.domain.repository.rbac

import br.com.softwalter.domain.model.rbac.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE u.userName =:userName")
    fun findByUserName(@Param("userName") userName: String?): Users?
}