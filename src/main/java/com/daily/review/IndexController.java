package com.daily.review;

import com.daily.domain.SysLawEntry;
import com.daily.service.SysLawEntryService;
import com.daily.utils.Node.TreeNode;
import com.daily.utils.builder.LawEntryTreeBuilder;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private SysLawEntryService lawEntryService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;


    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index()
    {
        return "redirect:/index.html";
    }

    @ResponseBody
    @RequestMapping(value="/law", method = RequestMethod.GET)
    public String lawEntryList()
    {
        String content = "";
        LawEntryTreeBuilder builder = new LawEntryTreeBuilder();

        List<SysLawEntry> entries = lawEntryService.list_Study_LawEntries();
        content = nodes2Content(builder.entry2ContentNode(entries));
        sendLawMail(content);

        System.out.println(content);
        return content;
    }


    /**
     * law_0 得到的 Node 转为实际的邮件内容
     * @param nodes
     * @return
     */
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


    /**
     * 发生邮件自己发给自己
     * @param content
     */
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
