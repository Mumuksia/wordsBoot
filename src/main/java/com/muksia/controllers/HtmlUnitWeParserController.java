/**
 * Copyright Flexpay AB
 */
package com.muksia.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.muksia.services.WebParserService;

@CrossOrigin
@RestController
public class HtmlUnitWeParserController {

	@Autowired
	private WebParserService webParserService;

	final String urlbase = "https://nya.boplats.se/";

	private final static int SKIPPED_ROWS = 4;

	@RequestMapping("/webParser")
	@ResponseBody
	public String getInfo() throws IOException {
		return webParserService.getChangedRowOrEmpty(urlbase, "//form[@action='https://nya.boplats.se/sok']", "objectlist", SKIPPED_ROWS);
	}
}
