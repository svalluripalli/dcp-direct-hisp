/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gov.cdc.dhcs.hehr.dcp.direct;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.microsoft.azure.functions.ExecutionContext;

import gov.cdc.dhcs.hehr.dcp.direct.function.AzureBlobService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;
import org.springframework.cloud.function.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

@SpringBootApplication
public class DocumentRepositoryApplicationMain {

	private static final Logger logger = LoggerFactory.getLogger(DocumentRepositoryApplicationMain.class);

	@Autowired
	private AzureBlobService azureBlobService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DocumentRepositoryApplicationMain.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DocumentRepositoryApplicationMain.class);
	}
	
	@Bean
	public Function<Message<Map<String, List<String>>>, String> archiveFiles() {
		return message -> {
			Map<String, List<String>> fileArchivePayload = message.getPayload();
			String response = azureBlobService.downloadFilesAndZipUpload(fileArchivePayload);
			logger.info("Azure blob file zip operation successful");
			return response;
		};
	}

	@Bean
	public Function<String, String> echo() {
		return payload -> payload;
	}
	
}
