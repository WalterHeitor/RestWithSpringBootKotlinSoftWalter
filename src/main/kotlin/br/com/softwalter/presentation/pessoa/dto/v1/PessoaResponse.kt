package br.com.softwalter.presentation.pessoa.dto.v1

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.RepresentationModel

data class PessoaResponse (
    @field:JsonProperty("id_pessoa")
    var idPessoa: Long? = 0,
    @field:JsonProperty("nome")
    var nome: String,
    @field:JsonProperty("email")
    var email: String,
        ) : RepresentationModel<PessoaResponse>() {

}
