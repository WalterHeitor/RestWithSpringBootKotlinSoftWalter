package br.com.softwalter.domain.usecase.impl


import br.com.softwalter.config.FileSorageConfing
import br.com.softwalter.domain.usecase.FileStorageUseCase
import br.com.softwalter.exceptions.FileStorageException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class FileStorageUseCaseImpl @Autowired constructor(fileSorageConfing: FileSorageConfing) : FileStorageUseCase {

    private var fileSorageLocation: Path

    init {
        fileSorageLocation = Paths.get(fileSorageConfing.uploadDir).toAbsolutePath().normalize()
        try {
            Files.createDirectories(fileSorageLocation)
        } catch (e: Exception) {
            throw FileStorageException("Could not create  the directory" +
                    " wher the uploaded files will be storage location: $fileSorageLocation", e)
        }
    }

    override fun storage(file: MultipartFile): String {
        val fileName = StringUtils.cleanPath(file.originalFilename!!)

        return try {
            if (fileName.contains("..")) throw FileStorageException("Sorry Filename invalid path sequence: $fileName")
            val targetLocation = fileSorageLocation.resolve(fileName)
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
            fileName
        } catch (e: Exception) {
            throw FileStorageException("Could not storage file '$fileName' please try again.", e)
        }
    }
}