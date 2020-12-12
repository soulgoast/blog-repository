/*
 * Copyright (C) 2013-2015 Denis Forveille titou10.titou10@gmail.com
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2020 DBeaver Corp and others
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
 */
package org.jkiss.dbeaver.ext.db2.model.dict;

import org.jkiss.code.NotNull;
import org.jkiss.dbeaver.model.DBPNamedObject;

/**
 * DB2 Index Column Order
 * 
 * @author Denis Forveille
 */
public enum DB2IndexColOrder implements DBPNamedObject {
    A("Ascending", true),

    D("Descending", false),

    I("Include", true),

    R("Random", true);

    private String name;
    private Boolean ascending;

    // -----------------
    // Constructor
    // -----------------
    private DB2IndexColOrder(String name, Boolean ascending)
    {
        this.name = name;
        this.ascending = ascending;
    }

    // -----------------------
    // Display @Property Value
    // -----------------------
    @Override
    public String toString()
    {
        return name;
    }

    // ----------------
    // Standard Getters
    // ----------------
    @NotNull
    @Override
    public String getName()
    {
        return name;
    }

    public Boolean isAscending()
    {
        return ascending;
    }
}