package br.com.softwalter.presentation.users

import br.com.softwalter.presentation.pessoa.dto.v1.PessoaRequest
import br.com.softwalter.presentation.pessoa.dto.v1.PessoaResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RestController
@RequestMapping("/cadastro/v1")
@Tag(name = "Usuarios", description = "Endpoints para cadastro de usuarios")
class UsersController {

    @CrossOrigin(origins = ["http://localhost:8080", "https://softWalter.com.br"])
    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun salvarUsuario(
        @RequestBody accountCredentialsRequest: AccountCredentialsRequest
    ): ResponseEntity<*> {

        return ResponseEntity(null, HttpStatus.CREATED)
    }

}