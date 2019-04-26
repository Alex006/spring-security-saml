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
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.saml2.registration.Saml2KeyData;
import org.springframework.security.saml2.registration.Saml2KeyType;

import static java.util.Optional.ofNullable;
import static org.springframework.util.StringUtils.hasText;

public class Saml2RotatingKeys {
	@NestedConfigurationProperty
	private Saml2KeyRepresentation active = null;
	@NestedConfigurationProperty
	private List<Saml2KeyRepresentation> standBy = new LinkedList<>();

	public List<Saml2KeyData> toList() {
		LinkedList<Saml2KeyData> result = new LinkedList<>();
		if (getActive()!=null) {
			result.add(getActive().toKeyData());
			result.add(getActive().toKeyData(getActive().getName()+"-encrypt", Saml2KeyType.ENCRYPTION));
		}
		result.addAll(
			ofNullable(getStandBy()).orElse(Collections.emptyList())
				.stream().map(k -> k.toKeyData())
				.collect(Collectors.toList())
		);
		return result;
	}

	public Saml2KeyRepresentation getActive() {
		return active;
	}

	public void setActive(Saml2KeyRepresentation active) {
		this.active = active;
		if (!hasText(active.getName())) {
			active.setName("active-key");
		}
	}

	public List<Saml2KeyRepresentation> getStandBy() {
		return standBy;
	}

	public void setStandBy(List<Saml2KeyRepresentation> standBy) {
		this.standBy = standBy;
	}
}
