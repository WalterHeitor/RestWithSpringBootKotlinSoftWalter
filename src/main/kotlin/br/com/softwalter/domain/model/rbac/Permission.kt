package br.com.softwalter.domain.model.rbac

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "permission")
class Permission : GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission")
    var idPermission: Long = 0

    @Column
    var description: String? = null
    override fun getAuthority(): String {
        return description!!
    }
}