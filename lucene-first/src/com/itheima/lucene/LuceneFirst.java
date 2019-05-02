package com.itheima.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class LuceneFirst {

    @Test
    public void createIndex() throws Exception{
        //1.创建一个Director对象，指定索引库保存的位置
        //把索引库保存到内存中
        //Directory directory=new RAMDirectory();
        //把索引库保存到磁盘中
        Directory directory= FSDirectory.open(new File("F:\\temp\\index").toPath());

        //2.基于Director对象创建一个IndexWriter对象
        IndexWriterConfig indexWriterConfig=new IndexWriterConfig(new IKAnalyzer());//中文的分词器  不穿参数就是默认的英文分词器
        IndexWriter indexWriter =new IndexWriter(directory,indexWriterConfig);

        //3.读取磁盘上的文件，对应每个文件创建一个文档对象。
        File dir=new File("D:\\黑马57\\讲义+笔记+资料\\流行框架\\61.会员版(2.0)-就业课(2.0)-Lucene\\lucene\\02.参考资料\\searchsource");
        File[] files = dir.listFiles();
        for (File f:files){
            //取文件名
            String fileName= f.getName();
            //文件的路径
            String filePath = f.getPath();
            //文件的内容
            String fileContent = FileUtils.readFileToString(f, "utf-8");
            //文件大小
            long fileSize = FileUtils.sizeOf(f);
            //创建filed
            //参数1：域的名称   参数2：域的内容  参数3：是否存储
           Field fieldName = new TextField("name",fileName,Field.Store.YES);
           //Field fieldPath = new TextField("path",filePath,Field.Store.YES);
           Field fieldPath = new StoredField("path",filePath);//只存储的域
           Field fieldContent = new TextField("content",fileContent,Field.Store.YES);
          // Field fieldSize = new TextField("size",fileSize+"",Field.Store.YES);
           Field fieldSize = new LongPoint("size",fileSize);//做运算的域
            Field fieldSizeStore=new StoredField("szie",fileSize);//只存储
           //创建文档对象
            Document document = new Document();
            //向文档对象中添加域
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContent);
            document.add(fieldSize);
            document.add(fieldSizeStore);

            //5.写入索引库
            indexWriter.addDocument(document);

        }

        //6.关闭indexwriter对象
        indexWriter.close();

    }

    @Test
    public void searchIndex() throws Exception{
        //1,创建一个Director对象，指定索引库的位置
        Directory directory= FSDirectory.open(new File("F:\\temp\\index").toPath());

        //2.创建一个IndexReader对象
        IndexReader indexReader= DirectoryReader.open(directory);

        //3.创建一个IndexSearcher对象，构造方法中的参数indexreader对象。
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);

        //4.创建一个Query对象，TermQuery
        Query query=new TermQuery(new Term("name","apache"));//eg：查询content这个域里面 包含spring的关键字的信息。

        //5.执行查询，得到一个TopDocs对象
        //参数1：查询对象 参数2：查询结果返回的最大记录数。
        TopDocs topDocs = indexSearcher.search(query, 10);

        //6.查询结果的总的记录数
        System.out.println("查询总的记录数："+topDocs.totalHits);

        //7.取文档列表
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        //8.打印文档中的内容
        for(ScoreDoc doc:scoreDocs){
            //取文档id
            int docId = doc.doc;
            //根据id取文档对象
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("name"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
            System.out.println(document.get("content"));
            System.out.println("----------------------------------------");

        }



        //9.关闭indexReader对象
        indexReader.close();

    }


    //查看分词效果
    @Test
    public void testTokenStream() throws Exception{
        //1.创建一个Analyzer对象，StandarAnalyzer对象
      //  Analyzer analyzer=new StandardAnalyzer();//标准分词器（处理英文没什么问题）
        Analyzer analyzer=new IKAnalyzer();//使用中文的分词器

        //2.使用分词器对象的tokenSream的方法获得一个TokenStream对象
        TokenStream tokenStream = analyzer.tokenStream("", "The Spring Framework provides a comprehensive programming and configuration model.");

        //3.向TokenStream对象中设置一个引用，相当于是一个指针
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);

        //4.调用tokenStream对象的rest方法。如果不调用抛异常。
        tokenStream.reset();

        //5.使用while循环遍历tokenstream对象
        while (tokenStream.incrementToken()){
            System.out.println(charTermAttribute.toString());
        }

        //6,关闭tokenStream对象。
        tokenStream.close();
    }




}
