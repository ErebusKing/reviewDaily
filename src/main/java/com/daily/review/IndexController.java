package com.daily.review;

import com.daily.domain.SysLawEntry;
import com.daily.service.SysLawEntryService;
import com.utils.brs.MailSimple;
import com.utils.type.Node.TreeNode;
import com.utils.type.builder.LawEntryTreeBuilder;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private SysLawEntryService lawEntryService;
    @Autowired
    HttpServletRequest request;



    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index()
    {
        return "redirect:/index.html";
    }


    @ResponseBody
    @RequestMapping(value="/law", method = RequestMethod.GET)
    public String lawEntryList()
    {
        System.out.println("获得等待复习的法学数据");
        LawEntryTreeBuilder builder = new LawEntryTreeBuilder();
        List<SysLawEntry> entries = lawEntryService.list_Study_LawEntries();
        if (entries == null || entries.size() == 0)
            return "";
        String content = MailSimple.nodes2Content(builder.entry2ContentNode(entries));
        MailSimple.sendLawMail(content,"法硕学习");

        return treeCss2Content(content,"./js/tree.css");
    }



    @ResponseBody
    @RequestMapping(value="/lastHard", method = RequestMethod.GET)
    public String lastEntryList(){
        System.out.println("获得重点复习的法学数据");
        LawEntryTreeBuilder builder = new LawEntryTreeBuilder();
        List<SysLawEntry> entries = lawEntryService.list_LastHard_LawEntries();
        if (entries == null || entries.size() == 0)
            return "";
        String content = MailSimple.nodes2Content(builder.entry2ContentNode(entries));

        return treeCss2Content(content,"././js/tree.css");
    }





    @ResponseBody
    @RequestMapping(value="/all", method = RequestMethod.GET)
    public String lawEntryAll()
    {
        System.out.println("查询所有法学数据");
        LawEntryTreeBuilder builder = new LawEntryTreeBuilder();
        List<SysLawEntry> entries = lawEntryService.list_Study_All();
        if (entries == null || entries.size() == 0)
            return "";
        String content = MailSimple.nodes2Content(builder.entry2ContentNode(entries));

        return treeCss2Content(content,"./js/tree.css");
    }


    @ResponseBody
    @RequestMapping(value="/hard", method = RequestMethod.GET)
    public String lawEntryHard()
    {
        System.out.println("查询所有法学数据重点--记忆错误3次以上");
        LawEntryTreeBuilder builder = new LawEntryTreeBuilder();
        List<SysLawEntry> entries = lawEntryService.list_Hard();
        if (entries == null || entries.size() == 0)
            return "";
        String content = MailSimple.nodes2Content(builder.entry2ContentNode(entries));

        return treeCss2Content(content,"./js/tree.css");
    }



    /**
     * 增加样式
     * @return
     */
    public String treeCss2Content(String content,String filepath){
        if (content == "")
            return content;
        String rel = "<link rel=\"stylesheet\" href=\""+filepath+"\" type=\"text/css\" />";
        String body = rel+"<div class=\"tree\">";
        String[] strs = content.split("<li>");
        if (strs.length == 0)
            return content;
        body += strs[0];
        for (int i=1; i<strs.length;i++){
            body += "<li><span><i class=\"fa fa-minus-circle\"></i>"+strs[i];
        }
        body += "</div>";

        return body;
    }





}
