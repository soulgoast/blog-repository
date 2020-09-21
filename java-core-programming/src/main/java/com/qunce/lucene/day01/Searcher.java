/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.lucene.day01;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName Searcher
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/12 18:55
 * @ModifyDate 2020/8/12 18:55
 * @Version 1.0
 */
public class Searcher {

    public static void main(String[] args) throws IOException, ParseException {
        String indexDir = "D:\\工作总结\\lucene";
        String query = "命令";
        search(indexDir, query);
    }

    private static void search(String indexDir, String key) throws IOException, ParseException {
        Directory directory = FSDirectory.open(new File(indexDir));
        IndexSearcher indexSearcher = new IndexSearcher(directory);

        QueryParser parser = new QueryParser(Version.LUCENE_30, "contents", new StandardAnalyzer(Version.LUCENE_30));
        Query query = parser.parse(key);

        long start = System.currentTimeMillis();
        TopDocs hits = indexSearcher.search(query, 10);
        long end = System.currentTimeMillis();

        System.err.println("Found " + hits.totalHits + " document(s) (in " + (end + start) + " milliseconds) that matched query '" + query + "':" );
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("fullpath"));
        }
        indexSearcher.close();

    }

}
