package com.allade.afric.word.controller

import com.allade.afric.word.services.PdfService
import com.allade.afric.word.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/pdf")
class PdfController(@Autowired val pdfService: PdfService, @Autowired val userService: UserService) {

    @RequestMapping("/generate", method = [RequestMethod.GET])
    fun generate(): ResponseEntity<ByteArray> {
        val data: HashMap<String,Any> = HashMap()
        data["users"] = userService.findAll().toMutableList()
        try {
            val pdfContent = pdfService.generatePdf("thymeleaf_template.html", data)

            val headers = HttpHeaders()
            headers.add("Content-Disposition", "attachment;filename=pdf.pdf")

            return ResponseEntity(pdfContent,headers,HttpStatus.OK)

        }catch (e:Exception){
            throw RuntimeException(e)
        }
    }
}