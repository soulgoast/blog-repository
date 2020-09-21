/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.lucene.day01;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;

import static org.apache.lucene.util.Version.LUCENE_30;

/**
 * @ClassName AAIndexer
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/12 16:11
 * @ModifyDate 2020/8/12 16:11
 * @Version 1.0
 */
public class Indexer {

    private IndexWriter writer;

    public Indexer(String indexDir) throws IOException {
        Directory dir = FSDirectory.open(new File(indexDir));
        writer = new IndexWriter(dir, new StandardAnalyzer(LUCENE_30), true, IndexWriter.MaxFieldLength.UNLIMITED);
    }

    public static void main(String[] args) throws IOException {
        String indexDir = "D:\\工作总结\\lucene";
        String dataDir = "D:\\工作总结";
        build(indexDir, dataDir);
    }

    public static void build(String indexDir, String dataDir) throws IOException {
        long start = System.currentTimeMillis();
        Indexer indexer = new Indexer(indexDir);

        int numindexed;
        try {
            numindexed = indexer.index(dataDir, new TextFilesFilter());
        } finally {
            indexer.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Indexing " + numindexed + "files took " + (end - start) + "milliseconds");

    }

    private void close() throws IOException {
        writer.close();
    }

    private int index(String dataDir, FileFilter filter) throws IOException {
        File[] files = new File(dataDir).listFiles();
        for (File f : files) {
            if (!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead() && (filter == null || filter.accept(f))) {
                indexFile(f);
            }
        }
        return writer.numDocs();
    }

    private void indexFile(File f) throws IOException {
        System.out.println("indexing " + f.getCanonicalPath());
        Document document = getDocument(f);
        writer.addDocument(document);
    }

    private Document getDocument(File f) throws IOException {
        Document document = new Document();
        document.add(new Field("contents", new FileReader(f)));
        document.add(new Field("filename", f.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        document.add(new Field("fullpath", f.getCanonicalPath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        return document;
    }

    private static class TextFilesFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            return pathname.getName().toLowerCase().endsWith(".txt");
        }
    }
}
