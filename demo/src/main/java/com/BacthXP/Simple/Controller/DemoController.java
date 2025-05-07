package com.BacthXP.Simple.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo/status/check") //initial path
public class DemoController {

	//C    R     U     D      Patch
	//POST GET   PUT   DELETE 
	@Value("${myName.firstName}") 
	private String name;
	
	@GetMapping()
	public String getStatus() {
		return name+" Modified Working!!!!";
	}
	
}
