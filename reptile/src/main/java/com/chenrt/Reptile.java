package com.chenrt;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Reptile {

    public static void main(String[] args) throws IOException {
        //爬取网站
        URL url = new URL("http://www.netbian.com");//https://www.nipic.com
        //获取链接
        Connection connect = Jsoup.connect(url+"/meinv/");///topic/show_27202_1.html

//        Document document = connect
//                .timeout(900000000)
//                .header("referer","https://www.tupianzj.com")
//                .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
//                .header("accept-encoding","gzip, deflate, br")
//                .header("accept-language","zh-CN,zh;q=0.9")
//                .method(Connection.Method.GET)
//                .maxBodySize(0)
//                .referrer("strict-origin-when-cross-origin")
//                .cookie("cookie","t=12fd0ca1acf54c5b41ea3407b253ba7a; r=20; Hm_lvt_f5329ae3e00629a7bb8ad78d0efb7273=1679767435; Hm_lpvt_f5329ae3e00629a7bb8ad78d0efb7273=1679768600")
//                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36").get();
//
//      System.out.println(connect);

        //获取前端页面代码
        Document document = connect.get();
        //定位ul标签id 提取想要的数据
//        Element ul_ElementById = document.getElementById("main");
        Elements ul_ElementById = document.getElementsByClass("list");
        //筛选标签
//        Elements aAttr = ul_ElementById.select("div > a");
        Elements el_Select = ul_ElementById.select("a > img");


//        String https = "https:";
        for (Element element : el_Select) {
//            System.out.println(https.concat(element.attr("src")));
            //拼接url地址  原先是这样的//pic3.ntimg.cn/pic/20181028/21983286_205527659036_4.jpg
//            String ht_url = https.concat(element.attr("src"));

            Connection.Response response = Jsoup.connect(element.attr("src"))
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36")
                    .ignoreContentType(true).execute();
            String file_Name = element.attr("alt");
//            String file_Name = aAttr.attr("title");

            ByteArrayInputStream stream = new ByteArrayInputStream(response.bodyAsBytes());
            FileUtils.copyInputStreamToFile(stream,new File("H://image//"+file_Name+".png"));

//            System.out.println(ht_url);
        }


//        System.out.println(ul_ElementById);
//        System.out.println(el_Select);
//        System.out.println(document);
//        System.out.println(coreSzie);

    }

}
