package br.com.softwalter.domain.model.rbac

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "users")
class Users : UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    var idUser: Long? = 0

    @Column
    var userName: String? = null

    @Column
    var fullName: String? = null

    @Column
    private var password: String? = null

    @Column
    var accountNonExpired: Boolean? = null

    @Column
    var accountNonLocked: Boolean? = null

    @Column
    var credentialsNonExpired: Boolean? = null

    @Column
    var ennabled: Boolean? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_permisson")]
    )
    var permissions: List<Permission>? = null

    val role: List<String?>
    get() {
        val roles: MutableList<String?> = ArrayList()
        for (permisso in permissions!!){
            roles.add(permisso.description)
        }
        return roles
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return this.permissions!!
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return userName!!
    }

    override fun isAccountNonExpired(): Boolean {
        return accountNonExpired!!
    }

    override fun isAccountNonLocked(): Boolean {
        return accountNonLocked!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return credentialsNonExpired!!
    }

    override fun isEnabled(): Boolean {
        return ennabled!!
    }

}
