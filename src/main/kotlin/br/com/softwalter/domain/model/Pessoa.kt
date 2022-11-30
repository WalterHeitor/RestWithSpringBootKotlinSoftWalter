package br.com.softwalter.domain.model

import javax.persistence.*


@Entity
@Table(name = "pessoa")
data class Pessoa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idPessoa: Long? = null,
    @Column(name = "nome")
    var nome: String = "",
    @Column(name = "email")
    var email: String = "",
    @Column(name = "cpf")
    var cpf: String,
    @Column(name = "dataNascimento")
    var dataNascimento: String,
    @Column(name = "ativo")
    var ativo: Boolean = true
) {

}
