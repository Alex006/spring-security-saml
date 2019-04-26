/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.springframework.security.saml2.boot.configuration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.saml2.registration.Saml2KeyData;
import org.springframework.security.saml2.registration.Saml2KeyType;

import static java.util.Optional.ofNullable;

abstract class RemoteSaml2ProviderConfiguration {
	private String alias;
	private String linktext;
	private boolean skipSslValidation = false;
	private List<String> verificationKeys = new LinkedList<>();

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getLinktext() {
		return linktext;
	}

	public void setLinktext(String linktext) {
		this.linktext = linktext;
	}

	public boolean isSkipSslValidation() {
		return skipSslValidation;
	}

	public void setSkipSslValidation(boolean skipSslValidation) {
		this.skipSslValidation = skipSslValidation;
	}

	public List<String> getVerificationKeys() {
		return verificationKeys;
	}

	public void setVerificationKeys(List<String> verificationKeys) {
		this.verificationKeys = verificationKeys;
	}

	protected List<Saml2KeyData> getVerificationKeyData() {
		return ofNullable(getVerificationKeys()).orElse(Collections.emptyList())
			.stream()
			.map(
				s -> new Saml2KeyData(
					"from-config-"+UUID.randomUUID().toString(),
					null,
					s,
					null,
					Saml2KeyType.SIGNING
				)
			)
			.collect(Collectors.toList());
	}
}
