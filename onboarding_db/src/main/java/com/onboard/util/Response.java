package com.onboard.util;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.onboard.entity.EntityCategory;
import com.onboard.entity.EntityDetails;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)

public class Response {
	
	private Integer statusCode;
	private String statusMsg;
	private EntityDetails data;
	private List<EntityDetails> datas;
	private EntityCategory cdata;
	private List<EntityCategory> catdatas;
	private String errorCode;
	private String errorMsg;
		
	}

