package br.com.softwalter.wrappers

import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import com.fasterxml.jackson.annotation.JsonProperty

class PessoaEmbeddedResponse {

    @JsonProperty("pessoaResponseList")
    var pessoas: List<PessoaResponse>? = null
}
