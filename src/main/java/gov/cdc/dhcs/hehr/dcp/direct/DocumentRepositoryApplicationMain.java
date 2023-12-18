package gov.cdc.dhcs.hehr.dcp.direct;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import gov.cdc.dhcs.hehr.dcp.direct.function.AzureBlobService;

/**
 * @author Sai Valluripalli
 */
@SpringBootApplication
public class DocumentRepositoryApplicationMain extends SpringBootServletInitializer {
	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DocumentRepositoryApplicationMain.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DocumentRepositoryApplicationMain.class, args);
	}

	@Autowired
	private AzureBlobService azureBlobService;

	@Bean
	public Function<Message<Map<String, List<String>>>, String> archiveFiles() {
		return message -> {
			Map<String, List<String>> fileArchivePayload = message.getPayload();
			String response = azureBlobService.downloadFilesAndZipUpload(fileArchivePayload);
			System.out.println("Azure blob file zip operation successful");
			return response;
		};
	}

	@Bean
	public Function<String, String> echo() {
		return payload -> payload;
	}
}
