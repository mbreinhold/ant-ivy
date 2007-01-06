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
package fr.jayasoft.ivy.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;

import junit.framework.TestCase;
import fr.jayasoft.ivy.DefaultModuleDescriptor;
import fr.jayasoft.ivy.Ivy;
import fr.jayasoft.ivy.ModuleDescriptor;
import fr.jayasoft.ivy.ModuleRevisionId;
import fr.jayasoft.ivy.repository.Resource;

public class ModuleDescriptorParserRegistryTest extends TestCase {
    public static class MyParser extends AbstractModuleDescriptorParser {
        public ModuleDescriptor parseDescriptor(Ivy ivy, URL descriptorURL, Resource res, boolean validate) throws ParseException, IOException {
            return DefaultModuleDescriptor.newDefaultInstance(ModuleRevisionId.newInstance("test", "parser", "1.0"));
        }

        public void toIvyFile(InputStream is, Resource res, File destFile, ModuleDescriptor md) throws ParseException, IOException {
        }

        public boolean accept(Resource res) {
            return true;
        }

    }
    public void testAddConfigured() throws Exception {
        Ivy ivy = new Ivy();
        ivy.addConfigured(new MyParser());
        ModuleDescriptor md = ModuleDescriptorParserRegistry.getInstance().parseDescriptor(ivy, ModuleDescriptorParserRegistryTest.class.getResource("nores"), false);
        assertNotNull(md);
        assertEquals(ModuleRevisionId.newInstance("test", "parser", "1.0"), md.getModuleRevisionId());
    }
}
