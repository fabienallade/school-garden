package com.allade.afric.word.services

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine
import java.io.ByteArrayOutputStream

@Service
class PdfService(
    private val templateEngine: TemplateEngine,
) {
    fun generatePdf(
        templateName: String,
        data: HashMap<String, Any>,
    ): ByteArray {
        val context = Context()
        context.setVariables(data)

        val htmlContent: String = templateEngine.process(templateName, context)

        try {
            ByteArrayOutputStream().use { os ->
                val builder = PdfRendererBuilder()
                builder.useFastMode()
                builder.withHtmlContent(htmlContent, null)
                builder.toStream(os)
                builder.run()
                return os.toByteArray()
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
