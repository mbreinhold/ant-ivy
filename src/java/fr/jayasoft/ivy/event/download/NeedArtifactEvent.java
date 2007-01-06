/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package fr.jayasoft.ivy.event.download;

import fr.jayasoft.ivy.Artifact;
import fr.jayasoft.ivy.DependencyResolver;
import fr.jayasoft.ivy.Ivy;

public class NeedArtifactEvent extends DownloadEvent {
    public static final String NAME = "need-artifact";
    
	private DependencyResolver _resolver;

    public NeedArtifactEvent(Ivy source, DependencyResolver resolver, Artifact artifact) {
        super(source, NAME, artifact);
        _resolver = resolver;
        addAttribute("resolver", _resolver.getName());
    }

    public DependencyResolver getResolver() {
        return _resolver;
    }

}
