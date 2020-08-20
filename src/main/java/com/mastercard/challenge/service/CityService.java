package com.mastercard.challenge.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {
	
	private final Logger log = LoggerFactory.getLogger(CityService.class);

	NodesCheck graph;
	
	public NodesCheck getGraph() {
		return graph;
	}
	
	
	public boolean checkCityConnection(String origin, String destination) {
		
		LinkedList<String> visited = new LinkedList();
		
		NodesCheck check = new NodesCheck();
		
		visited.add(origin.trim().toLowerCase());
		check.setOrigin(origin.trim().toLowerCase());
		check.setDestination(destination.trim().toLowerCase());
		check.checkNodeLink(this.graph, visited);
		System.out.println("Service result "+check.getResult());
		
		visited.clear();
	
		return check.getResult();
	}
	
	
	/**
	 * Load data from file to the service on application startup
	 * TODO:: TO THINK ABOUT ANOTHER ALTERNATIVE WHEN THE FILE GET BIGGER 
	 * THIS IS A WORKARROUND SOLUTION TO AVOID PROCESSING FILE EVERY TIME THE USER TRY TO CHECH
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		
		log.info("PROCESSING FILE ON STARTUP");
	    
		 graph = new NodesCheck();
		
		
		String resourceName = "city.txt";
		String line = "";
        String cvsSplitBy = ",";
        
        try {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(resourceName).getFile());
		FileInputStream inputStream =  new FileInputStream(file);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                String origin = country[0].trim().toLowerCase();
                String dest = country[1].trim().toLowerCase();
                graph.addEdge(origin ,  dest);
                graph.addEdge( dest,  origin);
                
              

            }  
            
                
        } catch (IOException e) {
            log.error("EXCEPTION OCCURED :: {}", e.getMessage());
        }
	    
	    
	}
}
