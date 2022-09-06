package br.com.softwalter.domain.usecase

import org.springframework.web.multipart.MultipartFile

interface FileStorageUseCase {

    fun storage(file: MultipartFile) : String
}