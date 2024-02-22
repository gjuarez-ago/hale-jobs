package com.services.chambitas.service.impl;

import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.dto.OfferDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.ICityRepository;
import com.services.chambitas.repository.ICompanyRepository;
import com.services.chambitas.repository.IComplaintRepository;
import com.services.chambitas.repository.ICountriesRepository;
import com.services.chambitas.repository.IJobCategoryRepository;
import com.services.chambitas.repository.IJobSubcategoryRepository;
import com.services.chambitas.repository.ILevelStudyRepository;
import com.services.chambitas.repository.IOfferRepository;
import com.services.chambitas.repository.IRangeAmountRepository;
import com.services.chambitas.repository.IStateRepository;
import com.services.chambitas.repository.ITypeOfJobRepository;
import com.services.chambitas.repository.ITypeOfPaymentRepository;
import com.services.chambitas.repository.IUserRepository;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.services.chambitas.domain.dto.OfferEditDTO;

@SpringBootTest
public class OfferServiceImplTest {
	@Autowired
	private OfferServiceImpl offerServiceImpl;

	@MockBean
	private IOfferRepository iOfferRepository;

	@MockBean
	private IUserRepository iUserRepository;

	@MockBean
	private ITypeOfPaymentRepository iTypeOfPaymentRepository;

	@MockBean
	private ITypeOfJobRepository iTypeOfJobRepository;

	@MockBean
	private IComplaintRepository iComplaintRepository;

	@MockBean
	private IJobCategoryRepository iJobCategoryRepository;

	@MockBean
	private ILevelStudyRepository iLevelStudyRepository;

	@MockBean
	private ICountriesRepository iCountriesRepository;

	@MockBean
	private ICityRepository iCityRepository;

	@MockBean
	private IStateRepository iStateRepository;

	@MockBean
	private ICompanyRepository iCompanyRepository;

	@MockBean
	private IJobSubcategoryRepository iJobSubcategoryRepository;

	@MockBean
	private IRangeAmountRepository iRangeAmountRepository;

	@Test
	public void createOffer() throws GenericException {
		OfferDTO request = new OfferDTO();
		Offer expected = new Offer();
		Offer actual = offerServiceImpl.createOffer(request);

		assertEquals(expected, actual);
	}

	@Test
	public void createOfferTODO() throws GenericException {
		OfferDTO request = new OfferDTO();
		Offer expected = new Offer();
		Offer actual = offerServiceImpl.createOffer(request);

		assertEquals(expected, actual);
	}

	@Test
	public void editOffer() throws GenericException {
		OfferEditDTO request = new OfferEditDTO();
		Offer expected = new Offer();
		Offer actual = offerServiceImpl.editOffer(request);

		assertEquals(expected, actual);
	}

	@Test
	public void createOfferTODOTODO() throws GenericException {
		OfferDTO request = new OfferDTO();
		Offer expected = new Offer();
		Offer actual = offerServiceImpl.createOffer(request);

		assertEquals(expected, actual);
	}

	@Test
	public void editOfferTODO() throws GenericException {
		OfferEditDTO request = new OfferEditDTO();
		Offer expected = new Offer();
		Offer actual = offerServiceImpl.editOffer(request);

		assertEquals(expected, actual);
	}
}
