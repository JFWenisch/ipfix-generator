package tech.wenisch.ipfix.generator.managers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;


public class ResponseManager {

	public static Map<String, String> getClientIPInformation(HttpServletRequest request )
	{
		String clientIp = request.getRemoteAddr();
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put("RemoteAddress", clientIp);
		return dataMap;
	}
	public static Map<String, String> getClientHeaderInformation(HttpServletRequest request )
	{
		Map<String, String> headers = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			System.out.println(headerName);
			headers.put(headerName, headerValue);
		}
	
		return headers;
	}
}
