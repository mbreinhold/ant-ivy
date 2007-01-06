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
package fr.jayasoft.ivy.latest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import fr.jayasoft.ivy.ArtifactInfo;

public class ComparatorLatestStrategy extends AbstractLatestStrategy {

	private Comparator _comparator;

	public ComparatorLatestStrategy() {
	}

	public ComparatorLatestStrategy(Comparator comparator) {
		_comparator = comparator;
	}

    public ArtifactInfo findLatest(ArtifactInfo[] artifacts, Date date) {
        if (artifacts == null) {
            return null;
        }
        ArtifactInfo found = null;
        for (int i = 0; i < artifacts.length; i++) {
            ArtifactInfo art = artifacts[i];
            if (found == null || _comparator.compare(art, found) > 0) {
                if (date != null) {
                    long lastModified = art.getLastModified();
                    if (lastModified > date.getTime()) {
                        continue;
                    }
                }
                found = art;
            }
        } 
        return found;
    }
    
    public List sort(ArtifactInfo[] infos) {
    	List ret = new ArrayList(Arrays.asList(infos));
    	Collections.sort(ret, _comparator);
    	return ret;
    }

	public Comparator getComparator() {
		return _comparator;
	}

	public void setComparator(Comparator comparator) {
		_comparator = comparator;
	}

}
