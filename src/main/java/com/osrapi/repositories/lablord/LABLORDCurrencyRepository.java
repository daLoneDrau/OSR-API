package com.osrapi.repositories.lablord;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.osrapi.models.lablord.LABLORDCurrencyEntity;

/**
 * @author drau
 */
@Repository
public interface LABLORDCurrencyRepository
		extends CrudRepository<LABLORDCurrencyEntity, Long> {
	/**
	 * Retrieves a list of currencys by their code.
	 * @param code the code
	 * @return {@link List}<{@link LABLORDCurrencyEntity}>
	 */
	List<LABLORDCurrencyEntity> findByCode(String code);
	/**
	 * Retrieves a list of currencys by their name.
	 * @param name the name
	 * @return {@link List}<{@link LABLORDCurrencyEntity}>
	 */
	List<LABLORDCurrencyEntity> findByName(String name);
	/**
	 * Retrieves a list of currencys by their sortOrder.
	 * @param sortOrder the sortOrder
	 * @return {@link List}<{@link LABLORDCurrencyEntity}>
	 */
	List<LABLORDCurrencyEntity> findBySortOrder(Long sortOrder);
}
