/*
 * Copyright 2004-2014 Brian McCallister
 * Copyright 2010-2014 Ning, Inc.
 * Copyright 2014-2020 Groupon, Inc
 * Copyright 2020-2020 Equinix, Inc
 * Copyright 2014-2020 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.skife.jdbi.v2.unstable.grammar;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.Lexer;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.skife.jdbi.rewriter.printf.FormatterStatementLexer;
import org.skife.jdbi.v2.JDBITests;

import static org.skife.jdbi.rewriter.printf.FormatterStatementLexer.EOF;
import static org.skife.jdbi.rewriter.printf.FormatterStatementLexer.INTEGER;
import static org.skife.jdbi.rewriter.printf.FormatterStatementLexer.LITERAL;
import static org.skife.jdbi.rewriter.printf.FormatterStatementLexer.QUOTED_TEXT;
import static org.skife.jdbi.rewriter.printf.FormatterStatementLexer.STRING;

/**
 *
 */
@Category(JDBITests.class)
public class TestPrintfGrammar extends GrammarTestCase
{
    @Test
    public void testFoo() throws Exception
    {
        expect("select id from something where name like '%d' and id = %d and name like %s",
               LITERAL, QUOTED_TEXT, LITERAL, INTEGER, LITERAL, STRING, EOF);
    }

    @Override
    protected String nameOf(int type)
    {
        switch (type) {
            case QUOTED_TEXT:
                return "QUOTED_TEXT";
            case INTEGER:
                return "INTEGER";
            case STRING:
                return "STRING";
            case LITERAL:
                return "LITERAL";
            case EOF:
                return "EOF";
        }
        return "unknown";
    }


    @Override
    protected Lexer createLexer(String s)
    {
        return new FormatterStatementLexer(new ANTLRStringStream(s));
    }
}
