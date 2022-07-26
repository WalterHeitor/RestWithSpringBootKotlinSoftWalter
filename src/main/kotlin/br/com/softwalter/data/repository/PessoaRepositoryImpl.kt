//package br.com.softwalter.data.repository
//
//import br.com.softwalter.domain.model.Pessoa
//import br.com.softwalter.domain.repository.PessoaRepository
//import org.springframework.stereotype.Service
//import java.util.logging.Logger
//
//@Service
//class PessoaRepositoryImpl (val pessoaRepository: PessoaRepository)  {
//
//    private val logger = Logger.getLogger(PessoaRepositoryImpl::class.java.name)
//
////    @Autowired
////    private lateinit val pessoaRepository: PessoaRepository
//
//     fun <S : Pessoa?> save(entity: S): S {
//        logger.info("salvando pessoa na base de dados")
//        return pessoaRepository.save(entity!!)
//    }
//
//     fun findAll(): MutableList<Pessoa> {
//        logger.info("Buscando pessoas na base de dados")
//        return pessoaRepository.findAll()
//    }
//
//     fun deleteById(id: Long) {
//        logger.info("Deletando pessoa na base de dados")
//        pessoaRepository.deleteById(id)
//    }
//
//     fun findById(id: Long): Pessoa? {
//        logger.info("Buscando pessoa por id na base de dados")
//        return pessoaRepository.findById(id).orElseThrow{ RuntimeException("")}
//    }
//}