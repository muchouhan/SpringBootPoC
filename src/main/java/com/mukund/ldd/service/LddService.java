package com.mukund.ldd.service;

import java.util.List;

import com.mukund.ldd.model.LddResult;

public interface LddService {
	List<LddResult> retrieve(String countryId) throws Exception;

	List<String> transform(List<LddResult> results) throws Exception;

	Boolean upload(List<String> files) throws Exception;

}
