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

import java.io.File;

import fr.jayasoft.ivy.Artifact;
import fr.jayasoft.ivy.ArtifactOrigin;
import fr.jayasoft.ivy.DependencyResolver;
import fr.jayasoft.ivy.Ivy;
import fr.jayasoft.ivy.report.ArtifactDownloadReport;

public class EndArtifactDownloadEvent extends DownloadEvent {
	public static final String NAME = "post-download-artifact";

    private DependencyResolver _resolver;
    private ArtifactDownloadReport _report;

    public EndArtifactDownloadEvent(Ivy source, DependencyResolver resolver, Artifact artifact, ArtifactDownloadReport report, File dest) {
        super(source, NAME, artifact);
        _resolver = resolver;
        _report = report;
        addAttribute("resolver", _resolver.getName());
        addAttribute("status", _report.getDownloadStatus().toString());
        addAttribute("size", String.valueOf(_report.getSize()));
        addAttribute("file", dest.getAbsolutePath());
        ArtifactOrigin origin = report.getArtifactOrigin();
        if (origin != null) {
        	addAttribute("origin", _report.getArtifactOrigin().getLocation());
        	addAttribute("local", String.valueOf(_report.getArtifactOrigin().isLocal()));
        } else {
            addAttribute("origin", "");
            addAttribute("local", "");
        }
    }

    public ArtifactDownloadReport getReport() {
        return _report;
    }

    public DependencyResolver getResolver() {
        return _resolver;
    }

}
