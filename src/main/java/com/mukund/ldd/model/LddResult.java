package com.mukund.ldd.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class LddResult {
	
	@XmlAttribute(name = "id")
	private String productId;
	
	@XmlElement(name = "description")
	private String description;
}
