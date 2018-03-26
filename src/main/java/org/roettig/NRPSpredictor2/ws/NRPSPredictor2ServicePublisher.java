package org.roettig.NRPSpredictor2.ws;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NRPSPredictor2ServicePublisher
{
	private static final Logger logger = LoggerFactory.getLogger(NRPSPredictor2ServicePublisher.class);
			
	public static void main(String[] args)
	{
		String svcurl = null;
		
		if(args.length!=1)
			svcurl  = String.format("http://%s:9999/ws/nrpspredictor2service","localhost");
		else
			svcurl = String.format("http://%s:9999/ws/nrpspredictor2service",args[0]);
		
		logger.info("starting NRPSpredictor2 web service at "+svcurl);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		Endpoint endpoint = Endpoint.publish(svcurl, new NRPSPredictor2ServiceImpl());
		endpoint.setExecutor(executor);
		
		executor.shutdown();
	}
}