package com.mastercard.challenge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.LinkedList;

//import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mastercard.challenge.service.CityService;
import com.mastercard.challenge.service.NodesCheck;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {


	@InjectMocks
	CityService service;



	@Test
	void testEndToEndService() {

		assertTrue(service.checkCityConnection("Boston", "Newark"));
		assertTrue(service.checkCityConnection("Boston", "Philadelphia"));
		assertFalse(service.checkCityConnection("Philadelphia", "Albany"));

	}

	@Test
	public void givenOriginAndDestination_thenTestCityConnectedHelper() {

		NodesCheck graph = new NodesCheck();

		// graph.addEdge("A", "B");

		graph.addEdge("Boston", "New York");
		graph.addEdge("New York", "Boston");

		graph.addEdge("Philadelphia", "Newark");
		graph.addEdge("Newark", "Philadelphia");

		graph.addEdge("Newark", "Boston");
		graph.addEdge("Boston", "Newark");

		graph.addEdge("Trenton", "Albany");
		graph.addEdge("Albany", "Trenton");

		LinkedList<String> visited = new LinkedList();

		NodesCheck test = new NodesCheck();

		visited.add("Boston");
		test.setOrigin("Boston");
		test.setDestination("Newark");
		test.checkNodeLink(graph, visited);

		NodesCheck test2 = new NodesCheck();
		visited.clear();
		visited.add("Boston");
		test2.setOrigin("Boston");
		test2.setDestination("Philadelphia");
		test2.checkNodeLink(graph, visited);

		NodesCheck test3 = new NodesCheck();
		visited.clear();
		visited.add("Philadelphia");
		test3.setOrigin("Philadelphia");
		test3.setDestination("Albany");
		test3.checkNodeLink(graph, visited);
		

		assertTrue(test.getResult());
		assertTrue(test2.getResult());
		assertFalse(test3.getResult());
	}

	@Test
	public void givenResourceFile_whenReadResourceWithClassLoader_thenPathEndWithFilename() {
		String resourceName = "city.txt";

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(resourceName).getFile());
		String absolutePath = file.getAbsolutePath();

		System.out.println(absolutePath);
		assertTrue(absolutePath.endsWith(File.separator + "city.txt"));
	}

}
