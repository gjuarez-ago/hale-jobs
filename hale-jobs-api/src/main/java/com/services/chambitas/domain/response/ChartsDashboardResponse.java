package com.services.chambitas.domain.response;


import lombok.Data;

@Data
public class ChartsDashboardResponse {
	
	private int offers;
	private int complaints;
	private int prospects;

	// private List<String> rangeAmounts;

	// private List<String> subcategories;

	// private List<Integer> offersByMonth;
	
	// private List<Integer> postulatesByMonth;

}
