package gov.cdc.dhcs.hehr.dcp.direct.executor;

import java.util.List;
import java.util.Map;


import gov.cdc.dhcs.hehr.dcp.direct.helper.HttpRequestHelper;

/**
 * 
 * @author Sai Valluripalli
 *
 */
public class AzureBlobArchiveExecutor implements Runnable {
	
	//private static final Logger logger = LoggerFactory.getLogger(AzureBlobArchiveExecutor.class);
	
	private Map<String, List<String>> fileArchiveApiPayload;
	
	private String fileArchiveApiUrl;
	
	private HttpRequestHelper httpRequestHelper;

	public AzureBlobArchiveExecutor(HttpRequestHelper httpRequestHelper, String fileArchiveApiUrl, Map<String, List<String>> fileArchiveApiPayload) {
		super();
		this.httpRequestHelper = httpRequestHelper;
		this.fileArchiveApiUrl = fileArchiveApiUrl;
		this.fileArchiveApiPayload = fileArchiveApiPayload;
	}

	@Override
	public void run() {
		System.out.println("Trying to invoke file archiver Function App");
		System.out.println("FileArchiveApiUrl="+ fileArchiveApiUrl);
		System.out.println("FileArchiveApiPayload="+ fileArchiveApiPayload);
		httpRequestHelper.postRequest(fileArchiveApiUrl, fileArchiveApiPayload);
		System.out.println("File archive request to Function App completed");
	}
	
	public String toString() {
		return "File .zip archive";
	}

}
