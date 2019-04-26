/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.springframework.security.saml2.serviceprovider.authentication;

import org.springframework.security.core.Authentication;

/**
 * An authentication containing SAML information
 */
public interface Saml2Authentication extends Authentication {

	/**
	 * Returns the entity id of the identity provider that issued the assertion
	 *
	 * @return entity id of IDP
	 */
	String getAssertingEntityId();

	/**
	 * Returns the entity id of the service provider that received the assertion
	 *
	 * @return entity id of SP
	 */
	String getHoldingEntityId();

	/**
	 * returns the assertion object that was used to create this authentication object
	 *
	 * @return assertion representing authentication
	 */
	Object getAssertion();

	/**
	 * returns the response object that wraps the assertion
	 *
	 * @return saml response object containing the assertion
	 */
	Object getSamlResponse();


	/**
	 * If the POST or REDIRECT contained a RelayState parameter this will be the value of it
	 *
	 * @return the RelayState parameter value, or null
	 */
	String getRelayState();
}
