package com.daily.review;

import com.daily.domain.SysLawEntry;
import com.daily.service.SysLawEntryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/19 0019 23:19
 * @since 1.0.0
 * History:
 * wangb        04-19         1.0        使用simpleMail发送
 * 作者姓名      修改时间       版本号       描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMail {

    @Autowired
    private SysLawEntryService lawEntryService;

    @Test
    public void sendMail()
    {
        String content = "";
        LawEntryTreeBuilder builder = new LawEntryTreeBuilder();

        List<SysLawEntry> lawEntries = lawEntryService.list_Study_LawEntries();
        content = nodes2Content(builder.entry2ContentNode(lawEntries));
        System.out.println(content);

        sendLawMail(content);
    }


    public String nodes2Content(List<TreeNode> nodes)
    {
        String content ="";
        int count = 0;
        if (nodes != null && nodes.size() != 0) {
            for (TreeNode node : nodes) {
                if("4" == node.getId())
                {
                    content += (++count) + "."+node.getName() + "<br/>";
                }else{
                    content += "<b>"+node.getName() + "</b><br/>" + nodes2Content(node.getChildren());
                }
            }
        }
        return content;
    }





    public void sendLawMail(String content)
    {

        Email email = EmailBuilder.startingBlank()
                .from("法硕学习", "chiromath@sohu.com")
                .to("DreamLife", "chiromath@sohu.com")
                .withSubject("Law EntryReview")
                .withHTMLText(content)
                .buildEmail();

        MailerBuilder.withSMTPServer("smtp.sohu.com", 25, "chiromath", "118512Qq")
                .buildMailer().sendMail(email);


    }


}

/*    public String entry2Content(List<SysLawEntry> lawEntries)
    {
        //Mail里 content处理
        String content = "";
        LawEntryTreeBuilder builder = new LawEntryTreeBuilder();
        List<Node> nodes = builder.entry2ContentNode(lawEntries);
        content = nodes2Content(nodes);
        System.out.println(content);
        return  content;
    }*/


/*    public List<Node> transLawEntry2Nodes(SysLawEntry lawEntry)
    {
        Node node = null;
        Node secChild = null;
        Node thrChild = null;
        Node fthChild = null;
        List<Node> nodes = new ArrayList<>();

        if (lawEntry != null)
        {
            node = new Node("1",lawEntry.getDiscipline(),"0","0");
            secChild = new Node("2",lawEntry.getChapter(),"1",lawEntry.getDiscipline());
            thrChild = new Node("3",lawEntry.getAspect(),"2",lawEntry.getChapter());
            String supplement = ""==lawEntry.getSupplement()?"":"(" +lawEntry.getSupplement()+")";
            fthChild = new Node("4",lawEntry.getContent()+supplement,"3",lawEntry.getAspect());

            nodes.add(node);
            nodes.add(secChild);
            nodes.add(thrChild);
            nodes.add(fthChild);
        }

        return nodes;
    }*/

/*    *//**
 * 只能针对T为基本类型的
 * @param list<T>
 * @return
 *//*
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }*/


    /*public List<Node> entry2ContentNode(List<SysLawEntry> lawEntries)
    {
        List<Node> nodes = new ArrayList<>();
        if(lawEntries != null && lawEntries.size() != 0)
        {
            for (SysLawEntry entry : lawEntries)
            {
                nodes.addAll(transLawEntry2Nodes(entry));
            }
        }


        *//**
 * TODO  N层数据怎么处理，怎样才最简单
 * 这里只过滤了 当前层 = 1层
 * 如果多层结构中出现 不同分支下的 2层一致，这里就不能区别
 * 结论：只要同层级，不允许出现（2个以及以上）的相同name
 *//*
        List<Node> unique = nodes.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.
                                comparing(Node::getName).thenComparing(Node::getParentId)
                                    .thenComparing(Node::getId).thenComparing(Node::getParentName))),
                        ArrayList::new)
        );
        unique.forEach(p -> System.out.println(p));




        TreeBuilder treeBuild = new LawEntryTreeBuilder();
        nodes = treeBuild.buildByRecursive(unique);
        System.out.println(nodes.toArray());

        return nodes;
    }*/








/*    public String entry2Content(List<SysLawEntry> lawEntries)
    {
        //Mail里 content处理
        String content = "";
        if(lawEntries != null && lawEntries.size() != 0)
        {
            int count = 0;
            for (SysLawEntry lawEntry : lawEntries)
            {
                content += (count++) + ". <b>"+lawEntry.getChapter()+"  "+lawEntry.getAspect()+"</b> "+lawEntry.getContent();
                if(!"".equals(lawEntry.getSupplement()))
                {
                    content += "("+lawEntry.getSupplement()+")";
                }
                content += "<br/>";
            }
        }
        return content;
    }*/