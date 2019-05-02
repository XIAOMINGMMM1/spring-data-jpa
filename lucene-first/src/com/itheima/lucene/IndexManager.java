package com.itheima.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * 索引库的维护
 */
public class IndexManager {

    private IndexWriter indexWriter;

    @Before
    public void init() throws Exception {
        this.indexWriter = new IndexWriter(FSDirectory.open(new File("F:\\temp\\index").toPath()), new IndexWriterConfig(new IKAnalyzer()));
    }

    //添加文档
    @Test
    public void addDocument() throws Exception {
        //创建一个indexWrite对象，需要使用IkAnalyzer作为分析器
        //IndexWriter indexWriter =new IndexWriter(FSDirectory.open(new File("F:\\temp\\index").toPath()),new IndexWriterConfig(new IKAnalyzer()));
        //创建一个document对象
        Document document = new Document();
        //向document对象中添加域
        document.add(new TextField("name", "新添加的文件", Field.Store.YES));
        document.add(new TextField("content", "新添加的文件内容", Field.Store.NO));//不存储
        //把文档写入索引库中
        indexWriter.addDocument(document);
        //关闭索引库
        indexWriter.close();

    }


    //删除全部
    @Test
    public void deleteAllDcument() throws Exception {

        //删除全部文档
        indexWriter.deleteAll();
        //关闭索引库
        indexWriter.close();
    }

    //根据查询来删除索引库
    @Test
    public void deleteDocumentByQuery() throws Exception {
        indexWriter.deleteDocuments(new Term("name","apache"));//name域中包括apache的文档

        indexWriter.close();
    }


    //更新索引库  先查询 在删除
    @Test
    public void updateDocument() throws Exception{
        //创建一个新的文档对象
        Document document=new Document();
        //向文档中添加域
        document.add(new TextField("name","更新之后的文档",Field.Store.YES));
        //更新操作
        indexWriter.updateDocument(new Term("name","spring"),document);
        //关闭索引库
        indexWriter.close();
    }
}

