package gov.cdc.dhcs.hehr.dcp.direct.service;

import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class DocumentRepositorySoapHeaders implements SOAPHandler<SOAPMessageContext> {

	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		final QName securityHeader = new QName(
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security",
				"wsse");

		final Set<QName> headers = new HashSet<QName>();
		headers.add(securityHeader);

		// notify the runtime that this is handled
		return headers;
	}

}
