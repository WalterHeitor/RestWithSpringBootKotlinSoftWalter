package br.com.softwalter.wrappers

import com.fasterxml.jackson.annotation.JsonProperty

class WrapperPessoaResponse {

    @JsonProperty("_embedded")
    var embedded: PessoaEmbeddedResponse? = null

}