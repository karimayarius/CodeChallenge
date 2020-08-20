package com.mastercard.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.challenge.service.CityService;

@RestController
@RequestMapping
public class CitiesController {

	private final Logger log = LoggerFactory.getLogger(CitiesController.class);

	@Autowired
	CityService cityService;

	@RequestMapping(method = RequestMethod.GET, path = "/connected")
	public String getConnectedCities(@RequestParam String origin, @RequestParam String destination) {

		log.info("CHECKING PATH BETWEEN :: {} and {}", origin, destination);

		// ALL THE EXCEPTIONS ARE HANDLED THROUGH THE EXCEPTION HANDLER CLASS USING THE
		// ADVISOR

		boolean result = cityService.checkCityConnection(origin, destination);

		/**
		 * 
		 * TODO: I would prefer to return JSON THE LNE BELOWS ARE JUST AS REQUIRED FROM
		 * THE ASSIGNEMENT
		 */
		if (result == true) {
			return "yes";
		} else {
			return "no";
		}

	}

}
