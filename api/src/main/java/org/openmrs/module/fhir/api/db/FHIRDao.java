/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.fhir.api.db;

import org.openmrs.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Database methods for FHIR Module
 */
@Transactional
public interface FHIRDao {

	/**
	 * Returns the orders for a given accession number
	 *
	 * @param accessionNumber The accession number of the order
	 * @param <Ord> The type of the order
	 * @return A list of Orders for the given accession number
	 */
	<Ord extends Order> List<Ord> getOrdersByAccessionNumber(String accessionNumber);

	/**
	 * Gets the encounter id corresponding to a given order id
	 *
	 * @param orderId the order id to find the encounter for
	 * @return the id of the encounter to get
	 */
	Integer getEncounterIdForObsOrder(int orderId);
}
