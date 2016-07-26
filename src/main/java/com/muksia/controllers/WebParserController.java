/**
 * Copyright Flexpay AB
 */
package com.muksia.controllers;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class WebParserController {

	final String urlbase =
			"https://nya.boplats.se/sok#itemtype=1hand&rent=&city=alla&rooms=&area=508A8CB406FE001F00030A60&508A8CB406FE001F00030A60=alla&508A8CB4FCA4001400031899=alla&508A8CB400A1001800031575=alla&54B00286892C0009000349A6=alla&508A8CB4044A002300035C4C=alla&53EC5D3E25A7000A0003704B=alla&508A8CB405A30025000303BA=alla&508A8CB406840026000312AF=alla&508A8CB4F981002B000339E4=alla&508A8CB4FBDC002E000345E7=alla&508A8CB4FD6E00300003D3DD=alla&squaremeters=&objecttype=alla&moreoptionsvisible=false&moveindate=any&deposit=&objectproperties=&sortorder=startPublishTime-descending&listtype=imagelist";

	@RequestMapping("/parser")
	@ResponseBody
	public String getInfo() throws IOException {
		String url = urlbase;
		Document document = Jsoup.connect(url).get();

		String question = document.select("#item .imageitem").text();
		System.out.println("Question: " + question);

		Elements answerers = document.select("#answers .user-details a");
		for (Element answerer : answerers) {
			System.out.println("Answerer: " + answerer.text());
		}
		return "ok";
	}

}
