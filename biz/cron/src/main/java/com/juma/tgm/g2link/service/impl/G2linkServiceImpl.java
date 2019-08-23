package com.juma.tgm.g2link.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.juma.tgm.g2link.service.G2linkService;

@Service
public class G2linkServiceImpl implements G2linkService {

	
	@Value("${g2link.http}")
	private String g2linkHttp;
	
	@Value("${g2link.appKey}")
	private String g2linkAppKey;
	
	@Value("${g2link.appSecret}")
	private String g2linkAppSecret;
	
	
}
