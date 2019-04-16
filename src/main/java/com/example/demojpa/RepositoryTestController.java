/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.demojpa;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.rest.core.mapping.ResourceMappings;
import org.springframework.data.rest.core.mapping.ResourceMetadata;
import org.springframework.data.rest.webmvc.RepositoryController;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * Controller for the root resource exposing links to the repository resources.
 * 
 * @author Jon Brisbin
 * @author Oliver Gierke
 */
@RestController
@RepositoryRestController
public class RepositoryTestController {

	@Autowired
	private Repositories repositories;
	@Autowired
	private EntityLinks entityLinks;
	@Autowired
	private ResourceMappings mappings;

	/**
	 * Lists all repositories exported by creating a link list pointing to resources exposing the repositories.
	 *
	 * @return
	 */
	@GetMapping("test")
	public HttpEntity<RepositoryLinksResource> listRepositories() {

		RepositoryLinksResource resource = new RepositoryLinksResource();

		for (Class<?> domainType : repositories) {

			ResourceMetadata metadata = mappings.getMetadataFor(domainType);
			if (metadata.isExported()) {
				resource.add(entityLinks.linkToCollectionResource(domainType));
			}
		}

		return new ResponseEntity<RepositoryLinksResource>(resource, HttpStatus.OK);
	}
}
